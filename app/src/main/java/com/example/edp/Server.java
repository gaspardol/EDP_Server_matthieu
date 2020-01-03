package com.example.edp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Server extends AppCompatActivity {


    TextView tv;
    String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        tv=findViewById(R.id.textView);

       //get the text
        text=getIntent().getExtras().getString("Value");
        //just display it (did it to make sure that the text was passed to this activity correctly)
        tv.setText(text);



        }
    //send it to a server via a socket
    public void Send(View V){
        MessageSender messageSender = new MessageSender();
        messageSender.execute(text);
    }

}

