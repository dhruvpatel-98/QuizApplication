package Components;
public class Score {

    private Score() {
    }

    private static Score single_instance = null;

    public static Score getInstance() {
        if (single_instance == null)
            single_instance = new Score();

        return single_instance;
    }

    private String usr;
    private int question_total;
    private int question_answer;
    private int questions_correct;

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getUsr() {
        return usr;
    }

    public void setQuestion_total(int total) {
        question_total = total;
    }

    public int getQuestion_total() {
        return question_total;
    }
}