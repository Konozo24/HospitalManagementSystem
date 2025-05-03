

class MedicalRecord {
    private String recordId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String diagnosis;
    private String treatment;
    private String prescription;
    private String notes;
    private String followUpDate;
    private static int recordNumber = 1;
    
    public MedicalRecord(Patient patient, Doctor doctor, 
                        String date, String diagnosis, String treatment, 
                        String prescription, String notes, String followUpDate) {
        this.recordId = "MR" + recordNumber;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.prescription = prescription;
        this.notes = notes;
        this.followUpDate = followUpDate;
        recordNumber++;
        
        // Add this record to the patient's medical history
        patient.addMedicalRecord(this);
    }

    // Getters and Setters
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }
    
    @Override
    public String toString() {
        return "Record ID: " + recordId +
               "\nPatient: " + patient.getName() +
               "\nDoctor: " + doctor.getName() +
               "\nDate: " + date +
               "\nDiagnosis: " + diagnosis +
               "\nTreatment: " + treatment +
               "\nPrescription: " + prescription +
               "\nNotes: " + notes +
               "\nFollow-up Date: " + followUpDate;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MedicalRecord that = (MedicalRecord) obj;
        return recordId.equals(that.recordId);
    }
}

