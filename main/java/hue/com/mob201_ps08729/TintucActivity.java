package hue.com.mob201_ps08729;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class TintucActivity extends AppCompatActivity {
WebView webView;
ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintuc);

        webView= findViewById(R.id.webview);
        imgback= findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent= getIntent();
        String link= intent.getStringExtra("link");


        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}
