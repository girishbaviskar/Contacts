package com.contacts;
import java.io.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    ArrayList <Record> list;
    Scanner scanner = new Scanner(System.in);
    transient private final String fileName;
    String action;

    public PhoneBook(){

        fileName="default";
        list = new ArrayList<>();
    }

    public PhoneBook(String fileName) {
        this.fileName = fileName;
        this.list = new ArrayList<>();
    }

    public void init() {

        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            action = scanner.nextLine();
            switch (action) {
                case "add" :
                    addRecord();
                    break;
                case "list" :
                    displayList();
                    while (true) {
                        if (action.equals("menu")) {
                            break;
                        }
                        System.out.print("[list] Enter action ([number], back): ");
                        action = scanner.nextLine();
                        if (action.equals("back")) {
                            break;
                        } else {
                            int index = Integer.parseInt(action);
                            infoElements(index);
                            while (!action.equals("menu")) {
                                System.out.print("[record] Enter action (edit, delete, menu): ");
                                action = scanner.nextLine();
                                switch (action) {
                                    case "edit" :
                                        editRecord(index);
                                        break;
                                    case "delete" :
                                        removeRecord(index);
                                        action = "menu";
                                        break;
                                    case "menu" :
                                        System.out.println();
                                        break;
                                }
                            }
                        }
                    }

                    break;
                case "search" :
                    searchRecords();
                    while (true) {
                        if (action.equals("menu")) {
                            break;
                        }
                        System.out.print("[search] Enter action ([number], back, again): ");
                        action = scanner.nextLine();
                        if (action.equals("back")) {
                            break;
                        } else if(action.equals("again")) {
                            searchRecords();
                        } else {
                            int index = Integer.parseInt(action);
                            infoElements(index);
                            while (!action.equals("menu")) {
                                System.out.print("[record] Enter action (edit, delete, menu): ");
                                action = scanner.nextLine();
                                switch (action) {
                                    case "edit" :
                                        editRecord(index);
                                        break;
                                    case "delete" :
                                        removeRecord(index);
                                        action = "menu";
                                        break;
                                    case "menu" :
                                        System.out.println();
                                        break;
                                }
                            }
                        }
                    }
                case "count":
                    countRecords();
                    break;
                case "exit" :
                    System.exit(0);

            }
        }

    }

    public void addRecord() {
        System.out.print("Enter the type (person, organization): ");
        String typeOfRecord = scanner.nextLine().toLowerCase();
        switch (typeOfRecord) {
            case "person" :
                addPerson();
                break;
            case "organization":
                addOrganization();
                break;
            default:
                System.out.println("Invalid choice");
        }
        save();
    }
    public void addPerson() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the birth date: ");
        String birthDate = scanner.nextLine();
        birthDate = checkBirthDate(birthDate);
        System.out.print("Enter gender (M, F): ");
        String gender = scanner.nextLine();
        gender = checkGender(gender);
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        number = checkValidityOfPhoneNumber(number);
        Record person = new Person(name, surname, number, birthDate ,gender);
        list.add(person);
        System.out.println("A record added.");
        System.out.println();

    }

    public void addOrganization() {
        System.out.print("Enter the organization name: ");
        String name= scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        number = checkValidityOfPhoneNumber(number);

        Record organization = new Organization(name, number, address);
        list.add(organization);
        System.out.println("A record added!");
        System.out.println();
    }

    public void searchRecords() {

        System.out.print("Enter search query: ");
        String query = "(?i).*" + scanner.nextLine() + ".*";
        Pattern pattern = Pattern.compile(query);
        ArrayList<Record> searchResult = new ArrayList<>();
        for (Record record : list) {
            if(pattern.matcher(record.getRecordAsString()).matches()) {
                searchResult.add(record);
            }
        }
        System.out.printf("Found %d result%s: \n", searchResult.size(), searchResult.size() > 1 ? "s" : "");
        int count = 1;
        for (Record record: searchResult) {
            System.out.printf("%d. %s\n", count, record.getShortInfo());
        }
    }
    public void removeRecord(int index) {
        if (list.size() == 0) {
            System.out.println("No records to remove!");
            return;
        }
        list.remove(index - 1);
        System.out.println("Record Removed!");
        System.out.println();
    }

    public void editRecord(int index) {
        if(list.size() == 0) {
            System.out.println("No records to edit");
            System.out.println("");
            return;
        }

        Record record = list.get(index - 1);

        if (record.getClass() == Person.class) {
            System.out.print("Select a field (name, surname, birth, gender, number): ");
            String field = scanner.nextLine();
            System.out.print("Enter new "+ field  + ": ");
            String newValue = scanner.nextLine();
            record.setField(field, newValue);
        } else {
            System.out.print("Select a field (name, address, number): ");
            String field = scanner.nextLine();
            System.out.print("Enter new "+ field + ": ");
            String newValue = scanner.nextLine();
            record.setField(field, newValue);

        }
        System.out.println("The record updated!");
        record.setLastEditDate();
        infoElements(index);
        save();

    }
    public void countRecords() {
        System.out.println("The Phone Book has " + list.size() + " records.");
        System.out.println();
    }
    public void displayList() {
        int i = 1;
        if (list.size() == 0) {
            System.out.println("We have no elements");
            System.out.println();
            action = "menu";
            return;
        }
        for (Record record : list) {
            if (record.getClass() == Person.class) {
                System.out.println(i++ + ". " + record.getName() +" " +((Person) record).getSurname());
            } else {
                System.out.println(i++ + ". " + record.getName());
            }
        }
        System.out.println();
    }
    public void infoElements(int index) {
        list.get(index - 1).printAllFields();
        System.out.println();
    }
    private String checkGender(String gender) {
        if (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Bad gender!");
            return "[no data]";
        } else {
            return gender;
        }
    }

    private String checkValidityOfPhoneNumber(String phoneNumber) {
        String splittedGroupsRegex = "\\+?((\\(?[a-zA-Z\\d]+\\)?)?([\\s-][a-zA-Z\\d]{2,})?|([a-zA-Z\\d]+[\\s-])?(\\(?[a-zA-Z\\d]{2,}\\)?)?)(([\\s-][a-zA-Z\\d]{2,})+)?";
        String oneGroupRegex = "\\+?(\\(?[a-zA-Z\\d]+\\)?[a-zA-Z\\d]+|[a-zA-Z\\d]+\\(?[a-zA-Z\\d]+\\)?)([a-zA-Z\\d]+)+";
        Pattern pattern = Pattern.compile(splittedGroupsRegex + "|" + oneGroupRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }
    private String checkBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
            return birthDate;
        } catch (Exception e) {
            System.out.println("Bad birth date!");
            return "[no data]";
        }
    }

    public boolean load( )  {
        System.out.println("open "+fileName+"\n");
        try( FileInputStream fileInputStream = new FileInputStream(new File(fileName));
             BufferedInputStream bufferedInputStream = new BufferedInputStream((fileInputStream));
             ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        ){
            list = (ArrayList<Record>) objectInputStream.readObject();



        }
        catch(IOException e){

            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public boolean save( ){
        try(
                FileOutputStream fileOutputStream =new FileOutputStream(new File(fileName));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream os = new ObjectOutputStream(bufferedOutputStream);
        )
        {

            os.writeObject(list);
        }
        catch (IOException e){
            return false;
        }
        return true;
    }
}
