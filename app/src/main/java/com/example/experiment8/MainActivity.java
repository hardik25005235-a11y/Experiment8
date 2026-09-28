package com.example.experiment8;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    WebView webView;

    String[] websites = {
            "Google",
            "YouTube",
            "GitHub",
            "Wikipedia"
    };

    String[] urls = {
            "https://www.google.com",
            "https://www.youtube.com",
            "https://github.com",
            "https://www.wikipedia.org"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        webView = findViewById(R.id.webView);

        // Enable JavaScript in WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Open websites inside WebView
        webView.setWebViewClient(new WebViewClient());

        // Display websites in GridView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                websites
        );

        gridView.setAdapter(adapter);

        // Handle GridView item click
        gridView.setOnItemClickListener((parent, view, position, id) -> {

            webView.loadUrl(urls[position]);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Home");
        menu.add("Google");
        menu.add("About");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title = item.getTitle().toString();

        if (title.equals("Home")) {

            webView.loadUrl("https://www.google.com");

        } else if (title.equals("Google")) {

            webView.loadUrl("https://www.google.com");

        } else if (title.equals("About")) {

            Toast.makeText(
                    this,
                    "Experiment 8 - Menus and WebView",
                    Toast.LENGTH_LONG
            ).show();
        }

        return true;
    }
}
