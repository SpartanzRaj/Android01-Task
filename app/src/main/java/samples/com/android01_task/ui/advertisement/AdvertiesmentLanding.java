package samples.com.android01_task.ui.advertisement;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import samples.com.android01_task.R;
import samples.com.android01_task.utils.ProgressView;


/**
 * This activity contain's webview component,if the banner ads are clicked, it loads the advertisement provider url
 * currently, the url is {@linkplain "https://courses.learncodeonline.in"}
 * **/
public class AdvertiesmentLanding extends AppCompatActivity {

    WebView landingPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertiment_landing);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        landingPage = (WebView)findViewById(R.id.webview);
        landingPage.getSettings().setJavaScriptEnabled(true);
        landingPage.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {

                ProgressView.dismissProgress();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        ProgressView.showProgress(this);
        landingPage.loadUrl("https://courses.learncodeonline.in");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
