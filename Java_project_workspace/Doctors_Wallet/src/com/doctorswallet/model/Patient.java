package com.doctorswallet.model;

public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String bloodGroup;
    private String address;
    private String phoneNumber;
    private String emergencyNumber;

    public Patient(int patientId, String name, int age, String gender, String bloodGroup, String address, String phoneNumber, String emergencyNumber) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emergencyNumber = emergencyNumber;
    }

    // Getters
    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getBloodGroup() { return bloodGroup; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmergencyNumber() { return emergencyNumber; }
}
