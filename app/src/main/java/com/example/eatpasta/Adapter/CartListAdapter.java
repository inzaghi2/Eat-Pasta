package com.example.eatpasta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.eatpasta.Model.Cart;
import com.example.eatpasta.R;

import java.util.ArrayList;

public class CartListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList <Cart> cartsList;


    public CartListAdapter(Context context, int layout, ArrayList<Cart> foodsList) {
        this.context = context;
        this.layout = layout;
        this.cartsList = foodsList;
    }

    @Override
    public int getCount() {
        return cartsList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtName, txtQuantity, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtQuantity = (TextView) row.findViewById(R.id.txtQuantity);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }
        Cart food = cartsList.get(position);
        holder.txtName.setText(food.getName());
        holder.txtQuantity.setText(food.getQuantity());
        holder.txtPrice.setText(food.getPrice());
        return row;
    }
}
