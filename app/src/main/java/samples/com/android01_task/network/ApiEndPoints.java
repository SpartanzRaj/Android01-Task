package samples.com.android01_task.network;

import retrofit2.Call;
import retrofit2.http.GET;
import samples.com.android01_task.pojos.QuestionResponse;

public interface ApiEndPoints {

    @GET("android/datastructure.json")
    Call<QuestionResponse> getQuestionList();


}
