package com.kudziadawid.fixercurrency.contract;

import java.util.List;

public interface CurrencyListContract {

    interface View {
        void showCurrencyList(List<String> currencyNameList);
    }

    interface Presenter {
        void getCurrencyList();
    }
}
