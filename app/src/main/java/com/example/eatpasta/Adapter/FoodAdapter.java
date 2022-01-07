package com.example.eatpasta.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatpasta.R;

import java.util.ArrayList;

import com.example.eatpasta.Model.Food;

public class FoodAdapter  extends ArrayAdapter<Food> {
    //Lakukan konstruktor untuk pertama kali
    public FoodAdapter(Activity context, ArrayList<Food> foods){
        super(context, 0, foods);
    }
    //getView merupakan methode turunan dari ArrayAdapter dengan fungsi mengambil view.
    public View getView(int position, View convertView, ViewGroup parent){
    //Variabel convertView akan membaca view yang akan diisi, nanti pakai ListView
        View listItemView=convertView;

    //jika listnya kosong, akan terisi oleh list_item.xml
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
    //currentFood mengambil data dari kelas Food yang telah dibuat berdasarkan posisi dari ArryList.
    Food currentFood = getItem(position);
        TextView foodNameTextView = (TextView) listItemView.findViewById(R.id.food_name_text_view);

    foodNameTextView.setText(currentFood.getFoodName());
    //Karena price itu int, harus diconvert terlebih dahulu
        TextView priceTextView = (TextView) listItemView.findViewById(R.id.price_text_view);

    priceTextView.setText(Integer.toString(currentFood.getFoodPrice()));

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

    imageView.setImageResource(currentFood.getmImageResouce());

        imageView.setVisibility(View.VISIBLE);

        return listItemView;
    }
}
