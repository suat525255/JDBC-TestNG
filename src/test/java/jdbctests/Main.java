package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        //52 li olan benim aws IP  port number 1521 herkes aynı
        String dbUrl = "jdbc:oracle:thin:@${ip}:1521:xe";
        String dbUsername = "HR";
        String dbPassword = "hr";

        //create connection java SQL seç
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);//url,user,password
        //create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in result set obj
        //ResultSet resultSet = statement.executeQuery("Select * from regions");
        ResultSet resultSet = statement.executeQuery("Select * from departments");

//        //move pointer to first row
//        resultSet.next();
//        System.out.println(resultSet.getString("region_name"));
//          //numara sutün numarası
//        System.out.println(resultSet.getInt(1)+" - " + resultSet.getString("region_name"));
//
//
//        //move pointer to second row
//        resultSet.next();
//        System.out.println(resultSet.getString("region_name"));
//        System.out.println(resultSet.getString(2));
//        // 1 yazmamıza reğmen 2-America yazar 2 yazsak hata verir
//        System.out.println(resultSet.getInt(1)+" - " + resultSet.getString("region_name"));
//
//        while (resultSet.next()){
//            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString("region_name"));
//        }

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2)+" - " + resultSet.getString(3)+" - " + resultSet.getString(4));
        }


            //close all connections
        //Closing order is important
        resultSet.close();
        statement.close();
        connection.close();


    }
}
