package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class Run extends AppCompatActivity{
    MaterialButton buttonStop;
    VideoView videoRun;
    public static final String CUSTOM_BROADCAST = "com.getmotivation.getmotivation.miosensore_CUSTOM_BROADCAST";
//    MaterialButton dati;
//    MaterialTextView nomeDati;
//    String passi;
//    String kcal;
//    String distanza;
//    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
//        registerReceiver(mReceiver, new IntentFilter(CUSTOM_BROADCAST));
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
//        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
//                "MyApp::MyWakelockTag");
//        wakeLock.acquire();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.interfacciaRun, new TastoStatisticheFragment())
                .commit();
//        dati = findViewById(R.id.dati);
//        nomeDati = findViewById(R.id.nomeDati);
//        nomeDati.setText(" PASSI: ");  //così setto il primo dato da vedere in fase di creazione
//        dati.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (nomeDati.getText().toString() == " PASSI: ") {
//                    nomeDati.setText(" KM PERCORSI: ");
//                    dati.setText(distanza);
//                } else {
//                    if (nomeDati.getText().toString() == " KM PERCORSI: ") {
//                        nomeDati.setText(" KCAL CONSUMATE: ");
//                        dati.setText(kcal);
//                    } else {
//                        nomeDati.setText(" PASSI: ");
//                        dati.setText(passi);
//                    }
//                }
//            }
//        });

//        distanza = String.valueOf(0);
//        kcal=String.valueOf(0);
//        passi=String.valueOf(0);

//        buttonStop = findViewById(R.id.stop_run);
        videoRun = findViewById(R.id.videoRun);
        String path= "android.resource://com.getmotivation.getmotivation.OrlandoAntonioVeneziano/"+R.raw.run_video;
        Uri uri = Uri.parse(path);
        videoRun.setVideoURI(uri);
        videoRun.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        //qua introduco un handler che permette di far ripartire il video più volte in modo da garantirne la riproduzione dentro questa activity
        videoRun.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

//        startFrg();
//
//        buttonStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //per il momento per provare
//                Intent intent = new Intent(Run.this,Home.class);
//                stopFrg();
//                startActivity(intent);
//            }
//        });


    }

//    private void startFrg() {
//        Intent i = new Intent(this, ServiceGetMotivation.class);
//        i.putExtra("TIME",0);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            ContextCompat.startForegroundService(this, i);}
//        else{
//            startService(i);
//        }
//    }
//
//
//    private void stopFrg(){
//        Intent i = new Intent(this, ServiceGetMotivation.class);
//        stopService(i);
//    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onDestroy() {
//        wakeLock.release();
        super.onDestroy();
    }

//        @Override
//    protected void onDestroy() {
//        stopFrg();
//        unregisterReceiver(mReceiver);
//        super.onDestroy();
//    }

//    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // Extract data included in the Intent
//            String message = intent.getAction();
//            passi =intent.getStringExtra("passi");
//            kcal =intent.getStringExtra("kcal");
//            distanza =intent.getStringExtra("distanza");
////            Toast.makeText(Run.this,passi +"  "+ kcal + "  "+distanza , Toast.LENGTH_LONG).show();
////
////            if (nomeDati.getText().toString() == " PASSI: ") {
//                dati.setText(passi);
//            } else {
//                if (nomeDati.getText().toString() == " KM PERCORSI: ") {
//                    dati.setText(distanza);
//                } else {
//                    dati.setText(kcal);
//                }
//            }

//        }
//    };
}