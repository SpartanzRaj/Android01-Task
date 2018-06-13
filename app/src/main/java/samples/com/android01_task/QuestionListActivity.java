package samples.com.android01_task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import samples.com.android01_task.pojos.Questions;
import samples.com.android01_task.ui.advertisement.AdvertiesmentLanding;
import samples.com.android01_task.ui.answers.AnswerDetailActivity;
import samples.com.android01_task.ui.question.IQuestionListMvpPresenter;
import samples.com.android01_task.ui.question.QuestionListMvpPresenter;
import samples.com.android01_task.ui.question.QuestionListMvpView;
import samples.com.android01_task.utils.ProgressView;
import samples.com.android01_task.utils.RecyclerViewListener;
import samples.com.android01_task.utils.adapter.QuestionsAdapter;


//This Activity get the list of question's from the server and show's in Recylcerview. Mvp architecture pattern is followed in this activity


public class QuestionListActivity extends AppCompatActivity implements QuestionListMvpView{

    RecyclerView questions;
    QuestionsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    IQuestionListMvpPresenter questionListMvpPresenter;
    ArrayList<Object> questionData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        questions =(RecyclerView)findViewById(R.id.question_list);
        layoutManager = new LinearLayoutManager(this);
        questions.setLayoutManager(layoutManager);
        questionData = new ArrayList<>();
        adapter = new QuestionsAdapter(questionData,this);
        questions.setAdapter(adapter);
        questions.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        questionListMvpPresenter = new QuestionListMvpPresenter(this);
        ProgressView.showProgress(this);
        questionListMvpPresenter.startLoadRemoteData();

    }

        /** Once the data is ready to show in recyclerview, the presenter {@link QuestionListMvpPresenter} triggers onDataLoadsSuccessfully() method.
         * In the onDataLoadsSuccessfully(), listener and updated data for recyclerview  is added**/
    @Override
    public void onDataLoadsSuccessfully(final List<Object> data) {

        ProgressView.dismissProgress();
        questionData.addAll(data);
        adapter.notifyDataSetChanged();

        questions.addOnItemTouchListener(new RecyclerViewListener(new RecyclerViewListener.OnRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (data.get(position) instanceof Questions)
                {
                    Intent answerActivity = new Intent(QuestionListActivity.this, AnswerDetailActivity.class);
                    answerActivity.putExtra("position",position-(position/3));
                    startActivity(answerActivity);
                }
                else
                {
                    Intent adsActivity = new Intent(QuestionListActivity.this,AdvertiesmentLanding.class);
                    startActivity(adsActivity);
                }

            }
        },this));

    }

    /**
     * If any error happens while loading or processing the data , presenter{@link QuestionListMvpPresenter} triggers onError() with error message
     *
     */

    @Override
    public void onError(String errorMsg) {

        ProgressView.dismissProgress();
        Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();
    }
}
