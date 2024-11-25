package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class Jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@${ip}:1521:xe";
    String dbUsername = "Hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        /*
       //sona git
        resultSet.last();
        int rowCount = resultSet.getRow();  //last number
        System.out.println(rowCount);

        resultSet.beforeFirst(); // başka işlem yapmak için başa al
          */

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
        //========================================
        //new obj
        System.out.println("-----------------------------------");
        resultSet = statement.executeQuery("select * from regions");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test2() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in result set object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());

        ResultSetMetaData rsMetaData= resultSet.getMetaData();

        int columnCount = rsMetaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        //colum name
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnName(2));

        System.out.println("-----------------------------------------*++++++++++++++++++++");
        //column name dynamically
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(rsMetaData.getColumnName(i));

        }




        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
