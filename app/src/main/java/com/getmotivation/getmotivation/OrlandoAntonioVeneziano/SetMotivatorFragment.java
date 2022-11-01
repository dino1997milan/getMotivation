package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class SetMotivatorFragment extends Fragment {
    ArrayList<Integer> mMotivatorImageList;
    public ArrayList<String> mMotivatorNameList;
    ArrayList<Integer> mSound1List;
    ArrayList<Integer> mSound2List;
    ArrayList<Integer> mSound3List;
    ArrayList<Integer> mSound4List;
    ArrayList<Integer> mSound5List;
    public ArrayList<String> chiaviSpList;
    //  ArrayList<Boolean> valoriSwitchList;
    public static ArrayList<DataModel> mDataModelList;
    // so che non conviene usare variabili statiche, però ho deciso di inserire nei dataModel tutto ciò che dopo fosse utile,poichè i ragionamenti successivi risultano notevolmente semplificati da questo "impacchettamento" delle informazioni
    TypedArray mTypedArray;
    TypedArray mTypedArrayS1;
    TypedArray mTypedArrayS2;
    TypedArray mTypedArrayS3;
    TypedArray mTypedArrayS4;
    TypedArray mTypedArrayS5;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_set_motivator, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mMotivatorImageList = new ArrayList<Integer>();
        mMotivatorNameList = new ArrayList<String>();
        mSound1List = new ArrayList<Integer>();
        mSound2List = new ArrayList<Integer>();
        mSound3List = new ArrayList<Integer>();
        mSound4List = new ArrayList<Integer>();
        mSound5List = new ArrayList<Integer>();
        chiaviSpList= new ArrayList<String>();
        //    valoriSwitchList= new ArrayList<Boolean>();
        mTypedArray = getActivity().getResources().obtainTypedArray(R.array.motivatorImageID);
        mTypedArrayS1 = getActivity().getResources().obtainTypedArray(R.array.sounds1);
        mTypedArrayS2 = getActivity().getResources().obtainTypedArray(R.array.sounds2);
        mTypedArrayS3 = getActivity().getResources().obtainTypedArray(R.array.sounds3);
        mTypedArrayS4 = getActivity().getResources().obtainTypedArray(R.array.sounds4);
        mTypedArrayS5 = getActivity().getResources().obtainTypedArray(R.array.sounds5);
        //col ciclo for popolo mTypedArray e gli mTypedArrayS (relativi ai suoni) con gli indirizzi definiti in xml in values

        TypedArray[] arrayOb = new TypedArray[6];  //qui ho creato un array di TypedArray (quello che sto facendo mi serve a creare un solo ciclo per l'inizializzazione dei data model)
        arrayOb[0] = mTypedArray;
        arrayOb[1] = mTypedArrayS1;
        arrayOb[2] = mTypedArrayS2;
        arrayOb[3] = mTypedArrayS3;
        arrayOb[4] = mTypedArrayS4;
        arrayOb[5] = mTypedArrayS5;

        ArrayList[] arrayLists = new ArrayList[6];  //qui ho creato un array di array(una matrice) contenente tutte le immagini ed i suoni
        arrayLists[0] = mMotivatorImageList;
        arrayLists[1] = mSound1List;
        arrayLists[2] = mSound2List;
        arrayLists[3] = mSound3List;
        arrayLists[4] = mSound4List;
        arrayLists[5] = mSound5List;
//CON QUESTI 2 CICLI ANNIDATI STO POPOLANDO GLI ARRAY DI SUONI ED IMMAGINI CREATI LATO JAVA (PRIMA DEL CICLO ANCORA VUOTI) (CHE HO INSERITO DENTRO LA MATRICE), CON SUONI ED IMMAGINI OTTENUTI ATTRAVERSO IL METODO OBTAINTYPEDARRAY (COMUNICANDO CON I FILE XML)
        for (int j = 0; j<arrayLists.length; j++) {
            for (int i = 0; i < mTypedArray.length(); i++) {
                int mId = arrayOb[j].getResourceId(i, -1);
                arrayLists[j].add(mId); //così facendo scorrere il ciclo si riesce ad inserire ogni immagine e suoni all'interno degli array di immagini e suoni(attraverso gli id trovati nella riga di codice precedente)
            }
        }
        mTypedArray.recycle(); //si utilizza per motivi legati alla memoria
        mTypedArrayS1.recycle();
        mTypedArrayS2.recycle();
        mTypedArrayS3.recycle();
        mTypedArrayS4.recycle();
        mTypedArrayS5.recycle();

        mMotivatorNameList.add("Ibrahimovic");
        mMotivatorNameList.add("Michael Jordan");
        mMotivatorNameList.add("Conte");
        mMotivatorNameList.add("Mourinho");
        mMotivatorNameList.add("Totti");
        //setto nomi chiavi e valori booleani di default
        chiaviSpList.add("value1");
        chiaviSpList.add("value2");
        chiaviSpList.add("value3");
        chiaviSpList.add("value4");
        chiaviSpList.add("value5");

        ArrayList<Boolean> valSwitch;
        valSwitch = new ArrayList<>();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", MODE_PRIVATE);
        mDataModelList = new ArrayList<DataModel>();
        for (int i = 0; i < mMotivatorNameList.size(); i++) {
            mDataModelList.add(new DataModel(
                    mMotivatorImageList.get(i),
                    mMotivatorNameList.get(i),
                    mSound1List.get(i), mSound2List.get(i), mSound3List.get(i), mSound4List.get(i), mSound5List.get(i),chiaviSpList.get(i),i
            ));
            valSwitch.add(sharedPreferences.getBoolean(chiaviSpList.get(i), false));
            //mDataModelList.get(i).setPosizioneNellaRecyclerView(i);   //l'idea era di usare la posizione nella classe ViewHolder per poi settare come cambia il booleano in seguito alla pressione dello switch
            //mDataModelList.get(i).setCondizioneSwitch(valSwitch.get(i));
            Log.d("SetMotivatorFragment","è stato inserito il motivatore "+ String.valueOf(mDataModelList.get(i).getmMotivatorName()+" nella lista motivatori"));
            //ho scritto questi messaggi nei file di Log, perchè mi son chiesto se andando direttamente dalla slashActvity alla Home venisse comunque istanziata la lista di dataModel, di cui farò uso nelle classi successive
        }

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        MyAdapter mAdapter = new MyAdapter(mDataModelList, getContext());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }

//    public static boolean noMotivator(){
//        boolean a = false;
//        for (int i=0;i< mDataModelList.size();i++) {
//            if (mDataModelList.get(i).isCondizioneSwitch()) {
//                a =true;
//                }
//        }
//        return a;
//    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_set_motivators,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getActivity(), Home.class));
        return true;
    }


}

