package progkorny.boatrentalweb01.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Customer {
    @Id private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String driverLicenseNumber;
    private String countryCode;

    public Customer() {}

    public static Builder builder() {
        return new Builder();
    }

    private Customer(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.driverLicenseNumber = builder.driverLicenseNumber;
        this.countryCode = builder.countryCode;
    }



    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String driverLicenseNumber;
        private String countryCode;

        public Builder id(int id) {this.id=id;return this;}
        public Builder firstName(String firstName) {this.firstName=firstName;return this;}
        public Builder lastName(String lastName) {this.lastName=lastName;return this;}
        public Builder email(String email) {this.email=email;return this;}
        public Builder phoneNumber(String phoneNumber) {this.phoneNumber=phoneNumber;return this;}
        public Builder driverLicenseNumber(String driverLicenseNumber) {this.driverLicenseNumber=driverLicenseNumber;return this;}
        public Builder countryCode(String countryCode) {this.countryCode=countryCode;return this;}
        public Customer build() {return new Customer(this);}
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}

