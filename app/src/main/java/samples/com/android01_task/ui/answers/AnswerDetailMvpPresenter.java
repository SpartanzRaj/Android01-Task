package samples.com.android01_task.ui.answers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samples.com.android01_task.network.ApiClient;
import samples.com.android01_task.network.ApiEndPoints;
import samples.com.android01_task.pojos.QuestionResponse;
import samples.com.android01_task.pojos.Questions;

public class AnswerDetailMvpPresenter implements IAnswerDetailMvpPresenter {

    public AnswerDetailMvpPresenter(AnswerDetailMvpView answerDetailMvpView) {
        this.answerDetailMvpView = answerDetailMvpView;
    }

    private AnswerDetailMvpView answerDetailMvpView;

    @Override
    public void startLoadRemoteData(final int position) {
        ApiEndPoints endPoints = ApiClient.getRetrofitClient().create(ApiEndPoints.class);
        Call<QuestionResponse> questionResponseCall = endPoints.getQuestionList();
        questionResponseCall.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    Questions questionResponse = response.body().getQuestionList().get(position);
                    answerDetailMvpView.onDataLoads(questionResponse.getQuestion(),questionResponse.getAnswer());

                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                answerDetailMvpView.onError(t.getMessage());
            }
        });
    }
}
