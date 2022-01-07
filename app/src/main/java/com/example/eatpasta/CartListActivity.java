package com.example.eatpasta;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eatpasta.Adapter.CartListAdapter;
import com.example.eatpasta.Database.SQLiteHelper;
import com.example.eatpasta.Model.Cart;
import com.example.eatpasta.Model.Request;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {
    ListView listView;
    public TextView totalPricetv;
    ArrayList<Cart> list = new ArrayList<Cart>();
    CartListAdapter adapter = null;
    //Database SQLite
    SQLiteHelper helper;

    //Database Firebase
    FirebaseDatabase database;
    DatabaseReference request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list_activity);

        Button btnOrder = (Button) findViewById(R.id.btnPesan);

        //Firebase
        database = FirebaseDatabase.getInstance("https://food-order-fb974-default-rtdb.firebaseio.com/");
        request = database.getReference("Cart");

        listView = (ListView) findViewById(R.id.listView);
        totalPricetv = findViewById(R.id.total);
        int total = 0;
        String totalPrice = null;

        list = new ArrayList<>();
        adapter = new CartListAdapter(this, R.layout.cart_item, list);
        listView.setAdapter(adapter);

        helper = new SQLiteHelper(this, "FoodDB.sqlite", null, 1);
        //get data from sqlite
        Cursor cursor = helper.getData("SELECT ID, NAME, QUANTITY, PRICE FROM CART");
        Cursor cursor1 = helper.getData("SELECT * FROM CART");
        list.clear();
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String quantity = cursor.getString(2);
                String price = cursor.getString(3);
                Log.e("price: ", price);
                total = total + Integer.parseInt(price);
                Log.e("pricetotla: ", String.valueOf(total));
                list.add(new Cart(id, name, quantity, price));
                
            } while (cursor.moveToNext());
        }
        totalPricetv.setText(String.valueOf(total));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int position, long l){
                AlertDialog.Builder builder = new AlertDialog.Builder(CartListActivity.this);
                final int pos = position;
                builder.setTitle("Dialog Hapus").setMessage("Apakah anda Ingin menghapus item ini ?").setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("CART ID =",list.get(pos).toString());
                        Cart cart = list.get(pos);
                        helper.deleteData(cart.getId());
                        list.remove(pos);
                        adapter.notifyDataSetChanged();
                        listView.invalidateViews();
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create();
                builder.show();
            }
        });
// order
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() > 0)
                    showAlertDialog();
                else
                    Toast.makeText(CartListActivity.this,"Keranjang Belanja Kosong",Toast.LENGTH_SHORT).show();
            }
        });
        listView.setFocusable(true);
        adapter.notifyDataSetChanged();
    }
    private void showAlertDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CartListActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_order, null);

        final EditText mName = (EditText) mView.findViewById(R.id.edt_nama_pemesan);
        final EditText mTlp = (EditText) mView.findViewById(R.id.edt_tlp_pemesan);
        final EditText mAddress = (EditText) mView.findViewById(R.id.edt_alamat_pemesan);
        final Button mfinalOrder = (Button) mView.findViewById(R.id.finisOrder);

        mfinalOrder.setOnClickListener((v) -> {
            Request request = new Request(
                    mName.getText().toString().trim(),
                    mTlp.getText().toString().trim(),
                    mAddress.getText().toString().trim(),
                    totalPricetv.getText().toString(),
                    list);
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
}
