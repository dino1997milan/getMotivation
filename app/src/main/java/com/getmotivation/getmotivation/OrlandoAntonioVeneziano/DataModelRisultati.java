package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

public class DataModelRisultati {

    String mNumeroCorsa;
    String mDataCorsa;
    String mPassi;
    String mKm;
    String mKcal;
    String email;

    public DataModelRisultati(String mNumeroCorsa, String mDataCorsa, String mPassi, String mKm, String mKcal, String email) {
        this.mNumeroCorsa = mNumeroCorsa;
        this.mDataCorsa = mDataCorsa;
        this.mPassi = mPassi;
        this.mKm = mKm;
        this.mKcal = mKcal;
        this.email = email;
    }

    public String getmPassi() {
        return mPassi;
    }

    public String getmKm() {
        return mKm;
    }

    public String getmKcal() {
        return mKcal;
    }

    public String getmNumeroCorsa() {
        return mNumeroCorsa;
    }

    public String getmDataCorsa() {
        return mDataCorsa;
    }

    public String getEmail() {
        return email;
    }
}
