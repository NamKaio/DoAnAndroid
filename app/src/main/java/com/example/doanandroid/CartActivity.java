package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.adapter.CartAdapter;
import com.example.doanandroid.models.HangHoa;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {

    @BindView(R.id.gridViewCart)
    GridView gridViewCart;

    @BindView(R.id.tvCartSummary)
    TextView tvCartSummary;

    @BindView(R.id.btCartHome)
    Button btCartHome;

    @BindView(R.id.btCartBuy)
    Button btCartBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Giỏ hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<HangHoa> hangHoas = getIntent().getParcelableArrayListExtra("id");
        CartAdapter cartAdapter = new CartAdapter(hangHoas, getApplicationContext());
        gridViewCart.setAdapter(cartAdapter);

        for (int i = 0; i < hangHoas.size(); i ++) {
            tvCartSummary.setText(hangHoas.get(i).getCost() + "");
        }
        ClickListener listener = new ClickListener();
        btCartHome.setOnClickListener(listener);
        btCartBuy.setOnClickListener(listener);
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btCartHome:
                    Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                    intentHome.putParcelableArrayListExtra("id", DetailActivity.gioHangs);
                    startActivity(intentHome);
                    break;
                case R.id.btCartBuy:
                    Toast msgb = Toast.makeText(getApplicationContext(), "Chức năng này em vẫn chưa làm :v !", Toast.LENGTH_SHORT);
                    msgb.show();
                    break;
            }
        }
    }
}
