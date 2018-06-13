package samples.com.android01_task.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import samples.com.android01_task.R;

public class ProgressView {

    private static Dialog progressBar=null;

    private static Dialog createProgressBar(Context context)
    {
        if (progressBar == null)
        {
            progressBar = new Dialog(context);
            progressBar.setContentView(R.layout.progress_view);
            progressBar.setCancelable(false);
        }

        return progressBar;
    }

    public static void showProgress(Context context)
    {
        createProgressBar(context);
        progressBar.show();
    }

    public static void dismissProgress()
    {
        if (progressBar != null)
        {
            progressBar.dismiss();
            progressBar=null;
        }
    }
}
