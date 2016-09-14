package com.android.amitbuchnik.lottonumbers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class LottoNumbers extends AppCompatActivity {

    private WebView browser = null;
    private WebSettings webSettings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_numbers);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.drawable.action_bar_bg);
        setSupportActionBar(myToolbar);

        WebView.setWebContentsDebuggingEnabled(true);
        browser = (WebView) findViewById(R.id.webView);
        browser.setWebViewClient(new URLIntercepter());
        webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); // allow local storage
        webSettings.setAllowUniversalAccessFromFileURLs(true); //allow ajax from file url
        browser.loadUrl("file:///android_asset/www/lottonumbers.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            browser.loadUrl("file:///android_asset/www/lottonumbers.html#settings");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (browser.isFocused() && browser.canGoBack() && browser.getUrl().contains("settings")) {
            browser.goBack();
        } else{
            super.onBackPressed();
            finish();

            /*new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exit!")
                    .setMessage("Are you sure you want to close?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();*/
        }
    }
}
