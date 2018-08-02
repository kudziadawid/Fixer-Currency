package com.kudziadawid.fixercurrency.presenter;

import android.util.Log;

import com.kudziadawid.fixercurrency.FixerRestClient;
import com.kudziadawid.fixercurrency.model.Currency;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyListPresenter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd-MM-yyyy");
    private static final DateTimeFormatter LINK_DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final String BASE_URL = "http://data.fixer.io/api/";
    private static final String ACCESS_KEY = "?access_key=75c4194d62f0eb5d093405e35997df29";

    Currency currency;
    DateTime actualDateTime;
    boolean isReadyForToday;
    JSONObject jsonObject;

    public CurrencyListPresenter() {
        currency = new Currency();
        actualDateTime = new DateTime();

        String tempString;
        tempString = actualDateTime.toString(LINK_DATE_FORMATTER);
        String url = BASE_URL + tempString + ACCESS_KEY;

        jsonObject = FixerRestClient.getFixerObject(url);
    }

    public String itemText(int position) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        int whichDayBehind = position / currency.getCurrencyListSize();
        int positionInCurrency = position % currency.getCurrencyListSize();

        if (whichDayBehind > 0 || (whichDayBehind == 0 && isReadyForToday)) {
            if (positionInCurrency == 0) {
                String tempString;
                DateTime tempDateTime;
                tempDateTime = actualDateTime.minusDays(whichDayBehind);
                tempString = tempDateTime.toString(DATE_FORMATTER);
                stringBuilder.append(currency.getCurrencyListItem(0) + ": " + tempString);
            } else {
                String tempString;
                DateTime tempDateTime;
                tempDateTime = actualDateTime.minusDays(whichDayBehind);
                tempString = tempDateTime.toString(LINK_DATE_FORMATTER);
                String url = BASE_URL + tempString + ACCESS_KEY;
                jsonObject = FixerRestClient.getFixerObject(url);
                Log.d("App", "ready: " + jsonObject.toString());
                stringBuilder.append(currency.getCurrencyListItem(positionInCurrency) + ": "
                        + String.valueOf(jsonObject.getJSONObject("rates").getDouble(currency.getCurrencyListItem(positionInCurrency))));
            }
        } else {
            if (positionInCurrency == 0) {
                String tempString;
                tempString = actualDateTime.toString(DATE_FORMATTER);
                stringBuilder.append(currency.getCurrencyListItem(0) + ": " + tempString);
            } else {
                String tempString;
                DateTime tempDateTime;
                tempDateTime = actualDateTime.minusDays(1);
                tempString = tempDateTime.toString(LINK_DATE_FORMATTER);
                String url = BASE_URL + tempString + ACCESS_KEY;
                jsonObject = FixerRestClient.getFixerObject(url);
                Log.d("App", "not ready: " + jsonObject.toString());
                stringBuilder.append(currency.getCurrencyListItem(positionInCurrency) + ": "
                        + String.valueOf(jsonObject.getJSONObject("rates").getDouble(currency.getCurrencyListItem(positionInCurrency))));
            }
        }
        Log.d("App", stringBuilder.toString());
        return stringBuilder.toString();
    }
}
