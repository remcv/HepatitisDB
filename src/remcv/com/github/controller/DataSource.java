package remcv.com.github.controller;

import remcv.com.github.model.InfoAndVisitsOfAPatient;
import remcv.com.github.model.MarkersAtAnEvaluation;
import remcv.com.github.model.PatientInfo;
import remcv.com.github.model.TableConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource implements TableConstants
{
    // fields
    private static final String DATABASE_NAME = "hepatitis.db";
    private static final String JDBC_URL = "jdbc:sqlite:/home/rem/Documents/Code/Projects/Java/HepatitisDB/" + DATABASE_NAME;
    private Connection conn;

    // fields - singleton
    private static final DataSource instance = new DataSource();

    // constructor
    private DataSource()
    {
        // leave this empty (singleton pattern)
    }

    // methods - getInstance()
    public static DataSource getInstance()
    {
        return instance;
    }

    // methods - open & close connection
    public boolean openConnection()
    {
        try
        {
            conn = DriverManager.getConnection(JDBC_URL);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection()
    {
        try
        {
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // methods - create & delete tables
    public void createTablePatients()
    {
        // CREATE TABLE IF NOT EXISTS patients_info (id INTEGER PRIMARY KEY, codename TEXT, age INTEGER, gender TEXT)
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(TableConstants.TABLE_PATIENTS_INFO);
        sb.append(" (id INTEGER PRIMARY KEY, ");
        sb.append(TableConstants.PATIENTS_COLUMN_CODENAME + " TEXT, ");
        sb.append(TableConstants.PATIENTS_COLUMN_AGE + " INTEGER, ");
        sb.append(TableConstants.PATIENTS_COLUMN_GENDER + " TEXT)");

        try (Statement statement = conn.createStatement())
        {
            statement.execute(sb.toString());
        }
        catch (SQLException e)
        {
            System.out.println("createTable() failed " + TableConstants.TABLE_PATIENTS_INFO);
            e.printStackTrace();
        }
    }

    public void createTableVisits()
    {
        // CREATE TABLE IF NOT EXISTS visits (id INTEGER PRIMARY KEY, patient_id INTEGER, visit TEXT, apri REAL, fib4 REAL, fibroTest_score REAL, fibroTest_categ TEXT)
        String stmString = String.format("CREATE TABLE IF NOT EXISTS %1$s (id INTEGER PRIMARY KEY, %2$s INTEGER, %3$s TEXT, %4$s REAL, %5$s REAL, %6$s REAL, %7$s TEXT)",
                TableConstants.TABLE_VISITS,                    // 1
                TableConstants.VISITS_COLUMN_PATIENT_ID,        // 2
                TableConstants.VISITS_COLUMN_VISIT,             // 3
                TableConstants.VISITS_COLUMN_APRI,              // 4
                TableConstants.VISITS_COLUMN_FIB4,              // 5
                TableConstants.VISITS_COLUMN_FIBROTEST_SCORE,   // 6
                TableConstants.VISITS_COLUMN_FIBROTEST_CATEG);  // 7

        try (Statement statement = conn.createStatement())
        {
            statement.execute(stmString);
        }
        catch (SQLException e)
        {
            System.out.println("createTable() failed " + TableConstants.TABLE_PATIENTS_INFO);
            e.printStackTrace();
        }
    }

    // methods - query data
    public List<PatientInfo> getListOfPatients()
    {
        String queryString = "SELECT * FROM " + TableConstants.TABLE_PATIENTS_INFO + " ORDER BY " + TableConstants.ID;

        try (Statement stm = conn.createStatement())
        {
            ResultSet results = stm.executeQuery(queryString);
            List<PatientInfo> patientList = new ArrayList<>();

            while (results.next())
            {
                int id = results.getInt(TableConstants.ID);
                String codename = results.getString(TableConstants.PATIENTS_COLUMN_CODENAME);
                int age = results.getInt(TableConstants.PATIENTS_COLUMN_AGE);
                char gender = results.getString(TableConstants.PATIENTS_COLUMN_GENDER).charAt(0);

                patientList.add(new PatientInfo(id, codename, age, gender));
            }

            return patientList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<MarkersAtAnEvaluation> getListOfMarkers()
    {
        String queryString = "SELECT * FROM " + TableConstants.TABLE_VISITS + " ORDER BY " + TableConstants.ID;

        try (Statement stm = conn.createStatement())
        {
            ResultSet results = stm.executeQuery(queryString);
            List<MarkersAtAnEvaluation> visitsList = new ArrayList<>();

            while (results.next())
            {
                int id = results.getInt(TableConstants.ID);
                int patient_id = results.getInt(TableConstants.VISITS_COLUMN_PATIENT_ID);
                String visit = results.getString(TableConstants.VISITS_COLUMN_VISIT);
                double apri = results.getDouble(TableConstants.VISITS_COLUMN_APRI);
                double fib4 = results.getDouble(TableConstants.VISITS_COLUMN_FIB4);
                String fibroTest_categ = results.getString(TableConstants.VISITS_COLUMN_FIBROTEST_CATEG);
                double fibroTest_score = results.getDouble(TableConstants.VISITS_COLUMN_FIBROTEST_SCORE);

                visitsList.add(new MarkersAtAnEvaluation(id, patient_id, visit, apri, fib4, fibroTest_categ, fibroTest_score));
            }

            return visitsList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<InfoAndVisitsOfAPatient> getOnePatientInfoAndVisits(int patientId)
    {
        /*
         * SELECT pi.id, pi.codename, pi.age, pi.gender, v.visit, v.apri, v.fib4, v.fibroTest_categ, v.fibroTest_score
         * FROM patients_info pi
         * INNER JOIN visits v
         * ON pi.id = v.patient_id
         * WHERE pi.id = 1
        */

        String queryString = String.format("SELECT %1$s.%2$s, %1$s.%3$s, %1$s.%4$s, %1$s.%5$s, %6$s.%7$s, %6$s.%8$s, %6$s.%9$s, %6$s.%10$s, %6$s.%11$s " +
                        "FROM %6$s LEFT JOIN %1$s ON %1$s.%2$s = %6$s.%12$s WHERE %1$s.%2$s = %13$d ORDER BY %7$s DESC",
                TableConstants.TABLE_PATIENTS_INFO,             // 1
                TableConstants.ID,                              // 2
                TableConstants.PATIENTS_COLUMN_CODENAME,        // 3
                TableConstants.PATIENTS_COLUMN_AGE,             // 4
                TableConstants.PATIENTS_COLUMN_GENDER,          // 5
                TableConstants.TABLE_VISITS,                    // 6
                TableConstants.VISITS_COLUMN_VISIT,             // 7
                TableConstants.VISITS_COLUMN_APRI,              // 8
                TableConstants.VISITS_COLUMN_FIB4,              // 9
                TableConstants.VISITS_COLUMN_FIBROTEST_CATEG,   // 10
                TableConstants.VISITS_COLUMN_FIBROTEST_SCORE,   // 11
                TableConstants.VISITS_COLUMN_PATIENT_ID,        // 12
                patientId);                                     // 13

        try (Statement stm = conn.createStatement();
            ResultSet results = stm.executeQuery(queryString))
        {
            int id_of_patient;
            String codename;
            int age;
            String gender;
            String visit;
            double apri;
            double fib4;
            String fibroTest_categ;
            double fibroTest_score;

            List<InfoAndVisitsOfAPatient> list = new ArrayList<>();

            while (results.next())
            {
                id_of_patient = results.getInt(TableConstants.ID);
                codename = results.getString(TableConstants.PATIENTS_COLUMN_CODENAME);
                age = results.getInt(TableConstants.PATIENTS_COLUMN_AGE);
                gender = results.getString(TableConstants.PATIENTS_COLUMN_GENDER);
                visit = results.getString(TableConstants.VISITS_COLUMN_VISIT);
                apri = results.getDouble(TableConstants.VISITS_COLUMN_APRI);
                fib4 = results.getDouble(TableConstants.VISITS_COLUMN_FIB4);
                fibroTest_categ = results.getString(TableConstants.VISITS_COLUMN_FIBROTEST_CATEG);
                fibroTest_score = results.getDouble(TableConstants.VISITS_COLUMN_FIBROTEST_SCORE);

                list.add(new InfoAndVisitsOfAPatient(id_of_patient, codename, age, gender, visit, apri, fib4,
                        fibroTest_categ, fibroTest_score));
            }

            return list;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // methods - insert rows
    public void addAPatientInfo(String codename, int age, String gender)
    {
        // INSERT INTO patients_info (codename, age, gender) VALUES ("AN", 33, 'F')
        String stmString = String.format("INSERT INTO patients_info (%1$s, %2$s, %3$s) VALUES (?, ?, ?)",
                TableConstants.PATIENTS_COLUMN_CODENAME,
                TableConstants.PATIENTS_COLUMN_AGE,
                TableConstants.PATIENTS_COLUMN_GENDER);

        PreparedStatement pStm = null;

        try
        {
            pStm = conn.prepareStatement(stmString);
            pStm.setString(1, codename);
            pStm.setInt(2, age);
            pStm.setString(3, gender);

            int addNumber = pStm.executeUpdate();
            if (addNumber == 1)
            {
                System.out.println("\tAdd operation was successful");
            }
        }
        catch (SQLException e)
        {
            System.out.println("\tAdd operation failed");
            e.printStackTrace();
        }
        finally
        {
            if (pStm != null)
            {
                try
                {
                    pStm.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addAVisit(int patient_id, String visit, double apri, double fib4, String fibroTest_categ, double fibroTest_score)
    {
        // INSERT INTO visits (int patient_id, String visit, double apri, double fib4, String fibroTest_categ, double fibroTest_score) VALUES (......)
        String stmString = String.format("INSERT INTO %7$s (%1$s, %2$s, %3$s, %4$s, %5$s, %6$s) VALUES (?, ?, ?, ?, ?, ?)",
                TableConstants.VISITS_COLUMN_PATIENT_ID,
                TableConstants.VISITS_COLUMN_VISIT,
                TableConstants.VISITS_COLUMN_APRI,
                TableConstants.VISITS_COLUMN_FIB4,
                TableConstants.VISITS_COLUMN_FIBROTEST_CATEG,
                TableConstants.VISITS_COLUMN_FIBROTEST_SCORE,
                TableConstants.TABLE_VISITS);

        PreparedStatement pStm = null;

        try
        {
            pStm = conn.prepareStatement(stmString);
            pStm.setInt(1, patient_id);
            pStm.setString(2, visit);
            pStm.setDouble(3, apri);
            pStm.setDouble(4, fib4);
            pStm.setString(5, fibroTest_categ);
            pStm.setDouble(6, fibroTest_score);

            int addNumber = pStm.executeUpdate();
            if (addNumber == 1)
            {
                System.out.println("\tAdd operation was successful");
            }
        }
        catch (SQLException e)
        {
            System.out.println("\tAdd operation failed");
            e.printStackTrace();
        }
        finally
        {
            if (pStm != null)
            {
                try
                {
                    pStm.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    // methods - update rows
    public void updateAPatientInfo(int idValue, String codename, int age, String gender)
    {
        // UPDATE patients_info SET codename = new_value_1, age = new_value_2, gender = new_value_3 WHERE id = id
        String stmString = String.format("UPDATE patients_info SET %1$s = ?, %2$s = ?, %3$s = ? WHERE %4$s = ?",
                TableConstants.PATIENTS_COLUMN_CODENAME,
                TableConstants.PATIENTS_COLUMN_AGE,
                TableConstants.PATIENTS_COLUMN_GENDER,
                TableConstants.ID);

        PreparedStatement pStm = null;

        try
        {
            pStm = conn.prepareStatement(stmString);
            pStm.setString(1, codename);
            pStm.setInt(2, age);
            pStm.setString(3, gender);
            pStm.setInt(4, idValue);

            int updateNumber = pStm.executeUpdate();
            if (updateNumber == 1)
            {
                System.out.println("\tUpdate successful");
            }
            else
            {
                System.out.println("\tUpdate failed - invalid id");
            }
        }
        catch (SQLException e)
        {
            System.out.println("\tUpdate failed");
            e.printStackTrace();
        }
        finally
        {
            if (pStm != null)
            {
                try
                {
                    pStm.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateAVisit(int visitId, int patient_id, String visit, double apri, double fib4, String fibroTest_categ, double fibroTest_score)
    {
        // UPDATE values SET patient_id = new_value_1, visit = new_value_2 ... WHERE id = visitId
        String stmString = String.format("UPDATE %1$s SET %2$s = ?, %3$s = ?, %4$s = ?, %5$s = ?, %6$s = ?, %7$s = ? WHERE %8$s = ?",
                TableConstants.TABLE_VISITS,                    // 1
                TableConstants.VISITS_COLUMN_PATIENT_ID,        // 2
                TableConstants.VISITS_COLUMN_VISIT,             // 3
                TableConstants.VISITS_COLUMN_APRI,              // 4
                TableConstants.VISITS_COLUMN_FIB4,              // 5
                TableConstants.VISITS_COLUMN_FIBROTEST_CATEG,   // 6
                TableConstants.VISITS_COLUMN_FIBROTEST_SCORE,   // 7
                TableConstants.ID);                             // 8

        PreparedStatement pStm = null;

        try
        {
            pStm = conn.prepareStatement(stmString);
            pStm.setInt(1, patient_id);
            pStm.setString(2, visit);
            pStm.setDouble(3, apri);
            pStm.setDouble(4, fib4);
            pStm.setString(5, fibroTest_categ);
            pStm.setDouble(6, fibroTest_score);
            pStm.setInt(7, visitId);

            int addNumber = pStm.executeUpdate();
            if (addNumber == 1)
            {
                System.out.println("\tUpdate operation was successful");
            }
            else
            {
                System.out.println("\tUpdate operation failed - invalid visit id");
            }
        }
        catch (SQLException e)
        {
            System.out.println("\tUpdate operation failed");
            e.printStackTrace();
        }
        finally
        {
            if (pStm != null)
            {
                try
                {
                    pStm.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    // methods - delete rows
    public void deleteAPatientInfo(int idValue)
    {
        // DELETE FROM patients_info WHERE id = idValue
        String stmString = String.format("DELETE FROM patients_info WHERE %s = ?", TableConstants.ID);

        PreparedStatement pStm = null;

        try
        {
            pStm = conn.prepareStatement(stmString);
            pStm.setInt(1, idValue);

            int deleteNumber = pStm.executeUpdate();
            if (deleteNumber == 1)
            {
                System.out.println("\tDelete operation was successful");
            }
            else
            {
                System.out.println("\tDelete failed - invalid id");
            }
        }
        catch (SQLException e)
        {
            System.out.println("\tDelete failed");
            e.printStackTrace();
        }
        finally
        {
            if (pStm != null)
            {
                try
                {
                    pStm.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteAVisit(int idValue)
    {
        // DELETE FROM visits WHERE id = idValue
        String stmString = String.format("DELETE FROM %s WHERE %s = ?", TableConstants.TABLE_VISITS, TableConstants.ID);

        PreparedStatement pStm = null;

        try
        {
            pStm = conn.prepareStatement(stmString);
            pStm.setInt(1, idValue);

            int deleteNumber = pStm.executeUpdate();
            if (deleteNumber == 1)
            {
                System.out.println("\tDelete operation was successful");
            }
            else
            {
                System.out.println("\tDelete failed - invalid id");
            }
        }
        catch (SQLException e)
        {
            System.out.println("\tDelete failed");
            e.printStackTrace();
        }
        finally
        {
            if (pStm != null)
            {
                try
                {
                    pStm.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
