package com.kudziadawid.fixercurrency.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kudziadawid.fixercurrency.R;
import com.kudziadawid.fixercurrency.adapter.CurrencyListAdapter;
import com.kudziadawid.fixercurrency.contract.CurrencyListContract;
import com.kudziadawid.fixercurrency.presenter.CurrencyListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements CurrencyListContract.View {

    @BindView(R.id.currency_list)
    RecyclerView currencyList;

    CurrencyListPresenter currencyListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AndroidThreeTen.init(this);
        Timber.plant(new Timber.DebugTree());

        currencyListPresenter = new CurrencyListPresenter();
        currencyList.setAdapter(currencyListPresenter.getCurrencyListAdapter());
    }

    @Override
    public void showCurrencyList(List<String> currencyNameList) {

    }
}
