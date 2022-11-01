package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;

import static java.lang.Integer.parseInt;

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

import com.google.android.material.button.MaterialButton;

public class SceltaPauseFragment extends Fragment {   //QUESTO FRAGMENT ALLA FINE NON L'HO USATO, AVREI VOLUTO MODIFICARE IN RELAZIONE ALLE SCELTE DELL'UTENTE IL TEMPO DEL TIMER TRA I DIVERSI AUDIO
    EditText secondiRun,secondiGetMotivation;
    MaterialButton apply;
    String TAG = "SceltaSecondiPausa";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scelta_pause, container, false);
        secondiRun=v.findViewById(R.id.secondiRun);
        secondiGetMotivation=v.findViewById(R.id.secondiGetMotivation);
        apply=v.findViewById(R.id.setModifiche);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contieneSpazzatura((secondiRun.getText().toString())) || contieneSpazzatura(secondiGetMotivation.getText().toString()) ){
                    Toast.makeText(getContext(), "Inserire valori interi positivi!", Toast.LENGTH_SHORT).show();
                }
                else if (secondiRun.getText().toString().length() == 0 || secondiGetMotivation.getText().toString().length()==0) {
                    Toast.makeText(getContext(), "Inserire campi mancanti!", Toast.LENGTH_SHORT).show();}
//                else if(parseInt(secondiRun.getText().toString()) < 0 || parseInt(secondiGetMotivation.getText().toString())<0){
//                    Toast.makeText(getContext(), "Inserire valori interi positivi!", Toast.LENGTH_SHORT).show();
//                }
                else{
//                    if(parseInt(secondiRun.getText().toString()) < 0 || parseInt(secondiGetMotivation.getText().toString())<0 ||secondiRun.getText().toString().contains("-") || secondiGetMotivation.getText().toString().contains("-")){
//                        Toast.makeText(getContext(), "Inserire valori interi positivi!", Toast.LENGTH_SHORT).show();
//                    }else{
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putInt("secondiRun", parseInt(secondiRun.getText().toString()));
                    editor.putInt("secondiGetMotivation", parseInt(secondiGetMotivation.getText().toString()));
                    editor.apply();
                    Toast.makeText(getContext(), "Modifiche eseguite correttamente!", Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(getContext(),Home.class);
                    startActivity(a);//}
                }
            }
        });
    }
//avrei potuto usare numberPassword per evitare tutto questo, però vedere l'inserimento di semplici numeri come l'inserimento di una password sarebbe stato insolito
    public boolean contieneSpazzatura(String string){
        if(string.contains(".")||string.contains(" ")||string.contains(",") ||string.contains("-")){
            return true;
        }
        else{
            return false;
        }
    }

//    public int isDoubleOrInt(String num){
//        try{
//            Integer.parseInt(num);
//            return 1;
//        }catch(Exception e){
//            try{
//                Double.parseDouble(num);
//                return 0;
//            }catch(Exception exception){
//                return -1;
//            }
//        }
//    }


}
