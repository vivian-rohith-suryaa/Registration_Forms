package com.forms.user;

public class User {
    private int userId;
    private String firstName, lastName, dob, gender, aadhar, pan, email, phone, address1, address2, district, state, country, pincode, password;
    
    public User() {}
    
    public User(int userId, String firstName, String lastName, String dob, String gender, String aadhar, String pan,
                String email, String phone, String address1, String address2, String district, String state,
                String country, String pincode, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.aadhar = aadhar;
        this.pan = pan;
        this.email = email;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.password = password;
    }

    public int getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getAadhar() { return aadhar; }
    public String getPan() { return pan; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress1() { return address1; }
    public String getAddress2() { return address2; }
    public String getDistrict() { return district; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getPincode() { return pincode; }
    public String getPassword() { return password; }
}
