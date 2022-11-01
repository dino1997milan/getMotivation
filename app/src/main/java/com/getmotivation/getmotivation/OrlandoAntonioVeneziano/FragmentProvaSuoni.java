package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SetMotivatorFragment.mDataModelList;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//ho preferito questo approccio con una seconda recyclerView poichè in presenza della prima recyclerView l'interfaccia utente era già abbastanza appesantita dalla presenza degli switch e dall'uso delle sharedPreferences
//ovviamente non ho ripetuto il popolamento dei DataModel già svolto in precedenza
public class FragmentProvaSuoni extends Fragment {
    //avevo anche pensato di utilizzare solo la recyclerView precedente e di nascondere gli switch ed i bottoni in base a ciò che veniva premuto dal menu a tendina, però per evitare errori e complicazioni nello spostamento trai fragment e nella gestione del tasto back, ho preferito creare una seconda recyclerView con un nuovo adapter partendo dallo stesso file xml e gli stessi dataModel
    //ImageView buttonStop;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_set_motivator, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        MyAdapterProvaSuoni mAdapter2 = new MyAdapterProvaSuoni(mDataModelList, getContext());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter2);
    }
}
        //NELLE RIGHE SUCCESSIVE AVEVO TENTATO ALTRI APPROCCI, PER INTRODURRE UN TASTO PER RILASCIARE IL SUONO DIRETTAMENTE SULLA HOME ACTIVITY
        //PERò SICCOME "SUONI" è UNA VARIABILE MEDIAPLAYER NON DEFINITA ALL'INTERNO DELLA HOME, ANZICCHè ABUSARE DEL FATTO CHE "SUONI" SIA UNA VARIABILE STATICA, HO PREFERITO METTERE UN TASTO DIRETTAMENTE DENTRO I VIEWHOLDER, DOVE RISULTA DEFINITA LA VARIABILE IN QUESTIONE.

//        tastoStop.setVisibility(View.VISIBLE);
//        tastoStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(suoni!=null){
//                    suoni.release();
//                    suoni=null;
//                }
//            }
//        });



//questo sotto è un altro metodo usato per sfruttare un booleano che ha valore vero quando il fragment è visibile, è però un metodo deprecato

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            buttonStop.setVisibility(View.VISIBLE);
//        }
//        else{
//            buttonStop.setVisibility(View.INVISIBLE);
//        }
//    }


     //facendo release nell'onPause del fragment riesco a bloccare il suono senza che il sistema faccia abortire l'applicazione


