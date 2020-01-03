package com.example.edp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class HappySelected extends AppCompatActivity {


    //private static final String TEXT_TO_PRONOUNCE = "text.txt";

    EditText mEditText;
    Button btn;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_selected);

        btn=findViewById(R.id.button_send);
        mEditText = findViewById(R.id.edit_text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HappySelected.this, Server.class);
                txt=mEditText.getText().toString();
                intent.putExtra("Value",txt);
                startActivity(intent);

                finish();
            }
        });
    }

}

    /*public void save(View v) {
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(TEXT_TO_PRONOUNCE, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + TEXT_TO_PRONOUNCE, Toast.LENGTH_LONG).show();
            mEditText.getText().clear();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /*SendToServer sendtoserver = new SendToServer();
        sendtoserver.execute(text);
        Toast.makeText(this, "Sent" + getFilesDir() + "/" + TEXT_TO_PRONOUNCE+"to server", Toast.LENGTH_LONG).show();


    }

    public void load(View v) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(TEXT_TO_PRONOUNCE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");


            }
            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }



    //now create an intend to call the function serve to server

    public void Send(View V) {
        SendToServer sendtoserver = new SendToServer();
        sendtoserver.execute(mEditText.getText().toString());


    }*/

