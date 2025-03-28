# Password Generator and Manager

## Overview
The **Password Generator and Manager** is a Java-based application that allows users to generate secure passwords, validate their strength, and store them securely in a **MySQL** database. Users can also retrieve their saved passwords.

## Features
- **Secure Password Generation**: Generates strong passwords with uppercase letters, numbers, and symbols.
- **Custom Password Input**: Users can manually set passwords that meet security criteria.
- **Password Validation**: Ensures passwords meet security requirements.
- **Save Passwords**: Stores passwords securely in a MySQL database.
- **Retrieve Saved Passwords**: Allows users to fetch their stored passwords.

## Technologies Used
- **Java** (JDK 8+)
- **MySQL** (Database Management System)
- **JDBC** (Java Database Connectivity)
- **Eclipse/IntelliJ** (Recommended IDEs)

## Database Schema
### Tables:
1. **passwords**: Stores usernames and passwords securely.

## Setup Instructions
### Prerequisites:
- Install [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- Install [MySQL](https://dev.mysql.com/downloads/)
- Set up a MySQL database and user.

### Steps:
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/PasswordGenerator.git
   ```
2. Open MySQL and execute the provided SQL script (`database.sql`) to create the database.
3. Update the `DB_URL`, `USER`, and `PASS` in `PasswordGeneratorApp.java`.
4. Compile and run the Java program:
   ```sh
   javac PasswordGeneratorApp.java
   java PasswordGeneratorApp
   ```

## Usage
1. **Run the application**
2. Enter your username.
3. Choose between manually entering a password or generating one.
4. If manually entering, ensure the password meets the security criteria.
5. The password is saved in the database.
6. Optionally, retrieve saved passwords.

## Future Enhancements
- Implement encryption for stored passwords.
- Develop a GUI for better user experience.
- Add multi-user support with authentication.

## License
This project is open-source and available under the [MIT License](LICENSE).

