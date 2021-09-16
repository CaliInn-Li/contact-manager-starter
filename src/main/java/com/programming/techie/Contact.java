package com.programming.techie;

import org.junit.platform.commons.util.StringUtils;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void validateFirstName() {
        if (StringUtils.isBlank(this.firstName))
            throw new RuntimeException("First Name Cannot be null or empty");
    }

    public void validateLastName() {
        if (StringUtils.isBlank(this.lastName))
            throw new RuntimeException("Last Name Cannot be null or empty");
    }

    public void validatePhoneNumber() {
        if (StringUtils.isBlank(this.phoneNumber)) {
            throw new RuntimeException("Phone Name Cannot be null or empty");
        }

        if (this.phoneNumber.length() <= 10) {
            throw new RuntimeException("Phone Number Should be more than 10 Digits Long");
        }
        if (!this.phoneNumber.matches("\\d+")) {
            throw new RuntimeException("Phone Number Contain only digits");
        }
        if (this.phoneNumber.startsWith("0")) {
            throw new RuntimeException("Phone Number Should not Start with 0");
        }
    }
}

