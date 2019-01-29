package com.example.matinal.sonido04;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageButton microfono, pause, play;
    private MediaRecorder miGrabadora;
    private String salida = null;
    MediaPlayer repro = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        microfono = findViewById(R.id.microfono);
        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);

        microfono.setEnabled(true);
        pause.setEnabled(false);
        play.setEnabled(false);

        salida = Environment.getExternalStorageDirectory().getAbsolutePath()+"/grabado.3gp";
        miGrabadora = new MediaRecorder();
        miGrabadora.setAudioSource(MediaRecorder.AudioSource.MIC);
        miGrabadora.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        miGrabadora.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        miGrabadora.setOutputFile(salida);

        microfono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    miGrabadora.prepare();
                    miGrabadora.start();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }

                microfono.setEnabled(false);
                pause.setEnabled(true);
                play.setEnabled(false);

                Toast.makeText(getApplicationContext(), "GRABANDO ...", Toast.LENGTH_SHORT).show();
            }

        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                miGrabadora.stop();
                miGrabadora.release();
                miGrabadora = null;

                play.setEnabled(true);
                pause.setEnabled(false);
                microfono.setEnabled(false);

                Toast.makeText(getApplicationContext(), "FIN DE LA GRABACIÓN", Toast.LENGTH_SHORT).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    repro.setDataSource(salida);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    repro.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                repro.start();
                Toast.makeText(getApplicationContext(), "REPRODUCIENDO", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
