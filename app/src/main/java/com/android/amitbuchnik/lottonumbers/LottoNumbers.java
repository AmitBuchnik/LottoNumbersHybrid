package com.android.amitbuchnik.lottonumbers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class LottoNumbers extends AppCompatActivity {

    private WebView browser = null;
    private WebSettings webSettings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_numbers);

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
