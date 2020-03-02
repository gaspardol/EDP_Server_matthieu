package com.example.edp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Play extends AppCompatActivity implements View.OnClickListener{

    WebView web;
    private TextView mTextViewReplyFromServer;
    private EditText mEditTextSendMessage;
    String ip ="192.168.91.33";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mEditTextSendMessage = (EditText) findViewById(R.id.edt_send_message);
        Button buttonSend = (Button) findViewById(R.id.button4);
        buttonSend.setOnClickListener((View.OnClickListener) this);
        Button button3Send = (Button) findViewById(R.id.button3);
        button3Send.setOnClickListener((View.OnClickListener) this);
        Button button2Send = (Button) findViewById(R.id.button2);
        button2Send.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        final WebView wbv = findViewById(R.id.webview);
        String head="http://";
        final String url=head.concat(ip);
        switch (v.getId()) {
            case R.id.button4:
                sendMessage(mEditTextSendMessage.getText().toString(),"happy");
                wbv.loadUrl(url.concat(":8002/v1_newloader.gif"));
                new CountDownTimer(11000, 1000) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        MediaPlayer mp=new MediaPlayer();
                        try {
                            mp.setDataSource(url.concat(":8004/wav/test_001.wav"));
                            mp.prepare();
                            mp.start();
                            wbv.loadUrl(url.concat(":8002/empty.html"));
                        }catch(Exception e){e.printStackTrace();}
                    }
                }.start();
                break;
            case R.id.button3:
                sendMessage(mEditTextSendMessage.getText().toString(),"neutral");
                wbv.loadUrl(url.concat(":8002/v1_newloader.gif"));
                new CountDownTimer(11000, 1000) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        MediaPlayer mp=new MediaPlayer();
                        try {
                            mp.setDataSource(url.concat(":8003/wav/test.wav"));
                            mp.prepare();
                            mp.start();
                            wbv.loadUrl(url.concat(":8002/empty.html"));
                        }catch(Exception e){e.printStackTrace();}
                    }
                }.start();
                break;
            case R.id.button2:
                sendMessage(mEditTextSendMessage.getText().toString(),"sad");
                wbv.loadUrl(url.concat(":8002/v1_newloader.gif"));
                new CountDownTimer(11000, 1000) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        MediaPlayer mp=new MediaPlayer();
                        try {
                            mp.setDataSource(url.concat(":8002/wav/test_001.wav"));
                            mp.prepare();
                            mp.start();
                            wbv.loadUrl(url.concat(":8002/empty.html"));
                        }catch(Exception e){e.printStackTrace();}
                    }
                }.start();
                break;
        }

    }

    private void sendMessage(final String msg, final String emo) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int port=0;
                if(emo.equals("happy")){
                    port=9004;
                }else if(emo.equals("neutral")){
                    port=9003;
                }else if(emo.equals("sad")){
                    port=9002;
                }

                try {
                    Socket s = new Socket(ip, port);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }



    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;

        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}