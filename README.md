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

-✅ Add Patient — Register new patients with details like name, age, gender, blood group, contact info, etc.
-✅ Add Medicine — Record medicines prescribed to a patient.
-✅ Add Medical History — Store previous illnesses or treatments.
-✅ Add Current Medical status.
-✅ Search Patient — Quickly search by patient name or medicine used.
-✅ View Patient details.
-✅ View Current Medical status.
-✅ View Medicine records.
-✅ Automatic Delete Cascade — Deleting a patient automatically deletes all related data.
-✅ Modern Swing UI — Clean and professional user interface.

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

<img width="972" height="845" alt="image" src="https://github.com/user-attachments/assets/66452880-6338-4414-aa31-127386886a1e" />

<img width="1172" height="462" alt="image" src="https://github.com/user-attachments/assets/3b967969-0561-46fa-851b-7d177a4628e4" />


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
