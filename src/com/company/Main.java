package com.company;

import com.sun.crypto.provider.PBEWithMD5AndDESCipher;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int[] processCount = {0};
    static int[] addCount = {-1}, updateCount = {-1}, deleteCount = {-1};
    static Scanner strInput = new Scanner(System.in);
    public static Library[] allRecordsInStart;
    public static String[] lastProcess;
    public static Library[] addedRecords = new Library[15];
    public static Library[] updatedRecords = new Library[15];
    public static String[] updateKeys= new String[15];
    public static String[] deleteKeys= new String[15];


    public static void main(String[] args) {

        lastProcess = new String[5];

        try {
            File file = new File("lib.txt");
            if (file.exists()) {
                allRecordsInStart = Library.getAllRecords();
                Library.deleteFile();
            } else {
                Library.deleteFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String choice, cont = "y";

        while (cont.equalsIgnoreCase("y")) {
            try {

                displayIntro();

                System.out.print("\n\n");
                System.out.println("Enter your choice: ");
                choice = strInput.nextLine();

                switch (choice) {
                    case "1":

                        if (checkProcessCount(addedRecords, updatedRecords, deleteKeys, updateKeys)) {
                            addCount[0] = -1;
                            updateCount[0] = -1;
                            deleteCount[0] = -1;
                            processCount[0] = 0;
                        }
                        addCount[0]++;
                        addedRecords[addCount[0]] = new Library();
                        addRecordToQueue(addedRecords[addCount[0]]);
                        lastProcess[processCount[0]] = "add";
                        processCount[0]++;


                        break;
                    case "2":

                        //delete record

                        if (checkProcessCount(addedRecords, updatedRecords, deleteKeys, updateKeys)) {
                            addCount[0] = -1;
                            updateCount[0] = -1;
                            deleteCount[0] = -1;
                            processCount[0] = 0;
                        }
                        deleteCount[0]++;
                        deleteKeys[deleteCount[0]] = deleteRecordToQueue();
                        lastProcess[processCount[0]] = "delete";
                        processCount[0]++;

                        break;
                    case "3":

                        //update record
                        if (checkProcessCount(addedRecords, updatedRecords, deleteKeys, updateKeys)) {
                            addCount[0] = -1;
                            updateCount[0] = -1;
                            deleteCount[0] = -1;
                            processCount[0] = 0;
                        }
                        updateCount[0]++;
                        updatedRecords[updateCount[0]] = new Library();
                        updateKeys[updateCount[0]] = updateRecordToQueue(updatedRecords[updateCount[0]]);
                        lastProcess[processCount[0]] = "update";
                        processCount[0]++;

                        break;
                    case "4":

                        //find record
                        findDataToShow();
                        int n = strInput.nextInt();
                        System.out.println("Enter the Value to Search : ");
                        String key = strInput.next();
                        Library.SearchRecordLinear(n, key);

                        break;
                    case "5":
                        //save record
                        Library.saveToFile(addedRecords, updatedRecords, deleteKeys, updateKeys
                                , addCount[0], updateCount[0], deleteCount[0]);
                        addCount[0] = -1;
                        updateCount[0] = -1;
                        deleteCount[0] = -1;
                        processCount[0] = 0;
                        break;
                    case "6":
                        //display all record
                        Library.ViewAllRecord();

                        break;
                    case "7":

                        // undo
                        undo();

                        break;
                    case "8":

                        //redo
                        redo();

                        break;
                    case "9":

                        //display most popular 5
                        Library.printTopFivePopularBooks();

                        break;
                    case "10":
                        Library.saveToFile(addedRecords, updatedRecords, deleteKeys, updateKeys
                                , addCount[0], updateCount[0], deleteCount[0]);

                        System.exit(1);

                }

                System.out.println("Do you want to continue? Y/N");
                cont = strInput.nextLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Library.saveToFile(addedRecords, updatedRecords, deleteKeys ,updateKeys
                    , addCount[0], updateCount[0] , deleteCount[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkProcessCount(Library[] addedRecords, Library[] updatedRecords ,String[] deletedRecords,String[] updatedkeys) throws IOException {

        if (processCount[0] == 5) {

            Library.saveToFile(addedRecords, updatedRecords, deletedRecords ,updatedkeys
                    , addCount[0], updateCount[0] , deleteCount[0]);

            System.out.println("you have done more than 5 process , i save the last five in file");

            return true;
        } else {
            return false;
        }

    }

    public static void displayIntro() {
        System.out.println("\t\t Library System\n\n");


        System.out.println("1 ===> Add New Book ");
        System.out.println("2 ===> Delete Book ");
        System.out.println("3 ===> Update Book ");
        System.out.println("4 ===> Find Book");
        System.out.println("5 ===> Save to file ");
        System.out.println("6 ===> Display all Books sorted");
        System.out.println("7 ===> Undo for last operation");
        System.out.println("8 ===> Redo for last undo. ");
        System.out.println("9 ===> Display the most 5 popular Books (the popularity of any Books ");
        System.out.println("10 ===> Quit.");


    }

    public static void addRecordToQueue(Library lib) {

        System.out.print("Enter the isbn: ");
        lib.setIsbn(strInput.nextLine());
        System.out.print("Enter the book_name: ");
        lib.setBook_name(strInput.nextLine());
        System.out.print("Enter the author_name: ");
        lib.setAuthor_name(strInput.nextLine());
        System.out.print("Enter the publisher_name: ");
        lib.setPublisher_name(strInput.nextLine());
        System.out.print("Enter the year_of_publishing: ");
        lib.setYear_of_publishing(Integer.parseInt(strInput.nextLine()));
        System.out.print("Enter the edition_number: ");
        lib.setEdition_number(Integer.parseInt(strInput.nextLine()));
    }

    public static String updateRecordToQueue(Library lib) {

        System.out.print("Enter the isbn to Update: ");
        String updateKey = strInput.nextLine();


        System.out.print("Enter the isbn: ");
        lib.setIsbn(strInput.nextLine());
        System.out.print("Enter the book_name: ");
        lib.setBook_name(strInput.nextLine());
        System.out.print("Enter the author_name: ");
        lib.setAuthor_name(strInput.nextLine());
        System.out.print("Enter the publisher_name: ");
        lib.setPublisher_name(strInput.nextLine());
        System.out.print("Enter the year_of_publishing: ");
        lib.setYear_of_publishing(Integer.parseInt(strInput.nextLine()));
        System.out.print("Enter the edition_number: ");
        lib.setEdition_number(Integer.parseInt(strInput.nextLine()));

        return updateKey;
    }
    public static String deleteRecordToQueue() {

        System.out.print("Enter the isbn: ");
        return strInput.nextLine();
    }

    public static void findDataToShow() {

        System.out.println("1 ===> Book_Name");
        System.out.println("2 ===> Auther_Name");
        System.out.println("3 ===> Year_of_Publishing");
        System.out.println("4 ===> ISBN");

        System.out.println("Enter the number : ");

    }

    public static void undo(){

        switch (lastProcess[processCount[0]-1]){

            case "add" :
                addCount[0]--;
                processCount[0]--;
                break;
            case "delete" :
                deleteCount[0]--;
                processCount[0]--;
                break;
            case "update" :
                updateCount[0]--;
                processCount[0]--;
                break;
            default:
                System.out.println("there is no operation !!!");
                break;

        }

    }
    public static void redo(){
        try {
            switch (lastProcess[processCount [0]+ 1]) {

                case "add":
                    addCount[0]++;
                    processCount[0]++;
                    break;
                case "delete":
                    deleteCount[0]++;
                    processCount[0]++;
                    break;
                case "update":
                    updateCount[0]++;
                    processCount[0]++;
                    break;
                default:
                    System.out.println("there is no operation !!!");
                    break;

            }
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
