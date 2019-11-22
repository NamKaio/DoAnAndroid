package com.example.doanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanandroid.R;
import com.example.doanandroid.models.HangHoa;
import com.example.doanandroid.utils.DownloadImage;

import java.util.ArrayList;

public class HangHoaAdapter extends BaseAdapter {
    private ArrayList<HangHoa> hangHoaList;
    Context context;

    public HangHoaAdapter(ArrayList<HangHoa> hanghoaList, Context context) {
        this.hangHoaList = hanghoaList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hangHoaList.size();
    }

    @Override
    public Object getItem(int position) {
        return hangHoaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return hangHoaList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyPostView dataitem;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            dataitem = new MyPostView();
            convertView = inflater.inflate(R.layout.post, null);
            dataitem.ivPostPhoto = convertView.findViewById(R.id.ivPostPhoto);
            dataitem.tvPostName = convertView.findViewById(R.id.tvPostName);
            dataitem.tvPostCountry = convertView.findViewById(R.id.tvPostCountry);
            dataitem.tvPostManufacturer = convertView.findViewById(R.id.tvPostManufacturer);
            dataitem.tvPostCost = convertView.findViewById(R.id.tvPostCost);
            dataitem.tvPostSold = convertView.findViewById(R.id.tvPostSold);
            dataitem.tvPostFavorite = convertView.findViewById(R.id.tvPostFavorite);
            convertView.setTag(dataitem);
        } else {
            dataitem = (MyPostView) convertView.getTag();
        }

        new DownloadImage(dataitem.ivPostPhoto).execute(hangHoaList.get(position).getPhoto());
        dataitem.tvPostName.setText(hangHoaList.get(position).getName());
        dataitem.tvPostCountry.setText(hangHoaList.get(position).getCountry());
        dataitem.tvPostManufacturer.setText(hangHoaList.get(position).getManufacturer());
        dataitem.tvPostCost.setText(hangHoaList.get(position).getCost() + "đ");
        dataitem.tvPostSold.setText(hangHoaList.get(position).getSold() + "");
        if (hangHoaList.get(position).getRate() >= 4.5f) {
            dataitem.tvPostFavorite.setVisibility(View.VISIBLE);
        } else {
            dataitem.tvPostFavorite.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    private static class MyPostView {
        ImageView ivPostPhoto;
        TextView tvPostName;
        TextView tvPostCountry;
        TextView tvPostManufacturer;
        TextView tvPostCost;
        TextView tvPostSold;
        TextView tvPostFavorite;
    }
}
