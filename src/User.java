public abstract class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String dateOfBirth;
    private String phoneNumber;
    private String gender;
    private String address;
    private int age;

    public User() {
    }

    public User(String firstName, String lastName, String emailAddress, String password,
                String phoneNumber, String dateOfBirth,String gender, String address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.dateOfBirth=dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.age = age;
    }


    // Common methods
    public String getFullName() {
        return firstName + " " + lastName;
    }


    // Getters and setters for common fields
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    // Abstract method to be implemented by subclasses
    public abstract void showDetails();
}


