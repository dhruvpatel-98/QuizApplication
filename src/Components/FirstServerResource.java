package Components;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import java.io.IOException;
import java.util.ArrayList;


public class FirstServerResource extends ServerResource {

    private static int count = 0;
    private static String json_string;
    private static ArrayList<Questions> allQuestionsAnswers = null;
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    public static void main(String[] args) throws Exception {

        builder.setPrettyPrinting();

        AllQuestions allQuestions = new AllQuestions();

        allQuestionsAnswers = allQuestions.getAllQuestionsAnswers();


        // Create the HTTP server and listen on port 8182
        new Server(Protocol.HTTP, 8182, FirstServerResource.class).start();
    }


    @Get
    public String toString() {
        count++;
        count = count % allQuestionsAnswers.size();
        Questions questionAnswer = allQuestionsAnswers.get(count);
        json_string = gson.toJson(questionAnswer);
        return  json_string;  // "Question " + Integer.valueOf(count).toString() + ": " +
    }
    @Post
    public Response addScore(Representation r) {
        String s;
        Request request = new Request();

        try {
            s = r.getText();
            System.out.println("s: " + s);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new Response(request);
    }

    /*
     * curl -v -d "Hello=YEAH" -X  PUT "http://localhost:8182" -H "Content-type: text/html; charset=UTF-8"
     */
    @Put
    public Response updateScore(Representation r) {
        String s;

        try {
            s = r.getText();
            System.out.println("s: " + s);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Request request = new Request();

        return new Response(request);
    }
}