#🩺 Doctor’s Wallet

Doctor’s Wallet is a Java mini-project developed using Swing (GUI) and MySQL (via JDBC) that helps doctors easily manage patient records — including personal details, medicines, and medical history — all in one organized system.

##📖 Table of Contents

-Overview
-Features
-Tech Stack
-Database Structure
-Project Screens
-Setup Instructions
-How to Use
-Future Enhancements
-Team Members
-License

##💡 Overview

Doctor’s Wallet is designed to simplify healthcare data management.
Doctors can:

-Add, view, search, update, and delete patient records.
-Manage patient medicines and medical history.
-Automatically link and remove related data across all tables.
-It provides a simple yet modern Swing-based interface for easy use in clinics and hospitals.

##⚙️ Features

✅ Add Patient — Register new patients with details like name, age, gender, blood group, contact info, etc.
✅ Add Medicine — Record medicines prescribed to a patient.
✅ Add Medical History — Store previous illnesses or treatments.
✅ Add Current Medical status.
✅ Search Patient — Quickly search by patient name or medicine used.
✅ View Patient details.
✅ View Current Medical status.
✅ View Medicine records.
✅ Automatic Delete Cascade — Deleting a patient automatically deletes all related data.
✅ Modern Swing UI — Clean and professional user interface.

##🧩 Tech Stack
Category	Technology Used:
Programming Language:	Java
GUI Framework:	Swing
Database:	MySQL
Connectivity:	JDBC
IDE Used: Eclipse.
Build Tool	Java SE 17 or later

##🗄️ Database Structure( I am providing with all the datas for better understanding)
mysql> use doctors_wallet;
Database changed

Tables Used,

mysql> show tables;
+--------------------------+
| Tables_in_doctors_wallet |
+--------------------------+
| currnt_med_sts           |
| doctors_auth             |
| medical_history          |
| medicines                |
| patients                 |
+--------------------------+

mysql> select*from currnt_med_sts;
+-----------+---------------------+--------------------+-----------------------------------------------+
| patientid | name                | diagonized_illness | description                                   |
+-----------+---------------------+--------------------+-----------------------------------------------+
|         1 | Aarav Mathew        | Cancer             | Diagonized for cancer                         |
|         2 | Maria John          | Type 2 Diabetes    | Maintaining sugar levels; continue Metformin. |
|         3 | Joel Varghese       | High Cholesterol   | Diet control advised; continue Atorvastatin.  |
|         4 | Aisha Rahman        | Mild Fever         | Continue Paracetamol; increase fluid intake.  |
|         5 | Rohit Menon         | Allergic Rhinitis  | Continue Cetirizine; avoid dust exposure.     |
|         6 | Abhiram Namboothiri | High Fever         | Diagonised High fever for 3 weeks             |
+-----------+---------------------+--------------------+-----------------------------------------------+
6 rows in set (0.00 sec)

mysql> select * from doctors_auth;
+-----------------+-------------+
| username        | password    |
+-----------------+-------------+
| Jacob Kuruvilla | doctor@1234 |
+-----------------+-------------+
1 row in set (0.00 sec)

mysql> select * from medical_history;;
+-----------+---------------+----------------------------------------------+
| patientid | name          | description                                  |
+-----------+---------------+----------------------------------------------+
|         1 | Aarav Mathew  | Treated for sinus infection last year.       |
|         2 | Maria John    | Had gestational diabetes during pregnancy.   |
|         3 | Joel Varghese | History of hypertension and mild chest pain. |
|         4 | Aisha Rahman  | Recovered from viral fever two months ago.   |
|         5 | Rohit Menon   | Seasonal allergies and mild asthma.          |
+-----------+---------------+----------------------------------------------+
5 rows in set (0.00 sec)

mysql> select * from medicines;
+-----------+---------------+------+---------------+----------------------------------------+
| patientid | name          | age  | medicine_used | med_description                        |
+-----------+---------------+------+---------------+----------------------------------------+
|         1 | Aarav Mathew  |   29 | Paracetamol   | For high fever                         |
|         2 | Maria John    |   35 | Metformin     | Used to control blood sugar (diabetes) |
|         3 | Joel Varghese |   42 | Atorvastatin  | Lowers cholesterol levels              |
|         4 | Aisha Rahman  |   31 | Paracetamol   | For fever and mild pain relief         |
|         5 | Rohit Menon   |   27 | Cetirizine    | Used for allergy and sneezing relief   |
+-----------+---------------+------+---------------+----------------------------------------+
5 rows in set (0.00 sec)

mysql> select*from patients;
+-----------+---------------------+------+--------+-------------+-----------------------------+--------------+----------------+
| patientid | name                | age  | gender | blood_group | address                     | phone_number | emrgncy_number |
+-----------+---------------------+------+--------+-------------+-----------------------------+--------------+----------------+
|         1 | Aarav Mathew        |   29 | Male   | B+          | Kochi,Kerala                | 9876543210   | 9847012345     |
|         2 | Maria John          |   35 | Female | O+          | Kottayam,Kerala             | 9895123456   | 9447123499     |
|         3 | Joel Varghese       |   42 | Male   | A-          | Thrissur, Kerala            | 9745123789   | 9526012345     |
|         4 | Aisha Rahman        |   31 | Female | AB+         | Malappuram,Kerala           | 9567123490   | 9807011122     |
|         5 | Rohit Menon         |   27 | Male   | B-          | Kozhikode, Kerala           | 9495123344   | 9995011111     |
|         6 | Abhiram Namboothiri |   28 | Male   | AB-         | Thiruvalla, Pathanamthittta | 9786578213   | 9567465821     |
+-----------+---------------------+------+--------+-------------+-----------------------------+--------------+----------------+
6 rows in set (0.00 sec)

mysql>

##🪟 Project Folder Structure.

<img width="393" height="696" alt="image" src="https://github.com/user-attachments/assets/9431c8e6-6044-42da-a2b4-803ee7034826" />

🧭 Setup Instructions
- 1. Clone the Repository
- 2. Open the Project
   Open it in Eclipse or IntelliJ IDEA.
- 3. Create the Database
    CREATE DATABASE doctors_wallet;
    USE doctors_wallet;
- 4. Import Tables
      Run the SQL scripts for patients, medicines, and medical_history tables.
- 5. Configure Database Connection
- 6. Run the Application
      Compile and run:
      javac Main.java
      java Main

##💻 How to Use

- Login to the system.
- Add Patient — Register new patients with details like name, age, gender, blood group, contact info, etc.
- Add Medicine — Record medicines prescribed to a patient.
- Add Medical History — Store previous illnesses or treatments.
- Add Current Medical status.
- Search Patient — Quickly search by patient name or medicine used.
- View Patient details.
- View Current Medical status.
- View Medicine records.

##👩‍⚕️ Team Members

1.Mabel Anto Moncy:	Team Lead / Developer	UI design, database integration.
2.Gopika Girish:	Project Advicer (Biology background).
3.Anaxa Anna Mathew:	Support	Assist in documentation.
4.Anandhu: 	Overall eye keeper.

##📜 License

This project is created as part of an Academic Mini Project under the Computer Science Department.
Feel free to use or modify it for educational purposes.
