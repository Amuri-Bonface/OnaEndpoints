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
import com.ona.myapplication.adapter.NutritionAdapter;
import com.ona.myapplication.constants.Constants;
import com.ona.myapplication.models.GlobalModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NutritionAnalysis extends AppCompatActivity {

    private List<GlobalModel> itemList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_analysis_activity);

        RecyclerView mList = findViewById(R.id.mainlist);

        itemList = new ArrayList<>();
        adapter = new NutritionAdapter(getApplicationContext(),itemList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

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

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.NUTRITIONURL, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);

                    GlobalModel globalModel = new GlobalModel();

                    // globalModel.setField1(jsonObject.getString(""));
                  globalModel.setField1(jsonObject.getString("_submitted_by"));
                  globalModel.setField2(jsonObject.getString("demo/religion"));
                  globalModel.setField3(jsonObject.getString("demo/education"));
                  globalModel.setField4(jsonObject.getString("demo/ethnicity"));
                  globalModel.setField5(jsonObject.getString("demo/marital_status"));
                  globalModel.setField6(jsonObject.getString("sec_3/cooking_methods"));
                  globalModel.setField7(jsonObject.getString("sec_3/prevent_heart_disease"));
                  globalModel.setField8(jsonObject.getString("sec_3/sugar_people_eat"));


                  itemList.add(globalModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
            adapter.notifyDataSetChanged();
            progressDialog.dismiss();
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
