package com.kudziadawid.fixercurrency.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kudziadawid.fixercurrency.R;
import com.kudziadawid.fixercurrency.adapter.CurrencyListAdapter;
import com.kudziadawid.fixercurrency.contract.CurrencyListContract;
import com.kudziadawid.fixercurrency.presenter.CurrencyListPresenter;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements CurrencyListContract.View {

    RecyclerView currencyList;

    CurrencyListPresenter currencyListPresenter;
    CurrencyListAdapter currencyListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        AndroidThreeTen.init(this);
        JodaTimeAndroid.init(this);
        Timber.plant(new Timber.DebugTree());

        currencyList = findViewById(R.id.currency_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        currencyList.setLayoutManager(layoutManager);

        currencyListPresenter = new CurrencyListPresenter();
        currencyListAdapter = new CurrencyListAdapter(currencyListPresenter);
        currencyList.setAdapter(currencyListAdapter);
    }

    @Override
    public void showCurrencyList(List<String> currencyNameList) {

    }
}
