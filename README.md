
# Phonebook Application README

## Overview
This application serves as a comprehensive contact management system, enabling users to efficiently manage personal and professional contacts. It offers functionalities such as adding, editing, and deleting user profiles and their associated contacts. To enhance user experience, the application incorporates visually appealing UI components like rounded buttons, custom scroll bars, and SVG icons.

## Features

- **User Management:** Create, edit, and delete user profiles.
- **Contact Management:** Add, edit, and delete contacts associated with a user profile.
- **Search Functionality:** Easily search for contacts within a user's profile.
- **User-Friendly Interface:** Custom UI components for an enhanced experience.

## Requirements
- **Java Version:** Ensure you have Java SE 17 or newer installed on your system.
- **MySQL Database:** A running MySQL database is required for the application to store user and contact information.
- **Database Properties:** A file named `database.properties` is needed to configure the database connection. This file should include database URL, username, and password.

## Getting Started
1. **Database Setup:** Create a database schema as per the provided schema file. Update `database.properties` with your database connection details.
2. **User Generation:** Make Sure the `UserGenerator.jar` is on the same working enviroment of the `Phonebook.jar`. Use the provided `UserGenerator.jar` to create an initial user and 100 sample persons for testing. The default username is `userTest` with the password `Password123!`.
3. **Running the Application:** Launch the application using `phonebook.jar`. Make sure Java is correctly installed and configured on your system.

## Application Images
For the application to load images correctly, ensure that `phonebook.jar` is placed in the same working environment as the provided `Images` folder. This folder contains essential graphics used throughout the application for a seamless user experience.

## Features
- **User Authentication:** Register new users, login, and delete user accounts.
- **Contact Management:** Add, edit, and delete contacts. Each contact includes details such as name, surname, address, phone number, and age.
- **Search Functionality:** Quickly find contacts using a simple search feature.

## How to Use
- **Login or Register:** Start by logging in with your username and password or create a new account.
- **Navigating the Interface:** Once logged in, use the intuitive interface to manage your contacts and user settings.
- **Adding Contacts:** Use the 'Add Contact' feature to input new contact details into your phonebook.
- **Editing and Deleting:** Easily edit or remove existing contacts as needed.

## Technical Documentation
For developers interested in the application's architecture and custom UI components, comprehensive Javadoc comments are available throughout the codebase, offering insights into the functionality and usage of different classes and methods.

## Support
If you encounter any issues or require assistance, please refer to the user manual included in the application package or contact our support team.

Enjoy managing your contacts with ease using the Phonebook application!

---

This README provides a basic overview of the application, its requirements, and how to get started with testing and exploring its features. For more detailed information, users and developers are encouraged to refer to specific documentation sections or contact support.
