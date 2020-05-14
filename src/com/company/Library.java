package com.company;

import org.omg.CORBA.MARSHAL;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Library {

    private String isbn;
    private String book_name;
    private String author_name;
    private String publisher_name;
    private int year_of_publishing;
    private int edition_number;
    private int popularity;

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Library() {
    }

    public Library(String isbn, String book_name, String author_name, String publisher_name, int year_of_publishing, int edition_number) {
        this.isbn = isbn;
        this.book_name = book_name;
        this.author_name = author_name;
        this.publisher_name = publisher_name;
        this.year_of_publishing = year_of_publishing;
        this.edition_number = edition_number;
    }

    public static void AddRecord(Library record) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("lib.txt", true));
        bw.write(record.getIsbn() + "," + record.getBook_name() + "," + record.getAuthor_name()
                + "," + record.getPublisher_name() + "," + record.getYear_of_publishing() + ","
                + record.getEdition_number() + "," + record.getPopularity());
        bw.flush();
        bw.newLine();
        bw.close();

    }

    public static void ViewAllRecord(){
        Scanner input = new Scanner(System.in);
        Library[] alldata;

        System.out.println("Display Records sorted by Book_name press 1 ");
        System.out.println("Display Records sorted by Year of publish press 2 ");

        System.out.println("Your Choice : ===> ");
        String sortedBy = input.nextLine();

        System.out.println("Display Records sorted Asc 1 ");
        System.out.println("Display Records sorted Desc 2 ");

        System.out.println("Your Choice : ===> ");
        String sortedWith = input.nextLine();

        System.out.println(" ---------------------------------------------------------------------------------------------- ");
        System.out.println("| ISBN\tbook_name\tauthor_name\tpublisher_name\tyear_of_publishing\tedition_number |");
        System.out.println(" ---------------------------------------------------------------------------------------------- ");


        if (sortedBy.equals("1")) {

            if (sortedWith.equals("1")) {

                //sort by book name and Asc
                alldata = sortByName(Main.allRecordsInStart);
                for (Library alldatum : alldata) {

                    System.out.println("| " + alldatum.getIsbn() + "\t" + alldatum.getBook_name()
                            + "\t" + alldatum.getAuthor_name() + "\t" + alldatum.getPublisher_name() + "\t"
                            + alldatum.getYear_of_publishing() + "\t" + alldatum.getEdition_number());

                }


            } else if (sortedWith.equals("2")) {

                //sort by book name and Desc
                alldata = sortByName(Main.allRecordsInStart);

                for (int i = alldata.length - 1; i >= 0; i--) {

                    System.out.println("| " + alldata[i].getIsbn() + "\t" + alldata[i].getBook_name()
                            + "\t" + alldata[i].getAuthor_name() + "\t" + alldata[i].getPublisher_name() + "\t"
                            + alldata[i].getYear_of_publishing() + "\t" + alldata[i].getEdition_number());

                }
            }

        } else if (sortedBy.equals("2")) {

            if (sortedWith.equals("1")) {

                //sort by year and Asc
                alldata = sortByYear(Main.allRecordsInStart);
                for (Library alldatum : alldata) {

                    System.out.println("| " + alldatum.getIsbn() + "\t" + alldatum.getBook_name()
                            + "\t" + alldatum.getAuthor_name() + "\t" + alldatum.getPublisher_name() + "\t"
                            + alldatum.getYear_of_publishing() + "\t" + alldatum.getEdition_number());

                }

            } else if (sortedWith.equals("2")) {

                //sort by year and Desc
                alldata = sortByYear(Main.allRecordsInStart);
                for (int i = alldata.length - 1; i >= 0; i--) {

                    System.out.println("| " + alldata[i].getIsbn() + "\t" + alldata[i].getBook_name()
                            + "\t" + alldata[i].getAuthor_name() + "\t" + alldata[i].getPublisher_name() + "\t"
                            + alldata[i].getYear_of_publishing() + "\t" + alldata[i].getEdition_number());

                }
            }

        }

        System.out.println("|	                                            	          |");
        System.out.println(" ------------------------------------------------------------- ");

    }

    public static void printDataRecord(Library record) {

        System.out.println(" ---------------------------------------------------------------------------------------------- ");
        System.out.println("| ISBN\tbook_name\tauthor_name\tpublisher_name\tyear_of_publishing\tedition_number |");
        System.out.println(" ---------------------------------------------------------------------------------------------- ");

        System.out.println("| " + record.getIsbn() + "\t\t" + record.getBook_name()
                + "\t\t" + record.getAuthor_name() + "\t\t" + record.getPublisher_name() + "\t\t"
                + record.getYear_of_publishing() + "\t\t" + record.getEdition_number());
        record.setPopularity((record.getPopularity() + 1));

        updatedDataWithPopularity(record);


        System.out.println("|	                                            	          |");
        System.out.println(" ------------------------------------------------------------- ");


    }

    public static void printDataRecords(Library[] records) {

        System.out.println(" ---------------------------------------------------------------------------------------------- ");
        System.out.println("| ISBN\tbook_name\tauthor_name\tpublisher_name\tyear_of_publishing\tedition_number |");
        System.out.println(" ---------------------------------------------------------------------------------------------- ");

        for (Library record : records) {

            System.out.println("| " + record.getIsbn() + "\t\t" + record.getBook_name()
                    + "\t\t" + record.getAuthor_name() + "\t\t" + record.getPublisher_name() + "\t\t"
                    + record.getYear_of_publishing() + "\t\t" + record.getEdition_number());
        }


        System.out.println("|	                                            	          |");
        System.out.println(" ------------------------------------------------------------- ");


    }

    public static Library[] updatedDataWithPopularity(Library record) {

        Library[] allRecords = sortByName(Main.allRecordsInStart);

        for (int i = 0; i < allRecords.length; i++) {

            if (allRecords[i].getIsbn().equals(record.getIsbn())
                    && allRecords[i].getBook_name().equals(record.getBook_name())
                    && allRecords[i].getPublisher_name().equals(record.getPublisher_name())
                    && allRecords[i].getAuthor_name().equals(record.getAuthor_name())) {
                allRecords[i] = record;

            }
        }
        Main.allRecordsInStart = allRecords;

        return allRecords;

    }

    public static Library[] sortByName(Library[] allData) {

        String temp;
        Library tempObj;

        String[] bookNames = new String[allData.length];

        for (int i = 0; i < allData.length; i++) {
            //set names to array
            bookNames[i] = allData[i].getBook_name();

        }


        for (int i = 0; i < bookNames.length; i++) {
            for (int j = i + 1; j < bookNames.length; j++) {
                if (bookNames[i].compareTo(bookNames[j]) > 0) {
                    // sort names
                    temp = bookNames[i];
                    bookNames[i] = bookNames[j];
                    bookNames[j] = temp;
                    // sort all data by name
                    tempObj = allData[i];
                    allData[i] = allData[j];
                    allData[j] = tempObj;
                }
            }
        }

        return allData;

    }

    public static Library[] getAllRecords() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("lib.txt"));

        File myObj = new File("lib.txt");
        Scanner lineNo = new Scanner(myObj);
        int fileLinesNo = 0;
        while (lineNo.hasNextLine()) {
            fileLinesNo++;
            lineNo.nextLine();
        }

        Library[] allData = new Library[fileLinesNo];

        String record;


        for (int i = 0; (record = br.readLine()) != null; i++) {

            StringTokenizer st = new StringTokenizer(record, ",");

            //set All data from file to object
            allData[i] = new Library();
            allData[i].setIsbn(st.nextToken());
            allData[i].setBook_name(st.nextToken());
            allData[i].setAuthor_name(st.nextToken());
            allData[i].setPublisher_name(st.nextToken());
            allData[i].setYear_of_publishing(Integer.parseInt(st.nextToken()));
            allData[i].setEdition_number(Integer.parseInt(st.nextToken()));
            allData[i].setPopularity(Integer.parseInt(st.nextToken()));
        }
        br.close();
        return allData;

    }

    public static Library[] sortByYear(Library[] allData) {
        int temp;
        Library tempObj;
        int[] year = new int[allData.length];

        String record;


        for (int i = 0; i < allData.length; i++) {
            //set names to array
            year[i] = allData[i].getYear_of_publishing();

        }


        for (int i = 0; i < allData.length; i++) {
            for (int j = i + 1; j < allData.length; j++) {
                if (year[i] > year[j]) {
                    temp = year[i];
                    year[i] = year[j];
                    year[j] = temp;

                    tempObj = allData[i];
                    allData[i] = allData[j];
                    allData[j] = tempObj;
                }
            }
        }
        return allData;

    }

    public static void DeleteRecordByID() {

    }

    public static void printTopFivePopularBooks(){

        Library[] allRecords = sortByName(Main.allRecordsInStart);
        Library tempObj;
        int temp;
        int[] mostFive = new int[allRecords.length];
        for (int i = 0; i < allRecords.length; i++) {
            mostFive[i] = allRecords[i].getPopularity();
        }

        for (int i = 0; i < mostFive.length; i++) {
            for (int j = i + 1; j < mostFive.length; j++) {
                if (mostFive[i] > mostFive[j]) {
                    temp = mostFive[i];
                    mostFive[i] = mostFive[j];
                    mostFive[j] = temp;

                    tempObj = allRecords[i];
                    allRecords[i] = allRecords[j];
                    allRecords[j] = tempObj;
                }
            }
        }

        int k = 0;
        for (int j = allRecords.length - 1; k < 5; j--) {

            printDataRecords(new Library[]{allRecords[j]});
            k++;

        }

    }

    public static void deleteFile() throws FileNotFoundException {
        File f = new File("lib.txt");
        if (f.exists()) {
            PrintWriter writer = new PrintWriter(f);
            writer.print("");
            writer.close();
        }
        {
            PrintWriter writer = new PrintWriter(f);
            writer.print("");
            writer.close();
        }
    }

    public static void SearchRecordLinear(int n, String key) {

        Library[] alldata = sortByName(Main.allRecordsInStart);

        switch (n) {
            case 1: //search by book name
                for (Library alldatum : alldata) {

                    if (alldatum.getBook_name().equals(key)) {
                        printDataRecord(alldatum);
                    }


                }
                break;
            case 2: // search by Auther_Name
                for (Library alldatum : alldata) {

                    if (alldatum.getAuthor_name().equals(key)) {
                        printDataRecord(alldatum);
                    }

                }
                break;
            case 3: // search by Year_of_Publishing
                for (Library alldatum : alldata) {

                    if (alldatum.getPublisher_name().equals(key)) {
                        printDataRecord(alldatum);
                    }

                }
                break;
            case 4:// search by ISBN
                for (Library alldatum : alldata) {

                    if ((alldatum.getYear_of_publishing() + "").equals(key)) {
                        printDataRecord(alldatum);
                    }

                }
                break;
            default:
                System.out.println("No Data");
                break;

        }

    }

    public static void saveToFile(Library[] addedRecords, Library[] updatedRecords, String[] deletedRecords
            , String[] updatedKeys, int addedCount, int updatedCout , int deletedCount) throws IOException {

        for (int i = 0; i <= updatedCout; i++) {
            UpdateRecord(updatedKeys[i], updatedRecords[i]);
        }


        // add Alldata to file
        for (int i = 0 ; i <= deletedCount ; i++) {
            for (int j = 0; j < Main.allRecordsInStart.length; j++) {

                if (!Main.allRecordsInStart[j].getIsbn().equals(deletedRecords[i])) {

                    AddRecord(Main.allRecordsInStart[j]);

                }

            }
        }
        // add added data to file
        for (int i = 0; i <= addedCount; i++) {

            AddRecord(addedRecords[i]);

        }
        // add updated data to file

        // add deleted data to file
        Main.allRecordsInStart = getAllRecords();

    }

    public static void UpdateRecord(String key, Library newRecord) {

        Library[] all = Main.allRecordsInStart;
        for (int i = 0; i < all.length; i++) {
            if (all[i].getIsbn().equals(key)) {
                all[i] = newRecord;
            }
        }
        Main.allRecordsInStart = all;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public int getYear_of_publishing() {
        return year_of_publishing;
    }

    public void setYear_of_publishing(int year_of_publishing) {
        this.year_of_publishing = year_of_publishing;
    }

    public int getEdition_number() {
        return edition_number;
    }

    public void setEdition_number(int edition_number) {
        this.edition_number = edition_number;
    }

}
