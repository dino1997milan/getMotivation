package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class ObbiettiviUtenteFragment extends Fragment {
    EditText obbiettivoPassi,obbiettivoKcal,obbiettivokm;
    MaterialButton apply;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_obbiettivi_utente,container,false);
        obbiettivoPassi = v.findViewById(R.id.testoPassi);
        obbiettivokm=v.findViewById(R.id.testoKm);
        obbiettivoKcal=v.findViewById(R.id.testoKcal);
        apply=v.findViewById(R.id.setModificheObbiettivi);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contieneSpazzatura((obbiettivoPassi.getText().toString())) || contieneSpazzaturaKm(obbiettivokm.getText().toString()) || contieneSpazzatura(obbiettivoKcal.getText().toString()) ){
                    Toast.makeText(getContext(), "Inserire valori (interi se non sono km) positivi!", Toast.LENGTH_SHORT).show();
                }
                else if (obbiettivoPassi.getText().toString().length() == 0 || obbiettivokm.getText().toString().length()==0 || obbiettivoKcal.getText().toString().length()==0) {
                    Toast.makeText(getContext(), "Inserire campi mancanti!", Toast.LENGTH_SHORT).show();}
                else{

                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putInt("obbiettivo passi", parseInt(obbiettivoPassi.getText().toString()));
                    editor.putFloat("obbiettivo km", parseFloat(obbiettivokm.getText().toString()));
                    editor.putInt("obbiettivo kcal", parseInt(obbiettivoKcal.getText().toString()));
                    editor.apply();
                    Toast.makeText(getContext(), "Modifiche eseguite correttamente!", Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(getContext(),Home.class);
                    startActivity(a);//}
                }
            }
        });
    }

    public boolean contieneSpazzatura(String string){
        if(string.contains(".")||string.contains(" ")||string.contains(",") ||string.contains("-")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean contieneSpazzaturaKm(String string){
        if(string.contains(" ")||string.contains(",") ||string.contains("-")){
            return true;
        }
        else{
            return false;
        }
    }

}
