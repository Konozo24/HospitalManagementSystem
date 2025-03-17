// Abstract base class for all people in the system

import java.util.ArrayList;

abstract class Person {
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String emergencyContact;
    private String dateOfBirth;
    private char gender;

    // Constructor
    public Person(String id, String name, String address, String phoneNumber, 
                  String email, String emergencyContact, String dateOfBirth, char gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.emergencyContact = emergencyContact;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    // Abstract methods to be implemented by subclasses
    public abstract String getRole();
    public abstract String getDetails();

    // Common method for all Person objects
    @Override
    public String toString() {
        return "ID: " + id + 
               "\nName: " + name + 
               "\nAddress: " + address + 
               "\nPhone: " + phoneNumber + 
               "\nEmail: " + email + 
               "\nEmergency Contact: " + emergencyContact +
               "\nDate of Birth: " + dateOfBirth +
               "\nGender: " + gender;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id.equals(person.id);
    }
}

// Abstract class for medical professionals (doctors and nurses)
abstract class MedicalProfessional extends Person {
    private String employeeId;
    private double salary;
    private String joinDate;
    private String department;
    private String qualification;
    
    public MedicalProfessional(String id, String name, String address, String phoneNumber, 
                              String email, String emergencyContact, String dateOfBirth, 
                              char gender, String employeeId, double salary, String joinDate, 
                              String department, String qualification) {
        super(id, name, address, phoneNumber, email, emergencyContact, dateOfBirth, gender);
        this.employeeId = employeeId;
        this.salary = salary;
        this.joinDate = joinDate;
        this.department = department;
        this.qualification = qualification;
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    
    // Abstract method to be implemented by Doctor and Nurse
    public abstract String getProfessionalInfo();
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nEmployee ID: " + employeeId + 
               "\nDepartment: " + department + 
               "\nQualification: " + qualification + 
               "\nJoin Date: " + joinDate;
    }
}

// Doctor class
class Doctor extends MedicalProfessional {
    private String specialization;
    private int yearsOfExperience;
    private java.util.ArrayList<Patient> patientList;
    private java.util.ArrayList<Appointment> appointments;
    
    public Doctor(String id, String name, String address, String phoneNumber, 
                 String email, String emergencyContact, String dateOfBirth, 
                 char gender, String employeeId, double salary, String joinDate, 
                 String department, String qualification, String specialization, 
                 int yearsOfExperience) {
        super(id, name, address, phoneNumber, email, emergencyContact, dateOfBirth, 
              gender, employeeId, salary, joinDate, department, qualification);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.patientList = new java.util.ArrayList<>();
        this.appointments = new java.util.ArrayList<>();
    }

    // Getters and Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public java.util.ArrayList<Patient> getPatientList() {
        return patientList;
    }
    
    public java.util.ArrayList<Appointment> getAppointments() {
        return appointments;
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
        return super.toString() + 
               "\nSpecialization: " + specialization + 
               "\nYears of Experience: " + yearsOfExperience + 
               "\nNumber of Patients: " + patientList.size();
    }
}

// Nurse class
class Nurse extends MedicalProfessional {
    private String assignedDoctor;
    private String shift;
    private String duties;
    
    public Nurse(String id, String name, String address, String phoneNumber, 
                String email, String emergencyContact, String dateOfBirth, 
                char gender, String employeeId, double salary, String joinDate, 
                String department, String qualification, String assignedDoctor,
                String shift, String duties) {
        super(id, name, address, phoneNumber, email, emergencyContact, dateOfBirth, 
              gender, employeeId, salary, joinDate, department, qualification);
        this.assignedDoctor = assignedDoctor;
        this.shift = shift;
        this.duties = duties;
    }

    // Getters and Setters
    public String getAssignedDoctor() {
        return assignedDoctor;
    }

    public void setAssignedDoctor(String assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }
    
    // Implementation of abstract methods
    @Override
    public String getRole() {
        return "Nurse";
    }
    
    @Override
    public String getDetails() {
        return "Nurse: " + getName() + ", Department: " + getDepartment();
    }
    
    @Override
    public String getProfessionalInfo() {
        return "Department: " + getDepartment() + ", Shift: " + shift + ", Assigned Doctor: " + assignedDoctor;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nAssigned Doctor: " + assignedDoctor + 
               "\nShift: " + shift + 
               "\nDuties: " + duties;
    }
}

// Patient class
class Patient extends Person {
    private String patientId;
    private String bloodGroup;
    private String admitDate;
    private String dischargeDate;
    private String insuranceInfo;
    private java.util.ArrayList<MedicalRecord> medicalHistory;
    private java.util.ArrayList<Appointment> appointments;
    
    public Patient(String id, String name, String address, String phoneNumber, 
                  String email, String emergencyContact, String dateOfBirth, 
                  char gender, String patientId, String bloodGroup, 
                  String admitDate, String dischargeDate, String insuranceInfo) {
        super(id, name, address, phoneNumber, email, emergencyContact, dateOfBirth, gender);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.admitDate = admitDate;
        this.dischargeDate = dischargeDate;
        this.insuranceInfo = insuranceInfo;
        this.medicalHistory = new java.util.ArrayList<>();
        this.appointments = new java.util.ArrayList<>();
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
    
    public java.util.ArrayList<MedicalRecord> getMedicalHistory() {
        return medicalHistory;
    }
    
    public java.util.ArrayList<Appointment> getAppointments() {
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
        return super.toString() + 
               "\nPatient ID: " + patientId + 
               "\nBlood Group: " + bloodGroup + 
               "\nAdmit Date: " + admitDate + 
               "\nDischarge Date: " + (dischargeDate != null ? dischargeDate : "Not discharged") + 
               "\nInsurance Info: " + insuranceInfo;
    }
}

// Appointment class
class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;
    private String consultationRoom;
    private String purpose;
    private String status; // Scheduled, Completed, Cancelled
    
    public Appointment(String appointmentId, Patient patient, Doctor doctor, 
                      String date, String time, String consultationRoom, 
                      String purpose) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.consultationRoom = consultationRoom;
        this.purpose = purpose;
        this.status = "Scheduled";
        
        // Add this appointment to both patient and doctor
        patient.addAppointment(this);
        doctor.addAppointment(this);
    }

    // Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getConsultationRoom() {
        return consultationRoom;
    }

    public void setConsultationRoom(String consultationRoom) {
        this.consultationRoom = consultationRoom;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    // Complete the appointment
    public void completeAppointment() {
        this.status = "Completed";
    }
    
    // Cancel the appointment
    public void cancelAppointment() {
        this.status = "Cancelled";
        patient.removeAppointment(this);
        doctor.removeAppointment(this);
    }
    
    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId +
               "\nPatient: " + patient.getName() +
               "\nDoctor: " + doctor.getName() +
               "\nDate: " + date +
               "\nTime: " + time +
               "\nRoom: " + consultationRoom +
               "\nPurpose: " + purpose +
               "\nStatus: " + status;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment that = (Appointment) obj;
        return appointmentId.equals(that.appointmentId);
    }
}

// Medical Record class
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
    
    public MedicalRecord(String recordId, Patient patient, Doctor doctor, 
                        String date, String diagnosis, String treatment, 
                        String prescription, String notes, String followUpDate) {
        this.recordId = recordId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.prescription = prescription;
        this.notes = notes;
        this.followUpDate = followUpDate;
        
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

// Department class
class Department {
    private String departmentId;
    private String name;
    private String location;
    private String headDoctor;
    private java.util.ArrayList<Doctor> doctors;
    private java.util.ArrayList<Nurse> nurses;
    
    public Department(String departmentId, String name, String location, String headDoctor) {
        this.departmentId = departmentId;
        this.name = name;
        this.location = location;
        this.headDoctor = headDoctor;
        this.doctors = new java.util.ArrayList<>();
        this.nurses = new java.util.ArrayList<>();
    }

    // Getters and Setters
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHeadDoctor() {
        return headDoctor;
    }

    public void setHeadDoctor(String headDoctor) {
        this.headDoctor = headDoctor;
    }
    
    public java.util.ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    
    public java.util.ArrayList<Nurse> getNurses() {
        return nurses;
    }
    
    // Methods to manage doctors
    public void addDoctor(Doctor doctor) {
        if (!doctors.contains(doctor)) {
            doctors.add(doctor);
        }
    }
    
    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }
    
    // Methods to manage nurses
    public void addNurse(Nurse nurse) {
        if (!nurses.contains(nurse)) {
            nurses.add(nurse);
        }
    }
    
    public void removeNurse(Nurse nurse) {
        nurses.remove(nurse);
    }
    
    @Override
    public String toString() {
        return "Department ID: " + departmentId +
               "\nName: " + name +
               "\nLocation: " + location +
               "\nHead Doctor: " + headDoctor +
               "\nNumber of Doctors: " + doctors.size() +
               "\nNumber of Nurses: " + nurses.size();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department that = (Department) obj;
        return departmentId.equals(that.departmentId);
    }
}

// Hospital class - Main class that manages all entities
class Hospital {
    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private java.util.ArrayList<Department> departments;
    private java.util.ArrayList<Doctor> doctors;
    private java.util.ArrayList<Nurse> nurses;
    private java.util.ArrayList<Patient> patients;
    private java.util.ArrayList<Appointment> appointments;
    private java.util.ArrayList<MedicalRecord> medicalRecords;
    
    public Hospital(String name, String address, String contactNumber, String email) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.departments = new java.util.ArrayList<>();
        this.doctors = new java.util.ArrayList<>();
        this.nurses = new java.util.ArrayList<>();
        this.patients = new java.util.ArrayList<>();
        this.appointments = new java.util.ArrayList<>();
        this.medicalRecords = new java.util.ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Patient> getPatients(){
        return patients;
    }

    public ArrayList<Appointment> getAppointments(){
        return appointments;
    }
    
    // Methods to manage departments
    public void addDepartment(Department department) {
        if (!departments.contains(department)) {
            departments.add(department);
        }
    }
    
    public void removeDepartment(Department department) {
        departments.remove(department);
    }
    
    public Department findDepartmentById(String departmentId) {
        for (Department department : departments) {
            if (department.getDepartmentId().equals(departmentId)) {
                return department;
            }
        }
        return null;
    }
    
    // Methods to manage doctors
    public void addDoctor(Doctor doctor) {
        if (!doctors.contains(doctor)) {
            doctors.add(doctor);
        }
    }
    
    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }
    
    public Doctor findDoctorById(String doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }
    
    // Methods to manage nurses
    public void addNurse(Nurse nurse) {
        if (!nurses.contains(nurse)) {
            nurses.add(nurse);
        }
    }
    
    public void removeNurse(Nurse nurse) {
        nurses.remove(nurse);
    }
    
    public Nurse findNurseById(String nurseId) {
        for (Nurse nurse : nurses) {
            if (nurse.getId().equals(nurseId)) {
                return nurse;
            }
        }
        return null;
    }
    
    // Methods to manage patients
    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
    }
    
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }
    
    public Patient findPatientById(String patientId) {
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                return patient;
            }
        }
        return null;
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
    
    public Appointment findAppointmentById(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }
    
    // Methods to manage medical records
    public void addMedicalRecord(MedicalRecord record) {
        if (!medicalRecords.contains(record)) {
            medicalRecords.add(record);
        }
    }
    
    public void removeMedicalRecord(MedicalRecord record) {
        medicalRecords.remove(record);
    }
    
    public void displayHospitalInfo() {
        System.out.println(toString());
    }

    public MedicalRecord findMedicalRecordById(String recordId) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getRecordId().equals(recordId)) {
                return record;
            }
        }
        return null;
    }
    
    // Display hospital information
    @Override
    public String toString() {
        return "Hospital: " + name +
               "\nAddress: " + address +
               "\nContact: " + contactNumber +
               "\nEmail: " + email +
               "\nDepartments: " + departments.size() +
               "\nDoctors: " + doctors.size() +
               "\nNurses: " + nurses.size() +
               "\nPatients: " + patients.size();
    }
}
