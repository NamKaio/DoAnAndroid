package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.doanandroid.adapter.CartAdapter;
import com.example.doanandroid.data.Data;
import com.example.doanandroid.models.HangHoa;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {

    int id = 0;

    @BindView(R.id.gridview_cart)
    GridView gridview_cart;

    @BindView(R.id.tv_cart_summary)
    TextView tv_cart_summary;

    @BindView(R.id.bt_cart_home)
    Button bt_cart_home;

    @BindView(R.id.bt_cart_buy)
    Button bt_cart_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", 0);
        ArrayList<HangHoa> hangHoas = new ArrayList<>();
        Data data = Data.getInstance(this);
        for (int i = 0; i < DetailActivity.giohangs.size(); i ++) {
            HangHoa hh = HangHoa.create(data, i);
            hangHoas.add(hh);
        }

        CartAdapter cartadapter = new CartAdapter(hangHoas, getApplicationContext());
        gridview_cart.setAdapter(cartadapter);

//        tv_cart_summary.setText();

        ClickListener listener = new ClickListener();
        bt_cart_home.setOnClickListener(listener);
        bt_cart_buy.setOnClickListener(listener);
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_cart_home:
                    Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentHome);
                    break;
                case R.id.bt_cart_buy:
                    break;
            }
        }
    }
}
