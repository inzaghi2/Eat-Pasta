package com.example.eatpasta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatpasta.Adapter.FoodAdapter;
import com.example.eatpasta.Model.Food;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    //Berfungsi untuk menampilkan Gambar
    public static final int REQUEST_CODE = 1;

    //Nilai Variabel akan diambil dari FoodAdapter
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    //ArrayList buat nampung nilai kelompok dan mmembuat nilai Array dari model Food
        final ArrayList<Food> foods = new ArrayList<>();
    //berikut nilai yang dibuat untuk mengisi konstruktor
            foods.add(new Food("MINUTEVEGAN PASTA", "Spanyol Pasta Food",  R.drawable.a1,54800));
            foods.add(new Food("CAPRESE PASTA SALAD","Indonesian Pasta Food",R.drawable.a2,48700));
            foods.add(new Food("CREAMY AVOCADO PASTA","Italian Pasta Food",R.drawable.a3,56700));
            foods.add(new Food("INSTANT POT CREAMY GARLIC","American Pasta Food",R.drawable.a2,48900));
            foods.add(new Food("ANTIPASTO TORTELLINI PASTA","Asian Pasta Food",R.drawable.a5, 54800));
            foods.add(new Food("SALAD PLATTER FOODIECRUSH","Korean Pasta Food",R.drawable.a6,44800));

            //Bertujuan untuk menghubungkan data dengan View
            adapter = new FoodAdapter(this,foods);
        ListView orderListView = (ListView) findViewById(R.id.order_list_view);
        orderListView.setAdapter(adapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id){
                Intent i = new Intent(MenuActivity.this,FoodDetailActivity.class);

                Food currentFood = foods.get(position);
                Log.e("FOOD NAME", currentFood.getFoodName());
                i.putExtra("name", currentFood.getFoodName());
                i.putExtra("image", currentFood.getmImageResouce());
                i.putExtra("price", currentFood.getFoodPrice());
                i.putExtra("type", currentFood.getType());
                        startActivity(i);
            }
        });
    }

    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent i = new Intent(MenuActivity.this, CartListActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}