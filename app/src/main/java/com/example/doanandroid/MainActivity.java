package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.doanandroid.adapter.HangHoaAdapter;
import com.example.doanandroid.data.Data;
import com.example.doanandroid.models.HangHoa;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gridview)
    GridView gridview;

    @BindView(R.id.bt_main_home)
    Button btnHome;

    @BindView(R.id.bt_main_beer)
    Button btnBeer;

    @BindView(R.id.bt_main_wine)
    Button btnWine;

    @BindView(R.id.bt_main_search)
    Button btSearch;

    @BindView(R.id.bt_main_cart)
    Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        HangHoaAdapter hanghoaadapter = new HangHoaAdapter(loadData(), getApplicationContext());
        gridview.setAdapter(hanghoaadapter);
        gridview.setOnItemClickListener(onitemclick);

        ClickListener listener = new ClickListener();
        btnHome.setOnClickListener(listener);
        btnBeer.setOnClickListener(listener);
        btnWine.setOnClickListener(listener);
        btnCart.setOnClickListener(listener);
        btSearch.setOnClickListener(listener);
    }

    private AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("id", (int) gridview.getAdapter().getItemId(position));
            startActivity(intent);
        }
    };

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_main_home:
                    Toast msgh = Toast.makeText(getApplicationContext(), "Trang chủ", Toast.LENGTH_SHORT);
                    msgh.show();
                    loadDataHome();
                    break;
                case R.id.bt_main_beer:
                    Toast msgb = Toast.makeText(getApplicationContext(), "Bia", Toast.LENGTH_SHORT);
                    msgb.show();
                    loadDataBeer();
                    break;
                case R.id.bt_main_wine:
                    Toast msgw = Toast.makeText(getApplicationContext(), "Rượu", Toast.LENGTH_SHORT);
                    msgw.show();
                    loadDataWine();
                    break;
                case R.id.bt_main_cart:
                    Intent intentCart = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intentCart);
                    break;
            }
        }
    }

    private void loadDataHome(){
        HangHoaAdapter hanghoaadapter = new HangHoaAdapter(loadData(), getApplicationContext());
        gridview.setAdapter(hanghoaadapter);
    }

    private void loadDataBeer(){
        HangHoaAdapter hanghoaadapter = new HangHoaAdapter(loadBeer(), getApplicationContext());
        gridview.setAdapter(hanghoaadapter);
        hanghoaadapter.notifyDataSetChanged();
    }

    private void loadDataWine(){
        HangHoaAdapter hanghoaadapter = new HangHoaAdapter(loadWine(), getApplicationContext());
        gridview.setAdapter(hanghoaadapter);
        hanghoaadapter.notifyDataSetChanged();
    }

    private ArrayList<HangHoa> loadData() {
        ArrayList<HangHoa> hanghoas = new ArrayList<>();
        Data data = Data.getInstance(this);
        for (int i = 0; i < data.ids().length; i ++) {
            HangHoa hh = HangHoa.create(data, i);
            hanghoas.add(hh);
        }
        return hanghoas;
    }

    private ArrayList<HangHoa> loadBeer() {
        ArrayList<HangHoa> hanghoas = new ArrayList<>();
        Data data = Data.getInstance(this);
        String [] kinds = data.kind();
        for (int i = 0; i < kinds.length; i ++) {
            if (kinds[i].equals("Bia")) {
                HangHoa hh = HangHoa.create(data, i);
                hanghoas.add(hh);
            }
        }
        return hanghoas;
    }

    private ArrayList<HangHoa> loadWine() {
        ArrayList<HangHoa> hanghoas = new ArrayList<>();
        Data data = Data.getInstance(this);
        String [] kinds = data.kind();
        for (int i = 0; i < data.kind().length; i ++) {
            if (kinds[i].equals("Rượu")) {
                HangHoa hh = HangHoa.create(data, i);
                hanghoas.add(hh);
            }
        }
        return hanghoas;
    }
}
