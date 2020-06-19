package com.contacts;

import java.time.LocalDateTime;

public class Organization extends Record {

    private String address;

    Organization(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void printAllFields() {
        System.out.println("Organization name: " + this.getName());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Number: " + this.getPhoneNumber());
        System.out.println("Time created: " + this.createDate);
        System.out.println("Time last edit: " + this.getLastEditDate());
    }

    @Override
    public void setField(String field, String newValue) {
        switch (field) {
            case "name" :
                setName(newValue);
                break;
            case "address" :
                setAddress(newValue);
                break;
            case "number":
                setPhoneNumber(newValue);
                break;
        }
        setLastEditDate();
    }

    @Override
    public String getRecordAsString() {
        return getName() + " " + getAddress() + " " +  getPhoneNumber();
    }

    @Override
    public String getShortInfo() {
        return getName();
    }
}
