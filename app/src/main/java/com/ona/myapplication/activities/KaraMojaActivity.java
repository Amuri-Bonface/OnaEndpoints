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
import com.ona.myapplication.adapter.KaraMojaAdapter;
import com.ona.myapplication.constants.Constants;
import com.ona.myapplication.models.GlobalModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KaraMojaActivity extends AppCompatActivity {
    private List<GlobalModel> itemList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karamoja_activity);

        RecyclerView mList = findViewById(R.id.mainlist);

        itemList = new ArrayList<>();
        adapter = new KaraMojaAdapter(getApplicationContext(),itemList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        //
        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.KARAMOJAURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        GlobalModel globalModel = new GlobalModel();

                      globalModel.setField1(jsonObject.getString("field_officersname"));
                      globalModel.setField2(jsonObject.getString("field_area_acres"));
                      globalModel.setField3(jsonObject.getString("marketprices_rice"));
                      globalModel.setField4(jsonObject.getString("marketprices_wood"));
                      globalModel.setField5(jsonObject.getString("marketprices_beans"));
                      globalModel.setField6(jsonObject.getString("marketprices_maize"));
                      globalModel.setField7(jsonObject.getString("marketprices_sorghum"));
                      globalModel.setField8(jsonObject.getString("assessment_comments"));

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