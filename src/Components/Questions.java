package Components;

public class Questions {
    private String question;
    private String question2;

    private String question3;
    private String question4;

    private String question5;

    private String question6;

    private String answer;

    private String answer2;
    private String answer3;
    private String answer4;

    private String answer5;

    private String answer6;

    public Questions(String q, String a,String q2, String b, String q3, String c, String q4, String d, String q5, String e, String q6, String f) {
        question = q;
        question2 = q2;
        question3 = q3;
        question4 = q4;
        question5 = q5;
        question6 = q6;
        answer = a;
        answer2 = b;
        answer3 = c;
        answer4 = d;
        answer5 = e;
        answer6 = f;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String q) {
        question = q;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String q2) {
        question2 = q2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String q3) {
        question3 = q3;
    }
    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String q4) {
        question4 = q4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String q5) {
        question5 = q5;
    }
    public String getQuestion6() {
        return question6;
    }

    public void setQuestion6(String q6) {
        question6 = q6;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String a) {
        answer = a;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String b) {
        answer2 = b;
    }
    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String c) {
        answer3 = c;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String d) {
        answer4 = d;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String e) {
        answer5 = e;
    }

    public String getAnswer6() {
        return answer6;
    }

    public void setAnswer6(String f) { answer6 = f; }

    public String toString() {
        return "[]";
    }
}
