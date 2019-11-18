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
    private ArrayList<HangHoa> hanghoaList;
    Context context;

    public HangHoaAdapter(ArrayList<HangHoa> hanghoaList, Context context) {
        this.hanghoaList = hanghoaList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hanghoaList.size();
    }

    @Override
    public Object getItem(int position) {
        return hanghoaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return hanghoaList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyPostView dataitem;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            dataitem = new MyPostView();
            convertView = inflater.inflate(R.layout.post, null);
            dataitem.iv_post_photo = convertView.findViewById(R.id.iv_post_photo);
            dataitem.tv_post_name = convertView.findViewById(R.id.tv_post_name);
            dataitem.tv_post_country = convertView.findViewById(R.id.tv_post_country);
            dataitem.tv_post_manufacturer = convertView.findViewById(R.id.tv_post_manufacturer);
            dataitem.tv_post_cost = convertView.findViewById(R.id.tv_post_cost);
            dataitem.tv_post_sold = convertView.findViewById(R.id.tv_post_sold);
            dataitem.tv_post_favorite = convertView.findViewById(R.id.tv_post_favorite);
            convertView.setTag(dataitem);
        } else {
            dataitem = (MyPostView) convertView.getTag();
        }

        new DownloadImage(dataitem.iv_post_photo).execute(hanghoaList.get(position).getPhoto());
        dataitem.tv_post_name.setText(hanghoaList.get(position).getName());
        dataitem.tv_post_country.setText(hanghoaList.get(position).getCountry());
        dataitem.tv_post_manufacturer.setText(hanghoaList.get(position).getManufacturer());
        dataitem.tv_post_cost.setText(hanghoaList.get(position).getCost() + "đ");
        dataitem.tv_post_sold.setText(hanghoaList.get(position).getSold() + "");
        if (hanghoaList.get(position).getRate() >= 4.5f) {
            dataitem.tv_post_favorite.setVisibility(View.VISIBLE);
        } else {
            dataitem.tv_post_favorite.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    private static class MyPostView {
        ImageView iv_post_photo;
        TextView tv_post_name, tv_post_country, tv_post_manufacturer, tv_post_cost, tv_post_sold, tv_post_favorite;
    }
}
