package remcv.com.github.view;

import remcv.com.github.model.PatientInfo;

import java.util.List;

public final class PrintInfo
{
    // methods
    public static void printMainMenu()
    {
        System.out.println("\nMain menu\n\t- 1 - print patients_info table\n" +
                "\t- 2 - print visits table\n" +
                "\t- 3 - add a record to patient_info table\n" +
                "\t- 4 - add a record to visits table\n" +
                "\t- 5 - update a record from the patients_info table\n" +
                "\t- 6 - update a record from the visits table\n" +
                "\t- 7 - delete a record from the patients_info table\n" +
                "\t- 8 - delete a record from the visits table\n" +
                "\t- exit to close the program");
    }

    public static <E> void printTable(List<E> list)
    {
        for (E item : list)
        {
            System.out.println(item.toString());
        }
    }


}
