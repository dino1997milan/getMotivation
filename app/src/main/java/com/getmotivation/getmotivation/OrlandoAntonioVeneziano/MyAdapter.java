package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.GONE;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<DataModel> mDataModelList;
    Context mContext;

    public MyAdapter(ArrayList<DataModel> datamodellist, Context context){
        mDataModelList = datamodellist;
        mContext = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recyclerview_item, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(mDataModelList.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return mDataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        MaterialTextView mMotivatorName;
        MaterialButton mButton;
        SwitchMaterial mSwitch;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mMotivatorName = itemView.findViewById(R.id.nameTextView);
            mButton = itemView.findViewById(R.id.buttonProva);
            mSwitch = itemView.findViewById(R.id.switchVH);
            mButton.setVisibility(GONE);
            mSwitch.setVisibility(View.VISIBLE);
        }
        //per implementare le sharedPreferences per ogni switch dei ViewHolder mi necessita aggiungere ai Data model un booleano e una stringa con i nomi che rappresentano le chiavi
        public void bind(DataModel dataModel, Context mContext) {
            mImageView.setImageDrawable(ContextCompat.getDrawable(mContext, dataModel.mMotivatorImageId));
            mMotivatorName.setText(dataModel.getmMotivatorName());
            //da qui implemento gli switch utilizzando le sharedPreferences per mantenerli in memoria anche in seguito alla chiusura dell'aplicazione
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("save",MODE_PRIVATE);
            mSwitch.setChecked(sharedPreferences.getBoolean(dataModel.getChiaveSp(), false));
            mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b==true){Toast.makeText(itemView.getContext(), mMotivatorName.getText().toString() + " selezionato!", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = mContext.getSharedPreferences("save",MODE_PRIVATE).edit();
                        editor.putBoolean(dataModel.getChiaveSp(), true);
                        editor.apply();
//                        dataModel.setCondizioneSwitch(true);   //--> a me non interessa il dataModel che compare nella recyclerView a me interessa il dataModel nella lista
//
////                        int a =dataModel.getPosizioneNellaRecyclerView();
////                        mDataModelList.get(a).setCondizioneSwitch(true);
                    }
                    else{Toast.makeText(itemView.getContext(), "Hai deselezionato "+mMotivatorName.getText().toString() + " !", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = mContext.getSharedPreferences("save",MODE_PRIVATE).edit();
                        editor.putBoolean(dataModel.getChiaveSp(), false);
                        editor.apply();
//                        dataModel.setCondizioneSwitch(false); //questo l'ho lasciato, però è irrilevante per quanto riguarda la costruzione della recyclerView sull'UI
//                        int a =dataModel.getPosizioneNellaRecyclerView();
//                        mDataModelList.get(a).setCondizioneSwitch(false);  //in questo modo aggiorno in corrispondenza della pressione degli switch lo stato dei dataModel nella lista
                    } //HO ABBANDONATO QUESTO APPROCCIO COMMENTATO PERCHè SICURAMENTE è MOGLIO NON SFRUTTARE TROPPO VARIABILI STATICHE E POI STAVO AGGIUNGENDO QUESTI ELEMENTI PER AVERE AGEVOLAZIONI NELLA SCRITTURA DI UN METODO, MA IN REALTà è PIù SEMPLICE RECUPERARE I VALORI DELLE SHAREDPREFERENCES E VALUTARE QUELLI
                }  //a me interessa tener conto dei valori degli switch anche nei DataModel, quindi prima di tutto nel momento stesso in cui avviene un click dello switch, Modifico il booleano del DataModel corrispondente, poi dovrò fare un'altra operazione
                //dovrò anche assegnare ai dataModel i valore contenuti nelle sharedPreferences in una classe che precede GetMotivation e Run(li ho assegnati nel momento del popolamento della lista di dataModel), altrimenti il valore booleano di un DataModel verrebbe assegnato solo nel momento della pressione dei tasti, quando invece le configurazioni salvate devono esser presenti anche nei DataModel
            });


        }
    }
}

