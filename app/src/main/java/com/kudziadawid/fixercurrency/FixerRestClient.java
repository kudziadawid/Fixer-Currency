package com.kudziadawid.fixercurrency;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class FixerRestClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static JSONObject getFixerObject(String url) {

        final JSONObject[] jsonObject = {new JSONObject()};
        FixerRestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of JSONArray
                jsonObject[0] = response;
                Log.d("App", "onsucob: " + jsonObject[0] + "\nresponse: " + response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                // Pull out the first event on the public timeline
                JSONObject firstEvent = null;
                try {
                    firstEvent = (JSONObject) jsonArray.get(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonObject[0] = firstEvent;
                Log.d("App", "onsucja: " + jsonObject[0] + "\nfirstevent: " + firstEvent);
            }
        });
        return jsonObject[0];
    }
}
