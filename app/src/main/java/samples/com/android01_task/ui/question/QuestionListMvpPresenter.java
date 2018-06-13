package samples.com.android01_task.ui.question;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samples.com.android01_task.R;
import samples.com.android01_task.network.ApiClient;
import samples.com.android01_task.network.ApiEndPoints;
import samples.com.android01_task.pojos.QuestionResponse;
import samples.com.android01_task.pojos.Questions;

public class QuestionListMvpPresenter implements IQuestionListMvpPresenter {


    public QuestionListMvpPresenter(QuestionListMvpView questionListMvpView) {
        this.questionListMvpView = questionListMvpView;
    }

    private QuestionListMvpView questionListMvpView;

    /**Once the activity call's startLoadRemoteData(), it starts network call to get data from server
     If the response is successfull, data is passed to messDataWithAdvertisement(List<Questions> data)
     If the response is not successfull, onError() is called with error message
     **/
    @Override
    public void startLoadRemoteData(final Context context) {

        if (!checkNetworkIsConnection(context))
        {
            questionListMvpView.onError(context.getString(R.string.network_connection));
            return;
        }
        ApiEndPoints endPoints = ApiClient.getRetrofitClient().create(ApiEndPoints.class);
        Call<QuestionResponse> questionResponseCall = endPoints.getQuestionList();
        questionResponseCall.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    ArrayList finalisedData=messDataWithAdvertisement(response.body().getQuestionList());
                    questionListMvpView.onDataLoadsSuccessfully(finalisedData);
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {

                questionListMvpView.onError(context.getString(R.string.error_msg));
            }
        });
    }


    private boolean checkNetworkIsConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    /**
     * messDataWithAdvertisement(List<Questions> data) method loads the advertisement banner image within the existing datalist.
     * After every three, question an add banner is added.
     * **/
    private ArrayList<Object> messDataWithAdvertisement(List<Questions> data)
    {
        ArrayList<Object> editedData = new ArrayList<>();
        for (int i=0;i<data.size();i++)
        {
            if (editedData.size()%3==0&& i!=0)
            {
                editedData.add(R.drawable.banner_add_src);
                editedData.add(data.get(i));
            }
            else
            {
                editedData.add(data.get(i));
            }

        }

        //editedData.add(R.drawable.banner_add_src);
        return editedData;
    }

}
