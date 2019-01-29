package com.matinal.sonido02;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    SeekBar sb_duracion;
    MediaPlayer mediaPlayer;
    TextView titulo, duracion;
    double tiempoFinal, tiempoPasado;
    int avance = 2000;
    int retroceso = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = findViewById(R.id.titulo);
        duracion = findViewById(R.id.duracion);
        sb_duracion = findViewById(R.id.sb_duracion);

        sb_duracion.setClickable(false);

        mediaPlayer = MediaPlayer.create(this, R.raw.codigo_davinci);
        tiempoFinal = mediaPlayer.getDuration();
        sb_duracion.setMax((int) tiempoFinal);
        titulo.setText("codigo_davinci.mp3");

    }


    public void play(View view) {
        mediaPlayer.start();
        tiempoPasado = mediaPlayer.getCurrentPosition();
        sb_duracion.setProgress((int) tiempoPasado);
        controladorTiempo.postDelayed(actualizaBarra, 100);
    }

    public void pause(View view) {
        mediaPlayer.pause();

    }

    public void avanzado(View view) {
        if ((tiempoPasado + avance) <= tiempoFinal) {
            tiempoPasado = tiempoPasado + avance;
            mediaPlayer.seekTo((int) tiempoPasado);
        }
    }

    public void retroceso(View view) {
        if ((tiempoPasado - retroceso) > 0) {
            tiempoPasado = tiempoPasado - retroceso;
            mediaPlayer.seekTo((int) tiempoPasado);
        }
    }

    private Runnable actualizaBarra = new Runnable() {

        @Override
        public void run() {
            tiempoPasado = mediaPlayer.getCurrentPosition();
            sb_duracion.setProgress((int) tiempoPasado);
            double timeRestante = tiempoFinal - tiempoPasado;
            duracion .setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) timeRestante),
                    TimeUnit.MILLISECONDS.toSeconds((long) timeRestante) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    timeRestante))));
            controladorTiempo.postDelayed(this, 100);
        }
    };

    private Handler controladorTiempo = new Handler();


}
