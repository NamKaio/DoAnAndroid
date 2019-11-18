package com.example.doanandroid.data;

import android.content.Context;

import com.example.doanandroid.R;

public class DataCart {
    private static DataCart instance;
    private Context mContext;

    private DataCart(Context context){
        mContext = context;
    }

    public static DataCart getInstance(Context context){
        if(instance == null){
            instance = new DataCart(context);
        }
        return instance;
    }

    public int [] ids(){
        return mContext.getResources().getIntArray(R.array.ids);
    }

    public String [] names(){
        return mContext.getResources().getStringArray(R.array.names);
    }

    public String [] urls(){
        return mContext.getResources().getStringArray(R.array.urls);
    }

    public String [] kind(){
        return mContext.getResources().getStringArray(R.array.kind);
    }

    public int [] costs(){
        return mContext.getResources().getIntArray(R.array.costs);
    }
}
