package com.example.thebestone.sismaalghozali.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;

import com.example.thebestone.sismaalghozali.R;

/**
 * Created by thebestone on 04/02/18.
 */

@SuppressLint("ValidFragment")
public class FragmentInventaris extends FragmentBeranda {

    Activity activity;
    ProgressDialog pd;

    public FragmentInventaris(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inventaris, null);

        WebView webView = v.findViewById(R.id.myWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.loadUrl("https://alghozalimalang.com/wp/");
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                onProgress();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }
        });

        return v;
    }

    public void onProgress(){
        pd = new ProgressDialog(getContext());
        pd.setMessage("Memuat Konten");
        pd.setCancelable(false);
        pd.show();
    }
}
