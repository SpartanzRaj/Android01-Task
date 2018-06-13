package samples.com.android01_task.ui.question;

import java.util.List;

import samples.com.android01_task.base.MvpBaseView;
import samples.com.android01_task.pojos.QuestionResponse;
import samples.com.android01_task.pojos.Questions;

public interface QuestionListMvpView extends MvpBaseView{

    void onDataLoadsSuccessfully(List<Object> data);

}
