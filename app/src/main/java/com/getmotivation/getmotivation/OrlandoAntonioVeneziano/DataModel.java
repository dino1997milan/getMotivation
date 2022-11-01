package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;
//NOTA: SONO CONSAPEVOLE DEL FATTO CHE PER POPOLARE I VIEWHOLDER NON E' NECESSARIO INSERIRE I SUONI NEI DATAMODEL
//PERO' MI E' MOLTO COMODO POPOLARE I DATAMODEL IN QUESTO MODO PER LA SCRITTURA DEI METODI SUCCESSIVI (IN QUESTO MODO POSSO ACCEDERE DIRETTAMENTE AD UN SOLO ARRAY DI DATAMODEL, SENZA DOVER UTILIZZARE PIUì ARRAY DIFFERENTI PER I DIVERSI SUONI)
public class DataModel {

    int mMotivatorImageId;
    String mMotivatorName;
    int mMotivatorSound1;
    int mMotivatorSound2;
    int mMotivatorSound3;
    int mMotivatorSound4;
    int mMotivatorSound5;
    String chiaveSp;
//    boolean condizioneSwitch;
    int posizioneNellaRecyclerView;

    public int getPosizioneNellaRecyclerView() {
        return posizioneNellaRecyclerView;
    }

    public void setPosizioneNellaRecyclerView(int posizioneNellaRecyclerView) {
        this.posizioneNellaRecyclerView = posizioneNellaRecyclerView;
    }

    public String getChiaveSp() {
        return chiaveSp;
    }

    void setChiaveSp(String chiaveSp) {
        chiaveSp = chiaveSp;
    }

//    public boolean isCondizioneSwitch() {
//        return condizioneSwitch;
//    }
//
//    public void setCondizioneSwitch(boolean condizioneSwitch) {
//        condizioneSwitch = condizioneSwitch;
//    }

    public DataModel (int motivatorImageId, String motivatorName,int motivatorSound1,int motivatorSound2,int motivatorSound3,int motivatorSound4, int motivatorSound5,String value,int posizione){
        mMotivatorImageId = motivatorImageId;
        mMotivatorName = motivatorName;
        mMotivatorSound1 = motivatorSound1;
        mMotivatorSound2 = motivatorSound2;
        mMotivatorSound3 = motivatorSound3;
        mMotivatorSound4 = motivatorSound4;
        mMotivatorSound5 = motivatorSound5;
        chiaveSp= value;
        posizioneNellaRecyclerView = posizione;
    }

    public int getmMotivatorImageId() {
        return mMotivatorImageId;
    }

    public void setmMotivatorImageId(int mMotivatorImageId) {
        this.mMotivatorImageId = mMotivatorImageId;
    }

    public String getmMotivatorName() {
        return mMotivatorName;
    }

    public void setmMotivatorName(String mMotivatorName) {
        this.mMotivatorName = mMotivatorName;
    }

    public int getmMotivatorSound1() {
        return mMotivatorSound1;
    }

    public void setmMotivatorSound1(int mMotivatorSound1) {
        this.mMotivatorSound1 = mMotivatorSound1;
    }

    public int getmMotivatorSound2() {
        return mMotivatorSound2;
    }

    public void setmMotivatorSound2(int mMotivatorSound2) {
        this.mMotivatorSound2 = mMotivatorSound2;
    }

    public int getmMotivatorSound3() {
        return mMotivatorSound3;
    }

    public void setmMotivatorSound3(int mMotivatorSound3) {
        this.mMotivatorSound3 = mMotivatorSound3;
    }

    public int getmMotivatorSound4() {
        return mMotivatorSound4;
    }

    public void setmMotivatorSound4(int mMotivatorSound4) {
        this.mMotivatorSound4 = mMotivatorSound4;
    }

    public int getmMotivatorSound5() {
        return mMotivatorSound5;
    }

    public void setmMotivatorSound5(int mMotivatorSound5) {
        this.mMotivatorSound5 = mMotivatorSound5;
    }
}
