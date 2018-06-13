package samples.com.android01_task.ui.answers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import samples.com.android01_task.R;
import samples.com.android01_task.ui.advertisement.AdvertiesmentLanding;
import samples.com.android01_task.utils.ProgressView;

/**
 * We get the user clicked position from the previous activity, by using the position content is feteched and show's in this activity
 * This actvity follows the MVP Architecture pattern
 * **/

public class AnswerDetailActivity extends AppCompatActivity implements AnswerDetailMvpView{

    private int clickedPosition;
    private IAnswerDetailMvpPresenter mvpPresenter;
    TextView showQuestion,showAnswer;
    ImageView adsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        clickedPosition=getIntent().getIntExtra("position",0);
        showAnswer=(TextView)findViewById(R.id.answer);
        showQuestion=(TextView)findViewById(R.id.question);
        adsView=(ImageView)findViewById(R.id.banner_add);
        mvpPresenter = new AnswerDetailMvpPresenter(this);


        adsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adsIntent = new Intent(AnswerDetailActivity.this, AdvertiesmentLanding.class);
                startActivity(adsIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ProgressView.showProgress(this);
        mvpPresenter.startLoadRemoteData(clickedPosition);
    }

    /**
     * If the data is ready to show, onDataLoads() is triggered from presenter{@link AnswerDetailMvpPresenter}
     * **/
    @Override
    public void onDataLoads(String question, String answer) {
        ProgressView.dismissProgress();
        showQuestion.setText(question);
        showAnswer.setText(answer);
    }

    /**
     * If any error occurs, while loading data onError() triggered by presenter{@link AnswerDetailMvpPresenter}
     * **/

    @Override
    public void onError(String errorMsg) {
        ProgressView.dismissProgress();
        Toast.makeText(this, "Sorry "+errorMsg+". Please try again after sometime", Toast.LENGTH_SHORT).show();
    }


}
