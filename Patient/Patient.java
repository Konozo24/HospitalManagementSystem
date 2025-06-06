
import java.util.ArrayList;

public class Patient extends Person {
    private String patientId;
    private String bloodGroup;
    private String admitDate;
    private String dischargeDate;
    private String insuranceInfo;
    private ArrayList<MedicalRecord> medicalHistory;
    private ArrayList<Appointment> appointments;
    private static int patientNumber = 1;
    
    public Patient(String id, String name, String address, String phoneNumber, 
                  String email, String emergencyContact, String dateOfBirth, 
                  char gender, String bloodGroup, 
                  String admitDate, String dischargeDate, String insuranceInfo) {
        super(id, name, address, phoneNumber, email, emergencyContact, dateOfBirth, gender);
        this.patientId = "P" + patientNumber;
        this.bloodGroup = bloodGroup;
        this.admitDate = admitDate;
        this.dischargeDate = dischargeDate;
        this.insuranceInfo = insuranceInfo;
        this.medicalHistory = new ArrayList<>();
        this.appointments = new ArrayList<>();
        patientNumber++;
    }

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(String admitDate) {
        this.admitDate = admitDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }
    
    public ArrayList<MedicalRecord> getMedicalHistory() {
        return medicalHistory;
    }
    
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    
    // Methods to manage medical records
    public void addMedicalRecord(MedicalRecord record) {
        medicalHistory.add(record);
    }
    
    public void removeMedicalRecord(MedicalRecord record) {
        medicalHistory.remove(record);
    }
    
    // Methods to manage appointments
    public void addAppointment(Appointment appointment) {
        if (!appointments.contains(appointment)) {
            appointments.add(appointment);
        }
    }
    
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
    
    // Implementation of abstract methods
    @Override
    public String getRole() {
        return "Patient";
    }
    
    @Override
    public String getDetails() {
        return "Patient: " + getName() + ", Patient ID: " + patientId;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
