package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SplashActivity.AD_UNIT_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.button.MaterialButton;

public class GetMotivation extends AppCompatActivity {
    MaterialButton buttonStop;
    VideoView videoRun;
    public InterstitialAd interstitialAd;
    public final String TAG = "GetMotivationInterstitial";
    int time;
//    PowerManager.WakeLock wakeLock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_motivation);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        SharedPreferences sharedPreferences = this.getSharedPreferences("save",MODE_PRIVATE);
//        time = sharedPreferences.getInt("secondiGetMotivation",0);
//        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
//        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
//                "MyApp::MyWakelockTag");
//        wakeLock.acquire();
        startFrg();

        buttonStop = findViewById(R.id.stop_motivation);
        videoRun = findViewById(R.id.videoMotivation);
        String path= "android.resource://com.getmotivation.getmotivation.OrlandoAntonioVeneziano/"+R.raw.get_motivation_video;
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

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(GetMotivation.this,Home.class);
                stopFrg();
                //startActivity(intent);
                showInterstitial();
            }
        });


    }
    private void startFrg() {
        Intent i = new Intent(this, ServiceGetMotivation.class);
        i.putExtra("TIME",1);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        ContextCompat.startForegroundService(this, i);}
        else{
            startService(i);
        }
    }


    private void stopFrg(){
        Intent i = new Intent(this, ServiceGetMotivation.class);
        stopService(i);
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onDestroy() {
        stopFrg();
//        wakeLock.release();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAd();
    }

    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                AD_UNIT_ID,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        GetMotivation.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MyActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        GetMotivation.this.interstitialAd = null;
                                        startActivity(new Intent(GetMotivation.this,Home.class));

                                        //videoHorror.setVisibility(View.VISIBLE);
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        GetMotivation.this.interstitialAd = null;
//                                        Intent i = new Intent(TabellaBMIActivity.this,Home.class);
//                                        startActivity(i);
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        interstitialAd = null;
//                        Intent i = new Intent(TabellaBMIActivity.this,Home.class);
//                        startActivity(i);

                        String error =
                                String.format(
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                        //Toast.makeText(
                        //       MyActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                        //  .show();
                    }
                });
    }

    public void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null) {
            //videoHorror.setVisibility(View.INVISIBLE);
            interstitialAd.show(this);
        } else {
            //Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            //startGame();
            if (interstitialAd == null) {
                loadAd();
                Intent i = new Intent(GetMotivation.this,Home.class);
                startActivity(i);
            }
        }
    }
}