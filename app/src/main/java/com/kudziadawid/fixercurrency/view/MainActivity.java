package com.kudziadawid.fixercurrency.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.kudziadawid.fixercurrency.R;
import com.kudziadawid.fixercurrency.contract.CurrencyListContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements CurrencyListContract.View {

    @BindView(R.id.currency_list)
    RecyclerView currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Timber.plant(new Timber.DebugTree());
    }

    @Override
    public void showCurrencyList(List<String> currencyNameList) {

    }
}
