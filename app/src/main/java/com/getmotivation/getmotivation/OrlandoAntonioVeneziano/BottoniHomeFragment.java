package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;
//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.Home.isBack;
//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.Home.setBack;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class BottoniHomeFragment extends Fragment implements View.OnClickListener {
    MaterialButton run, goRun, motivation, goMotivation;
    ConstraintLayout sfondo;
    boolean noM;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottoni_home, container, false);
        run = v.findViewById(R.id.materialButtonRun);
        run.setOnClickListener(this);
        goRun = v.findViewById(R.id.materialButtonGoRun);
        goRun.setOnClickListener(this);
        motivation = v.findViewById(R.id.materialButtonMotivation);
        motivation.setOnClickListener(this);
        goMotivation = v.findViewById(R.id.materialButtonGoMotivation);
        goMotivation.setOnClickListener(this);
        sfondo = v.findViewById(R.id.homeButtonsLayout);
        sfondo.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();  //ho preferito questa callBack rispetto ad onPause perchè l'effetto visivo risulta più "pulito" (utilizzando onPause si sarebbero viste le modifiche pochi istanti prima del cambio di fragment/activity oppure prima della pressione del tasto back, e questo effetto risultava spiacevole)

            run.setVisibility(View.VISIBLE);
            goRun.setVisibility(View.GONE);
            motivation.setVisibility(View.VISIBLE);
            goMotivation.setVisibility(View.GONE);
            sfondo.setBackgroundResource(R.drawable.image_motivation_3);
        //ho fatto override del metodo onStop() per far tornare l'immagine ed i tasti nella configurazione iniziale in seguito alla pressione del tasto back o lo stato di stop dell'applicazione
    }

    @Override   //qua anzicchè implementare più volte l'interfaccia OnClickListener ho preferito con un solo metodo onClick scrivere le varie operazioni in modo analogo all'uso su java del costrutto switch(avrei potuto usare anche quello)
    public void onClick(View view) {
        if (view.getId() == R.id.materialButtonRun) {
            run.setVisibility(View.GONE);
            goRun.setVisibility(View.VISIBLE);
            motivation.setVisibility(View.VISIBLE);
            goMotivation.setVisibility(View.GONE);
            sfondo.setBackgroundResource(R.drawable.run_image);
        }
        else if(view.getId()== R.id.homeButtonsLayout){
            run.setVisibility(View.VISIBLE);
            goRun.setVisibility(View.GONE);
            motivation.setVisibility(View.VISIBLE);
            goMotivation.setVisibility(View.GONE);
            sfondo.setBackgroundResource(R.drawable.image_motivation_3);
        }
        else if (view.getId() == R.id.materialButtonMotivation) {
            run.setVisibility(View.VISIBLE);
            goRun.setVisibility(View.GONE);
            motivation.setVisibility(View.GONE);
            goMotivation.setVisibility(View.VISIBLE);
            sfondo.setBackgroundResource(R.drawable.image_motivation_1);
        } else if (view.getId() == R.id.materialButtonGoRun) {
            Intent intent = new Intent(getContext(),Run.class);
            startActivity(intent);
            //per quanto riguarda la corsa faccio in modo che l'utente possa avviare anche senza aver selezionato nessun motivatore
        } else if (view.getId() == R.id.materialButtonGoMotivation) {
            if(noMotivator()){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Per accedere alla modalità GetMotivation(), devi aver prima selezionato almeno un motivatore dall'apposita schermata di selezione").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent a = new Intent(getContext(),MainActivity.class);
                    startActivity(a);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();}

            else{
            Intent intent = new Intent(getContext(),GetMotivation.class);
            startActivity(intent);
                }
            //alla pressione del tasto "go()" relativo a questa classe associo un alertDialog, il cui messaggio positivo mi manda al fragment di selezione dei motivatori. Tornerò in questo caso alla MainActivity in cui era presente l'onAttach col fragment di interesse e poi portava alla Home
            //siccome voglio permettere all'utente di svolgere l'attività della corsa anche senza suoni non ho messo alcun vincolo sul tasto che dal fragment mi manda alla Home

        }

    }


    public boolean noMotivator(){   //questo metodo è vero quando non ci sono i motivatori
        noM = true;
        ArrayList<Boolean> valSwitch;
        ArrayList<String> chiaviSpListMain = new ArrayList<String>();
        valSwitch = new ArrayList<>();
        //setto nomi chiavi, per recuperare booleani precedenti
        chiaviSpListMain.add("value1");
        chiaviSpListMain.add("value2");
        chiaviSpListMain.add("value3");
        chiaviSpListMain.add("value4");
        chiaviSpListMain.add("value5");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", MODE_PRIVATE);
        for (int i = 0; i < chiaviSpListMain.size(); i++) {
            valSwitch.add(sharedPreferences.getBoolean(chiaviSpListMain.get(i), false));

        }
        for(int i=0; i<valSwitch.size();i++){
            if(valSwitch.get(i)){
                noM= false;
                break;
            }
        }
        return noM;
    }

//        public final static int settaTempo(){
//        int secondi=sharedPreferences.getInt("secondiGetMotivation",0);
//        return secondi;
//}
    //ho avuto modo di vedere come il metodo onBackPressed non esiste per i fragment ed ho dovuto cercare una strategia differente
//    @Override
//    public void onBackPressed() {
//        run.setVisibility(View.VISIBLE);
//        goRun.setVisibility(View.GONE);
//        motivation.setVisibility(View.VISIBLE);
//        goMotivation.setVisibility(View.GONE);
//        sfondo.setBackgroundResource(R.drawable.image_motivation_3);
//        Intent a = new Intent(Intent.ACTION_MAIN);
//        a.addCategory(Intent.CATEGORY_HOME);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(a);
//    }

}

