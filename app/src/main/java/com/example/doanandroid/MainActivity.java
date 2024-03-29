package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.doanandroid.adapter.HangHoaAdapter;
import com.example.doanandroid.data.Data;
import com.example.doanandroid.models.HangHoa;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    HangHoaAdapter hangHoaAdapter;

    @BindView(R.id.gridView)
    GridView gridView;

    @BindView(R.id.btMainHome)
    Button btMainHome;

    @BindView(R.id.btMainBeer)
    Button btMainBeer;

    @BindView(R.id.btMainWine)
    Button btMainWine;

    @BindView(R.id.btMainCart)
    Button btMainCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        hangHoaAdapter = new HangHoaAdapter(loadData(), getApplicationContext());
        gridView.setAdapter(hangHoaAdapter);
        gridView.setOnItemClickListener(onItemClick);

        ClickListener listener = new ClickListener();
        btMainHome.setOnClickListener(listener);
        btMainBeer.setOnClickListener(listener);
        btMainWine.setOnClickListener(listener);
        btMainCart.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hangHoaAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("id", (int) gridView.getAdapter().getItemId(position));
            startActivity(intent);
        }
    };

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btMainHome:
                    Toast msgh = Toast.makeText(getApplicationContext(), "Trang chủ", Toast.LENGTH_SHORT);
                    msgh.show();
                    loadDataHome();
                    break;
                case R.id.btMainBeer:
                    Toast msgb = Toast.makeText(getApplicationContext(), "Bia", Toast.LENGTH_SHORT);
                    msgb.show();
                    loadDataBeer();
                    break;
                case R.id.btMainWine:
                    Toast msgw = Toast.makeText(getApplicationContext(), "Rượu", Toast.LENGTH_SHORT);
                    msgw.show();
                    loadDataWine();
                    break;
                case R.id.btMainCart:
                    Intent intentCart = new Intent(getApplicationContext(), CartActivity.class);
                    intentCart.putParcelableArrayListExtra("id", DetailActivity.gioHangs);
                    startActivity(intentCart);
                    break;
            }
        }
    }

    private void loadDataHome(){
        hangHoaAdapter = new HangHoaAdapter(loadData(), getApplicationContext());
        gridView.setAdapter(hangHoaAdapter);
    }

    private void loadDataBeer(){
        hangHoaAdapter = new HangHoaAdapter(loadBeer(), getApplicationContext());
        gridView.setAdapter(hangHoaAdapter);
        hangHoaAdapter.notifyDataSetChanged();
    }

    private void loadDataWine(){
        hangHoaAdapter = new HangHoaAdapter(loadWine(), getApplicationContext());
        gridView.setAdapter(hangHoaAdapter);
        hangHoaAdapter.notifyDataSetChanged();
    }

    private ArrayList<HangHoa> loadData() {
        ArrayList<HangHoa> hangHoas = new ArrayList<>();
        Data data = Data.getInstance(this);
        for (int i = 0; i < data.ids().length; i ++) {
            HangHoa hh = HangHoa.create(data, i);
            hangHoas.add(hh);
        }
        return hangHoas;
    }

    private ArrayList<HangHoa> loadBeer() {
        ArrayList<HangHoa> hangHoas = new ArrayList<>();
        Data data = Data.getInstance(this);
        String [] kinds = data.kind();
        for (int i = 0; i < kinds.length; i ++) {
            if (kinds[i].equals("Bia")) {
                HangHoa hh = HangHoa.create(data, i);
                hangHoas.add(hh);
            }
        }
        return hangHoas;
    }

    private ArrayList<HangHoa> loadWine() {
        ArrayList<HangHoa> hangHoas = new ArrayList<>();
        Data data = Data.getInstance(this);
        String [] kinds = data.kind();
        for (int i = 0; i < data.kind().length; i ++) {
            if (kinds[i].equals("Rượu")) {
                HangHoa hh = HangHoa.create(data, i);
                hangHoas.add(hh);
            }
        }
        return hangHoas;
    }
}
