package com.yeldar.javalesson;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"Orazgaliev99@mail.ru"};
    String subject = "Заказ";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Ваш заказ");
        Intent receivedOrderIntent = getIntent(); //получений интент заказа
        String userName = receivedOrderIntent.getStringExtra("userName");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0);
        double price = receivedOrderIntent.getDoubleExtra("price", 0);

        emailText ="Имя: "+userName + "\n"
                + "Марка: "+goodsName + "\n" +"Количество: "
                + quantity + "\n"+ "Цена: "+orderPrice+"\n" + "Цена за одну: "+price;
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);
    }

    public void sumbitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}