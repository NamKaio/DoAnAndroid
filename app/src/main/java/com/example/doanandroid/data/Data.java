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

//public class Data {
//    public static int[] ids = {0, 1, 2, 3};
//
//    public static String[] urls = {
//            "http://douongcaocap.vn/wp-content/uploads/2018/11/Bia-Becks-5-Lon-500ml-Th%C3%B9ng-12-Lon.jpg",
//            "http://douongcaocap.vn/wp-content/uploads/2017/09/Bia-Chimay-v%C3%A0ng-48-chai-330ml-300x340-1.png",
//            "http://douongcaocap.vn/wp-content/uploads/2016/10/La-Trappe-Quadrupel-330ml-1.png",
//            "http://douongcaocap.vn/wp-content/uploads/2016/12/Falo-1.png",
//
//    };
//
//    public static String[] names = {
//            "Bia Beck's",
//            "Bia Chimay Vàng",
//            "Bia La Trappe Quadrupel",
//            "Rượu Vang Falo",
//
//    };
//
//    public static String[] countrys = {
//            "Đức",
//            "Bỉ",
//            "Hà Lan",
//            "Ý",
//
//    };
//
//    public static String[] manufacturers = {
//            "Brauerei Beck & Co",
//            "Brasserie de Chimay",
//            "Weyerbacher Brewing",
//            "San Marzano",
//
//    };
//
//    public static float[] concentrations = {
//            5f,
//            4.8f,
//            10f,
//            13.5f,
//
//    };
//
//    public static String[] fulldescs = {
//            "Thuộc dòng bia lager hấp dẫn, nhẹ nhàng đem đến những trải nghiệm đầy thích thú cho người uống. Vị bia thơm mát, nhẹ nhàng dễ uống và dễ cảm nhận. Tất cả sẽ làm người thưởng thức cực kỳ thích thú. Vị bia thơm thơm, nhẹ nhàng và ấn tượng càng làm người dùng thực sự yêu thích. Bia đầy lôi cuốn và hấp dẫn, càng làm người dùng bị chinh phục.",
//            "Chimay Vàng là loại bia đặc biệt với hương vị riêng, khác với các loại Chimay khác. Nếu bạn đã từng uống Chimay xanh hay Chimay đỏ thì cần phải thử thêm Chimay Gold – một loại bia đặc biệt của Chimay, thì mới có thể thưởng thức đầy đủ các hương vị của Chimay.",
//            "Với nồng độ cồn cao, nên bia hơi kén chọn người uống. Chỉ khi bạn biết “uống đúng cách” thì mới có thể cảm nhận đầy đủ mùi vị thơm ngon và thú vị mà dòng bia này mang lại.",
//            "Rượu vang Ý Falo Negroamaro,  là một chai rượu ngon được sản xuất từ giống nho Negroamaro, thuộc miền Nam nước Ý xa xôi của hãng rượu danh tiếng San Marzano.",
//
//    };
//
//    public static String[] kinds = {
//            "Bia",
//            "Bia",
//            "Bia",
//            "Rượu",
//
//    };
//
//    public static int[] costs = {
//            295000,
//            2000000,
//            2380000,
//            440000,
//
//    };
//
//    public static int[] solds = {
//            123,
//            233,
//            101,
//            82,
//
//    };
//
//    public static float[] rates = {
//            4.0f,
//            4.8f,
//            4.5f,
//            3.5f,
//
//    };
//
//    public static int[] quantitys = {
//            235,
//            98,
//            46,
//            33,
//
//    };
//}

