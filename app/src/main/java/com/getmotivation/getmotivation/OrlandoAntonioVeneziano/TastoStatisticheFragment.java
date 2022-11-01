package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.DataBaseHelper.TABLE_NAME;
//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.Home.USER;
import static android.content.Context.MODE_PRIVATE;
import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.Run.CUSTOM_BROADCAST;
import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SplashActivity.AD_UNIT_ID;

import static java.lang.Integer.parseInt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TastoStatisticheFragment extends Fragment {
    MaterialButton dati;
    MaterialTextView nomeDati;
    String passi;
    String kcal;
    String distanza;
    MaterialButton buttonStop;
    DataBaseHelper mDatabaseHelper;
    String user;
    //String USER = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//    MyWakefulReceiver myWakefulReceiver;
//    public final static String CUSTOM_BROADCUST_WAKELOCK= "com.getmotivation.getmotivation.OrlandoAntonioVeneziano.CUSTOM_BROADCAST_WAKELOCK";
    public InterstitialAd interstitialAd;
    public final String TAG = "TastoStatisticheFragmentInterstitial";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_tasto_statistiche,container,false);
        dati = v.findViewById(R.id.dati);
        nomeDati = v.findViewById(R.id.nomeDati);
        buttonStop = v.findViewById(R.id.stop_run);
        return v;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        USER = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dati = view.findViewById(R.id.dati);
        nomeDati = view.findViewById(R.id.nomeDati);
        buttonStop = view.findViewById(R.id.stop_run);
        getActivity().registerReceiver(mReceiver, new IntentFilter(CUSTOM_BROADCAST));
//        myWakefulReceiver = new MyWakefulReceiver();
//        getActivity().registerReceiver(myWakefulReceiver, new IntentFilter(CUSTOM_BROADCUST_WAKELOCK));
        nomeDati.setText(" PASSI: ");  //così setto il primo dato da vedere in fase di creazione
        dati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nomeDati.getText().toString() == " PASSI: ") {
                    nomeDati.setText(" KM PERCORSI: ");
                    dati.setText(distanza);
                } else {
                    if (nomeDati.getText().toString() == " KM PERCORSI: ") {
                        nomeDati.setText(" KCAL CONSUMATE: ");
                        dati.setText(kcal);
                    } else {
                        nomeDati.setText(" PASSI: ");
                        dati.setText(passi);
                    }
                }
            }
        });
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", MODE_PRIVATE);
//        user = sharedPreferences.getString("email",USER);
        user ="corse";
        //String email = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        //user = email;
        startFrg();
        mDatabaseHelper = new DataBaseHelper(getContext(),user);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getContext(), ActivityRisultatiCorsa.class);
//                intent.putExtra("risultato passi",passi);
//                intent.putExtra("risultato km",distanza);
//                intent.putExtra("risultato kcal",kcal);
                Date c = Calendar.getInstance().getTime();
                Log.d("ListaRisultatiFragment","Current time => " + c);

                SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = dataFormat.format(c);
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                mDatabaseHelper.doInsert(formattedDate,passi,distanza,kcal,email,user);

                SharedPreferences.Editor editor = getContext().getSharedPreferences("save",MODE_PRIVATE).edit();
                editor.putString("risultato passi",passi);
                editor.putString("risultato km",distanza);
                editor.putString("risultato kcal",kcal);
                editor.apply();
                stopFrg();
                showInterstitial();
                //startActivity(intent);
            }
        });
        distanza = String.valueOf(0);
        kcal=String.valueOf(0);
        passi=String.valueOf(0);

    }

    private void startFrg() {
//        Intent i = new Intent(CUSTOM_BROADCUST_WAKELOCK);
        Intent i = new Intent(getContext(),ServiceGetMotivation.class);
        i.putExtra("TIME",0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //getActivity().startService(i);
            ContextCompat.startForegroundService(getContext(), i);
            }
        else{
            getActivity().startService(i);
        }
//        getActivity().sendBroadcast(i);
    }


    private void stopFrg(){
        Intent i = new Intent(getContext(), ServiceGetMotivation.class);
        getActivity().stopService(i);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String message = intent.getAction();
            passi =intent.getStringExtra("passi");
            kcal =intent.getStringExtra("kcal");
            distanza =intent.getStringExtra("distanza");
            //Toast.makeText(getContext(),passi +"  "+ kcal + "  "+distanza , Toast.LENGTH_LONG).show();

            if (nomeDati.getText().toString() == " PASSI: ") {
                dati.setText(passi);
            } else {
                if (nomeDati.getText().toString() == " KM PERCORSI: ") {
                    dati.setText(distanza);
                } else {
                    dati.setText(kcal);
                }
            }

        }
    };

    @Override
    public void onDestroyView() {
        getActivity().unregisterReceiver(mReceiver);
//        getActivity().unregisterReceiver(myWakefulReceiver);
        stopFrg();
//        MyWakefulReceiver.completeWakefulIntent(new Intent(getContext(),ServiceGetMotivation.class));
        super.onDestroyView();
    }


//    public class MyWakefulReceiver extends WakefulBroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
////            int modalità= intent.getIntExtra("TIME",0);
//            Intent service = new Intent(context,ServiceGetMotivation.class);
////            if(modalità==1){
//                service.putExtra("TIME",0);
//
////            }
////            else{
////                service.putExtra("TIME",0);
////            }
//            startWakefulService(context,service);
//
//        }
//    }
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
                        TastoStatisticheFragment.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MyActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        TastoStatisticheFragment.this.interstitialAd = null;
                                        Intent i = new Intent(getContext(),ActivityRisultatiCorsa.class);
                                        startActivity(i);
                                        //videoHorror.setVisibility(View.VISIBLE);
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        TastoStatisticheFragment.this.interstitialAd = null;
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
                Intent i = new Intent(getContext(),ActivityRisultatiCorsa.class);
                startActivity(i);
            }
        }
    }

}
