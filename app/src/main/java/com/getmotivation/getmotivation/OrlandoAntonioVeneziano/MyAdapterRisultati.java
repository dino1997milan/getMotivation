package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MyAdapterRisultati extends RecyclerView.Adapter<MyAdapterRisultati.MyViewHolder> {

    ArrayList<DataModelRisultati> mDataModelList;
    Context mContext;

    public MyAdapterRisultati(ArrayList<DataModelRisultati> datamodellist, Context context){
        mDataModelList = datamodellist;
        mContext = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_risultati_item, parent, false);
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

        MaterialTextView mNumeroCorsa;
        MaterialTextView mDataCorsa;
        TextView mPassi,mKm,mKcal;
        //TextView mEmail;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumeroCorsa = itemView.findViewById(R.id.numeroCorsaTextView);
            mDataCorsa = itemView.findViewById(R.id.dataTextView);
            mPassi = itemView.findViewById(R.id.numeroPassiRegistrati);
            mKm = itemView.findViewById(R.id.numeroKmRegistrati);
            mKcal = itemView.findViewById(R.id.numeroKcalRegistrate);
            //mEmail = itemView.findViewById(R.id.emailUtente);

        }

        public void bind(DataModelRisultati dataModel, Context mContext) {
            mNumeroCorsa.setText("CORSA NUMERO:  "+dataModel.getmNumeroCorsa());
            mDataCorsa.setText(dataModel.getmDataCorsa());
            mPassi.setText(dataModel.getmPassi());
            mKm.setText(dataModel.getmKm());
            mKcal.setText(dataModel.getmKcal());
            //mEmail.setText("utente: "+dataModel.getEmail());



        }
    }
}
