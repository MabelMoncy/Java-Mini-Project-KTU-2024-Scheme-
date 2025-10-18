/*package com.doctorswallet.model;

public class Medicine {
    private int patientId;
    private String name;
    private int age;
    private String medicineUsed;
    private String medDescription;

    public Medicine(int patientId, String name, int age, String medicineUsed, String medDescription) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicineUsed = medicineUsed;
        this.medDescription = medDescription;
    }

    // Getters and setters
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getMedicineUsed() { return medicineUsed; }
    public void setMedicineUsed(String medicineUsed) { this.medicineUsed = medicineUsed; }

    public String getMedDescription() { return medDescription; }
    public void setMedDescription(String medDescription) { this.medDescription = medDescription; }
}
*/
package com.doctorswallet.model;

public class Medicine {
    private int patientId;
    private String name;
    private int age;
    private String medicineUsed;
    private String medDescription;

    public Medicine(int patientId, String name, int age, String medicineUsed, String medDescription) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicineUsed = medicineUsed;
        this.medDescription = medDescription;
    }

    // Getters
    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMedicineUsed() { return medicineUsed; }
    public String getMedDescription() { return medDescription; }

    // Setters (optional)
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setMedicineUsed(String medicineUsed) { this.medicineUsed = medicineUsed; }
    public void setMedDescription(String medDescription) { this.medDescription = medDescription; }
}

