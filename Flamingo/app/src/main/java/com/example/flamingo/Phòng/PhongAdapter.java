package com.example.flamingo.Phòng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flamingo.R;

import java.util.ArrayList;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewHolder>
{
    ArrayList<Phong> lstPhong;
    Context context;
    PhongCallback phongCallback;

    public PhongAdapter(ArrayList<Phong> lstPhong){
        this.lstPhong = lstPhong;
    }
    public void setCallback(PhongCallback callback){
        this.phongCallback = callback;
    }
    @NonNull
    @Override
    public PhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //nạp layout cho View biểu diễn phần tử Phong
        View phongView = inflater.inflate(R.layout.list_view_phong,parent,false);
        PhongViewHolder viewHolder = new PhongViewHolder(phongView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhongViewHolder holder, int position) {
        Phong item = lstPhong.get(position);
        holder.tvSophong.setText(item.getSoPhong());
        holder.tvTinhTrang.setText(item.getTinhTrang());
        holder.tvLoaiPhong.setText(item.getLoaiPhong());
        holder.tvTrangThai.setText(item.getTrangThai());
        holder.btnsua.setOnClickListener(view -> phongCallback.onItemEditClicked(item,position));
        holder.btnxoa.setOnClickListener(view -> phongCallback.onItemDeleteClicked(item,position));
    }

    @Override
    public int getItemCount() {
        return lstPhong.size();
    }

    class PhongViewHolder extends RecyclerView.ViewHolder{
        TextView tvSophong, tvTinhTrang, tvLoaiPhong, tvTrangThai;
        Button btnxoa, btnsua;
        public PhongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSophong  = itemView.findViewById(R.id.tvTenKH);
            tvTinhTrang  = itemView.findViewById(R.id.tvCCCD);
            tvLoaiPhong  = itemView.findViewById(R.id.tvSDT);
            tvTrangThai = itemView.findViewById(R.id.tvSL);

            btnxoa = itemView.findViewById(R.id.btnXoa);
            btnsua = itemView.findViewById(R.id.btnSua);

        }
    }
    public interface PhongCallback{
        void onItemDeleteClicked(Phong phong, int position);
        void onItemEditClicked(Phong phong, int position);
    }
}
