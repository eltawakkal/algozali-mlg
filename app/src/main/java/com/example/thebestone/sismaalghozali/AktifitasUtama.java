package com.example.thebestone.sismaalghozali;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thebestone.sismaalghozali.fragment.FragmentBeranda;
import com.example.thebestone.sismaalghozali.fragment.FragmentInventaris;
import com.example.thebestone.sismaalghozali.fragment.WebviewMain;

public class AktifitasUtama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentBeranda fragmentBeranda;
    FragmentInventaris fragmentInventaris;
    WebviewMain webviewMain;
    WebView webView;
    ProgressDialog pd;

    Dialog dialog;

    LinearLayout linearError;
    ImageButton imgRefresh;

    ImageView imgGoback, imgHome, imgGoForwod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktifitas_utama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initFragments();

        webView.loadUrl("https://alghozalimalang.com/wp/");

        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
            }
        });

        imgGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goBack();
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertKeluar();
            }
        });

        imgGoForwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goForward();
            }
        });

    }

    private void initFragments() {
        imgGoback = findViewById(R.id.imgGobackUtama);
        imgHome = findViewById(R.id.imgHomeUtama);
        imgGoForwod = findViewById(R.id.imgGoForwordUtama);

        linearError = findViewById(R.id.layoutErrorUtama);
        imgRefresh = findViewById(R.id.imgRefreshUtama);

        fragmentBeranda = new FragmentBeranda();
        fragmentInventaris = new FragmentInventaris(this);
        webviewMain = new WebviewMain("https://alghozalimalang.com/wp/");

        pd = new ProgressDialog(this);
        webView = findViewById(R.id.mWebViewUtama);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                onProgress();
                if (url.endsWith(".pdf")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    // if want to download pdf manually create AsyncTask here
                    // and download file
                }
                linearError.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
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
        AlertDialog.Builder alert = new AlertDialog.Builder(AktifitasUtama.this);
        alert.setTitle("Keluar").setMessage("Kembali Menu Awal?").setCancelable(false)
                .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(AktifitasUtama.this, SelectStatus.class));
                        finish();
                    }
                })
                .setNegativeButton("Batal", null)
                .create().show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            alertKeluar();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.aktifitas_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
//            startActivity(new Intent(AktifitasUtama.this, ProfileActivity.class));
            showMe();
            return true;}
//        } else if (id == R.id.actionBack) {
//            webView.goBack();
//            return true;
//        } else if (id == R.id.actionForword) {
//            webView.goForward();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_beranda:
//                webviewMain = new WebviewMain("https://alghozalimalang.com/wp/");
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, webviewMain);
                webView.loadUrl("https://alghozalimalang.com/wp/");
                break;
            case R.id.nav_quotes:
                webView.loadUrl("https://alghozalimalang.com/wp/category/quotes/");
                break;
//            case R.id.nav_inventaris:
//                webView.loadUrl("https://alghozalimalang.com/wp/category/inventaris/");
//                break;
//            case R.id.nav_lapKeuangan:
//                webView.loadUrl("https://alghozalimalang.com/wp/category/laporan-keuangan/");
//                break;
//            case R.id.nav_kegiatan:
//                webView.loadUrl("https://alghozalimalang.com/wp/category/kegiatan/");
//                break;
            case R.id.nav_jadwal:
                webView.loadUrl("https://alghozalimalang.com/wp/jadwal-sholat-area-malang/");
                break;
            case R.id.nav_pengumuman:
                webView.loadUrl("https://alghozalimalang.com/wp/category/pengumuman/");
                break;
            case R.id.nav_acara:
                webView.loadUrl("https://alghozalimalang.com/wp/category/Undangan/");
                break;

            case R.id.nav_send:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"fadhli.tech@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Masukan");
                email.setType("plain/text");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Segera Hadir!", Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showMe(){
        View view = getLayoutInflater().inflate(R.layout.content_profile, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view
        );
        alert.setPositiveButton("Close", null);
        alert.setCancelable(false);

        Dialog dialog = alert.create();

        FloatingActionButton fabCall = view.findViewById(R.id.fabCall);
        FloatingActionButton fabEmail = view.findViewById(R.id.fabEmail);

        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:082131503923"));
                startActivity(intent);
            }
        });

        fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"ayumaghfurroh95@gmail.com"});
                email.setType("plain/text");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        dialog.show();
    }
}
