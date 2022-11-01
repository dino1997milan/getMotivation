package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Double.parseDouble;
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

public class MisureFragment extends Fragment {
    EditText altezza,peso,lPasso;
    MaterialButton apply;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_misure,container,false);
        altezza = v.findViewById(R.id.testoAltezza);
        peso = v.findViewById(R.id.testoPeso);
        lPasso = v.findViewById(R.id.testoLunghezzaPasso);
        apply = v.findViewById(R.id.setModificheMisure);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        if(contieneSpazzatura((altezza.getText().toString())) || contieneSpazzatura(peso.getText().toString()) || contieneSpazzatura(lPasso.getText().toString())){
            Toast.makeText(getContext(), "Inserire valori numerici positivi! (per le cifre dopo la virgola usare il punto)", Toast.LENGTH_SHORT).show();
        }
        else if (altezza.getText().toString().length() == 0 || peso.getText().toString().length()==0 || peso.getText().toString().length()==0) {
            Toast.makeText(getContext(), "Inserire campi mancanti!", Toast.LENGTH_SHORT).show();}

        else{
            if(!valutaDominio()){
                Toast.makeText(getContext(), "Attenzione! Forse stai inserendo valori troppo alti o troppo bassi", Toast.LENGTH_SHORT).show();
            }
            else{
            SharedPreferences.Editor editor = getContext().getSharedPreferences("save",MODE_PRIVATE).edit();
            editor.putFloat("altezza", parseFloat(altezza.getText().toString()));
            editor.putFloat("peso", parseFloat(peso.getText().toString()));
            editor.putFloat("lunghezzaPasso", parseFloat(lPasso.getText().toString()));
            editor.apply();
            Toast.makeText(getContext(), "Modifiche eseguite correttamente!", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(getContext(),TabellaBMIActivity.class);
            float bmi1 =parseFloat(peso.getText().toString())/(parseFloat(altezza.getText().toString())*parseFloat(altezza.getText().toString()));
            double bmi = (double)bmi1;
            bmi = Math.ceil(bmi*1000)/1000;
            a.putExtra("valore BMI",bmi);
            startActivity(a);}
        }
    }
});
        }
        public boolean valutaDominio(){
        if(parseFloat(altezza.getText().toString())<2.5 && parseFloat(peso.getText().toString())<235 && parseFloat(lPasso.getText().toString())< 2.6 && (parseFloat(altezza.getText().toString())>0.2) && parseFloat(peso.getText().toString())>10 && parseFloat(lPasso.getText().toString())> 0.1){
            //con questi valori in un certo senso esagerati ho tenuto anche conto della presenza di persone con malattie, bambini e per quanto riguarda i passi di persone che corrono; gli estremi inferiori servono solo ad evitare lo zero che annullerebbe tutti i conti
            return true;
        }
        else{return false;}
        }

    public boolean contieneSpazzatura(String string){
        if(string.contains(" ")||string.contains(",") ||string.contains("-")){
            return true;
        }
        else{
            return false;
        }
    }
}
