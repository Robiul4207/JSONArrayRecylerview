package com.robiultech.jsonarrayrecylerview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.robiultech.jsonarrayrecylerview.R;
import com.robiultech.jsonarrayrecylerview.models.Aime;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private Context mContext;
    private List<Aime> mData;

    public RecyclerAdapter(Context mContext, List<Aime> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view;
        view= layoutInflater.inflate(R.layout.aime_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.aime_Name.setText("Name: "+mData.get(position).getName());
        holder.aime_Email.setText("E-mail: "+mData.get(position).getEmail());
        holder.aime_Phone.setText("Mob: "+mData.get(position).getPhone());
        holder.aime_District.setText("District: "+mData.get(position).getDistrict());
        Glide.with(mContext).load(mData.get(position).getImgUrl()).placeholder(R.drawable.ic_launcher_background)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView aime_Name,aime_Email,aime_Phone,aime_District;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            aime_Name=itemView.findViewById(R.id.anim_nameId);
            aime_Email=itemView.findViewById(R.id.anim_emailId);
            aime_Phone=itemView.findViewById(R.id.anim_phoneId);
            aime_District=itemView.findViewById(R.id.anim_districtId);
            thumbnail=itemView.findViewById(R.id.thumbnail);
        }
    }
}
