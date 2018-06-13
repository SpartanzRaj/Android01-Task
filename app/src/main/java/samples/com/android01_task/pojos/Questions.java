package samples.com.android01_task.pojos;

import com.google.gson.annotations.SerializedName;

/**
 * This getter and Setter method is represent the individual block from the json response
 * **/
public class Questions {

    @SerializedName("question")
    private String question;

    @SerializedName("Answer")
    private String answer;
    public String getQuestion() {
        return question;
    }

    public Questions setQuestion(String question) {
        this.question = question;

        return this;
    }

    public Questions(String question) {
        this.question = question;
    }


    public String getAnswer() {
        return answer;
    }

    public Questions setAnswer(String answer) {
        this.answer = answer;
        return this;
    }



}
