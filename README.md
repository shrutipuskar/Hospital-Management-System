# Hospital Management System

## Overview
This project implements a Hospital Management System using Java and MySQL, allowing management of patients, doctors, and appointments within a hospital setting.

## Features
- Add patients with details like name, age, and gender.
- View the list of patients.
- View the list of doctors along with their specializations.
- Book appointments between patients and doctors.

## Technologies Used
- Java
- MySQL
- JDBC for database connectivity

## Setup Instructions
### Database Setup:
1. Create a MySQL database named `hospital`.
2. Execute the following SQL queries to create the necessary tables:
   ```sql
   CREATE TABLE patients (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       age INT NOT NULL,
       gender VARCHAR(10) NOT NULL
   );

   CREATE TABLE doctors (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       specialization VARCHAR(100) NOT NULL
   );

   CREATE TABLE appointments (
       id INT AUTO_INCREMENT PRIMARY KEY,
       patient_id INT NOT NULL,
       doctor_id INT NOT NULL,
       appointment_date DATE NOT NULL,
       FOREIGN KEY (patient_id) REFERENCES patients(id),
       FOREIGN KEY (doctor_id) REFERENCES doctors(id)
   );
## Java Application Setup:

- Ensure you have Java Development Kit (JDK) installed.
- Clone this repository to your local machine.
- Import the project into your preferred IDE (Eclipse, IntelliJ IDEA, etc.).
- Update the MySQL connection details (url, username, password) in HospitalManagementSystem.java file to match your MySQL setup.
- Run the HospitalManagementSystem.java file to start the application.

## Project Setup
- Clone the repository or download the project files.

- Add MySQL Connector/J to the project libraries.

- Download the MySQL Connector/J from the official website.
- Add the connector JAR file to your project's classpath.
- Update database credentials:

- Open HospitalManagementSystem.java.
- Update the url, username, and password variables with your MySQL database credentials.
## Usage
- Upon running the application, you will see a menu with options to add/view patients, view doctors, book appointments, and exit.
