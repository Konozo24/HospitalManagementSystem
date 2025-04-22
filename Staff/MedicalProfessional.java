package Assignment;

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
