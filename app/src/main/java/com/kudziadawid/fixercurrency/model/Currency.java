package com.kudziadawid.fixercurrency.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Currency {

    private List<String> currencyList = new ArrayList<>();

    private String[] allCurrencies = new String[] {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS",
            "AUD", "AWG", "AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTC","BTN",
            "BWP","BYR","BZD","CAD","CDF","CHF","CLF","CLP","CNY","COP","CRC","CUP","CVE","CZK","DJF","DKK",
            "DOP","DZD","EGP","ETB","EUR","FJD","FKP","GBP","GEL","GHS","GIP","GMD","GNF","GTQ","GYD","HKD",
            "HNL","HRK","HTG","HUF","IDR","ILS","INR","IQD","IRR","ISK","JEP","JMD","JOD","JPY","KES","KGS","KHR",
            "KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA",
            "MKD","MMK","MNT","MOP","MRO","MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD",
            "OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG",
            "SEK","SGD","SHP","SLL","SOS","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD",
            "TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","WST","XAF","XCD","XDR","XOF","XPF","YER",
            "ZAR","ZMK","ZMW","ZWL"};

    public Currency() {
        currencyList.addAll(Arrays.asList(allCurrencies));
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public String getCurrencyListItem(int position) {
        return currencyList.get(position);
    }

    public int getCurrencyListSize() {
        return currencyList.size();
    }
}
