package utilities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.net.URI;
import javax.swing.JPanel;
import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

/**
 * A custom JPanel designed to display an SVG image. This panel supports
 * scalable vector graphics, allowing for high-quality icons or images
 * that maintain their resolution and clarity at any scale.
 * @author Leuti Michele
 */
public class SvgIconPanel extends JPanel {
	private static final long serialVersionUID = 3780537273231596389L;
	private SVGDiagram svgDiagram;
    
	/**
     * Constructs an SvgIconPanel with the specified SVG file path.
     * The SVG is loaded and prepared for rendering on the panel.
     *
     * @param filePath The path to the SVG file to be displayed on the panel.
     */
    public SvgIconPanel(String filePath) {
        try {
        	File f = new File(filePath);
        	URI svgUri = f.toURI();
            SVGUniverse svgUniverse = SVGCache.getSVGUniverse();
            svgDiagram = svgUniverse.getDiagram(svgUri);
            adjustSvgToButtonSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Adjusts the SVG size to fit within the panel dimensions while maintaining aspect ratio.
     */
    private void adjustSvgToButtonSize() {
        if (svgDiagram != null) {
            // Calculate the scale to fit the SVG within the button
            float widthScale = (float) getWidth() / svgDiagram.getWidth();
            float heightScale = (float) getHeight() / svgDiagram.getHeight();
            float scale = Math.min(widthScale, heightScale); // Use the smallest scale to maintain aspect ratio

            // Calculate the new width and height based on the scale
            int newWidth = (int) (svgDiagram.getWidth() * scale);
            int newHeight = (int) (svgDiagram.getHeight() * scale);

            // Set the device viewport of the SVG diagram to match the new size
            // This effectively resizes the SVG content to fit within the button
            svgDiagram.setDeviceViewport(new java.awt.Rectangle(0, 0, newWidth, newHeight));
        }
    }
    
    /**
     * Overrides the paintComponent method to render the SVG within the panel. It ensures
     * the SVG is scaled appropriately to fit the panel size while maintaining its aspect ratio.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (svgDiagram != null) {
        	
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Calculate scale to fit the SVG in the button
            float scaleX = getWidth() / svgDiagram.getWidth();
            float scaleY = getHeight() / svgDiagram.getHeight();
            float scale = Math.min(scaleX, scaleY); // Maintaining aspect ratio

            // Calculate the translation to center the image
            float tx = (getWidth() - svgDiagram.getWidth() * scale) / 2;
            float ty = (getHeight() - svgDiagram.getHeight() * scale) / 2;

            g2d.translate(tx, ty);
            g2d.scale(scale, scale);

            try {
                svgDiagram.render(g2d);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                g2d.dispose();
            }
        }
    }

    /**
     * Sets the size of the panel and adjusts the SVG viewport to match the new size.
     * Ensures that the SVG image is resized and displayed correctly within the panel.
     *
     * @param width The new width of the panel.
     * @param height The new height of the panel.
     */
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        if (svgDiagram != null && svgDiagram.getRoot() != null) { // Check if svgDiagram and its root are not null
            try {
                svgDiagram.setDeviceViewport(new java.awt.Rectangle(0, 0, width, height));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}