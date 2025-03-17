// HospitalManagementSystem.java
import java.util.Scanner;

public class HospitalManagementSystem {
    private static Hospital hospital = new Hospital("City Hospital", "123 Main St", "555-1234", "contact@cityhospital.com");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize with sample data for testing
        initializeSampleData();

        // Main loop for user interaction
        while (true) {
            displayMenu();
            int choice = getChoice();
            processChoice(choice);
        }
    }

    private static void initializeSampleData() {
        // Add a sample doctor
        Doctor doctor = new Doctor("D001", "Dr. Smith", "456 Oak St", "555-5678", "smith@hospital.com",
                "555-9012", "1970-01-01", 'M', "EMP001", 100000.0, "2020-01-01",
                "Cardiology", "MD", "Cardiologist", 15);
        hospital.addDoctor(doctor);
    }

    private static void displayMenu() {
        System.out.println("\n=== Hospital Management System ===");
        System.out.println("1. Add Patient");
        System.out.println("2. Schedule Appointment");
        System.out.println("3. Add Medical Record");
        System.out.println("4. Cancel Appointment");
        System.out.println("5. View Patient Details");
        System.out.println("6. View All Patients");
        System.out.println("7. View All Appointments");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
            displayMenu();
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    private static void processChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    addMedicalRecord();
                    break;
                case 4:
                    cancelAppointment();
                    break;
                case 5:
                    viewPatientDetails();
                    break;
                case 6:
                    viewAllPatients();
                    break;
                case 7:
                    viewAllAppointments();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addPatient() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Emergency Contact: ");
        String emergencyContact = scanner.nextLine();
        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        String dob = scanner.nextLine();
        System.out.print("Enter Gender (M/F): ");
        char gender = scanner.nextLine().charAt(0);
        System.out.print("Enter Blood Group: ");
        String bloodGroup = scanner.nextLine();
        System.out.print("Enter Admit Date (yyyy-MM-dd): ");
        String admitDate = scanner.nextLine();
        System.out.print("Enter Insurance Info: ");
        String insuranceInfo = scanner.nextLine();

        Patient patient = new Patient("P" + patientId, name, address, phone, email, emergencyContact, dob, gender,
                patientId, bloodGroup, admitDate, null, insuranceInfo);
        hospital.addPatient(patient);
    }

    private static void scheduleAppointment() {
        System.out.print("Enter Appointment ID: ");
        String apptId = scanner.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = hospital.findPatientById(patientId);
        if (patient == null) {
            System.out.println("Error: Patient not found with ID: " + patientId);
            return;
        }
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        Doctor doctor = hospital.findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Error: Doctor not found with ID: " + doctorId);
            return;
        }
        System.out.print("Enter Date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter Time (HH:mm): ");
        String time = scanner.nextLine();
        System.out.print("Enter Consultation Room: ");
        String room = scanner.nextLine();
        System.out.print("Enter Purpose: ");
        String purpose = scanner.nextLine();

        // Check doctor availability manually (since no isDoctorAvailable method exists)
        boolean isAvailable = true;
        for (Appointment appt : doctor.getAppointments()) {
            if (appt.getDate().equals(date) && appt.getTime().equals(time) && appt.getStatus().equals("Scheduled")) {
                isAvailable = false;
                break;
            }
        }
        if (!isAvailable) {
            System.out.println("Error: Doctor is not available at " + date + " " + time);
            return;
        }

        Appointment appointment = new Appointment(apptId, patient, doctor, date, time, room, purpose);
        hospital.addAppointment(appointment);
    }

    private static void addMedicalRecord() {
        System.out.print("Enter Record ID: ");
        String recordId = scanner.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = hospital.findPatientById(patientId);
        if (patient == null) {
            System.out.println("Error: Patient not found with ID: " + patientId);
            return;
        }
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        Doctor doctor = hospital.findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Error: Doctor not found with ID: " + doctorId);
            return;
        }
        System.out.print("Enter Date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();
        System.out.print("Enter Prescription: ");
        String prescription = scanner.nextLine();
        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();
        System.out.print("Enter Follow-up Date (yyyy-MM-dd, or press Enter for none): ");
        String followUpDate = scanner.nextLine();
        if (followUpDate.isEmpty()) followUpDate = null;

        MedicalRecord record = new MedicalRecord(recordId, patient, doctor, date, diagnosis, treatment, prescription, notes, followUpDate);
        patient.addMedicalRecord(record); // Add to patient's history
        hospital.addMedicalRecord(record); // Add to hospital's records
    }

    private static void cancelAppointment() {
        System.out.print("Enter Appointment ID to cancel: ");
        String apptId = scanner.nextLine();
        Appointment appt = hospital.findAppointmentById(apptId);
        if (appt == null) {
            System.out.println("Error: Appointment not found with ID: " + apptId);
            return;
        }
        appt.cancelAppointment();
        hospital.removeAppointment(appt);
    }

    private static void viewPatientDetails() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = hospital.findPatientById(patientId);
        if (patient == null) {
            System.out.println("Error: Patient not found with ID: " + patientId);
            return;
        }
        System.out.println("\n=== Patient Details ===");
        System.out.println(patient.toString());
        System.out.println("\nMedical History:");
        for (MedicalRecord record : patient.getMedicalHistory()) {
            System.out.println(record.toString());
        }
        System.out.println("\nAppointments:");
        for (Appointment appt : patient.getAppointments()) {
            System.out.println(appt.toString());
        }
    }

    private static void viewAllPatients() {
        hospital.displayHospitalInfo();
        if (hospital.getPatients().isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("\n=== List of Patients ===");
            for (Patient patient : hospital.getPatients()) {
                System.out.println(patient.getDetails());
            }
        }
    }

    private static void viewAllAppointments() {
        if (hospital.getAppointments().isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("\n=== List of Appointments ===");
            for (Appointment appt : hospital.getAppointments()) {
                System.out.println(appt.toString());
            }
        }
    }
}
