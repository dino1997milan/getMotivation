package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;

import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SplashActivity.AD_UNIT_ID;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class LogoutFragment extends Fragment {
    private FirebaseAuth auth;
    public InterstitialAd interstitialAd;
    public final String TAG = "LogoutFragmentInterstitial";
    public boolean home;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        home = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Sei sicuro di voler uscire?").setPositiveButton("Sì", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = getContext().getSharedPreferences("save",MODE_PRIVATE).edit();
                editor.putBoolean("salta",false);
                editor.apply();
                home = false;
                logoutUser();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                home= true;
                showInterstitial();
//                startActivity(new Intent(getActivity(),Home.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logout, container, false);
        v.findViewById(R.id.tasto_torno_alla_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home = true;
                showInterstitial();
//                Intent a = new Intent(getContext(),Home.class);
//                startActivity(a);
            }
        });
        return v;

    }
    private void logoutUser() {
        auth.signOut();
        if(auth.getCurrentUser() == null)
        {   ArrayList<String> chiaviSpListLogout = new ArrayList<String>();
            chiaviSpListLogout.add("value1");
            chiaviSpListLogout.add("value2");
            chiaviSpListLogout.add("value3");
            chiaviSpListLogout.add("value4");
            chiaviSpListLogout.add("value5");
            SharedPreferences.Editor editor = getContext().getSharedPreferences("save",MODE_PRIVATE).edit();
            for(int i=0; i< chiaviSpListLogout.size();i++){
            editor.putBoolean(chiaviSpListLogout.get(i), false);}
            editor.apply();

            //rientrando nell'app si va subito alla Home e non si vedono i motivatori selezionati
            //ho voluto che l'app funzionasse in questo modo, perchè altrimenti ripassando dal Main senza uscire dall'applicazione si ritroverebbero solo parte delle operazioni di recupero dei valori degli switch precedenti

            //(attenzione, bisogna comunque mettere un alertDialog sui tasti go(), perchè esiste una configurazione in cui si può entrare nell'app senza aver selezionato nessun motivatore)

            showInterstitial();
            //startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAd();
    }

    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                getContext(),
                AD_UNIT_ID,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        LogoutFragment.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MyActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        LogoutFragment.this.interstitialAd = null;
                                        if(!home){
                                        Intent i = new Intent(getContext(),LoginActivity.class);
                                        startActivity(i);}
                                        else{
                                            Intent i = new Intent(getContext(),Home.class);
                                            startActivity(i);
                                        }
                                        //videoHorror.setVisibility(View.VISIBLE);
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        LogoutFragment.this.interstitialAd = null;
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
            interstitialAd.show(getActivity());
        } else {
            //Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            //startGame();
            if (interstitialAd == null) {
                loadAd();
                if(!home){
                    Intent i = new Intent(getContext(),LoginActivity.class);
                    startActivity(i);}
                else{
                    Intent i = new Intent(getContext(),Home.class);
                    startActivity(i);
                }
//                Intent i = new Intent(getContext(),LoginActivity.class);
//                startActivity(i);
            }
        }
    }
}
