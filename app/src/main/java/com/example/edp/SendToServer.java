package com.example.edp;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendToServer  extends AsyncTask<String, Void, Void> {
    Socket s;
    DataOutputStream dos;
    PrintWriter pw;
    @Override
    protected Void doInBackground(String... voids) {

        String text = voids[0];

        try
        {
            s=new Socket("192.168.1.16",7800);
            pw= new PrintWriter(s.getOutputStream());
            pw.write(text);
            pw.flush();
            pw.close();
            s.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }




        return null;
    }
}
