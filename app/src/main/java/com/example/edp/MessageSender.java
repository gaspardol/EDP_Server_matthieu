package com.example.edp;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends Server{

    public void main(String[] args) throws IOException {

        String text;

        text=getIntent().getExtras().getString("Value");

        Socket socket = new Socket();
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create a data output stream from the output stream so we can send data through it
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        Toast.makeText(getApplicationContext(), "Sent to server", Toast.LENGTH_SHORT).show();

        System.out.println("Sending string to the ServerSocket");

        // write the message we want to send
        dataOutputStream.writeUTF(text);
        dataOutputStream.flush(); // send the message
        dataOutputStream.close(); // close the output stream when we're done.

        System.out.println("Closing socket and terminating program.");
        socket.close();
    }


}
    /*Socket s;
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
    }*/
