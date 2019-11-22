package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.data.Data;
import com.example.doanandroid.models.HangHoa;
import com.example.doanandroid.utils.DownloadImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    int id = 0;
    public static ArrayList<HangHoa> gioHangs = new ArrayList<>();

    @BindView(R.id.ivDetailPhoto)
    ImageView ivDetailPhoto;

    @BindView(R.id.tvDetailName)
    TextView tvDetailName;

    @BindView(R.id.tvDetailCost)
    TextView tvDetailCost;

    @BindView(R.id.tvDetailSold)
    TextView tvDetailSold;

    @BindView(R.id.tvDetailCountry)
    TextView tvDetailCountry;

    @BindView(R.id.tvDetailManufacturer)
    TextView tvDetailManufacturer;

    @BindView(R.id.tvDetailKind)
    TextView tvDetailKind;

    @BindView(R.id.tvDetailConcentration)
    TextView tvDetailConcentration;

    @BindView(R.id.tvDetailQuantity)
    TextView tvDetailQuantity;

    @BindView(R.id.tvDetailFullDesc)
    TextView tvDetailFullDesc;

    @BindView(R.id.tvDetailFavorite)
    TextView tvDetailFavorite;

    @BindView(R.id.rbDetailRate)
    RatingBar rbDetailRate;

    @BindView(R.id.btDetailAddToCart)
    Button btDetailAddToCart;

    @BindView(R.id.btDetailCancel)
    Button btDetailCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", 0);

        Data data = Data.getInstance(this);
        new DownloadImage(ivDetailPhoto).execute(data.urls()[id]);
        tvDetailName.setText(data.names()[id]);
        tvDetailCost.setText(data.costs()[id] + "đ");
        tvDetailSold.setText("Đã bán: " + data.solds()[id] + "");
        tvDetailCountry.setText(data.countries()[id]);
        tvDetailManufacturer.setText(data.manufacturers()[id]);
        tvDetailKind.setText(data.kind()[id]);
        tvDetailConcentration.setText(data.concentrations()[id] + "%");
        tvDetailQuantity.setText(data.quantities()[id] + "");
        tvDetailFullDesc.setText(data.fullDescs()[id]);
        rbDetailRate.setRating(data.rates()[id]);
        if (data.rates()[id] >= 4.5f) {
            tvDetailFavorite.setVisibility(View.VISIBLE);
        } else {
            tvDetailFavorite.setVisibility(View.INVISIBLE);
        }

        ClickListener listener = new ClickListener();
        btDetailAddToCart.setOnClickListener(listener);
        btDetailCancel.setOnClickListener(listener);
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btDetailAddToCart:
                    Data data = Data.getInstance(DetailActivity.this);
                    HangHoa hh = HangHoa.create(data, id);
                    gioHangs.add(hh);
                    Intent intentAdd = new Intent(getApplicationContext(), CartActivity.class);
                    intentAdd.putParcelableArrayListExtra("id", gioHangs);
                    Toast msg = Toast.makeText(getApplicationContext(), "Sản phẩm đã được thêm vào giỏ!", Toast.LENGTH_SHORT);
                    msg.show();
                    break;
                case R.id.btDetailCancel:
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
