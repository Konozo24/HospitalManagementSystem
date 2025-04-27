import java.util.ArrayList;

class Doctor extends MedicalProfessional {
    private String specialization;
    private int yearsOfExperience;
    private ArrayList<Patient> patientList;
    private ArrayList<Appointment> appointments;
    
    public Doctor(String id, String name, String address, String phoneNumber, 
                 String email, String emergencyContact, String dateOfBirth, 
                 char gender, String employeeId, String salary, String joinDate, 
                 String department, String qualification, String specialization, 
                 int yearsOfExperience){
        super(id, name, address, phoneNumber, email, emergencyContact, dateOfBirth, gender, employeeId, salary, joinDate, department, qualification);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.patientList = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    // Getters and Setters
    public String getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return this.yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public ArrayList<Patient> getPatientList() {
        return this.patientList;
    }
    
    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }
    
    // Methods to manage patients
    public void addPatient(Patient patient) {
        if (!patientList.contains(patient)) {
            patientList.add(patient);
        }
    }
    
    public void removePatient(Patient patient) {
        patientList.remove(patient);
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
        return "Doctor";
    }
    
    @Override
    public String getDetails() {
        return "Doctor: " + getName() + ", Specialization: " + specialization;
    }
    
    @Override
    public String getProfessionalInfo() {
        return "Specialization: " + specialization + ", Experience: " + yearsOfExperience + " years";
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
