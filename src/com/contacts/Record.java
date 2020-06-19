package com.contacts;



import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Record {
    private String name;
    private String phoneNumber;
    public final LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime lastEditDate = LocalDateTime.now();


    Record(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber ;
    }

    abstract public void printAllFields();
    abstract public void setField(String fieldName, String newValue);
    abstract public String getRecordAsString();
    abstract public String getShortInfo();

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber =  phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setLastEditDate(    ) {
        this.lastEditDate = LocalDateTime.now();
    }

    public LocalDateTime getLastEditDate() {
        return lastEditDate;
    }

    public boolean hasNumber() {
        return (!this.phoneNumber.isEmpty());
    }

    /*protected static boolean checkValidityOfPhoneNumber(String phoneNumber) {
        String spitedGroupsRegex = "\\+?((\\(?[a-zA-Z\\d]+\\)?)?([\\s-][a-zA-Z\\d]{2,})?|([a-zA-Z\\d]+[\\s-])?(\\(?[a-zA-Z\\d]{2,}\\)?)?)(([\\s-][a-zA-Z\\d]{2,})+)?";
        String oneGroupRegex = "\\+?(\\(?[a-zA-Z\\d]+\\)?[a-zA-Z\\d]+|[a-zA-Z\\d]+\\(?[a-zA-Z\\d]+\\)?)([a-zA-Z\\d]+)+";
        Pattern pattern = Pattern.compile(spitedGroupsRegex + "|" + oneGroupRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }*/

//    abstract public String visualizeRecord();
//    abstract public String getNameForList();
//    abstract public String getRecordAsString();


}



