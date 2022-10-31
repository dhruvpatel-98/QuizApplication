package UnitTest;
//import org.testng.annotations.Test;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.testng.Assert.assertEquals;

public class TestQuestionTable {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    static final String USER = "sa";
    static final String PASS = "";


    @Test
    public void test() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            /* */
            //STEP 3: Insert a test row in the score table

            stmt = conn.createStatement();
            String sql = "INSERT INTO questions " +
                    "(question,a,b,c, answer) " +
                    "VALUES ('How many continents are there?', '54', '7', '12', '7'); ";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
            /* */

            // STEP 4 Select the row
            stmt = conn.createStatement();

            sql = "SELECT * FROM questions WHERE answer='7';";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String aStr = rs.getString("a");
                assertEquals(aStr, "54");
                String bStr = rs.getString("b");
                assertEquals(bStr, "7");
                String cStr = rs.getString("c");
                assertEquals(cStr, "12");
                String questionStr = rs.getString("question");
                assertEquals(questionStr, "How many continents are there?");
            }

            //STEP 5: Remove the test row
            sql = "DELETE FROM questions WHERE answer='7';";
            stmt.executeUpdate(sql);

            System.out.println("Deleted the test rows from the table...");

            //Clean Up Environment

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
    }

}