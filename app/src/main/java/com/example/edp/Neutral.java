package com.example.edp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Neutral extends AppCompatActivity {


    EditText mEditText;
    Button btn;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neutral);

        btn=findViewById(R.id.button_send);
        mEditText=findViewById(R.id.edittext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Neutral.this, MessageSender2.class);
                txt=mEditText.getText().toString();
                intent.putExtra("Value",txt);
                startActivity(intent);

                finish();
            }
        });


    }
}
