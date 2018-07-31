package com.kudziadawid.fixercurrency.presenter;

import com.kudziadawid.fixercurrency.contract.CurrencyListContract;
import com.kudziadawid.fixercurrency.model.Currency;

import java.util.List;

public class CurrencyListPresenter extends BasePresenter<CurrencyListContract.View> implements CurrencyListContract.Presenter{

    private Currency currency;

    public CurrencyListPresenter(Currency currency) {
        this.currency = currency;
    }

    @Override
    public void getCurrencyList() {

        List<String> currencyNameList = currency.getCurrencyNameList();

        view.showCurrencyList(currencyNameList);
    }
}
