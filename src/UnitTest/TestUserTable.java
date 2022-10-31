package UnitTest;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.testng.Assert.assertEquals;

public class TestUserTable {

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
            String sql = "INSERT INTO  quiz_takers " +
                    "(first,last,age) " +
                    "VALUES ('Dante','OConnor', 20); ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO  quiz_takers " +
                    "(first,last,age) " +
                    "VALUES ('John','Brown', 12); ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO  quiz_takers " +
                    "(first,last,age) " +
                    "VALUES ('Alex','Smith', 99); ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO  quiz_takers " +
                    "(first,last,age) " +
                    "VALUES ('That','Guy', 1242); ";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
            /* */

            // STEP 4 Select the row
            stmt = conn.createStatement();

            sql = "SELECT * FROM quiz_takers WHERE first = 'Dante' AND last = 'OConnor';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String ageStr = rs.getString("age");
                int ageInt = Integer.parseInt(ageStr);
                assertEquals(ageInt, 20);
            }

            sql = "SELECT * FROM quiz_takers WHERE first = 'John' AND last = 'Brown';";
            ResultSet rs2 = stmt.executeQuery(sql);
            while (rs2.next()) {
                String ageStr = rs2.getString("age");
                int ageInt = Integer.parseInt(ageStr);
                assertEquals(ageInt, 12);
            }

            sql = "SELECT * FROM quiz_takers WHERE first = 'Alex' AND last = 'Smith';";
            ResultSet rs3 = stmt.executeQuery(sql);
            while (rs3.next()) {
                String ageStr = rs3.getString("age");
                int ageInt = Integer.parseInt(ageStr);
                assertEquals(ageInt, 99);
            }

            sql = "SELECT * FROM quiz_takers WHERE first = 'That' AND last = 'Guy';";
            ResultSet rs4 = stmt.executeQuery(sql);
            while (rs4.next()) {
                String ageStr = rs4.getString("age");
                int ageInt = Integer.parseInt(ageStr);
                assertEquals(ageInt, 1242);
            }

            //STEP 5: Remove the test row
            sql = "DELETE FROM quiz_takers WHERE first = 'Dante' AND last = 'OConnor';"
                    + "DELETE FROM quiz_takers WHERE first = 'John' AND last = 'Brown';"
                    + "DELETE FROM quiz_takers WHERE first = 'Alex' AND last = 'Smith';"
                    + "DELETE FROM quiz_takers WHERE first = 'That' AND last = 'Guy';";

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
