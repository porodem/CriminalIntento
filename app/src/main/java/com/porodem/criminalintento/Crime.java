package com.porodem.criminalintento;

import java.util.Date;
import java.util.UUID;

/**
 * Created by porod on 10.02.2018.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequestPolice;
    private String mSuspect;

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id){
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public boolean isRequestPolice() {
        return mRequestPolice;
    }

    public void setRequestPolice(boolean requestPolice) {
        mRequestPolice = requestPolice;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public String getPhotoFileName(){
        return "IMG_" + getId().toString() + ".jpg";
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
}
