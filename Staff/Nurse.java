

class Nurse extends MedicalProfessional {
    private String assignedDoctor;
    private String shift;
    private String duties;
    private static int nurseNumber = 1;
    
    public Nurse(String id, String name, String address, String phoneNumber, 
                String email, String emergencyContact, String dateOfBirth, 
                char gender, double salary, String joinDate, 
                String department, String qualification, String assignedDoctor,
                String shift, String duties) {
        super(id, name, address, phoneNumber, email, emergencyContact, dateOfBirth, 
              gender, "N" + nurseNumber, salary, joinDate, department, qualification);
        this.assignedDoctor = assignedDoctor;
        this.shift = shift;
        this.duties = duties;

        nurseNumber++;
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
