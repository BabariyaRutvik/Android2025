package com.example.imageupload;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MediaPlayer extends AppCompatActivity {

    ImageView image_play;
    ImageView image_pause;
    ImageView image_stop;

    TextView text_video;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);


        image_play = findViewById(R.id.image_play);
        image_pause = findViewById(R.id.image_pause);
        image_stop = findViewById(R.id.image_stop);
        text_video = findViewById(R.id.text_video);




        // For Playing a music
        android.media.MediaPlayer mediaPlayer = new android.media.MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        String epath = "android.resource://" + getPackageName() + "/" + R.raw.sound_;
       //  String onlineAudio = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
        Uri uri = Uri.parse(epath);
        try {
            mediaPlayer.setDataSource(this,uri);
            mediaPlayer.prepare();

        } catch (Exception e) {
            e.printStackTrace();
        }

        image_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

            }
        });

        image_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        image_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mediaPlayer.pause();
               mediaPlayer.seekTo(0);
            }
        });


        //

        text_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaPlayer.this,VideoViewActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    }