package remcv.com.github.controller;

import remcv.com.github.model.PatientInfo;
import remcv.com.github.view.PrintInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class MainController
{
    // fields
    private static final String EXIT_WORD = "exit";

    // methods - CRUD for patients_info table
    public static void addAPatientInfo()
    {
        // prepare user input
        Scanner scanner = new Scanner(System.in);
        String codename;
        int age;
        String gender;

        System.out.print("\nAdd a patient to database\n\t- enter codename >> ");
        codename = scanner.nextLine();

        System.out.print("\t- enter age >> ");
        age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\t- enter gender (F/M) >> ");
        gender = scanner.nextLine();

        // add user input to database
        DataSource.getInstance().addAPatientInfo(codename, age, gender);
    }

    public static void printPatientsTable()
    {
        System.out.println("\nTable patients_info");
        PrintInfo.printTable(DataSource.getInstance().getListOfPatients());
    }

    public static void updateAPatientInfo()
    {
        // prepare user input
        Scanner scanner = new Scanner(System.in);
        String codename;
        int age;
        String gender;
        int id;

        System.out.print("\nUpdate a patient data\n\t- enter id for patients_info >> ");
        id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\t- enter codename >> ");
        codename = scanner.nextLine();

        System.out.print("\t- enter age >> ");
        age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\t- enter gender (F/M) >> ");
        gender = scanner.nextLine();

        // add user input to database
        DataSource.getInstance().updateAPatientInfo(id, codename, age, gender);
    }

    public static void deleteAPatientInfo()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDelete a patient record\n\t- enter id for patients_info >> ");
        int idValue = scanner.nextInt();

        DataSource.getInstance().deleteAPatientInfo(idValue);
    }

    // methods - CRUD for visits table
    public static void addAVisit()
    {
        // prepare user input
        Scanner scanner = new Scanner(System.in);
        int patient_id;
        String visit;
        double apri;
        double fib4;
        String fibroTest_categ;
        double fibroTest_score;

        System.out.print("\nAdd a visit to database\n\t- enter patient_id >> ");
        patient_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\t- enter visit (baseline / 6Mo / 12Mo) >> ");
        visit = scanner.nextLine();

        System.out.print("\t- enter APRI score >> ");
        apri = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("\t- enter FIB-4 score >> ");
        fib4 = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("\t- enter FibroTest category (F...) >> ");
        fibroTest_categ = scanner.nextLine();

        System.out.print("\t- enter FibroTest score >> ");
        fibroTest_score = scanner.nextDouble();
        scanner.nextLine();

        // add user input to database
        DataSource.getInstance().addAVisit(patient_id, visit, apri, fib4, fibroTest_categ, fibroTest_score);
    }

    public static void printVisitsTable()
    {
        System.out.println("\nTable visits");
        PrintInfo.printTable(DataSource.getInstance().getListOfMarkers());
    }
}
