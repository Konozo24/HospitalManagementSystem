

public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;
    private String consultationRoom;
    private String purpose;
    private String status; // Scheduled, Completed, Cancelled
    private static int appointmentNumber = 1;
    
    public Appointment(Patient patient, Doctor doctor, 
                      String date, String time, String consultationRoom, 
                      String purpose) {
        this.appointmentId = "A" + appointmentNumber;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.consultationRoom = consultationRoom;
        this.purpose = purpose;
        this.status = "Scheduled";
        appointmentNumber++;

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
