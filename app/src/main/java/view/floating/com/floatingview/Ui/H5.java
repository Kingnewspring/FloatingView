package view.floating.com.floatingview.Ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import view.floating.com.floatingview.R;

/**
 * 项目名称：hzsdk
 * 类描述：
 * 创建人：Wangxc
 * 创建时间：2016/7/4 14:14
 * 修改人：Administrator
 * 修改时间：2016/7/4 14:14
 * 修改备注：
 */
public class H5 extends Activity implements View.OnClickListener{

    private WebView webView;
    private String Url;
    private TextView tv_title;
    private RelativeLayout rela_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Url = getIntent().getStringExtra("url");
        setContentView(R.layout.hzsdk_h5);

        initWebview();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initWebview(){

        webView = (WebView) findViewById(R.id.h5);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rela_back = (RelativeLayout) findViewById(R.id.rela_back);
        rela_back.setOnClickListener(this);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);

        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setSupportZoom(true);

        webView.loadUrl(Url);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                tv_title.setText(title);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rela_back) {
            if (!webView.canGoBack())
                finish();
            else
                webView.goBack();
        }
    }
}
