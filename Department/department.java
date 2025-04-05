
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
