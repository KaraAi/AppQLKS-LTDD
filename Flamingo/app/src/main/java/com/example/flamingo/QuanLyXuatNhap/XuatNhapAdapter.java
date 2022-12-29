package com.example.flamingo.QuanLyXuatNhap;

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

public class XuatNhapAdapter extends RecyclerView.Adapter<XuatNhapAdapter.XuatNhapViewHolder>
{
    ArrayList<XuatNhap> lstXN;
    Context context;
    XuatNhapCallback xuatnhapCallback;

    public XuatNhapAdapter(ArrayList<XuatNhap> lstXN){
        this.lstXN = lstXN;
    }
    public void setCallback(XuatNhapCallback callback){
        this.xuatnhapCallback = callback;
    }
    @NonNull
    @Override
    public XuatNhapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //nạp layout cho View biểu diễn phần tử Phong
        View xuatNhapView = inflater.inflate(R.layout.list_view_xuatnhap,parent,false);
        XuatNhapViewHolder viewHolder = new XuatNhapViewHolder(xuatNhapView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull XuatNhapViewHolder holder, int position) {
        XuatNhap item = lstXN.get(position);
        holder.tvXuatNhap.setText(item.getXuatNhap());
        holder.tvNgay.setText(item.getNgayXN());
        holder.tvTenVP.setText(item.getTenVatPham());
        holder.tvSL.setText(item.getSoLuong());
        holder.btnsua.setOnClickListener(view -> xuatnhapCallback.onItemEditClicked(item,position));
        holder.btnxoa.setOnClickListener(view -> xuatnhapCallback.onItemDeleteClicked(item,position));
    }

    @Override
    public int getItemCount() {
        return lstXN.size();
    }

    class XuatNhapViewHolder extends RecyclerView.ViewHolder{
        TextView tvXuatNhap, tvNgay, tvTenVP, tvSL;
        Button btnxoa, btnsua;
        public XuatNhapViewHolder(@NonNull View itemView) {
            super(itemView);
            tvXuatNhap  = itemView.findViewById(R.id.tvTenKH);
            tvNgay  = itemView.findViewById(R.id.tvCCCD);
            tvTenVP  = itemView.findViewById(R.id.tvSDT);
            tvSL = itemView.findViewById(R.id.tvSL);

            btnxoa = itemView.findViewById(R.id.btnXoa);
            btnsua = itemView.findViewById(R.id.btnSua);

        }
    }
    public interface XuatNhapCallback{
        void onItemDeleteClicked(XuatNhap xn, int position);
        void onItemEditClicked(XuatNhap xn, int position);
    }
}
