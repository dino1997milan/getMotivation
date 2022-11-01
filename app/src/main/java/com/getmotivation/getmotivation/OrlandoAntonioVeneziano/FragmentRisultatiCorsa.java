package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentRisultatiCorsa extends Fragment {
    SharedPreferences sharedPreferences;
    TextView risultatoPassi,risultatoKm,risultatoKcal,titoloGrafico;
//    TextView superamentoObPassi,superamentoObKm,superamentoObKcal;
    MaterialButton home;

    BarChart barchart,barchartKm,barchartKcal;
    Calendar calendar;
    Date today,yesterday,beforeYesterday,meno3Today,meno9Today,meno4Today,meno5Today,meno6Today;
    String oggi,ieri,avantIeri,meno3daOggi,meno9daOggi,meno4daOggi,meno5daOggi,meno6daOggi;
    String[] stringheGiorni;
    String email;

    String user;
    DataBaseHelper dataBaseHelper;
    float passiXdayOggi,floatDiAppoggio,passiXdayIeri,passiXdayAvantieri,passiXdayOggiMeno3,passiXdayOggiMeno4,passiXdayOggiMeno5,passiXdayOggiMeno6;
    float kmXdayOggi,floatDiAppoggioKm,kmXdayIeri,kmXdayAvantieri,kmXdayOggiMeno3,kmXdayOggiMeno4,kmXdayOggiMeno5,kmXdayOggiMeno6;
    float kcalXdayOggi,floatDiAppoggioKcal,kcalXdayIeri,kcalXdayAvantieri,kcalXdayOggiMeno3,kcalXdayOggiMeno4,kcalXdayOggiMeno5,kcalXdayOggiMeno6;
    float[] passiXday,kmXday,kcalXday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.risultati_corsa_fragment,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        risultatoPassi = view.findViewById(R.id.testoRisultatoPassi);
        risultatoKm = view.findViewById(R.id.testoRisultatoDistanza);
        risultatoKcal = view.findViewById(R.id.testoRisultatoKcal);
//        superamentoObKcal = view.findViewById(R.id.obbiettivoRaggiuntoKcal);
//        superamentoObKm = view.findViewById(R.id.obbiettivoRaggiuntoKm);
//        superamentoObPassi = view.findViewById(R.id.obbiettivoRaggiuntoPassi);
        home = view.findViewById(R.id.tornaAllaHome);
        titoloGrafico = view.findViewById(R.id.titoloGrafico);
        sharedPreferences = getActivity().getSharedPreferences("save",MODE_PRIVATE);
        int obPassi = sharedPreferences.getInt("obbiettivo passi",10000);
        float obKm = sharedPreferences.getFloat("obbiettivo km",3);
        int obKcal =sharedPreferences.getInt("obbiettivo kcal",200);
        int passi = parseInt(sharedPreferences.getString("risultato passi","0"));
        float km = parseFloat(sharedPreferences.getString("risultato km","0"));
        float kcal = parseFloat(sharedPreferences.getString("risultato kcal","0"));


        double km1= (double)km;
        km1 = Math.ceil(km1*1000)/1000;
        double kcal1= (double)kcal;
        kcal1 = Math.ceil(kcal1*1000)/1000;
        risultatoPassi.setText("passi: "+passi);
        risultatoKm.setText("km: "+km1);
        risultatoKcal.setText("kcal: "+kcal1);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Home.class);
                startActivity(intent);
            }
        });
        passiXdayOggi = 0;
        floatDiAppoggio =0;
        passiXdayIeri = 0;
        passiXdayAvantieri = 0;
        passiXdayOggiMeno3 = 0;
        passiXdayOggiMeno4 = 0;
        passiXdayOggiMeno5 = 0;
        passiXdayOggiMeno6 = 0;
        passiXday = new float[]{passiXdayOggiMeno6,passiXdayOggiMeno5,passiXdayOggiMeno4,passiXdayOggiMeno3, passiXdayAvantieri,passiXdayIeri,passiXdayOggi};

        kmXdayOggi = 0;
        floatDiAppoggioKm =0;
        kmXdayIeri = 0;
        kmXdayAvantieri = 0;
        kmXdayOggiMeno3 = 0;
        kmXdayOggiMeno4 = 0;
        kmXdayOggiMeno5 = 0;
        kmXdayOggiMeno6 = 0;
        kmXday = new float[]{kmXdayOggiMeno6,kmXdayOggiMeno5,kmXdayOggiMeno4,kmXdayOggiMeno3, kmXdayAvantieri,kmXdayIeri,kmXdayOggi};

        kcalXdayOggi = 0;
        floatDiAppoggioKcal =0;
        kcalXdayIeri = 0;
        kcalXdayAvantieri = 0;
        kcalXdayOggiMeno3 = 0;
        kcalXdayOggiMeno4 =0;
        kcalXdayOggiMeno5 = 0;
        kcalXdayOggiMeno6 = 0;
        kcalXday = new float[]{kcalXdayOggiMeno6,kcalXdayOggiMeno5,kcalXdayOggiMeno4,kcalXdayOggiMeno3, kcalXdayAvantieri,kcalXdayIeri,kcalXdayOggi};

        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        user = "corse";
        dataBaseHelper = new DataBaseHelper(getContext(),user);


        barchart = view.findViewById(R.id.BarChartPassi);
        barchartKm = view.findViewById(R.id.BarChartKm);
        barchartKcal = view.findViewById(R.id.BarChartKcal);

        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        calendar = Calendar.getInstance();


        Date[] giorni = {meno6Today,meno5Today,meno4Today,meno3Today,beforeYesterday,yesterday,today };
        stringheGiorni = new String[7];
        stringheGiorni[0] = meno6daOggi;
        stringheGiorni[1] =meno5daOggi;
        stringheGiorni[2] =meno4daOggi;
        stringheGiorni[3] =meno3daOggi;
        stringheGiorni[4] =avantIeri;
        stringheGiorni[5] =ieri;
        stringheGiorni[6] =oggi;

        calendar.add(Calendar.DATE,-7);

        for(int i=0; i<7;i++) {
//            if(i==0){
//                calendar.add(Calendar.DATE,0);
//            }
//            else{
            calendar.add(Calendar.DATE,+1);//}
            giorni[i] = calendar.getTime();
            stringheGiorni[i]= dataFormat.format(giorni[i]);
            Log.d("Date girni registrate",stringheGiorni[i]);
        }
//questa era solo una prova per vedere la possibile presenza di errori nel passaggio da un anno all'altro
        calendar.add(Calendar.DATE,-9);
        meno9Today = calendar.getTime();
        meno9daOggi= dataFormat.format(meno9Today);
        Log.d("Date girni registrate",meno9daOggi);

        calendar.add(Calendar.DATE,+9);

//        String[] giorniAllaRovescia = {meno3daOggi,avantIeri,ieri,oggi};
//        stringheGiorni = giorniAllaRovescia;

        for(int i=0; i<7;i++){
            Cursor cursor = dataBaseHelper.getDay(email, user, stringheGiorni[i]);
            while (cursor.moveToNext()) {
                floatDiAppoggio = 0;
                floatDiAppoggioKm=0;
                floatDiAppoggioKcal=0;
                floatDiAppoggio = parseFloat(cursor.getString(2));
                floatDiAppoggioKm=parseFloat(cursor.getString(3));
                floatDiAppoggioKcal=parseFloat(cursor.getString(4));
                passiXday[i] = floatDiAppoggio + passiXday[i];
                kmXday[i] = floatDiAppoggioKm + kmXday[i];
                kcalXday[i] = floatDiAppoggioKcal + kcalXday[i];
                //Log.d("FragmentRisultatiCorsa",cursor.getString(2));
            }
            cursor.close();
        }


        barchart.setDrawBarShadow(false);
        barchart.setDrawValueAboveBar(true);
        barchart.setMaxVisibleValueCount(obPassi+200);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(true);
//        barchart.getAxisLeft().setDrawLabels(false);
        barchart.getAxisRight().setDrawLabels(false);
//        barchart.getXAxis().setDrawLabels(false);
//        barchart.setVisibleXRangeMaximum();
//        barchart.setVisibleYRange();


        barchart.getLegend().setEnabled(false);
        Description desc = new Description();
        desc.setText("");
        barchart.setDescription(desc);
//        barchart.getDescription().setPosition(1,50);


        ArrayList<BarEntry> barEntries = new ArrayList<>();

//        barEntries.add(new BarEntry(1, 0));
//        barEntries.add(new BarEntry(2, 0));
//        barEntries.add(new BarEntry(3, 0));

        for(int j=0;j<7;j++){
        barEntries.add(new BarEntry(j+1, passiXday[j]));}

        BarDataSet barDataSet = new BarDataSet(barEntries, "Day");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);
        LimitLine obbPassi = new LimitLine(obPassi);
        obbPassi.setLineColor(Color.RED);
        //obPassi.setTypeface(Typeface.DEFAULT_BOLD);
        obbPassi.setLabel("obbiettivo passi");
        obbPassi.setLineWidth(4f);
        YAxis leftAxis = barchart.getAxisLeft();
        //il comando per togliere le linee orizzontali non mi funziona
        //leftAxis.setDrawGridLines(false);  //non sapendo come rimuovere le linee della griglia orizzontali ho scelto di aggiungere in seguito quelle verticali
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(obbPassi);
        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(barchart);
        XAxis xAxis = barchart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);

        xAxis.setGranularity(1f);
        xAxis.setTextSize(6f);
        xAxis.setValueFormatter(xAxisFormatter);


        barchart.setData(data);

        //da qui ricopio le stesse operazioni svolte sopra stavolta per ottenere il grafico relativo ai km
        //(molte istruzioni,(alcune istruzioni di istanza e) soprattutto quelle riguardanti l'uso dei cursori non le ho ripetute, perchè l'apertura dei cursori è un'operazione onerosa, quindi è meglio sfruttare il ciclo utilizzato sopra per prelevare tutti i dati di interesse e poi chiudere subito un solo cursore)

        barchartKm.setDrawBarShadow(false);
        barchartKm.setDrawValueAboveBar(true);
        barchartKm.setMaxVisibleValueCount((int) obKm+200);
        barchartKm.setPinchZoom(false);
        barchartKm.setDrawGridBackground(true);
        barchartKm.getAxisRight().setDrawLabels(false);

        barchartKm.getLegend().setEnabled(false);
//        Description desc = new Description();
//        desc.setText("");
        barchartKm.setDescription(desc);



        ArrayList<BarEntry> barEntriesKm = new ArrayList<>();


        for(int j=0;j<7;j++){
            barEntriesKm.add(new BarEntry(j+1, kmXday[j]));}

        BarDataSet barDataSetKm = new BarDataSet(barEntriesKm, "Day");
        barDataSetKm.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData dataKm = new BarData(barDataSetKm);
        dataKm.setBarWidth(0.9f);
        LimitLine obbKm = new LimitLine(obKm);
        obbKm.setLineColor(Color.RED);
        obbKm.setLabel("obbiettivo km");
        obbKm.setLineWidth(4f);
        YAxis leftAxisKm = barchartKm.getAxisLeft();

        leftAxisKm.removeAllLimitLines();
        leftAxisKm.addLimitLine(obbKm);
        ValueFormatter xAxisFormatterKm = new DayAxisValueFormatter(barchartKm);
        XAxis xAxisKm = barchartKm.getXAxis();

        xAxisKm.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisKm.setDrawGridLines(true);

        xAxisKm.setGranularity(1f);  //questo è per impostare la distanza tra due elementi dell'asse delle x
        xAxisKm.setTextSize(6f);
        xAxisKm.setValueFormatter(xAxisFormatterKm);


        barchartKm.setData(dataKm);


        //da qui ricopio le stesse operazioni svolte sopra stavolta per ottenere il grafico relativo ai kcal
        //(molte istruzioni, soprattutto quelle riguardanti l'uso dei cursori non le ho ripetute, perchè l'apertura dei cursori è un'operazione onerosa, quindi è meglio sfruttare il ciclo utilizzato sopra per prelevare tutti i dati di interesse)

        barchartKcal.setDrawBarShadow(false);
        barchartKcal.setDrawValueAboveBar(true);
        barchartKcal.setMaxVisibleValueCount(obKcal+200);
        barchartKcal.setPinchZoom(false);
        barchartKcal.setDrawGridBackground(true);
        barchartKcal.getAxisRight().setDrawLabels(false);

        barchartKcal.getLegend().setEnabled(false);
//        Description desc = new Description();
//        desc.setText("");
        barchartKcal.setDescription(desc);



        ArrayList<BarEntry> barEntriesKcal = new ArrayList<>();


        for(int j=0;j<7;j++){
            barEntriesKcal.add(new BarEntry(j+1, kcalXday[j]));}

        BarDataSet barDataSetKcal = new BarDataSet(barEntriesKcal, "Day");
        barDataSetKcal.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData dataKcal = new BarData(barDataSetKcal);
        dataKcal.setBarWidth(0.9f);
        LimitLine obbKcal = new LimitLine(obKcal);
        obbKcal.setLineColor(Color.RED);
        obbKcal.setLabel("obbiettivo kcal");
        obbKcal.setLineWidth(4f);
        YAxis leftAxisKcal = barchartKcal.getAxisLeft();

        leftAxisKcal.removeAllLimitLines();
        leftAxisKcal.addLimitLine(obbKcal);
        ValueFormatter xAxisFormatterKcal = new DayAxisValueFormatter(barchartKcal);
        XAxis xAxisKcal = barchartKcal.getXAxis();

        xAxisKcal.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisKcal.setDrawGridLines(true);

        xAxisKcal.setGranularity(1f);
        xAxisKcal.setTextSize(6f);
        xAxisKcal.setValueFormatter(xAxisFormatterKcal);


        barchartKcal.setData(dataKcal);


        /////////////////////////
        if(passiXday[6]>obPassi){
//            superamentoObPassi.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "COMPLIMENTI, OGGI HAI SUPERATO IL TUO OBBIETTIVO DI PASSI PERCORSI!", Toast.LENGTH_SHORT).show();
        }
//        if(kmXday[3]>obKm){
////            superamentoObKm.setVisibility(View.VISIBLE);
//        }
//        if(kcalXday[3]>obKcal){
////            superamentoObKcal.setVisibility(View.VISIBLE);
//        }

        //qui infine faccio override del metodo onClick per i differenti grafici
        barchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titoloGrafico.setText("GRAFICO KM PERCORSI");
                barchart.setVisibility(View.GONE);
                barchartKm.setVisibility(View.VISIBLE);
                if(kmXday[6]>obKm){
//            superamentoObKm.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "COMPLIMENTI, OGGI HAI SUPERATO IL TUO OBBIETTIVO DI KM PERCORSI!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        barchartKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titoloGrafico.setText("GRAFICO KCAL BRUCIATE");
                barchartKm.setVisibility(View.GONE);
                barchartKcal.setVisibility(View.VISIBLE);
                if(kcalXday[6]>obKcal){
//            superamentoObKcal.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "COMPLIMENTI, OGGI HAI SUPERATO IL TUO OBBIETTIVO DI KCAL BRUCIATE!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        barchartKcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titoloGrafico.setText("GRAFICO PASSI PERCORSI");
                barchartKcal.setVisibility(View.GONE);
                barchart.setVisibility(View.VISIBLE);
                if(passiXday[6]>obPassi){
//            superamentoObPassi.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "COMPLIMENTI, OGGI HAI SUPERATO IL TUO OBBIETTIVO DI PASSI PERCORSI!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public class DayAxisValueFormatter extends ValueFormatter {
        private final BarLineChartBase<?> chart;
        public DayAxisValueFormatter(BarLineChartBase<?> chart) {
            this.chart = chart;
        }
        @Override
        public String getFormattedValue(float value) {
            return stringheGiorni[((int)value-1)];  //qua sono indicati i valori restituiti sull'asseX
        }
    }

}
