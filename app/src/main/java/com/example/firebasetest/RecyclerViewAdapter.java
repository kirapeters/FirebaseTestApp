package com.example.firebasetest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    protected static final String TAG = "RecyclerViewAdapter";
    private Context context;
    private List<SampleData> sampleData;

    RecyclerViewAdapter(Context ct, List<SampleData> sd) {
        context = ct;
        sampleData = sd;
        Log.d(TAG, "created a RecyclerViewAdapter");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sample_data_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");

        holder.textName.setText(sampleData.get(position).getName());
        holder.textDescription.setText(sampleData.get(position).getDescription());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewActivity.class);
                Log.d(TAG, "putting name: " + sampleData.get(position).getName());
                intent.putExtra("name", sampleData.get(position).getName());
                Log.d(TAG, "putting name: " + sampleData.get(position).getDescription());
                intent.putExtra("description", sampleData.get(position).getDescription());
                Log.d(TAG, "sending intent");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");

        return sampleData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textDescription;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("ViewHolder", "ViewHolder constructor");
            textName = itemView.findViewById(R.id.text_name);
            textDescription = itemView.findViewById(R.id.text_description);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
