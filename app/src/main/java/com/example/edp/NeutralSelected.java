package com.example.edp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NeutralSelected extends AppCompatActivity {

    private static final String TEXT_TO_PRONOUNCE= "text.txt";

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neutral_selected);


    mEditText= findViewById(R.id.edit_text);

    }

    public void save (View v){
        String text = mEditText.getText().toString();
        FileOutputStream fos= null;

        try{
            fos=openFileOutput(TEXT_TO_PRONOUNCE,MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this,"Saved to"+getFilesDir()+"/"+TEXT_TO_PRONOUNCE,Toast.LENGTH_LONG).show();

            mEditText.getText().clear();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (fos!=null){
                try{
                    fos.close();

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public void load(View v){
        FileInputStream fis=null;

        try {
            fis =openFileInput(TEXT_TO_PRONOUNCE);
            InputStreamReader isr =new InputStreamReader(fis);
            BufferedReader br= new BufferedReader(isr);
            StringBuilder sb= new StringBuilder();
            String text;

            while ((text = br.readLine())!=null) {
                sb.append(text).append("\n");


            }
            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (fis!=null){
                try{
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }


}
