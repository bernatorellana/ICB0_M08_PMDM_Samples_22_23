package com.example.webviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webviewapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGo.setOnClickListener(this);

        // Configurem el WebView per a que
        // carregui les pàgines insitu.
        // També customitzen el WebViewClient
        // per tal que inici/acabi la descàrrega de la
        // pàgina mostrant/amagant un ProgressBar
        WebViewClient client = new WebViewClient() {


            /**
             * Restricció de navegació, només URLS al domini de Google.
             * @param view
             * @param request
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return !request.getUrl().toString().contains("google.com");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.pgrBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.pgrBar.setVisibility(View.INVISIBLE);
            }
        };
        binding.webPage.setWebViewClient(client);

        WebChromeClient chromeClient = new WebChromeClient();
    }

    @Override
    public void onClick(View view) {
        binding.webPage.loadUrl(binding.edtUrl.getText().toString());
    }
}