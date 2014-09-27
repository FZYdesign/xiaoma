package com.gowithwind.xiaoma;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.inputmethodservice.InputMethodService;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class IMEService extends InputMethodService {
	View view;
	private WebView wv;

	@Override
	public View onCreateInputView() {
		wv =(WebView) getLayoutInflater().inflate(
				R.layout.input, null);
		//wv=(WebView) view.findViewById(R.id.webView1);
		setupWebview();
		return wv;
	}
	

	@SuppressLint("JavascriptInterface")
	private void setupWebview() {
		// TODO Auto-generated method stub
		wv.getSettings().setJavaScriptEnabled(true);
		wv.addJavascriptInterface(this, "android");
		wv.setWebChromeClient(new WebChromeClient() {
			public boolean onConsoleMessage(ConsoleMessage cm) {
				Log.d("apps",
						"web : " + cm.message() + " -- From line "
								+ cm.lineNumber() + " of " + cm.sourceId());
				return true;
			}
		});
		wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.d("apps",url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		String html=getFromAssets("www/main.html");
		//wv.loadUrl("file:///android_asset/www/view/" + "main.html?dsds");
		wv.loadDataWithBaseURL("file:///android_asset/www/", html, "text/html", "UTF-8", null);
	}

	 public String getFromAssets(String fileName){  
	        String result = "";  
	            try {  
	                InputStream in = getResources().getAssets().open(fileName);  
	                int lenght = in.available();  
	                byte[]  buffer = new byte[lenght];  
	                in.read(buffer);  
	                result=new String(buffer); 
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	            return result;  
	}
	@JavascriptInterface
	public void input(String s) {
		getCurrentInputConnection().commitText(s, 1); 
	}
	@JavascriptInterface
	public void backspace() {
		getCurrentInputConnection().deleteSurroundingText(1, 0);
	}
}
