# NeDoMed Android App

## Overview

The NeDoMed Android app provides users with a mobile interface to interact with the NeDoMed-backend Django project. It allows patients and doctors to access their data, schedule appointments, and manage medical records from their Android devices.

## Features

- **User Authentication**: Log in securely using existing credentials or sign up for a new account.
- **Patient Dashboard**: View medical history, prescribed medications, allergies, and other health-related information.
- **Doctor Dashboard**: Access patient data, view scheduled appointments, and update medical records.
- **Appointment Scheduling**: Schedule appointments with doctors based on availability.
- **Real-time Notifications**: Receive notifications for appointment reminders and updates.
- **Offline Access**: Access previously viewed data offline for convenience.

## Installation

1. **Clone the Repository**: Clone the NeDoMed Android app repository using the following command:

   ```bash
   git clone https://github.com/yourusername/NeDoMed-android.git
2. **Open in Android Studio**: Import the project into Android Studio to build and run the app.
3. **Configure API Endpoint**: Update the API endpoint URL in the app's configuration to point to your NeDoMed-backend Django project.
4. **Build and Run**: Build the app and run it on an Android device or emulator.

## Screenshots
![image](https://github.com/AlanAquinas/NeDoMed/assets/116744376/983ed24c-002c-4f14-afdc-43411087d121)



### NeDoMed Backend API Documentation
Authentication
The NeDoMed backend API uses JSON Web Tokens (JWT) for authentication. To access protected endpoints, clients must include a valid JWT token in the Authorization header of their HTTP requests.

# Example Authorization Header:
Authorization: Bearer <JWT_TOKEN>

# Base URL
The base URL for the NeDoMed backend API is:

(https://nedomed-backend.onrender.com)

## Endpoints
1. User Authentication
POST /api/login
Description: Authenticate user and generate JWT token.

Request Body:
username (string): User's username
password (string): User's password

Response:
token (string): JWT token for authentication

Authorization: Not required
POST /api/signup

Description: Create a new user account.

Request Body:
username (string): User's desired username
email (string): User's email address
password (string): User's password
user_type (string): User's role (admin, patient, doctor)

Response:
id (int): User ID
username (string): User's username
email (string): User's email address
user_type (string): User's role
Authorization: Not required
## 3. Patient Endpoints
GET /api/patient/{id}
Description: Get patient data by ID.
Response:
id (int): Patient ID
first_name (string): Patient's first name
last_name (string): Patient's last name
date_of_birth (string): Patient's date of birth
blood_type (string): Patient's blood type
allergies (string): Patient's allergies
phone (string): Patient's phone number
Authorization: Required (Patient or Admin)
## 4. Doctor Endpoints
GET /api/doctor/{id}
Description: Get doctor data by ID.
Response:
id (int): Doctor ID
first_name (string): Doctor's first name
last_name (string): Doctor's last name
specialization (string): Doctor's specialization
qualifications (string): Doctor's qualifications
experience_years (int): Doctor's years of experience
license_number (string): Doctor's license number
room (string): Doctor's room number
Authorization: Required (Doctor or Admin)
5. Appointment Endpoints
POST /api/appointment/{doctor_id}/schedule
Description: Schedule an appointment with a doctor.
Request Body:
appointment_date (string): Appointment date (YYYY-MM-DD)
start_time (string): Appointment start time (HH:MM)
reason (string): Reason for appointment
notes (string): Additional notes
Response:
id (int): Appointment ID
patient_id (int): Patient ID
doctor_id (int): Doctor ID
appointment_date (string): Appointment date
start_time (string): Appointment start time
end_time (string): Appointment end time
reason (string): Reason for appointment
is_accepted (boolean): Appointment acceptance status
notes (string): Additional notes
Authorization: Required (Patient or Admin)
Error Handling
The NeDoMed backend API returns appropriate HTTP status codes along with error messages in JSON format for any encountered errors. Common error codes include:

400 Bad Request: Invalid request parameters or missing required fields.
401 Unauthorized: Invalid or missing JWT token for authentication.
404 Not Found: Resource not found or ID does not exist.
500 Internal Server Error: Unexpected server error.
