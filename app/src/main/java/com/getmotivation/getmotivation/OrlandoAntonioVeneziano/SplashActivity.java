package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    public static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/8691691433";
    // di prova:  ca-app-pub-3940256099942544/8691691433
// finale fornito da adMob: procurarsene uno su adMob
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //dal momento che questa è un'applicazione per la corsa e la riproduzione musicale, non ho ritenuto la rotazione dello schermo necessaria, poichè l'app lavora per la maggior parte del tempo in background

        //in alternativa se avessi dovuto gestire la rotazione avrei generato i file xml relativi all'orientazione orizzontale (facendo click su landscape ed utilizzando i file xml già presenti) ed avrei fatto override dei metodi che permettono di ripristinare lo stato, onSaveIstanceState ed onRestoreIstanceState (però poichè questo viene chiamato dopo onStart, avrei recuperato meglio lo stato all'interno di onCreate se non nullo)

        int TIME_SPLASH_ACTIVITY = 4000;

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(TIME_SPLASH_ACTIVITY);
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();

    }
        }

