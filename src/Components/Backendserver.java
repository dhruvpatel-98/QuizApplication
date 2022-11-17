package Components;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;


public class Backendserver extends ServerResource {

    private static int count = 0;
    private static String json_string;
    private static ArrayList<Questions> allAnswers = null;
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    public static void main(String[] args) throws Exception {

        builder.setPrettyPrinting();

        AllQuestions allQuestions = new AllQuestions();

        allAnswers = allQuestions.getAllQuestionsAnswers();


        // Create the HTTP server and listen on port 8182
        new Server(Protocol.HTTP, 8182, Backendserver.class).start();
    }


    @Get
    public String toString() {
        count++;
        count = count % allAnswers.size();
        Questions questionAnswer = allAnswers.get(count);
        json_string = gson.toJson(questionAnswer);
        return  json_string;
    }

    @Post
    public void getData(String myData) {
        System.out.println("POST worked: <" + myData + ">");
    }

}
