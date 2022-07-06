package Database;

import Model.user;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public static user getUser(String userName) throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "SELECT * FROM users WHERE User_Name = '" + userName + "'";
        Query.makeQuery(sqlStmt);
        user userResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int userID = result.getInt("User_ID");
            String userNAME = result.getString("User_Name");
            String password = result.getString("Password");
            userResult = new user(userID, userNAME, password);
            return userResult;
        }

        return null;

    }

    public static boolean createUser(String userName, String password) throws SQLException, Exception {
        try {
            JDBC.getConnection();
            String sqlStmt =
                    "INSERT INTO users (User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES('"
                            + userName + "','" + password + "', sysdate(), '" + userName + "', sysdate(), '" + userName + "')";
            //System.out.println(sqlStmt);
            Query.makeQuery(sqlStmt);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
