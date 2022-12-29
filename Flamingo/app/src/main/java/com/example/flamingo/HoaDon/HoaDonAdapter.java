package com.example.flamingo.HoaDon;

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

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder>
{
    ArrayList<HoaDon> lstHoaDon;
    Context context;
    HoaDonCallback hoaDonCallback;

    public HoaDonAdapter(ArrayList<HoaDon> lstHoaDon){
        this.lstHoaDon = lstHoaDon;
    }
    public void setCallback(HoaDonCallback callback){
        this.hoaDonCallback = callback;
    }
    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //nạp layout cho View biểu diễn phần tử hoadon
        View hoadonView = inflater.inflate(R.layout.list_item_hoadon,parent,false);
        HoaDonViewHolder viewHolder = new HoaDonViewHolder(hoadonView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        HoaDon item = lstHoaDon.get(position);
        holder.tvSophong.setText(item.getSoPhong());
        holder.tvHotenthue.setText(item.getHoTenKH());

        holder.tvGiothue.setText(item.getGio());
        holder.tvNgaythue.setText(item.getNgayThang());
        holder.tvCmnd.setText(item.getCmnd());
        holder.tvSdt.setText(item.getSdt());
        holder.btnsua.setOnClickListener(view -> hoaDonCallback.onItemEditClicked(item,position));
        holder.btnxoa.setOnClickListener(view ->hoaDonCallback.onItemDeleteClicked(item,position));
    }

    @Override
    public int getItemCount() {
        return lstHoaDon.size();
    }

    class HoaDonViewHolder extends RecyclerView.ViewHolder{
        TextView tvSophong, tvHotenthue, tvGiothue, tvNgaythue, tvCmnd, tvSdt;
        Button btnxoa, btnsua;
        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSophong  = itemView.findViewById(R.id.sophong);
            tvHotenthue  = itemView.findViewById(R.id.hotennguoithue);

            tvGiothue  = itemView.findViewById(R.id.giothue);
            tvNgaythue  = itemView.findViewById(R.id.ngaythue);
            tvCmnd = itemView.findViewById(R.id.cmnd);
            tvSdt = itemView.findViewById(R.id.sdt);
            btnxoa = itemView.findViewById(R.id.btnXoa);
            btnsua = itemView.findViewById(R.id.btnSua);

        }
    }
    public interface HoaDonCallback{
        void onItemDeleteClicked(HoaDon hd, int position);
        void onItemEditClicked(HoaDon hd, int position);
    }
}
