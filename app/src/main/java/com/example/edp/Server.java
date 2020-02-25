package com.example.edp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Server extends AppCompatActivity {


    TextView tv;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        Toast.makeText(getApplicationContext(), "Sent to server", Toast.LENGTH_SHORT).show();

        tv = findViewById(R.id.textView);

        //get the text
        text = getIntent().getExtras().getString("Value");
        //just display it (did it to make sure that the text was passed to this activity correctly)
        tv.setText(text);

        //Intent intent1 =new Intent(Server.this,MessageSender2.class);
        //intent1.putExtra("Value", text);

        //startActivity(intent1);
        //finish();


    }


}



