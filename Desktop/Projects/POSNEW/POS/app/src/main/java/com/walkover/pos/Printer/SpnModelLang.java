package com.walkover.pos.Printer;

/**
 * Created by saurabh on 16/2/17.
 */

public class SpnModelLang {
    private String mModelName = "";
    private int mModelConstant = 0;

    public SpnModelLang(String mModelName, int mModelConstant) {
        this.mModelName = mModelName;
        this.mModelConstant = mModelConstant;
    }

    public int getModelConstant() {
        return mModelConstant;
    }

    @Override
    public String toString() {
        return mModelName;
    }
}
