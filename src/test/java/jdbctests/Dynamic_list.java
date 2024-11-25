package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@${ip}:1521:xe";
    String dbUsername = "Hr";
    String dbPassword = "hr";

    @Test
    public void dynamic_list() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from address");

        //get the result set object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsMetadata.getColumnCount();

        //loop through each row
        while(resultSet.next()){//add row
            Map<String,Object> row = new HashMap<>();

            for (int i = 1; i <=colCount; i++) {//add column

                row.put(rsMetadata.getColumnName(i),resultSet.getObject(i));

            }

            //add your map to your list
            //1.1-1.2-1.3-1.4
            //2.1-2.2-2.3-2.4
            queryData.add(row);
        }


        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

}
