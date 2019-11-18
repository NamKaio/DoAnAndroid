package com.example.doanandroid.utils;

public class ResourceHelper {

    public static float[] convertArrayStringToArrayFloat(String[] arrStr){
        float rs[] = new float[arrStr.length];
        for (int i=0; i < arrStr.length; i++){
            rs[i] = Float.valueOf(arrStr[i]);
        }
        return rs;
    }
}
