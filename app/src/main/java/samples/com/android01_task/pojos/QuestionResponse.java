package samples.com.android01_task.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class loads the list of {@link Questions} object
 * **/
public class QuestionResponse {

    public List<Questions> getQuestionList() {
        return questionList;
    }

    public QuestionResponse setQuestionList(List<Questions> questionList) {
        this.questionList = questionList;
        return this;
    }

    public QuestionResponse(List<Questions> questionList) {
        this.questionList = questionList;
    }

    @SerializedName("questions")
    private List<Questions> questionList;
}
