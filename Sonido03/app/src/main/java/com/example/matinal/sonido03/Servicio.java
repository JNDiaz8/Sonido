package com.example.matinal.sonido03;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;


public class Servicio extends Service {

    MediaPlayer player;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.codigo_davinci);
        player.setLooping(true);
        Toast.makeText(this, "SERVICIO CREADO", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onStart(Intent intent, int startid){
        player.start();
        Toast.makeText(this, "SERVICIO INICIADO", Toast.LENGTH_LONG).show();

    }

    public void onStop(){
        player.stop();
        Toast.makeText(this, "SERVICIO PARADO", Toast.LENGTH_LONG).show();
    }


}
