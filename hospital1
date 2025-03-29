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
    private ArrayList<MedicalRecord> medicalrecords;

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
        this.medicalrecords = new ArrayList<>();
    }

    // setter and getter
    public String getname(){
        return this.name;
    }
    public void setname(String name){
        this.name = name;
        if (name == null)throw new IllegalArgumentException("Name is empty");
    }
    public String getaddress(){
        return this.address;
    }
    public void setaddress(String address){
        this.address = address;
        if (name == null)throw new IllegalArgumentException("Address is empty");
    }
    public String getcontactNumber(){
        return this.contactNumber;
    }
    public void setcontactNumber(String contactNumber){
        this.contactNumber = contactNumber;
        if (name == null)throw new IllegalArgumentException("contact number is empty");
    }
    public String getemail(){
        return this.email;
    }
    public void setemail(String email){
        this.email = email;
        if (name == null)throw new IllegalArgumentException("Email is empty");
    }
    
    // department method
    public void addDepartment(Department department){
        departments.add(department);
        if (name == null)throw new IllegalArgumentException("Department is empty");
    }
    public void removeDepartment(Department department){
        departments.remove(department);
        if (name == null)throw new IllegalArgumentException("Department is empty");
        if(!department.contains(department))throw new IllegalArgumentException("Department cannot be found");
    }
    public Department findDepartmentById(String departmentId){
        if (name == null)throw new IllegalArgumentException("Department cannot be found");
        for(Department d : departments){
            if(d.getId().equals(departmentId)){
                return d;
            }
        }
        throw new IllegalArgumentException("Department ID " + departmentId + "not found");
    }

    // doctor method
    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
        if (name == null)throw new IllegalArgumentException("Doctor is empty");
    }
    public void removeDoctor(Doctor doctor){
        doctors.remove(doctor);
        if (name == null)throw new IllegalArgumentException("Doctor is empty");
        if(!doctor.contains(doctor))throw new IllegalArgumentException("Doctor cannot be found");
    }
    public Doctor findDoctorById(String doctorId){
        if (name == null)throw new IllegalArgumentException("Doctor cannot be found");
        for(Doctor d : doctors){
            if(d.getId().equals(doctorId)){
                return d;
            }
        }
        throw new IllegalArgumentException("Doctor ID " + doctorId + "not found");
    }
    
    // nurse method
    public void addNurse(Nurse nurse){
        nurses.add(nurse);
        if (name == null)throw new IllegalArgumentException("Nurse is empty");
    }
    public void removeNurse(Nurse nurse){
        nurses.remove(nurse);
        if (name == null)throw new IllegalArgumentException("Nurse is empty");
        if(!nurse.contains(nurse))throw new IllegalArgumentException("Nurse cannot be found");
    }
    public Nurse findNurseById(String nurseId){
        if (name == null)throw new IllegalArgumentException("Nurse cannot be found");
        for(Nurse d : nurses){
            if(d.getId().equals(nurseId)){
                return d;
            }
        }
        throw new IllegalArgumentException("Nurse ID " + nurseId + "not found");
    }

    // patient method
    public void addPatient(Patient patient){
        patients.add(patient);
        if (name == null)throw new IllegalArgumentException("Patient is empty");
    }
    public void removePatient(Patient patient){
        patients.remove(patient);
        if (name == null)throw new IllegalArgumentException("Patient is empty");
        if(!patient.contains(patient))throw new IllegalArgumentException("Patient cannot be found");
    }
    public Patient findPatientById(String patientId){
        if (name == null)throw new IllegalArgumentException("Patient cannot be found");
        for(Patient d : patients){
            if(d.getId().equals(patientId)){
                return d;
            }
        }
        throw new IllegalArgumentException("Patient ID " + patientId + "not found");
    }

    // appointment method
    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        if (name == null)throw new IllegalArgumentException("Appointment is empty");
    }
    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
        if (name == null)throw new IllegalArgumentException("Appointment is empty");
        if(!appointment.contains(appointment))throw new IllegalArgumentException("Appointment cannot be found");
    }
    public Appointment findAppointmentById(String appointmentId){
        if (name == null)throw new IllegalArgumentException("No appointment");
        for(Appointment d : appointments){
            if(d.getId().equals(appointmentId)){
                return d;
            }
        }
        throw new IllegalArgumentException("Appointment ID " + appointmentId + "not found");
    }

    // medical record method
    public void addMedicalRecord(MedicalRecord record){
        medicalrecords.add(record);
        if (name == null)throw new IllegalArgumentException("Record is empty");
    }
    public void removeMedicalRecord(MedicalRecord record){
        medicalrecords.remove(record);
        if (name == null)throw new IllegalArgumentException("Record is empty");
        if(!record.contains(record))throw new IllegalArgumentException("Record cannot be found");
    }
    public MedicalRecord findMedicalRecordById(String recordId){
        if (name == null)throw new IllegalArgumentException("Record cannot be found");
        for(MedicalRecord d : medicalrecords){
            if(d.getId().equals(recordId)){
                return d;
            }
        }
        throw new IllegalArgumentException("Medical record ID " + recordId + "not found");
    }

    public String toString(){
        try {
            return String.format("%-10s %-10s %-10s %-10s" , this.name,this.address,this.contactNumber,this.email);
        } catch (Exception e) {
            return "Error hospital details";
        }
    }
    
}
