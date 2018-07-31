package com.kudziadawid.fixercurrency.adapter;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kudziadawid.fixercurrency.R;
import com.kudziadawid.fixercurrency.model.Currency;
import com.kudziadawid.fixercurrency.presenter.CurrencyListPresenter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class CurrencyListAdapter extends RecyclerView.Adapter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-mm-yyyy");

    CurrencyListPresenter currencyListPresenter;
    Currency currency;
    List<String> recyclerList = new ArrayList<>();
    Activity activity;
    Context context;
    ZonedDateTime actualDateTime;
    private RecyclerView recyclerView;

    private class ViewHolder extends RecyclerView.ViewHolder {
        public TextView singleItem;

        public ViewHolder(View pItem) {
            super(pItem);
            singleItem = pItem.findViewById(R.id.tv_item_text);
        }
    }

    public CurrencyListAdapter() {

        currencyListPresenter = new CurrencyListPresenter();
        actualDateTime = ZonedDateTime.now();
    }

//    @Override
//    public int getCount() {
//        return recyclerList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return recyclerList.get(position);
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_currency_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String temp;
        temp = currencyListPresenter.itemText(position);
        ((ViewHolder) holder).singleItem.setText(temp);
    }

    @Override
    public int getItemCount() {
        return currency.getCurrencyListSize();
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.view_currency_list_item, parent, false);
//        }
//
//        TextView itemTV = convertView.findViewById(R.id.tv_item_text);
//
//        long whichDayBehind = (position / currency.getCurrencyListSize());
//
//        int positionInCurrency = position % currency.getCurrencyListSize();
//
//        if (positionInCurrency == 0) {
//            itemTV.setText("Dzień " + actualDateTime.minusDays(whichDayBehind).format(DATE_FORMATTER));
//        } else {
//
//        }
//
//        return convertView;
//    }
//
//    private Double letsDoSomeNetworking(String url, int position) {
//
//        final Double[] currencyValue = new Double[1];
//
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(url, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    currencyValue[0] = response.getJSONObject("rates").getDouble("AED");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        return currencyValue[0];
//    }
}