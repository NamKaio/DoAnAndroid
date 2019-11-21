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

    private ArrayList<HangHoa> giohang_list;
    Context context;

    public CartAdapter(ArrayList<HangHoa> giohang_list, Context context) {
        this.giohang_list = giohang_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return giohang_list.size();
    }

    @Override
    public Object getItem(int position) {
        return giohang_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return giohang_list.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyCartView dataitem;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            dataitem = new MyCartView();
            convertView = inflater.inflate(R.layout.cart, null);
            dataitem.iv_cart_photo = convertView.findViewById(R.id.iv_cart_photo);
            dataitem.tv_cart_name = convertView.findViewById(R.id.tv_cart_name);
            dataitem.tv_cart_kind = convertView.findViewById(R.id.tv_cart_kind);
            dataitem.tv_cart_cost = convertView.findViewById(R.id.tv_cart_cost);
            dataitem.tv_cart_quantity = convertView.findViewById(R.id.tv_cart_quantity);
            dataitem.et_cart_custom_quantity = convertView.findViewById(R.id.et_cart_custom_quantity);
            dataitem.bt_cart_mines = convertView.findViewById(R.id.bt_cart_mines);
            dataitem.bt_cart_plus = convertView.findViewById(R.id.bt_cart_plus);
            dataitem.bt_cart_delete = convertView.findViewById(R.id.bt_cart_delete);
            convertView.setTag(dataitem);
        } else {
            dataitem = (MyCartView) convertView.getTag();
        }

        new DownloadImage(dataitem.iv_cart_photo).execute(giohang_list.get(position).getPhoto());
        dataitem.tv_cart_name.setText(giohang_list.get(position).getName());
        dataitem.tv_cart_kind.setText(giohang_list.get(position).getKind());
        dataitem.tv_cart_cost.setText(giohang_list.get(position).getCost() + "đ");
        dataitem.tv_cart_quantity.setText(giohang_list.get(position).getQuantity() + "");
        dataitem.et_cart_custom_quantity.setText(1 + "");

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int customquantity = Integer.parseInt(dataitem.et_cart_custom_quantity.getText().toString());
                int quantity = Integer.parseInt(dataitem.tv_cart_quantity.getText().toString());
                switch (v.getId()){
                    case R.id.bt_cart_mines:
                        if (customquantity > 1) {
                            dataitem.et_cart_custom_quantity.setText(customquantity - 1 + "");
                        } else {
                            Toast msg = Toast.makeText(context, "Mặt hàng đã đạt giá trị tối thiểu!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                        break;
                    case R.id.bt_cart_plus:
                        if (customquantity <= quantity) {
                            dataitem.et_cart_custom_quantity.setText(customquantity + 1 + "");
                        } else {
                            Toast msg = Toast.makeText(context, "Mặt hàng đã đạt giá trị tối đa!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                        break;
                    case R.id.bt_cart_delete:

                        break;
                }
            }
        };

        dataitem.bt_cart_mines.setOnClickListener(onClick);
        dataitem.bt_cart_plus.setOnClickListener(onClick);
        dataitem.bt_cart_delete.setOnClickListener(onClick);
        return convertView;
    }

    private static class MyCartView {
        ImageView iv_cart_photo;
        TextView tv_cart_name;
        TextView tv_cart_kind;
        TextView tv_cart_cost;
        TextView tv_cart_quantity;
        EditText et_cart_custom_quantity;
        Button bt_cart_mines;
        Button bt_cart_plus;
        Button bt_cart_delete;
    }
}
