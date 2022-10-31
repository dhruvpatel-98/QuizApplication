package Components;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


    public class restServer extends ServerResource {

        //private static int count = 0;
        private static String json_string;
        private static String json_string2;

        public static void main(String[] args) throws Exception {

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            Questions one_q = new Questions("How many members of the US Congress?", "435", "For the last 8 years of his life, Galileo was under house arrest for espousing this man's theory", "Copernicus", "No. 2: 1912 Olympian; football star at Carlisle Indian School; 6 MLB seasons with the Reds, Giants & Braves", "Jim Thorpe", "The city of Yuma in this state has a record average of 4,055 hours of sunshine each year", "Arizona", "In 1963, live on The Art Linkletter Show, this company served its billionth burger", "McDonald's", "Signer of the Dec. of Indep., framer of the Constitution of Mass., second President of the United Stzxates","John Adams");

            json_string = gson.toJson(one_q);



            // Create the HTTP server and listen on port 8182
            new Server(Protocol.HTTP, 8182, restServer.class).start();
        }


        @Get
        public String toString() {
            // count++;
            return  json_string;  // "Question " + Integer.valueOf(count).toString() + ": " +
        }


    }
