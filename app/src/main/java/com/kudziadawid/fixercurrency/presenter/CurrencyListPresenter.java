package com.kudziadawid.fixercurrency.presenter;

import android.os.Debug;
import android.util.Log;

import com.kudziadawid.fixercurrency.adapter.CurrencyListAdapter;
import com.kudziadawid.fixercurrency.contract.CurrencyListContract;
import com.kudziadawid.fixercurrency.model.Currency;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CurrencyListPresenter extends BasePresenter<CurrencyListContract.View> implements CurrencyListContract.Presenter{

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd-MM-yyyy");
    private static final DateTimeFormatter LINK_DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final String BASE_URL = "http://data.fixer.io/api/";
    private static final String ACCESS_KEY = "?access_key=9590262b5023fa9beeca4dbb2c7a82af";

    Currency currency;
    CurrencyListAdapter currencyListAdapter;
    DateTime actualDateTime;
    boolean isReadyForToday;

    public CurrencyListPresenter() {
        currency = new Currency();
        actualDateTime = new DateTime();
        Log.d("App", "act: " + actualDateTime);
        String tempString;
        tempString = actualDateTime.toString(LINK_DATE_FORMATTER);
        String url = BASE_URL + tempString + ACCESS_KEY;
        isReadyForToday = isItReadyToday(url);
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
        int whichDayBehind = (position / currency.getCurrencyListSize());

        int positionInCurrency = position % currency.getCurrencyListSize();

        Log.d("App", "pos: " + position + "\nwdb: " + whichDayBehind + "\npic: " + positionInCurrency);

        if (isReadyForToday) {
            if (positionInCurrency == 0) {
                String tempString;
                DateTime tempDateTime;
                tempDateTime = actualDateTime.minusDays(whichDayBehind);
                tempString = tempDateTime.toString(DATE_FORMATTER);
                stringBuilder.append("Dzień " + tempString);
                Log.d("App", "pic0, ts: " + tempString + "dt: " + tempDateTime);
            } else {
                String tempString;
                DateTime tempDateTime;
                tempDateTime = actualDateTime.minusDays(whichDayBehind);
                tempString = tempDateTime.toString(LINK_DATE_FORMATTER);
                String url = BASE_URL + tempString + ACCESS_KEY;
                stringBuilder.append(letsDoSomeNetworking(url, positionInCurrency));
                Log.d("App", "pic21, ts: " + tempString + "dt: " + tempDateTime);
            }
        } else {
            if (positionInCurrency == 0) {
                String tempString;
                tempString = actualDateTime.toString(DATE_FORMATTER);
                stringBuilder.append("Dzień " + tempString);
                Log.d("App", "pic0, ts: " + tempString + "dt: " + actualDateTime);
            } else {
                String tempString;
                DateTime tempDateTime;
                tempDateTime = actualDateTime.minusDays(1);
                tempString = tempDateTime.toString(LINK_DATE_FORMATTER);
                String url = BASE_URL + tempString + ACCESS_KEY;
                stringBuilder.append(letsDoSomeNetworking(url, positionInCurrency));
                Log.d("App", "pic21, ts: " + tempString + "dt: " + tempDateTime);
            }
        }
        return stringBuilder.toString();
    }

    private Double letsDoSomeNetworking(String url, final int position) {

        final Double[] currencyValue = new Double[1];

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    currencyValue[0] = response.getJSONObject("rates").getDouble(currency.getCurrencyListItem(position));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return currencyValue[0];
    }

    private boolean isItReadyToday(String url) {

        final boolean[] successResponse = new boolean[1];

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    successResponse[0] = response.getBoolean("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    return successResponse[0];
    }
}
