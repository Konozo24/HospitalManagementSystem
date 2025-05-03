


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
        if (!(obj instanceof Person)) {return false;}
        Person other = (Person) obj; 
        return this.id != null && this.id.equals(other.id);
    }
}
