package remcv.com.github;

import remcv.com.github.controller.DataSource;
import remcv.com.github.controller.MainController;
import remcv.com.github.view.PrintInfo;

import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        // create DataSource instance and open database connection
        DataSource ds = DataSource.getInstance();
        ds.openConnection();

        // create tables (if not exists)
        ds.createTablePatients();
        ds.createTableVisits();

        // print main menu
        PrintInfo.printMainMenu();
        System.out.print("\nEnter a choice >> ");
        String userInput = scanner.nextLine();

        while(!userInput.equals("exit"))
        {
            switch (userInput)
            {
                case "1":
                    MainController.printPatientsTable();
                    break;
                case "2":
                    MainController.printVisitsTable();
                    break;
                case "3":
                    MainController.addAPatientInfo();
                    break;
                case "4":
                    MainController.addAVisit();
                    break;
                case "5":
                    MainController.updateAPatientInfo();
                    break;
                case "6":
                    PrintInfo.printMainMenu();
                default:
                    System.out.println("Invalid user input. Try again!");
            }

            // update loop control variable
            PrintInfo.printMainMenu();
            System.out.print("\nEnter a choice >> ");
            userInput = scanner.nextLine();

        }

        ds.closeConnection();
    }
}
