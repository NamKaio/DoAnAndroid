package com.example.doanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanandroid.R;
import com.example.doanandroid.models.HangHoa;
import com.example.doanandroid.utils.DownloadImage;

import java.util.ArrayList;

public class HangHoaAdapter extends BaseAdapter implements Filterable {
    private ArrayList<HangHoa> hangHoaList;
    private ArrayList<HangHoa> hangHoaSearch;
    Context context;

    public HangHoaAdapter(ArrayList<HangHoa> hanghoaList, Context context) {
        this.hangHoaList = hanghoaList;
        hangHoaSearch = new ArrayList<>(hanghoaList);
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return hangHoaFilter;
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

    private Filter hangHoaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<HangHoa> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(hangHoaSearch);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (HangHoa item : hangHoaSearch) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            hangHoaList.clear();
            hangHoaList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}
