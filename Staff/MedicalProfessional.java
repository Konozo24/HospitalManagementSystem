public class MedicalProfessional extends Person {
    private String employeeId;
    private double salary;
    private String joinDate;
    private String department;
    private String qualification;

    // Constructor
    public MedicalProfessional(String id, String name, int age, String gender, String employeeId, double salary, String joinDate, String department, String qualification) {
        super(id, name, age, gender);
        this.employeeId = employeeId;
        this.salary = salary;
        this.joinDate = joinDate;
        this.department = department;
        this.qualification = qualification;
    }
    public String toString() {
        return "Medical Professional: " + name + 
               "\nEmployee ID: " + employeeId + 
               "\nSalary: $" + salary + 
               "\nJoin Date: " + joinDate + 
               "\nDepartment: " + department + 
               "\nQualification: " + qualification;
    }

    public String getDepartment(){
        return this.department;
    }
}
