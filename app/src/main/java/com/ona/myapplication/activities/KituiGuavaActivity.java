package com.ona.myapplication.activities;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ona.myapplication.R;
import com.ona.myapplication.adapter.KituiGuavaAdapter;
import com.ona.myapplication.constants.Constants;
import com.ona.myapplication.models.GlobalModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KituiGuavaActivity extends AppCompatActivity {

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<GlobalModel> itemList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitui_guava_activity);

        mList = findViewById(R.id.mainlist);

        itemList = new ArrayList<>();
        adapter = new KituiGuavaAdapter(getApplicationContext(),itemList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.KITUIGUAVAURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        GlobalModel globalModel = new GlobalModel();

                        // globalModel.setField1(jsonObject.getString(""));
                      globalModel.setField1(jsonObject.getString("Date_of_interview"));
                      globalModel.setField2(jsonObject.getString("_1_Name_of_respondent"));
                      globalModel.setField3(jsonObject.getString("_3_Age_of_respondent"));
                      globalModel.setField4(jsonObject.getString("_6_1_If_yes_to_whom_do_sell_th"));
                      globalModel.setField5(jsonObject.getString("_6_3_How_much_is_spent_on_food"));
                      globalModel.setField6(jsonObject.getString("_34_According_to_you_potential_of_guavas"));
                      globalModel.setField7(jsonObject.getString("_6_2_If_yes_what_is_ava_sales_per_season"));
                      globalModel.setField8(jsonObject.getString("_13_1_If_yes_please_can_obtain_buy_them"));


                      itemList.add(globalModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
