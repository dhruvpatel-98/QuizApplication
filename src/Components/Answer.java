package Components;

public class Answer {

    private int id;
    private String question;
    private String answer;

    public Answer(int id, String ques, String ans) {
        this.id = id;
        this.question = ques;
        this.answer = ans;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String ques) {
        question = ques;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String ans) {
        answer = ans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}