package com.example.sounddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mplayer;

    public void playAudio(View view){

        mplayer.start();
    }
    public void pauseAudio(View view){

        mplayer.stop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mplayer = MediaPlayer.create(this, R.raw.music);
    }
}