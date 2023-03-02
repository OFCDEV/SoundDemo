package com.example.sounddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mplayer;
    AudioManager audioManager;

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

        audioManager =(AudioManager) getSystemService(AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        SeekBar volumeControll = (SeekBar) findViewById(R.id.seekBar);

        volumeControll.setMax(maxVolume);
        volumeControll.setProgress(curVolume);
        volumeControll.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC , progress, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        final SeekBar scrubber = (SeekBar) findViewById(R.id.scrubber);

        scrubber.setMax(mplayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            //we're going to run in here immediately and then every
            //
            //second.
            //
            //So that's what this whole chunk of code is doing.
            public void run() {
                scrubber.setProgress(mplayer.getCurrentPosition());
            }
        },0,100); //Starting from 0 to every 100miliseconds


        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                mplayer.seekTo(progress);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}