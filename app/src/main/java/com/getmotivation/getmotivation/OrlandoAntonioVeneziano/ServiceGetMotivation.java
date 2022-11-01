package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Intent.getIntent;
import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.Run.CUSTOM_BROADCAST;
import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.SetMotivatorFragment.mDataModelList;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceGetMotivation extends Service implements AudioManager.OnAudioFocusChangeListener,SensorEventListener {
    private static final String TAG = "ServiceGetMotivation";

    Timer timer;
    MediaPlayer mp;
    ArrayList<Integer> playlist;
    int i;
    AudioManager mAudioManager;
    Random generator = new Random();
    int tmp;
    int time;
    int conta;
    IntentFilter rumoreIntentFilter;
    AudioRumoroso mAudioRumoroso;
    PowerManager.WakeLock wakeLock;
    boolean noM;
    SensorManager sensorManager;
    private long lastUpdate;
    Sensor sensor;
    Integer passi;
    double s0;
    double a;
    double t;
    double s;
    double a0;
    double delta_a;
    double v;
    double kcal;
    String kmPercorsi;
    Intent intent;
    int modalità;
    float peso;
    float lPasso;
    double valLstep;
    double valPeso;
//    AcquisizioniSensore acquisizioneSensore;

    @Override
    public void onCreate() {
        super.onCreate();
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.acquire();
    }

    private class AudioRumoroso extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            soundStop();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
//        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
//                "MyApp::MyWakelockTag");
//        wakeLock.acquire();
        i = 0;
        conta = 0;

        modalità = intent.getIntExtra("TIME", 1);
        if (modalità == 1) {
            SharedPreferences sharedPreferences = this.getSharedPreferences("save", MODE_PRIVATE);
            time = sharedPreferences.getInt("secondiGetMotivation", 0);

            createNotificationChannel();
//            Intent resultIntent = new Intent(this,GetMotivation.class);
//            PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            Notification mNotification = new NotificationCompat.Builder(this,
                    "MY_CHANNEL_ID")
                    .setContentTitle("Get focused")
                    .setContentText("Fallisci solo quando smetti di provare")
                    .setSmallIcon(R.drawable.icons8motivation256)
//                    .setContentIntent(PendingIntent
//                    .getActivity(this, 0, new Intent(this, GetMotivation.class),
//                            PendingIntent.FLAG_UPDATE_CURRENT))
//                    .setContentIntent(resultPendingIntent)
                    .build();
// SE IL SERVICE è AVVIATO DALL?ACTIVITY GETMOTIVATION IL PENDINGINTENT NON FUNZIONA ANCHE SE HO USATO LE STESSE RIGHE DI CODICE, HO SUPPOSTO CHE IL VIDEO APPESANTISCA L'ACTIVITY E PER QUESTO NON FUNZIONI
// HO PREFERITO RIMUOVERE LE RIGHE DI CODICE DEL PENDINGINTENT PERCHè INSISTENTO CON SVARIATI TENTATIVI HO NOTATO UN PAIO DI VOLTE CHE QUESTO INTENT FINISCE PER FAR CRASHARE L'APP
            startForeground(1, mNotification);
        } else {
            SharedPreferences sharedPreferences = this.getSharedPreferences("save", MODE_PRIVATE);
            time = sharedPreferences.getInt("secondiRun", 20);
            peso = sharedPreferences.getFloat("peso",65);
            lPasso= sharedPreferences.getFloat("lunghezzaPasso",(float)0.6);

            valPeso =(double)peso;
            valLstep =(double)lPasso;

            createNotificationChannel();
            Notification mNotification = new NotificationCompat.Builder(this,
                    "MY_CHANNEL_ID")
                    .setContentTitle("Get focused")
                    .setContentText("Step by step, day by day")
                    .setSmallIcon(R.drawable.menutendina_run_icon_app_goapp_icons8startskintype448)
//                    .setContentIntent(PendingIntent
//                            .getActivity(this, 0, new Intent(this, Run.class),
//                                    PendingIntent.FLAG_UPDATE_CURRENT))
                    //.setColor(Color.BLACK)
                    .build();

            startForeground(1, mNotification);
            passi = 0;
            s0 = 0;
            s = 0;
            a = 0;
            t = 0;
            a0 = 0;
            delta_a = 0;
            v = 0;
            kcal = 0;
//            acquisizioneSensore = new AcquisizioniSensore();
//            acquisizioneSensore.execute();
            kmPercorsi = String.valueOf(0);
            //qua continuo ad implementare tutto ciò che serve in run
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            lastUpdate = System.currentTimeMillis();
            sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);


        }
        timer = new Timer();

        mp = null;
        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        mAudioRumoroso = new AudioRumoroso();
        rumoreIntentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);

        if (!noMotivator()){
        new GetMotivation().execute();}


        return START_NOT_STICKY;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent!= null){
        //if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerazione(sensorEvent);
        }
    }

    private void getAccelerazione(SensorEvent event) {
        float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        double stimaRadiceSommaInQuadratura = Math.sqrt((x * x + y * y + z * z));
        a = stimaRadiceSommaInQuadratura;
        delta_a = a - a0;
        a0 = a;
        long actualTime = event.timestamp;
        //t= (actualTime-lastUpdate)*Math.pow(10,-3);
        if (delta_a > 6) {
//            if (actualTime - lastUpdate < 200) {
//                return;
//            } //se non è trascorso almeno 1/5 di secondo non si aggiorna
            lastUpdate = actualTime;
            passi++;
            s = valLstep * passi* Math.pow(10,-3);   //un passo equivale a 60cm , ho moltiplicato per 10^-3 per rappresentare la distanza in chilometri
            s = Math.ceil(s * 1000) / 1000;
            //s=((s0+(t*t*a))/1000); //ho considerato il percorso svolto rettilineo ed ho usato una semplice formula di fisica classica
            //s0=s*1000;  //questo perchè all'interno della formula s0 mi serve in metri
            //v= delta_a*t*3.6;
            kmPercorsi = String.valueOf(s);
            kcal = passi * 0.5 * Math.pow(10, -3) * valPeso;  //ho trovato questa formula su internet, 65 è il valore di default del peso
            kcal = Math.ceil(kcal * 1000) / 1000;
            intent = new Intent(CUSTOM_BROADCAST);
            intent.putExtra("passi", passi.toString());
            intent.putExtra("distanza", kmPercorsi);
            intent.putExtra("kcal", String.valueOf(kcal));
            sendBroadcast(intent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {

        NotificationChannel nc = new NotificationChannel("MY_CHANNEL_ID",
                "Stai utilizzando GetMotivation()", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(nc);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (!noMotivator()){
        soundStop();
        mp.release();
        timer.cancel();
        unregisterReceiver(mAudioRumoroso);}
        wakeLock.release();
        if (modalità == 0) {
            sensorManager.unregisterListener(this);
        }

        //so che esiste un istante esatto in cui l'utente premendo il tasto stop svolgerà due unregister e farà crashare l'app
        //però: questa cosa può avvenire solo durante l'esecuzione del metodo playNext quando vengono svolte le istruzioni unregister, create e poi soundStart;
        //in media un'istruzione impiega due cicli di clock per esser svolta e la frequenza dei processori odierni è elevatissima
        //mi è sembrato molto più produttivo deregistrare il Broadcast quando il service viene distrutto, senza lasciare il compito Garbage Collector, altrimenti riaprendo e chiudendo il service verrà allocata sempre più memoria
        super.onDestroy();
        Log.d(TAG, "onDestroy: MyFrgService has been killed!");
    }

    public ArrayList<Boolean> valSwitch() {   //questo metodo è vero quando non ci sono i motivatori
        ArrayList<Boolean> valSwitch;
        ArrayList<String> chiaviSpListMain = new ArrayList<String>();
        valSwitch = new ArrayList<>();
        //setto nomi chiavi, per recuperare booleani precedenti
        chiaviSpListMain.add("value1");
        chiaviSpListMain.add("value2");
        chiaviSpListMain.add("value3");
        chiaviSpListMain.add("value4");
        chiaviSpListMain.add("value5");
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("save", MODE_PRIVATE);
        for (int i = 0; i < chiaviSpListMain.size(); i++) {
            valSwitch.add(sharedPreferences.getBoolean(chiaviSpListMain.get(i), false));
        }
        return valSwitch;
    }

    // "algoritmo per una permutazione casuale di un insieme finito"
    // con questo metodo scritto qui di seguito ho riprodotto un semplice algoritmo trovato su internet, inizialmente ideato da Ronald Fisher e Frank Yates, che si pone l'obbiettivo di disordinare l'ordine degli elementi all'interno di un vettore
    // per far si che l'ordinamento sia il più casuale possibile ho introdotto un ulteriore ciclo for, che permette di ripetere l'algoritmo un numero di volte pari al numero
    public ArrayList<Integer> disordinaPlaylist(ArrayList<Integer> playlist) {
        for (int j = 0; j < playlist.size(); j++) {
            for (int i = 0; i < playlist.size(); i++) {
                int randomIndex = generator.nextInt(playlist.size());
                tmp = playlist.get(i);
                playlist.set(i, playlist.get(randomIndex));
                playlist.set(randomIndex, tmp);
            }
        }
        return playlist;
    }

    public ArrayList<Integer> getMotivation() {
        ArrayList<Integer> listaDaCaricare;
        listaDaCaricare = new ArrayList<>();
        ArrayList<Boolean> valSwitch = new ArrayList<>();
        valSwitch = valSwitch();
        for (int l = 0; l < valSwitch.size(); l++) {
            if (valSwitch.get(l)) {
                listaDaCaricare.add(mDataModelList.get(l).getmMotivatorSound1());
                listaDaCaricare.add(mDataModelList.get(l).getmMotivatorSound2());
                listaDaCaricare.add(mDataModelList.get(l).getmMotivatorSound3());
                listaDaCaricare.add(mDataModelList.get(l).getmMotivatorSound4());
                listaDaCaricare.add(mDataModelList.get(l).getmMotivatorSound5());
            }
        }

        return listaDaCaricare;
    }

    public void playNext2() {
        //final int ATTESA = 1000 * (sharedPreferences.getInt("secondiGetMotivation", 0));
//        for(int j=0;j<playlist.size();j++){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                soundStop();
                unregisterReceiver(mAudioRumoroso);
                mp = MediaPlayer.create(getApplicationContext(), playlist.get(++i));
                soundStart(); //per esser più precisi questa riga di codice andrebbe eseguita sse questa applicazione aveva già l'audioFocus
                if (playlist.size() > i + 1) {
                    playNext();
                    Log.d("PlayNext", String.valueOf(++conta));
                } else {
                    i = -1;
                    playlist = disordinaPlaylist(playlist);
                    playNext();
                    Log.d("PlayNext", String.valueOf(++conta));
                }
            }
        }, mp.getDuration() + 100 + (time * 1000));
        //}
    }

    public void playNext() {
        //final int ATTESA = 1000 * (sharedPreferences.getInt("secondiGetMotivation", 0));
//        for(int j=0;j<playlist.size();j++){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                soundStop();
                mp.release();
                unregisterReceiver(mAudioRumoroso);
                mp = MediaPlayer.create(getApplicationContext(), playlist.get(++i));
                soundStart(); //per esser più precisi questa riga di codice andrebbe eseguita sse questa applicazione aveva già l'audioFocus
                if (playlist.size() > i + 1) {
                    playNext2();
                    Log.d("PlayNext", String.valueOf(++conta));
                } else {
                    i = -1;
                    playlist = disordinaPlaylist(playlist);
                    playNext2();
                    Log.d("PlayNext", String.valueOf(++conta));
                }
            }
        }, mp.getDuration() + 100 + (time * 1000));
        //}
    }

    public int trovaILTempo(ArrayList<Integer> playlist) {
        int durata = 0;
        MediaPlayer mp2;
        for (int i = 0; i < playlist.size(); i++) {
            mp2 = MediaPlayer.create(getApplicationContext(), playlist.get(i));
            durata = durata + mp2.getDuration() + 100 + (time * 1000);
        }

        return durata;
    }



    public void soundStart() {
        registerReceiver(mAudioRumoroso, rumoreIntentFilter);
        int requestAudioFocusResult = mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (requestAudioFocusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mp.start();
        }
    }

    public void soundStop() {
        mp.stop();
        mAudioManager.abandonAudioFocus(this);

    }

    @Override
    public void onAudioFocusChange(int audioFocusChanged) {
        switch (audioFocusChanged) {
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS:
                mp.setVolume(0.7f, 0.7f);  //siccome l'esecuzione dell'asyncTask continua per fare in modo che l'utente sia a conoscenza del fatto che l'app è ancora in esecuzione abbasso solo il volume anzicchè stoppare il suono (ovviamente con le chiamate e col navigatore non ci sono problemi)
                //soundStop();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                mp.setVolume(0.2f, 0.2f);  //così se l'utente usa googleMaps mentre corre non ci sono problemi
                break;
            case AudioManager.AUDIOFOCUS_GAIN:
                mp.setVolume(1f, 1f);
                break;
        }
    }

    public void loopMusic() {
        playlist = new ArrayList<>();
//            playlist.add(R.raw.sound1conte);
//            playlist.add(R.raw.sound4conte);
        playlist = getMotivation();   //col metodo getMotivation() mi costruisco la playlist (lista degli indirizzi associati ad i diversi suoni)
        playlist = disordinaPlaylist(playlist);   //per rendere l'esperienza di ascolto non ridondante ho aggiunto un metodo che rende casuale l'ordinamento dei suoni all'interno della playlist
//    timer = new Timer();
        mp = MediaPlayer.create(getApplicationContext(), playlist.get(0));
        soundStart();
        if (playlist.size() > 1) {
            playNext();
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
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
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


    public class GetMotivation extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            //while(5>4){
            loopMusic();
//            try {
//                Thread.sleep(trovaILTempo(playlist));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Log.d("Background service GetMotivation", "finita l'esecuzione di doInBackground"); //}
//            playlist = new ArrayList<>();
////            playlist.add(R.raw.sound1conte);
////            playlist.add(R.raw.sound4conte);
//            playlist= getMotivation();   //col metodo getMotivation() mi costruisco la playlist (lista degli indirizzi associati ad i diversi suoni)
//            playlist =disordinaPlaylist(playlist);   //per rendere l'esperienza di ascolto non ridondante ho aggiunto un metodo che rende casuale l'ordinamento dei suoni all'interno della playlist
//            timer = new Timer();
//                mp = MediaPlayer.create(getApplicationContext(), playlist.get(0));
//                soundStart();
//                if (playlist.size() > 1) {
//                    playNext();
//                }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            //null; //qui non faccio nulla poichè gestisco solo la riproduzione della musica
        }


    }


}