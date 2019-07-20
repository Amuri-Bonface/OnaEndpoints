package com.ona.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ona.myapplication.R;
import com.ona.myapplication.models.GlobalModel;

import java.util.List;


public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.ViewHolder> {

    private Context context;
    private List<GlobalModel> list;

    public NutritionAdapter(Context context, List<GlobalModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlobalModel globalModel = list.get(position);

        holder.field1.setText("Submitted by: "+globalModel.getField1());
        holder.field2.setText("religion: "+globalModel.getField2());
        holder.field3.setText("Education: "+globalModel.getField3());
        holder.field4.setText("Ethnicity: "+globalModel.getField4());
        holder.field5.setText("Marital Status: "+globalModel.getField5());
        holder.field6.setText("Cooking Methods: "+globalModel.getField6());
        holder.field7.setText("Prevention of Heart disease: "+globalModel.getField7());
        holder.field8.setText("Sugar causes: "+globalModel.getField8());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView field1,field2,field3,field4,field5,field6,field7,field8;

        public ViewHolder(View itemView) {
            super(itemView);

            field1 = itemView.findViewById(R.id.textField1);
            field2 = itemView.findViewById(R.id.textField2);
            field3 = itemView.findViewById(R.id.textField3);
            field4 = itemView.findViewById(R.id.textField4);
            field5 = itemView.findViewById(R.id.textField5);
            field6 = itemView.findViewById(R.id.textField6);
            field7 = itemView.findViewById(R.id.textField7);
            field8 = itemView.findViewById(R.id.textField8);

        }
    }

}