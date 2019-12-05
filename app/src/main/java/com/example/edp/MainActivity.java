package com.example.edp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSad = findViewById(R.id.buttonsad);
        Button buttonNeutral = findViewById(R.id.buttonneutral);
        Button buttonHappy = findViewById(R.id.buttonhappy);

        buttonHappy.setOnClickListener(this);
        buttonNeutral.setOnClickListener(this);
        buttonSad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonhappy:
                openHappySelected();
                break;
            case R.id.buttonneutral:
                openNeutralSelected();
                break;
            case R.id.buttonsad:
                openSadSelected();
                break;

        }

    }
    public void openSadSelected(){
        Intent intent1 = new Intent(this, SadSelected.class);
        startActivity(intent1);

    }
    public void openHappySelected(){
        Intent intent2 =new Intent(this, HappySelected.class);

    }
    public void openNeutralSelected(){
        Intent intent3= new Intent (this, NeutralSelected.class);

    }

}
