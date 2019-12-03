package com.example.doanandroid.data;

import android.content.Context;
import com.example.doanandroid.R;
import com.example.doanandroid.utils.ResourceHelper;

public final class Data {

    private static Data instance;
    private Context mContext;

    private Data(Context context){
        mContext = context;
    }

    public static Data getInstance(Context context){
        if(instance == null){
            instance = new Data(context);
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

    public String [] countries(){
        return mContext.getResources().getStringArray(R.array.countries);
    }

    public String [] manufacturers(){
        return mContext.getResources().getStringArray(R.array.manufacturers);
    }

    public float [] concentrations(){
        return ResourceHelper.convertArrayStringToArrayFloat(mContext.getResources().getStringArray(R.array.concentrations));
    }

    public String [] fullDescs(){
        return mContext.getResources().getStringArray(R.array.fulldescs);
    }


    public String [] kind(){
        return mContext.getResources().getStringArray(R.array.kind);
    }


    public int [] costs(){
        return mContext.getResources().getIntArray(R.array.costs);
    }

    public float [] rates(){
        return ResourceHelper.convertArrayStringToArrayFloat(mContext.getResources().getStringArray(R.array.rates));
    }

    public int [] solds(){
        return mContext.getResources().getIntArray(R.array.solds);
    }

    public int [] quantities(){
        return mContext.getResources().getIntArray(R.array.quantity);
    }
}
