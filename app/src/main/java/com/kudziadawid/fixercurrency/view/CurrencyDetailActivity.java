package com.kudziadawid.fixercurrency.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kudziadawid.fixercurrency.R;

public class CurrencyDetailActivity extends AppCompatActivity {

    TextView currencyDetailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_detail);
        Intent intent = getIntent();

        currencyDetailTV = findViewById(R.id.currencyDetailTV);

        currencyDetailTV.setText(intent.getStringExtra("itemText"));
    }
}
