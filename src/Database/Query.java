package Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import static Database.JDBC.connection;

public class Query {
    /**
     * String variable to hold type of query
     */
    private static String query;
    /**
     * Statement variable to hold SQL queries
     */
    private static Statement stmt;
    /**
     * Result Set variable to hold results of queries
     */
    private static ResultSet result;

    /**
     * Executes a SQL query based on type of String passed to method.
     *   @param q string variable to hold type of query.
     */
    public static void makeQuery(String q){
        query=q;
        try{
            stmt=connection.createStatement();
            // determine query execution
            if(query.toLowerCase().startsWith("select"))
                result=stmt.executeQuery(q);
            if(query.toLowerCase().startsWith("delete")||query.toLowerCase().startsWith("insert")||query.toLowerCase().startsWith("update"))
                stmt.executeUpdate(q);

        }
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    public static ResultSet getResult() {
        return result;
    }
}
