package com.kudziadawid.fixercurrency.presenter;

import com.kudziadawid.fixercurrency.adapter.CurrencyListAdapter;
import com.kudziadawid.fixercurrency.contract.CurrencyListContract;
import com.kudziadawid.fixercurrency.model.Currency;

import java.util.List;

public class CurrencyListPresenter extends BasePresenter<CurrencyListContract.View> implements CurrencyListContract.Presenter{

    private Currency currency;
    CurrencyListAdapter currencyListAdapter;

    public CurrencyListPresenter() {
        currency = new Currency();
        currencyListAdapter = new CurrencyListAdapter();
    }

    @Override
    public void getCurrencyList() {

        List<String> currencyNameList = currency.getCurrencyNameList();



        //view.showCurrencyList(currencyNameList);
    }

    public CurrencyListAdapter getCurrencyListAdapter() {
        return currencyListAdapter;
    }

    public String itemText(int position) {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.toString();
    }
}
