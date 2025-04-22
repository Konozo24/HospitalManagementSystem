package Assignment;
import java.util.ArrayList;

public class Hospital{
    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private ArrayList<Department> departments;
    private ArrayList<Doctor> doctors;
    private ArrayList<Nurse> nurses;
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> appointments;
    private ArrayList<MedicalRecord> medicalRecords;
   

    public Hospital(String name,String address,String contactNumber,String email){
        if(name == null || address == null || contactNumber == null || email == null){
            throw new IllegalArgumentException("Details is empty");
        }
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.departments = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();

        addPatient(new Patient("001", "John Doe", "123 Street", "0123456789", "9876543210", "john@mail.com", "1990-01-01", 'F', "A+", "2000-200-200", "200-2301-231", "Hello"));
        addDoctor(new Doctor("0012", "Lebron", "123 Street", "0123456789", "9876543210", "john@mail.com", "1990-01-01", 'M', "D011", 2500.00, "2025-1-1", "Doctor", "SPM", "Cardiology", 5));
        
    }

    // setter and getter
    public String getname(){
        return this.name;
    }
    public void setname(String name){
        if (name == null)throw new IllegalArgumentException("Name is empty");
        this.name = name;
    }
    public String getaddress(){
        return this.address;
    }
    public void setaddress(String address){
        if (address == null)throw new IllegalArgumentException("Address is empty");
        this.address = address;
        
    }
    public String getcontactNumber(){
        return this.contactNumber;
    }
    public void setcontactNumber(String contactNumber){
        if (contactNumber == null)throw new IllegalArgumentException("Contact number is empty");
        this.contactNumber = contactNumber;
        
    }
    public String getemail(){
        return this.email;
    }
    public void setemail(String email){
        if (email == null)throw new IllegalArgumentException("Email is empty");
        this.email = email;
        
    }

    
    // Department method
    public void addDepartment(Department department){
        if (!departments.contains(department)) {
            departments.add(department);
        }
    }
        
    public void removeDepartment(Department department){
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }

        departments.remove(department);
    }

    public Department findDepartmentById(String departmentId){
        for(Department d : departments){
            if(d.getDepartmentId().equals(departmentId)){
                return d;
            }
        }
        throw new IllegalArgumentException("Department ID " + departmentId + "not found");
    }

    public ArrayList<Department> getAllDepartments() {
        return departments;
    }
    // doctor method
    public void addDoctor(Doctor doctor){
        if (!doctors.contains(doctor)) {
            doctors.add(doctor);
        }
    }

    public void removeDoctor(Doctor doctor){
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }
        doctors.remove(doctor);
    }

    public Doctor findDoctorById(String doctorId){
        for(Doctor d : doctors){
            if(d.getId().equals(doctorId)){
                return d;
            }
        }
        throw new IllegalArgumentException("Doctor ID " + doctorId + "not found");
    }

    public ArrayList<Doctor> getAllDoctors() {
        return doctors;
    }
    
    // nurse method
    public void addNurse(Nurse nurse){
        if (!nurses.contains(nurse)) {
            nurses.add(nurse);
        }
    }

    public void removeNurse(Nurse nurse){
        if (name == null){
            throw new IllegalArgumentException("Nurse cannot be null");
        }

        nurses.remove(nurse);
    }
    public Nurse findNurseById(String nurseId){
        for(Nurse d : nurses){
            if(d.getId().equals(nurseId)){
                return d;
            }
        }
        return null;
    }

    public ArrayList<Nurse> getAllNurses() {
        return nurses;
    }
    
    // patient method
    public void addPatient(Patient patient){
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
        
    }
    public void removePatient(Patient patient){
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        patients.remove(patient);
    }
    public Patient findPatientById(String patientId){
        for(Patient d : patients){
            if(d.getId().equals(patientId)){
                return d;
            }
        }
        return null;
    }

    public ArrayList<Patient> getAllPatients() {
        return patients;
    }

    // appointment method
    public void addAppointment(Appointment appointment){
        if (!appointments.contains(appointment)) {
            appointments.add(appointment);
        }

    }
    
    public void removeAppointment(Appointment appointment){
        if(!appointments.contains(appointment))throw new IllegalArgumentException("Appointment cannot be found");

        appointments.remove(appointment);
    }
    public Appointment findAppointmentById(String appointmentId){
        for(Appointment a : appointments){
            if(a.getAppointmentId().equals(appointmentId)){
                return a;
            }
        }
        return null;
    }

    public ArrayList<Appointment> getAllAppointments() {
        return appointments;
    }

    // medical record method
    public void addMedicalRecord(MedicalRecord record){
        if (!medicalRecords.contains(record)) {
            medicalRecords.add(record);
        }
        
    }
    public void removeMedicalRecord(MedicalRecord record){
        medicalRecords.remove(record);
        
    }

    public ArrayList<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }

    public MedicalRecord findMedicalRecordById(String recordId){
        for(MedicalRecord d : medicalRecords){
            if(d.getRecordId().equals(recordId)){
                return d;
            }
        }
        return null;
    }

    public String toString(){
        try {
            return "Hospital: " + name +
            "\nAddress: " + address +
            "\nContact: " + contactNumber +
            "\nEmail: " + email +
            "\nDepartments: " + departments.size() +
            "\nDoctors: " + doctors.size() +
            "\nNurses: " + nurses.size() +
            "\nPatients: " + patients.size();
        } catch (Exception e) {
            return "Error hospital details";
        }
    }
    
}
