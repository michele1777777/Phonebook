package utilities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.net.URI;
import javax.swing.JButton;
import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

/**
 * A custom JButton designed to display an SVG icon. This button supports
 * scalable vector graphics to provide high-quality icons that maintain
 * their resolution and clarity at any size.
 * @author Leuti Michele
 */
public class SvgButton extends JButton {
	private static final long serialVersionUID = 6337405258308560812L;
	private String filePath;
    private SVGDiagram svgDiagram;
    private int radius;
    
    /**
     * Constructs an SvgButton with the specified SVG file path.
     * The SVG is loaded and prepared for rendering on the button.
     *
     * @param filePath The path to the SVG file to display on the button.
     */
    public SvgButton(String filePath) {
    	this.radius = 25;
        try {
        	File f = new File(filePath);
        	URI svgURI = f.toURI();
            SVGUniverse svgUniverse = SVGCache.getSVGUniverse();
            svgDiagram = svgUniverse.getDiagram(svgURI);
            setSize((int) svgDiagram.getWidth(), (int) svgDiagram.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sets the file path of the SVG to be used as the button icon.
     *
     * @param path The file path of the SVG.
     */
    public void setFilePath(String path) {
    	this.filePath = path;
    }
    
    /**
     * Gets the file path of the SVG used as the button icon.
     *
     * @return The file path of the SVG.
     */
    public String getFilePath() {
    	return this.filePath;
    }
    
    /**
     * Sets a new SVG URI for the button, updates the icon accordingly.
     *
     * @param svgURI The URI of the new SVG to display.
     */
    public void setSvgURI(URI svgURI) {
        try {
            SVGUniverse svgUniverse = SVGCache.getSVGUniverse();
            svgDiagram = svgUniverse.getDiagram(svgURI);
            // Update the size of the button based on the new SVG dimensions
            adjustSvgToButtonSize();
            repaint(); // Request a repaint to display the new SVG
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Adjusts the SVG size to fit within the button dimensions while maintaining aspect ratio.
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
     * Overrides the paintComponent method to render the SVG within the button. It ensures
     * the SVG is scaled appropriately to fit the button size while maintaining its aspect ratio.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (svgDiagram != null) {
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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clip the background to a rounded rectangle based on the panel's bounds and the specified radius
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
        g2d.setClip(roundedRectangle);

        // Paint the background within the clipped area
        g2d.setColor(g2d.getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
    
    /**
     * Sets the size of the button and adjusts the SVG viewport to match the new size.
     *
     * @param width The new width of the button.
     * @param height The new height of the button.
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