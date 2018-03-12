package com.example.thebestone.sismaalghozali;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thebestone.sismaalghozali.R;

/**
 * Created by thebestone on 21/02/18.
 */

public class Donatur extends AppCompatActivity {

    TabItem tabLapUang, tabInven, tabLapKegiatan;
    TabLayout tabLayout;
    WebView webViewDonatur;

    ProgressDialog pd;

    LinearLayout linearError;
    ImageButton imgRefresh;

    ImageView imgBack, imgHome, imgGo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_donatur);

        init();

        webViewDonatur.loadUrl("https://alghozalimalang.com/wp/category/Inventaris/");

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        webViewDonatur.clearHistory();
                        webViewDonatur.loadUrl("https://alghozalimalang.com/wp/category/Inventaris/");
                        break;
                    case 1:
                        webViewDonatur.clearHistory();
                        webViewDonatur.loadUrl("https://alghozalimalang.com/wp/category/laporan-keuangan/");
                        break;
                    case 2:
                        webViewDonatur.clearHistory();
                        webViewDonatur.loadUrl("https://alghozalimalang.com/wp/category/laporan-kegiatan/");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webViewDonatur.reload();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webViewDonatur.goBack();
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertKeluar();
            }
        });

        imgGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webViewDonatur.goForward();
            }
        });

    }

    private void init() {
        imgBack = findViewById(R.id.imgGobackDonatur);
        imgHome = findViewById(R.id.imgHomeDonatur);
        imgGo = findViewById(R.id.imgGoForwordDonatur);

        imgRefresh = findViewById(R.id.imgRefresh);
        linearError = findViewById(R.id.layoutError);

        pd = new ProgressDialog(this);

        tabInven = findViewById(R.id.tabInven);
        tabLapUang = findViewById(R.id.tabUang);
        tabLapKegiatan = findViewById(R.id.tabKegiatan);

        tabLayout = findViewById(R.id.tabLayoutDonatur);

        webViewDonatur = findViewById(R.id.webDonatur);

        webViewDonatur.getSettings().setJavaScriptEnabled(true);
        webViewDonatur.getSettings().setLoadsImagesAutomatically(true);
        webViewDonatur.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webViewDonatur.getSettings().setBuiltInZoomControls(true);
        webViewDonatur.getSettings().setDisplayZoomControls(false);
        webViewDonatur.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if (url.endsWith(".pdf")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    // if want to download pdf manually create AsyncTask here
                    // and download file
                    return true;
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                onProgress();
                linearError.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }



            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                linearError.setVisibility(View.VISIBLE);
            }
        });
    }

    public void onProgress(){
        pd.setMessage("Memuat Konten...");
        pd.setCancelable(false);
        pd.show();

//        AlertDialog.Builder aler = new AlertDialog.Builder(this);
//        aler
//                .setView(R.layout.loading_content)
//                .setCancelable(false);
//
//        dialog = aler.create();
//        dialog.show();

    }

    public void alertKeluar() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Donatur.this);
        alert.setTitle("Keluar").setMessage("Kembali Menu Awal?").setCancelable(false)
                .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Donatur.this, SelectStatus.class));
                        finish();
                    }
                })
                .setNegativeButton("Batal", null)
                .create().show();
    }
}
