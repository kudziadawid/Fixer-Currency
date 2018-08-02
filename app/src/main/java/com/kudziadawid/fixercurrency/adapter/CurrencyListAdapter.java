package com.kudziadawid.fixercurrency.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kudziadawid.fixercurrency.R;
import com.kudziadawid.fixercurrency.presenter.CurrencyListPresenter;
import com.kudziadawid.fixercurrency.view.CurrencyDetailActivity;

import org.json.JSONException;

import timber.log.Timber;

public class CurrencyListAdapter extends RecyclerView.Adapter {

    CurrencyListPresenter currencyListPresenter;
    Activity activity;
    RecyclerView currencyListRV;

    private class ViewHolder extends RecyclerView.ViewHolder {
        public TextView singleItem;

        public ViewHolder(View pItem) {
            super(pItem);
            singleItem = pItem.findViewById(R.id.tv_item_text);
        }
    }

    public CurrencyListAdapter(CurrencyListPresenter currencyListPresenter, Activity activity, RecyclerView currencyListRV) {
        this.activity = activity;
        this.currencyListPresenter = currencyListPresenter;
        this.currencyListRV = currencyListRV;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_currency_list_item, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CurrencyDetailActivity.class);
                int itemPosition = currencyListRV.getChildAdapterPosition(v);
                String itemText = "";
                try {
                    itemText = currencyListPresenter.itemText(itemPosition);
                } catch (JSONException e) {
                    Timber.d(e, "JSONException onCreateViewHolder itemText method");
                }
                intent.putExtra("itemText", itemText);
                activity.startActivity(intent);
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String temp = "";

        try {
            temp = currencyListPresenter.itemText(position);
        } catch (JSONException e) {
            Timber.d(e, "JSONException onBindViewHolder itemText method");
        }
        ((ViewHolder) holder).singleItem.setText(temp);
    }

    @Override
    public int getItemCount() {
       return Integer.MAX_VALUE;
    }
}