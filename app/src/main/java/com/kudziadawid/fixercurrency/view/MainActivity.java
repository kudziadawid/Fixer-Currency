package com.kudziadawid.fixercurrency.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kudziadawid.fixercurrency.R;
import com.kudziadawid.fixercurrency.adapter.CurrencyListAdapter;
import com.kudziadawid.fixercurrency.presenter.CurrencyListPresenter;

import net.danlew.android.joda.JodaTimeAndroid;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RecyclerView currencyListRV;

    CurrencyListPresenter currencyListPresenter;
    CurrencyListAdapter currencyListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        JodaTimeAndroid.init(this);
        Timber.plant(new Timber.DebugTree());

        currencyListRV = findViewById(R.id.currency_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        currencyListRV.setLayoutManager(layoutManager);

        currencyListPresenter = new CurrencyListPresenter();

        currencyListAdapter = new CurrencyListAdapter(currencyListPresenter, this, currencyListRV);
        currencyListRV.setAdapter(currencyListAdapter);
    }
}
