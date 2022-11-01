package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.DataBaseHelper.TABLE_NAME;
//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.Home.USER;

import static android.content.Context.MODE_PRIVATE;
import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.DataBaseHelper.USER;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ListaRisultatiFragment extends Fragment {

    ArrayList<DataModelRisultati> mDataModelList;
    ArrayList<DataModelRisultati> mDataModelListAlContrario;
    String user;
    int conta;

    DataBaseHelper mDatabaseHelper;
    //String USER = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_risultati, container, false);
        return v;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        USER = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", MODE_PRIVATE);
//        user = sharedPreferences.getString("email",USER);
        user = "corse";
//        String email= String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//        user = email;
        mDatabaseHelper = new DataBaseHelper(getContext(),user);
        conta =0;

//        Date c = Calendar.getInstance().getTime();
//        Log.d("ListaRisultatiFragment","Current time => " + c);
//
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//        String formattedDate = df.format(c);
//
//        mDatabaseHelper.doInsert(formattedDate,"321","2","84",TABLE_NAME);
////

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Cursor cursor = mDatabaseHelper.getDatiUtente(email,user);
        mDataModelList = new ArrayList<DataModelRisultati>();
        while(cursor.moveToNext()){
            ++conta;
            mDataModelList.add(new DataModelRisultati(String.valueOf(conta),
                    cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getString(5)));  //così posso ottenere a mio piacimento tutte le informazioni di ogni colonna

        }
        cursor.close();  //per non allocare memori in modo oneroso soprattutto quando il database diventa ricco di informazioni ho chiuso il cursore
        mDataModelListAlContrario = new ArrayList<DataModelRisultati>();
        for(int i= mDataModelList.size()-1;i>-1;i--){
            mDataModelListAlContrario.add(mDataModelList.get(i));
        }

            GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 1);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerViewRisultati);
        MyAdapterRisultati mAdapter = new MyAdapterRisultati(mDataModelListAlContrario, getContext());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



    }
}
