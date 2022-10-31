package H2Database;

import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 *
 * Partial from https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.htm
 *
 */
public class ReadCSVInsertIntoH2 {
    // Data file
    private final static String file = CONSTANTS.CsvFileLocation;// CONSTANTS.TestCsvFileLocation;
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test"; //

    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";

    private static Connection conn = null;
    private static Statement stmt = null;
    //
    /*
     *H2 table created as:
     * CREATE TABLE questions (id int AUTO_INCREMENT, question VARCHAR(1024),
     * answer varchar(1024),  PRIMARY KEY(id));
     *
     */
    private static void connectToH2() {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e) {
            System.err.println("Error! in connectToH2()");
        }
    }

    private static void disconnectFromH2() {
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Error! in disconnecFromH2()");
        }
    }

    private static void insertIntoH2(int id, String question, String answer) {
        try {
            stmt = conn.createStatement();
            String sql =  "INSERT INTO  questions " +
                    "(question,answer) " +
                    "VALUES ('" +
                    question +
                    "','" +
                    answer +
                    "'" +
                    "); ";
            System.out.println("SQL: "+ sql.toString());

            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error! in insertIntoH2");
        }
    }

    /*
     * Example from use of CSV formatting
     *
     */
    public static void main(String[] args) {
        try {

            connectToH2();
            System.out.println(file);
            Reader in = new FileReader(file);

            int id = 1;

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String question = record.get("Question");
                String answer = record.get("Answer");
                System.out.println("Question: " + question+"\n Answer: "+answer);
                insertIntoH2(id,question, answer);
                id += 1;
            }

            in.close();
            disconnectFromH2();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }
}