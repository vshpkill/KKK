package com.youlehuo.app.aboutview.recycleview.citylist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youlehuo.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dayima on 17-1-12.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    public interface ItemClickListener{
        void itemClick(int position);
    }
    private List<CityBean> list;
    private Context context;
    private ItemClickListener listener;

    public CityAdapter(Context context,ItemClickListener listener){
        this.context = context;
        this.listener = listener;
    }
    public void setData(List<CityBean> list){
        this.list = list;
    }
    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(context).inflate(R.layout.city_item,null));
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, final int position) {
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(position);
            }
        });
        holder.tv_name.setText(list.get(position).getCityName());
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tv_name;
        public CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
