package com.contacts;
import java.time.LocalDate;

public class Person extends Record {
    private String surname;
    private String gender;
    private String birthDate;

    public Person(String name, String surname, String phoneNumber, String birthDate, String gender) {
        super(name, phoneNumber);
        this.surname = surname;
        this.birthDate = birthDate ;
        this.gender = gender;
    }

    /*private boolean checkBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }*/

    /*private String checkGender(String gender) {
        if (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Bad gender!");
            return "[no data]";
        } else {
            return gender;
        }
    }*/

    @Override
    public void setField(String fieldName, String newValue) {
        switch (fieldName) {
            case "name" :
                this.setName(newValue);
                break;
            case "surname" :
                this.setSurname(newValue);
                break;
            case "birth":
                this.setBirthDate(newValue);
                break;
            case "gender" :
                this.setGender(newValue);
                break;
            case "number" :
                this.setPhoneNumber(newValue);
        }
    }

    @Override
    public void printAllFields() {
        System.out.println("Name: " + this.getName());
        System.out.println("Surname: " + this.getSurname() );
        System.out.println("Birth date: " + this.getBirthDate());
        System.out.println("Gender: "+ this.getGender());
        System.out.println("Number: " + this.getPhoneNumber());
        System.out.println("Time created: " + this.createDate);
        System.out.println("Time last edit: "+ this.getLastEditDate());

    }

    @Override
    public String getRecordAsString() {
        return getName() + " " + getSurname() + " " + getBirthDate() + " " + getGender()  + " " + getPhoneNumber();
    }

    @Override
    public String getShortInfo() {
        return getName()+ " " + getSurname();
    }
    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
