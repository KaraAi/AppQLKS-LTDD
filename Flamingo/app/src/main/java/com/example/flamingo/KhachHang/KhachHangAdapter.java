package com.example.flamingo.KhachHang;

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

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachHangViewHolder>{
    ArrayList<KhachHang> lstPhong;
    Context context;
    KhachHangAdapter.KhachHangCallback khachHangCallback;

    public KhachHangAdapter(ArrayList<KhachHang> lstPhong){
        this.lstPhong = lstPhong;
    }
    public void setCallback(KhachHangCallback callback){
        this.khachHangCallback = callback;
    }
    @NonNull
    @Override
    public KhachHangAdapter.KhachHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //nạp layout cho View biểu diễn phần tử Phong
        View phongView = inflater.inflate(R.layout.list_view_khach_hang,parent,false);
        KhachHangAdapter.KhachHangViewHolder viewHolder = new KhachHangAdapter.KhachHangViewHolder(phongView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangViewHolder holder, int position) {
        KhachHang item = lstPhong.get(position);
        holder.tvtenKH.setText(item.getTenKH());
        holder.tvcccd.setText(item.getcCCD());
        holder.tvsdt.setText(item.getsDT());

        holder.btnsua.setOnClickListener(view -> khachHangCallback.onItemEditClicked(item,position));
        holder.btnxoa.setOnClickListener(view -> khachHangCallback.onItemDeleteClicked(item,position));
    }

    @Override
    public int getItemCount() {
        return lstPhong.size();
    }

    class KhachHangViewHolder extends RecyclerView.ViewHolder{
        TextView tvtenKH, tvsdt, tvcccd;
        Button btnxoa, btnsua;
        public KhachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtenKH  = itemView.findViewById(R.id.tvTenKH);
            tvcccd  = itemView.findViewById(R.id.tvCCCD);
            tvsdt  = itemView.findViewById(R.id.tvSDT);

            btnxoa = itemView.findViewById(R.id.btnXoa);
            btnsua = itemView.findViewById(R.id.btnSua);

        }
    }
    public interface KhachHangCallback{
        void onItemDeleteClicked(KhachHang phong, int position);
        void onItemEditClicked(KhachHang phong, int position);
    }
}
