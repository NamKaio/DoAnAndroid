package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.data.Data;
import com.example.doanandroid.utils.DownloadImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    int id = 0;
    public static ArrayList<Integer> giohangIds = new ArrayList<Integer>();

    @BindView(R.id.iv_detail_photo)
    ImageView iv_detail_photo;

    @BindView(R.id.tv_detail_name)
    TextView tv_detail_name;

    @BindView(R.id.tv_detail_cost)
    TextView tv_detail_cost;

    @BindView(R.id.tv_detail_sold)
    TextView tv_detail_sold;

    @BindView(R.id.tv_detail_country)
    TextView tv_detail_country;

    @BindView(R.id.tv_detail_manufacturer)
    TextView tv_detail_manufacturer;

    @BindView(R.id.tv_detail_kind)
    TextView tv_detail_kind;

    @BindView(R.id.tv_detail_concentration)
    TextView tv_detail_concentration;

    @BindView(R.id.tv_detail_quantity)
    TextView tv_detail_quantity;

    @BindView(R.id.tv_detail_fulldesc)
    TextView tv_detail_fulldesc;

    @BindView(R.id.tv_detail_favorite)
    TextView tv_detail_favorite;

    @BindView(R.id.rb_detail_rate)
    RatingBar rb_detail_rate;

    @BindView(R.id.bt_detail_add_to_cart)
    Button bt_detail_add_to_cart;

    @BindView(R.id.bt_detail_cancel)
    Button bt_detail_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", 0);

        Data data = Data.getInstance(this);
        new DownloadImage(iv_detail_photo).execute(data.urls()[id]);
        tv_detail_name.setText(data.names()[id]);
        tv_detail_cost.setText(data.costs()[id] + "đ");
        tv_detail_sold.setText("Đã bán: " + data.solds()[id] + "");
        tv_detail_country.setText(data.countries()[id]);
        tv_detail_manufacturer.setText(data.manufacturers()[id]);
        tv_detail_kind.setText(data.kind()[id]);
        tv_detail_concentration.setText(data.concentrations()[id] + "%");
        tv_detail_quantity.setText(data.quantities()[id] + "");
        tv_detail_fulldesc.setText(data.fullDescs()[id]);
        rb_detail_rate.setRating(data.rates()[id]);
        if (data.rates()[id] >= 4.5f) {
            tv_detail_favorite.setVisibility(View.VISIBLE);
        } else {
            tv_detail_favorite.setVisibility(View.INVISIBLE);
        }

        ClickListener listener = new ClickListener();
        bt_detail_add_to_cart.setOnClickListener(listener);
        bt_detail_cancel.setOnClickListener(listener);
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_detail_add_to_cart:
                    Intent intentAdd = new Intent(getApplicationContext(), CartActivity.class);
                    intentAdd.putExtra("id", id);
                    System.out.println("id hàng được click: " + id);
                    Toast msg = Toast.makeText(getApplicationContext(), "Sản phẩm đã được thêm vào giỏ!", Toast.LENGTH_SHORT);
                    msg.show();
                    giohangIds.add(id);
                    System.out.println("Giỏ hàng add id: " + id);
                    startActivity(intentAdd);
                    break;
                case R.id.bt_detail_cancel:
                    Intent intentCancel = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentCancel);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity( new Intent(getApplicationContext(), MainActivity.class));
    }
}
