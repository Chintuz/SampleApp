package com.sampleapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sampleapp.R;
import com.sampleapp.model.Flowers;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by OneSnaps on 11/11/2016.
 */
public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<Flowers> mAndroidList;
    private Context mContext;

    public FlowerAdapter(Context context, LayoutInflater inflater) {
        mInflater = inflater;
        mAndroidList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Flowers androidModel = mAndroidList.get(position);
        holder.name.setText("Name : " + androidModel.getName());
        holder.category.setText("Category : " +androidModel.getCategory());
    }

    public void addFlowers(List<Flowers> androidList) {
        mAndroidList.addAll(androidList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.category)
        TextView category;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, mAndroidList.get(getLayoutPosition()).getName(), Toast.LENGTH_LONG).show();
        }
    }
}
