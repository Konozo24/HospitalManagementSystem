

import java.util.List;
import java.util.ArrayList;

public class HospitalService {
    Hospital hospital;

    public HospitalService(Hospital hospital) {
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital is empty");
        }

        this.hospital = hospital;
    }

    public void addDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department is null");
        } else if (hospital.findDepartmentById(department.getDepartmentId()) != null) {
            throw new IllegalArgumentException("Department already exists");
        }
        hospital.addDepartment(department);
        System.out.println("Department added successfully: " + department.getName());
    }

    public void removeDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department is null");
        }
        if(!hospital.getAllDepartments().contains(department))throw new IllegalArgumentException("Department cannot be found");
        hospital.removeDepartment(department);
        System.out.println("Department removed successfully: " + department.getName());
    }

    public void editDepartment(Department updatedDepartment) {
        if (updatedDepartment == null || updatedDepartment.getDepartmentId() == null) {
            throw new IllegalArgumentException("Department or Department ID is null");
        }
    
        Department existing = hospital.findDepartmentById(updatedDepartment.getDepartmentId());
        if (existing == null) {
            throw new IllegalArgumentException("Department not found");
        }
    
        existing.setName(updatedDepartment.getName());
        existing.setLocation(updatedDepartment.getLocation());
        existing.setHeadDoctor(updatedDepartment.getHeadDoctor());
    }

    public Department findDepartmentById(String departmentID) {
        if (departmentID == null) {
            throw new IllegalArgumentException("Department ID is null");
        }
        
        return hospital.findDepartmentById(departmentID);
    }

    public List<Department> viewAllDepartments() {
        return new ArrayList<>(hospital.getAllDepartments());
    }

    public void addDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor is null");
        } else if (hospital.findDoctorById(doctor.getId()) != null) {
            throw new IllegalArgumentException("Doctor already exists");
        }
        hospital.addDoctor(doctor);
        System.out.println("Doctor added successfully: " + doctor.getName());
    }

    public void removeDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor is null");
        }
        if(!hospital.getAllDoctors().contains(doctor))throw new IllegalArgumentException("Doctor cannot be found");
        hospital.removeDoctor(doctor);
        System.out.println("Doctor removed successfully: " + doctor.getName());
    }

    public Doctor findDoctorById(String doctorID) {
        if (doctorID == null) {
            throw new IllegalArgumentException("Doctor ID is null");
        }
        
        return hospital.findDoctorById(doctorID);
    }

    public List<Doctor> viewAllDoctors() {
        return new ArrayList<>(hospital.getAllDoctors());
    }

    public void addNurse(Nurse nurse) {
        if (nurse == null) {
            throw new IllegalArgumentException("Nurse is null");
        } else if (hospital.findNurseById(nurse.getId()) != null) {
            throw new IllegalArgumentException("Nurse already exists");
        }
        hospital.addNurse(nurse);
        System.out.println("Nurse added successfully: " + nurse.getName());
    }

    public void removeNurse(Nurse nurse) {
        if (nurse == null) {
            throw new IllegalArgumentException("Nurse is null");
        }
        if(!hospital.getAllNurses().contains(nurse))throw new IllegalArgumentException("Nurse cannot be found");
        hospital.removeNurse(nurse);
        System.out.println("Nurse removed successfully: " + nurse.getName());
    }

    public Nurse findNurseById(Nurse nurse) {
        if (nurse == null || nurse.getId() == null) {
            throw new IllegalArgumentException("Nurse ID is null");
        }
        
        return hospital.findNurseById(nurse.getId());
    }

    public List<Nurse> viewAllNurses() {
        return new ArrayList<>(hospital.getAllNurses());
    }

    public void addPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient is null");
        } else if (hospital.findPatientById(patient.getId()) != null) {
            throw new IllegalArgumentException("Patient already exists");
        }
        hospital.addPatient(patient);
        System.out.println("Patient added successfully: " + patient.getName());
    }
    
    public void removePatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient is null");
        }
        if(!hospital.getAllPatients().contains(patient))throw new IllegalArgumentException("Patient cannot be found");
        hospital.removePatient(patient);
        System.out.println("Patient removed successfully: " + patient.getName());
    }   

    public Patient findPatientById(String patientID){
        if (patientID == null) {
            throw new IllegalArgumentException("Patient ID is null");
        }
       return hospital.findPatientById(patientID);   
    }

    public List<Patient> viewAllPatients() {
        return new ArrayList<>(hospital.getAllPatients());
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment is null");
        } else if (hospital.findAppointmentById(appointment.getAppointmentId()) != null) {
            throw new IllegalArgumentException("Appointment already exists");
        }
        hospital.addAppointment(appointment);
        System.out.println("Appointment added successfully: " + appointment.getAppointmentId());
    }   

    public void removeAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment is null");
        }
        if(!hospital.getAllAppointments().contains(appointment))throw new IllegalArgumentException("Appointment cannot be found");
        hospital.removeAppointment(appointment);
        System.out.println("Appointment removed successfully: " + appointment.getAppointmentId());
    }   

    public void findAppointmentById(Appointment appointment){
        if (appointment == null || appointment.getAppointmentId() == null) {
            throw new IllegalArgumentException("Appointment ID is null");
        }
        hospital.findAppointmentById(appointment.getAppointmentId());
    }

    public List<Appointment> viewAllAppointments() {
        return new ArrayList<>(hospital.getAllAppointments());
    }

    public void addMedicalRecord(MedicalRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Medical record is null");
        } else if (hospital.findMedicalRecordById(record.getRecordId()) != null) {
            throw new IllegalArgumentException("Medical record already exists");
        }
        hospital.addMedicalRecord(record);
        System.out.println("Medical record added successfully: " + record.getRecordId());
    }

    public void removeMedicalRecord(MedicalRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Medical record is null");
        }
        if(!hospital.getAllMedicalRecords().contains(record))throw new IllegalArgumentException("Record cannot be found");
        hospital.removeMedicalRecord(record);
        System.out.println("Medical record removed successfully: " + record.getRecordId());
    }

    public MedicalRecord findMedicalRecordById(String recordID){
        if (recordID == null) {
            throw new IllegalArgumentException("Record ID is null");
        }
        return hospital.findMedicalRecordById(recordID);
    }

    public List<MedicalRecord> viewAllMedicalRecords() {
        return new ArrayList<>(hospital.getAllMedicalRecords());
    }
}
