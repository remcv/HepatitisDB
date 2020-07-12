package remcv.com.github.controller;

import remcv.com.github.model.InfoAndVisitsOfAPatient;
import remcv.com.github.model.PatientInfo;
import remcv.com.github.view.PrintInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class MainController
{
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

    public static void updateAVisit()
    {
        // prepare user input
        Scanner scanner = new Scanner(System.in);
        int id;
        int patient_id;
        String visit;
        double apri;
        double fib4;
        String fibroTest_categ;
        double fibroTest_score;

        System.out.print("\nUpdate data from a visit\n\t- enter id for visit >> ");
        id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\t- enter patient_id >> ");
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
        DataSource.getInstance().updateAVisit(id, patient_id, visit, apri, fib4, fibroTest_categ, fibroTest_score);
    }

    public static void deleteAVisit()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDelete a visit record\n\t- enter id for visit >> ");
        int idValue = scanner.nextInt();

        DataSource.getInstance().deleteAVisit(idValue);
    }

    // methods - query data
    public static void getOnePatientInfoAndVisits()
    {
        Scanner scanner = new Scanner(System.in);
        int idOfPatient;

        System.out.print("\nObtain information about visits from a single patient\n\t- enter patient id >> ");
        idOfPatient = scanner.nextInt();
        scanner.nextLine();

        List<InfoAndVisitsOfAPatient> list = DataSource.getInstance().getOnePatientInfoAndVisits(idOfPatient);

        if (list == null)
        {
            System.out.println("\tEmpty list");
        }
        else if (list.size() == 0)
        {
            System.out.println("\tInvalid patient id");
        }
        else
        {
            PrintInfo.printTable(list);
        }
    }
}
