package com.example.doanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.R;
import com.example.doanandroid.models.HangHoa;
import com.example.doanandroid.utils.DownloadImage;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private ArrayList<HangHoa> gioHangList;
    Context context;

    public CartAdapter(ArrayList<HangHoa> giohang_list, Context context) {
        this.gioHangList = giohang_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gioHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return gioHangList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyCartView dataItem;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            dataItem = new MyCartView();
            convertView = inflater.inflate(R.layout.cart, null);
            dataItem.ivCartPhoto = convertView.findViewById(R.id.ivCartPhoto);
            dataItem.tvCartName = convertView.findViewById(R.id.tvCartName);
            dataItem.tvCartKind = convertView.findViewById(R.id.tvCartKind);
            dataItem.tvCartCost = convertView.findViewById(R.id.tvCartCost);
            dataItem.tvCartQuantity = convertView.findViewById(R.id.tvCartQuantity);
            dataItem.etCartCustomQuantity = convertView.findViewById(R.id.etCartCustomQuantity);
            dataItem.btCartMines = convertView.findViewById(R.id.btCartMines);
            dataItem.btCartPlus = convertView.findViewById(R.id.btCartPlus);
            dataItem.btCartDelete = convertView.findViewById(R.id.btCartDelete);
            convertView.setTag(dataItem);
        } else {
            dataItem = (MyCartView) convertView.getTag();
        }

        new DownloadImage(dataItem.ivCartPhoto).execute(gioHangList.get(position).getPhoto());
        dataItem.tvCartName.setText(gioHangList.get(position).getName());
        dataItem.tvCartKind.setText(gioHangList.get(position).getKind());
        dataItem.tvCartCost.setText(gioHangList.get(position).getCost() + "đ");
        dataItem.tvCartQuantity.setText(gioHangList.get(position).getQuantity() + "");
        dataItem.etCartCustomQuantity.setText(1 + "");

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int customquantity = Integer.parseInt(dataItem.etCartCustomQuantity.getText().toString());
                int quantity = Integer.parseInt(dataItem.tvCartQuantity.getText().toString());
                switch (v.getId()){
                    case R.id.btCartMines:
                        if (customquantity > 1) {
                            dataItem.etCartCustomQuantity.setText(customquantity - 1 + "");
                        } else {
                            Toast msg = Toast.makeText(context, "Mặt hàng đã đạt giá trị tối thiểu!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                        break;
                    case R.id.btCartPlus:
                        if (customquantity <= quantity) {
                            dataItem.etCartCustomQuantity.setText(customquantity + 1 + "");
                        } else {
                            Toast msg = Toast.makeText(context, "Mặt hàng đã đạt giá trị tối đa!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                        break;
                    case R.id.btCartDelete:

                        break;
                }
            }
        };

        dataItem.btCartMines.setOnClickListener(onClick);
        dataItem.btCartPlus.setOnClickListener(onClick);
        dataItem.btCartDelete.setOnClickListener(onClick);
        return convertView;
    }

    private static class MyCartView {
        ImageView ivCartPhoto;
        TextView tvCartName;
        TextView tvCartKind;
        TextView tvCartCost;
        TextView tvCartQuantity;
        EditText etCartCustomQuantity;
        Button btCartMines;
        Button btCartPlus;
        Button btCartDelete;
    }
}
