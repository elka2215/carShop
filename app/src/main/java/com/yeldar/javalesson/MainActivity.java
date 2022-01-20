package com.yeldar.javalesson;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0; // кол-во
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;// название товара
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateSpinner();
        CreateMap();

        userNameEditText = findViewById(R.id.nameEditText);
    }

    void CreateSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("BMW");
        spinnerArrayList.add("Audi");
        spinnerArrayList.add("Toyota");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // выпадает список
        spinner.setAdapter(spinnerAdapter);
    }

    void CreateMap(){
        goodsMap = new HashMap();
        goodsMap.put("BMW", 2000.0);
        goodsMap.put("Audi", 1500.0);
        goodsMap.put("Toyota", 1000.0);
    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity*price);
    }

    public void decreaseQuantity(View view) {
        quantity = quantity-1;
        if(quantity<0){
            quantity = 0;
        }
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText(""+quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity*price);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();// выбранное значение в спинере в данный момнт
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity*price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch(goodsName){
            case "BMW":
                goodsImageView.setImageResource(R.drawable.bmw);
                break;
            case "Audi":
                goodsImageView.setImageResource(R.drawable.audi);
                break;
            case "Toyota":
                goodsImageView.setImageResource(R.drawable.toyota);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.bmw);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCard(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.orderPrice = price*quantity;
        order.quantity = quantity;
        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        startActivity(orderIntent);

    }
}