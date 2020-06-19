package com.contacts;


import javax.crypto.SealedObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PhoneBook phoneBook;
        if (args.length == 0){
            phoneBook = new PhoneBook();

        }
        else {
            String fileName=args[0];
            phoneBook = new PhoneBook(fileName);
            phoneBook.load();
        }
        phoneBook.init();

    }
}
