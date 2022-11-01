package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SplashActivity.AD_UNIT_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class TabellaBMIActivity extends AppCompatActivity {
MaterialButton home;
MaterialTextView valoreBMI;
public InterstitialAd interstitialAd;
public final String TAG = "TabellaBMIActivityAd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabella_bmiactivity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        double val = getIntent().getDoubleExtra("valore BMI",25);
        valoreBMI = findViewById(R.id.valoreBMI);
        valoreBMI.setText(String.valueOf(val));
        home = findViewById(R.id.tornaHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
//                Intent i = new Intent(TabellaBMIActivity.this,Home.class);
//                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
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
                        TabellaBMIActivity.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MyActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        TabellaBMIActivity.this.interstitialAd = null;
                                        Intent i = new Intent(TabellaBMIActivity.this,Home.class);
                                        startActivity(i);
                                        //videoHorror.setVisibility(View.VISIBLE);
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        TabellaBMIActivity.this.interstitialAd = null;
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
                Intent i = new Intent(TabellaBMIActivity.this,Home.class);
                startActivity(i);
            }
        }
    }
}