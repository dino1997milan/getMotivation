package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SetMotivatorFragment.mDataModelList;
import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SplashActivity.AD_UNIT_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ProvaSuoniActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener{
    //AVVISO: CI SONO ALCUNI OPERAZIONI COMMENTATE, PERCHè VOLEVO IMPARARE A CARICARE LE IMMAGINI FACENDO USO DEGLI URI ANZICCHè DEGLI INDIRIZZI
    // QUESTO PERCHè AVEVO PENSATO IN SEGUITO DI INSERIRE IMMAGINI E SUONI CARICATI DAGLI UTENTI
    //SONO RIUSCITO AD APPRENDERE CIò CHE MI INTERESSAVA FACENDO USO DELL'IMAGEVIEW CHE HO RAPPRESENTATO COME "TASTO BACK"
    // UNA VOLTA APPRESO IL MECCANISMO CHE MI INTERESSAVA SON TORNATO INDIETRO ED HO IMPOSTATO LO SFONDO COME FATTO IN PRECEDENZA FACENDO USO DEGLI INDIRIZZI

    MediaPlayer mp;
    AudioManager mAudioManager;
    MaterialButton sound1;
    MaterialButton sound2;
    MaterialButton sound3;
    MaterialButton sound4;
    MaterialButton sound5;
    ImageView stop;
    ImageView back;
    ConstraintLayout sfondo;
    //ImageView pippo;
    int indiceRV;
    ArrayList[] vettoreListeConImmagini = new ArrayList[5];

    ArrayList <Integer> indirizziFotoIbra;
    int conta;

    IntentFilter rumoreIntentFilter;
    AudioRumoroso mAudioRumoroso;

    public InterstitialAd interstitialAd;
    public final String TAG = "ProvaSuoniActivityInterstitial";

    private class AudioRumoroso extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            soundStop();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova_suoni);
        //pippo= findViewById(R.id.tastoIndietro);
        sfondo = findViewById(R.id.sfondo);
        indiceRV = getIntent().getExtras().getInt("VedoChièIlGiocatore");
        sfondo.setBackgroundResource(mDataModelList.get(indiceRV).getmMotivatorImageId());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mAudioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        mAudioRumoroso = new AudioRumoroso();
        rumoreIntentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);

        mp=null;
        conta =3;
        indirizziFotoIbra = new ArrayList<Integer>();
        indirizziFotoIbra=creaVettoreIndirizziIbra();
        vettoreListeConImmagini[0]= indirizziFotoIbra;
        vettoreListeConImmagini[1]=creaVettoreIndirizziJordan();
        vettoreListeConImmagini[2]=creaVettoreIndirizziConte();
        vettoreListeConImmagini[3]=creaVettoreIndirizziMou();
        vettoreListeConImmagini[4]=creaVettoreIndirizziTotti();
       sfondo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                aggiornaSfondo();
           }
       });

        stop= findViewById(R.id.tastoStop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundStop();
            }
        });
        back= findViewById(R.id.tastoIndietro);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundStop();
                showInterstitial();
//                Intent intent = new Intent(ProvaSuoniActivity.this,Home.class);
//                startActivity(intent);
            }
        });
        sound1=findViewById(R.id.sound1);
        sound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundStop();
                mp=MediaPlayer.create(getApplicationContext(),mDataModelList.get(indiceRV).getmMotivatorSound1());
                soundStart();
            }
        });

        sound2=findViewById(R.id.sound2);
        sound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundStop();
                mp=MediaPlayer.create(getApplicationContext(),mDataModelList.get(indiceRV).getmMotivatorSound2());
                soundStart();
            }
        });

        sound3=findViewById(R.id.sound3);
        sound3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundStop();
                mp=MediaPlayer.create(getApplicationContext(),mDataModelList.get(indiceRV).getmMotivatorSound3());
                soundStart();
            }
        });

        sound4=findViewById(R.id.sound4);
        sound4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundStop();
                mp=MediaPlayer.create(getApplicationContext(),mDataModelList.get(indiceRV).getmMotivatorSound4());
                soundStart();
            }
        });

        sound5=findViewById(R.id.sound5);
        sound5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundStop();
                mp=MediaPlayer.create(getApplicationContext(),mDataModelList.get(indiceRV).getmMotivatorSound5());
                soundStart();
            }
        });

    }
    public ArrayList<Integer> creaVettoreIndirizziIbra(){
        ArrayList<Integer> ibra = new ArrayList<>();
        //String path0= "android.resource://com.getmotivation.getmotivation.OrlandoAntonioVeneziano/"+ R.drawable.ibrahimovic0;
        //ibra.add(Uri.parse(path0));
        ibra.add(R.drawable.ibrahimovic0);
        //String path1= "android.resource://com.getmotivation.getmotivation.OrlandoAntonioVeneziano/"+ R.drawable.ibrahimovic1;
        //ibra.add(Uri.parse(path1));
        ibra.add(R.drawable.ibrahimovic1);
        //String path2= "android.resource://com.getmotivation.getmotivation.OrlandoAntonioVeneziano/"+ R.drawable.ibrahimovic2;
        //ibra.add(Uri.parse(path2));
        ibra.add(R.drawable.ibrahimovic2);
        //String path3= "android.resource://com.getmotivation.getmotivation.OrlandoAntonioVeneziano/"+ R.drawable.image1;
        //ibra.add(Uri.parse(path3));
        ibra.add(R.drawable.image1);
        return ibra;
    }

    public ArrayList<Integer> creaVettoreIndirizziJordan(){
        ArrayList<Integer> jordan = new ArrayList<>();
        jordan.add(R.drawable.michael_jordan0);

        jordan.add(R.drawable.michael_jordan1);

        jordan.add(R.drawable.michael_jordan2);

        jordan.add(R.drawable.image2);
        return jordan;
    }

    public ArrayList<Integer> creaVettoreIndirizziConte(){
        ArrayList<Integer> j = new ArrayList<>();
        j.add(R.drawable.conte0);

        j.add(R.drawable.conte1);

        j.add(R.drawable.conte2);

        j.add(R.drawable.image3);
        return j;
    }

    public ArrayList<Integer> creaVettoreIndirizziMou(){
        ArrayList<Integer> j = new ArrayList<>();
        j.add(R.drawable.mourinho0);

        j.add(R.drawable.mourinho1);

        j.add(R.drawable.mourinho2);

        j.add(R.drawable.image4);
        return j;
    }

    public ArrayList<Integer> creaVettoreIndirizziTotti(){
        ArrayList<Integer> j = new ArrayList<>();
        j.add(R.drawable.totti0);

        j.add(R.drawable.totti1);

        j.add(R.drawable.totti2);

        j.add(R.drawable.image5);
        return j;
    }

    public void aggiornaSfondo(){
        if(indiceRV<5) {
            if(conta==3){
                conta=0;
                sfondo.setBackgroundResource((Integer) vettoreListeConImmagini[indiceRV].get(conta));
                //pippo.setImageURI((Uri) vettoreListeConImmagini[indiceRV].get(conta));
            }
            else{
                sfondo.setBackgroundResource((Integer) vettoreListeConImmagini[indiceRV].get(++conta));
                //pippo.setImageURI((Uri) vettoreListeConImmagini[indiceRV].get(++conta));
            }
        }else{}
    }


    public void soundStart(){
        registerReceiver(mAudioRumoroso,rumoreIntentFilter);
            int requestAudioFocusResult = mAudioManager.requestAudioFocus(this,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
            if(requestAudioFocusResult== AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            mp.start();
        }
    }

    public void soundStop(){
        if(mp!=null) {
            mp.stop();
            mp.release();
            mp= null;
            mAudioManager.abandonAudioFocus(this);
            unregisterReceiver(mAudioRumoroso);
        }
    }

    @Override
    public void onAudioFocusChange(int audioFocusChanged) {
        switch(audioFocusChanged){
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS:
                mp.setVolume(0.7f,0.7f);  //siccome l'esecuzione dell'asyncTask continua per fare in modo che l'utente sia a conoscenza del fatto che l'app è ancora in esecuzione abbasso solo il volume anzicchè stoppare il suono (ovviamente con le chiamate e col navigatore non ci sono problemi)
                //soundStop();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                mp.setVolume(0.2f,0.2f);  //così se l'utente usa googleMaps mentre corre non ci sono problemi
                break;
            case AudioManager.AUDIOFOCUS_GAIN:
                mp.setVolume(1f,1f);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        soundStop();
        //Intent intent = new Intent(ProvaSuoniActivity.this,Home.class);
        //startActivity(intent);
        super.onBackPressed();
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
                        ProvaSuoniActivity.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MyActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        ProvaSuoniActivity.this.interstitialAd = null;
                                        soundStop();
                                        Intent i = new Intent(ProvaSuoniActivity.this,Home.class);
                                        startActivity(i);
                                        //videoHorror.setVisibility(View.VISIBLE);
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        ProvaSuoniActivity.this.interstitialAd = null;
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
                soundStop();
                Intent i = new Intent(this,Home.class);
                startActivity(i);
            }
        }
    }


}