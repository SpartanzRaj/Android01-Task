package samples.com.android01_task.base;

/**
 * All the {@link samples.com.android01_task.ui.answers.AnswerDetailMvpView,samples.com.android01_task.ui.question.QuestionListMvpView}
 * have onError() method, so the methods is created in base view and used in all places
 * **/
public interface MvpBaseView {
    void onError(String errorMsg);
}
