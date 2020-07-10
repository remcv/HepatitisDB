package remcv.com.github.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataSource
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
    public void createTable(String tableName, List<String> colNamesAndTypes)
    {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(tableName);
        sb.append(" (id INTEGER PRIMARY KEY, ");
        for (int i = 0; i < colNamesAndTypes.size(); i += 2)
        {
            sb.append(colNamesAndTypes.get(i) + " " + colNamesAndTypes.get(i+1) + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        try (Statement statement = conn.createStatement())
        {
            statement.execute(sb.toString());
        }
        catch (SQLException e)
        {
            System.out.println("createTable() failed " + tableName);
            e.printStackTrace();
        }
    }

    public void deleteTable(String tableName)
    {
        String deleteStatementString = "DROP TABLE " + tableName;
        Statement deleteStm = null;

        try
        {
           deleteStm = conn.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            deleteStm.execute(deleteStatementString);
        }
        catch (SQLException e)
        {
            System.out.println("\t" + tableName + " is an invalid table name");
        }
        
        try
        {
            deleteStm.close();
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
