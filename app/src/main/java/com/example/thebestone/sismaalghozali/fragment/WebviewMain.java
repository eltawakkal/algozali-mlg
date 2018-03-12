package com.example.thebestone.sismaalghozali.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.thebestone.sismaalghozali.R;

/**
 * Created by thebestone on 20/02/18.
 */

@SuppressLint("ValidFragment")
public class WebviewMain extends Fragment {

    WebView webView;
    ProgressDialog pd;
    String url;

    public WebviewMain(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web, null);

        init(v);

        return v;
    }

    public void onProgress(){
        pd.setMessage("Memuat Konten...");
        pd.setCancelable(false);
        pd.show();
    }

    private void init(View v) {
        pd = new ProgressDialog(getContext());

//        webView = v.findViewById(R.id.mWebViewFragment);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
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
        webView.loadUrl(url);
    }
}
