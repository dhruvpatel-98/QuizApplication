package UnitTest;

import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.testng.Assert.assertEquals;

public class TestScoreTable {

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
            String sql = "INSERT INTO  score " +
                    "(usr,questions_total,questions_answered,questions_correct) " +
                    "VALUES ('Dante OConnor', 88, 87, 1); ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO  score " +
                    "(usr,questions_total,questions_answered,questions_correct) " +
                    "VALUES ('John Brown', 55, 35, 6); ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO  score " +
                    "(usr,questions_total,questions_answered,questions_correct) " +
                    "VALUES ('Alex Smith', 50, 40, 5); ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO  score " +
                    "(usr,questions_total,questions_answered,questions_correct) " +
                    "VALUES ('That Guy', 40, 40, 5); ";
            stmt.executeUpdate(sql);


            System.out.println("Inserted records into the table...");
            /* */

            // STEP 4 Select the row
            stmt = conn.createStatement();

            sql = "SELECT * FROM score WHERE usr='Dante OConnor';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String questionsTotal = rs.getString("questions_total");
                int TotalInt = Integer.parseInt(questionsTotal);
                assertEquals(TotalInt, 88);

                String questionsAnswered = rs.getString("questions_answered");
                int AnsweredInt = Integer.parseInt(questionsAnswered);
                assertEquals(AnsweredInt, 87);

                String questionsCorrect = rs.getString("questions_correct");
                int CorrectInt = Integer.parseInt(questionsCorrect);
                assertEquals(CorrectInt, 1);
            }

            sql = "SELECT * FROM score WHERE usr='John Brown';";
            ResultSet rs2 = stmt.executeQuery(sql);
            while (rs2.next()) {
                String questionsTotal = rs2.getString("questions_total");
                int TotalInt = Integer.parseInt(questionsTotal);
                assertEquals(TotalInt, 55);

                String questionsAnswered = rs2.getString("questions_answered");
                int AnsweredInt = Integer.parseInt(questionsAnswered);
                assertEquals(AnsweredInt, 35);

                String questionsCorrect = rs2.getString("questions_correct");
                int CorrectInt = Integer.parseInt(questionsCorrect);
                assertEquals(CorrectInt, 6);
            }

            sql = "SELECT * FROM score WHERE usr='Alex Smith';";
            ResultSet rs3 = stmt.executeQuery(sql);
            while (rs3.next()) {
                String questionsTotal = rs3.getString("questions_total");
                int TotalInt = Integer.parseInt(questionsTotal);
                assertEquals(TotalInt, 50);

                String questionsAnswered = rs3.getString("questions_answered");
                int AnsweredInt = Integer.parseInt(questionsAnswered);
                assertEquals(AnsweredInt, 40);

                String questionsCorrect = rs3.getString("questions_correct");
                int CorrectInt = Integer.parseInt(questionsCorrect);
                assertEquals(CorrectInt, 5);
            }

            sql = "SELECT * FROM score WHERE usr='That Guy';";
            ResultSet rs4 = stmt.executeQuery(sql);
            while (rs4.next()) {
                String questionsTotal = rs4.getString("questions_total");
                int TotalInt = Integer.parseInt(questionsTotal);
                assertEquals(TotalInt, 40);

                String questionsAnswered = rs4.getString("questions_answered");
                int AnsweredInt = Integer.parseInt(questionsAnswered);
                assertEquals(AnsweredInt, 40);

                String questionsCorrect = rs4.getString("questions_correct");
                int CorrectInt = Integer.parseInt(questionsCorrect);
                assertEquals(CorrectInt, 5);
            }


            //STEP 5: Remove the test row
            sql = "DELETE FROM score WHERE usr='Dante OConnor';"
                    + "DELETE FROM score WHERE usr='John Brown';"
                    + "DELETE FROM score WHERE usr='Matthew Dunn';"
                    + "DELETE FROM score WHERE usr='That Guy'";
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