package samples.com.android01_task.ui.answers;

import samples.com.android01_task.base.MvpBaseView;

public interface AnswerDetailMvpView extends MvpBaseView{

    void onDataLoads(String question,String answer);

}
