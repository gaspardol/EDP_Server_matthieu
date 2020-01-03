package com.example.edp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static android.media.AudioManager.STREAM_MUSIC;
import static android.media.AudioRecord.getMinBufferSize;
import static android.media.AudioTrack.MODE_STREAM;

public class PlaySentence extends AppCompatActivity {

    //audio variables
    static final int sampleFreq = 44100;
    static final int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
    static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    static final int streamType = STREAM_MUSIC;
    static final int audioMode = MODE_STREAM;
    static final int bufferSize = getMinBufferSize(sampleFreq, channelConfig, audioMode);


    //socket variables
    public Socket mySocket;
    private int SERVERPORT = 7800;
    private static final String SERVER_IP = "192.168.1.16";

    String debugStr;
    String str1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sentence);
    }

    public void onClickListen(View view) {
        //call async task
        new Listen().execute();
    }


    private class Listen extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {
                //get IP and port number
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                Log.d(debugStr, "In initial listen connect");
                //create socket
                mySocket = new Socket(serverAddr, SERVERPORT);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                if (mySocket == null) {
                    str1 = "Socket became null, listener";
                    return null;
                }



                try {
                    //creates buffer to hold incoming audio data
                    byte [] audioBuffer = new byte[4096];

                    //creates input stream readers to read incoming data
                    BufferedInputStream myBis = new BufferedInputStream(mySocket.getInputStream());
                    DataInputStream myDis = new DataInputStream(myBis);

                    Log.d(debugStr, "Input created, listener");
                    AudioTrack myAudioTrack = new AudioTrack(streamType, sampleFreq, channelConfig, audioEncoding, bufferSize, audioMode);
                    //Log.d(debugStr, String.valueOf(mySocket.getInputStream().read(audioBuffer)));

                    Log.d(debugStr, "track made");
                    // Read the file into the music array.
                    int i = 0;

                    while (mySocket.getInputStream().read(audioBuffer) != -1) {

                        audioBuffer[audioBuffer.length-1-i] = myDis.readByte();
                        myAudioTrack.play();
                        myAudioTrack.write(audioBuffer, 0, audioBuffer.length);
                        i++;
                    }



                    //close input streams
                    myDis.close();
                    myBis.close();


                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    mySocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return null;
            }
        }


    }
}
