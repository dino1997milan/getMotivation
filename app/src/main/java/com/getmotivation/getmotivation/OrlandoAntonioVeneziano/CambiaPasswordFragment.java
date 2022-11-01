package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;

import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SplashActivity.AD_UNIT_ID;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CambiaPasswordFragment extends Fragment {
    private EditText inputNuovaPassword;
    private MaterialButton buttonCambiaPassword;
    SharedPreferences sharedPreferences;
    private FirebaseAuth auth;
    public InterstitialAd interstitialAd;
    public final String TAG = "CambiaPasswordFragmentInterstitial";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("save",MODE_PRIVATE);
        boolean salta = sharedPreferences.getBoolean("salta",false);
        if (salta){
            Intent a = new Intent(getContext(), Home.class);
            Toast.makeText(getContext(), "Attenzione! Non puoi cambiare la password se stai utilizzando l'account di prova", Toast.LENGTH_SHORT).show();
            startActivity(a);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputNuovaPassword = view.findViewById(R.id.nuova_password);
        buttonCambiaPassword = view.findViewById(R.id.button_cambia_password);
        inputNuovaPassword.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        auth = FirebaseAuth.getInstance();

//
//            if(auth.getCurrentUser() != null) //con questo metodo valuto se l'utente è loggato
//            auth.getCurrentUser().getEmail(); //questa sfruttala per SharePreferences e per database futuri, assegna ad una stringa le email utilizzate sul dispositivo, crea metodo per scorrere la stringa, poi ad ogni variabile della stringa saranno associati dati diversi
        buttonCambiaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputNuovaPassword.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Inserire nuova password!", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences = getActivity().getSharedPreferences("save",MODE_PRIVATE);
                    boolean salta = sharedPreferences.getBoolean("salta",false);
                    if (salta){
                        Toast.makeText(getContext(), "Attenzione! Non puoi cambiare la password se stai utilizzando l'account di prova", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    changePassword(inputNuovaPassword.getText().toString());}
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cambia_password,container,false);
        return v;
    }

    private void changePassword(String newPassword) {
        FirebaseUser user = auth.getCurrentUser();
        user.updatePassword(newPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getContext(), "Password cambiata con successo!", Toast.LENGTH_SHORT).show();
                    showInterstitial();
                }
            }
        });
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
                        CambiaPasswordFragment.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MyActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        CambiaPasswordFragment.this.interstitialAd = null;
                                        Intent i = new Intent(getContext(),Home.class);
                                        startActivity(i);
                                        //videoHorror.setVisibility(View.VISIBLE);
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        CambiaPasswordFragment.this.interstitialAd = null;
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
                Intent i = new Intent(getContext(),Home.class);
                startActivity(i);
            }
        }
    }



}
