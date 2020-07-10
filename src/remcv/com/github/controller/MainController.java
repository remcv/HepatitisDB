package remcv.com.github.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class MainController
{
    // fields
    private static final String EXIT_WORD = "exit";

    // methods
    public static void createTable(DataSource ds)
    {
        // prepare user input
        Scanner scanner = new Scanner(System.in);
        String tableName;
        String colName;
        String colType;
        List<String> colNamesAndTypes = new ArrayList<>();

        System.out.println("\nTable creation");
        System.out.print("\tEnter table name >> ");
        tableName = scanner.nextLine();

        System.out.print("\tEnter column name >> ");
        colName = scanner.nextLine();
        System.out.print("\tEnter column type >> ");
        colType = scanner.nextLine();

        colNamesAndTypes.add(colName);
        colNamesAndTypes.add(colType);

        while (true)
        {
            System.out.print("\tEnter column name >> ");
            colName = scanner.nextLine();

            if (colName.equals(EXIT_WORD))
            {
                break;
            }

            System.out.print("\tEnter column type >> ");
            colType = scanner.nextLine();

            colNamesAndTypes.add(colName);
            colNamesAndTypes.add(colType);
        }

        // add user input to database
        ds.createTable(tableName, colNamesAndTypes);
        System.out.println("Table " + tableName + " created successfully");
        scanner.close();
    }

    public static void deleteTable(DataSource ds)
    {
        // prepare user input
        String tableName;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nDelete table");
        System.out.print("\tEnter table name >> ");
        tableName = scanner.nextLine();

        ds.deleteTable(tableName);
    }
}
