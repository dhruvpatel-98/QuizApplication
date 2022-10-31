package Components;

import H2Database.Questions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class restServer extends ServerResource {

        //private static int count = 0;
        private static String json_string;
        private static final String JDBC_DRIVER = "org.h2.Driver";
        private static final String DB_URL = "jdbc:h2:~/rest"; //
        private static final String USER = "sa";
        private static final String PASS = "";

        //private static Connection conn = null;
        //private static Statement stmt = null;
        private static int row = 1;


        public static void main(String[] args) throws Exception {

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            //change to import from
            Questions one_q =  new Questions("welcome", "please click next question");
            json_string = gson.toJson(one_q);



            // Create the HTTP server and listen on port 8182
            new Server(Protocol.HTTP, 8182, restServer.class).start();
        }
        private static void getQuestion(){
            Connection conn = connectToH2();
            Statement stmt = null;
            Questions retval;
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            try {
                stmt = conn.createStatement();
                String sql =  "SELECT question, answer  FROM questions WHERE id=" + row + ";";
                row++;
                ResultSet rs = stmt.executeQuery(sql);
                //System.out.println(rs);
                retval = new Questions(rs.getString("question"), rs.getString("answer"));
                stmt.close();
            }catch (Exception e){
                e.printStackTrace();
                //disconnectFromH2();
                json_string = gson.toJson(new Questions("out of quests", "Thanks for participating"));
                return;
            }
            //disconnectFromH2();
            json_string = gson.toJson(retval);
            return;
        }
        private static Connection connectToH2() {
            try {
                // STEP 1: Register JDBC driver
                Class.forName(JDBC_DRIVER);

                //STEP 2: Open a connection
                System.out.println("Connecting to database...");
                return DriverManager.getConnection(DB_URL,USER,PASS);
            } catch (Exception e) {
                System.err.println("Error! in connectToH2()");
                System.out.println(e);
                return null;
            }

        }
        /*private static void disconnectFromH2() {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println("Error! in disconnectFromH2()");
            }
        }*/


        @Get
        public String toString() {
            getQuestion();
            return  json_string;  // "Question " + Integer.valueOf(count).toString() + ": " +
        }


    }
