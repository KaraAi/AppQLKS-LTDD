package com.example.flamingo.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flamingo.KhachHang.KhachHang;
import com.example.flamingo.KhachHang.KhachHangAdapter;
import com.example.flamingo.KhachHang.KhachHangDataQuery;
import com.example.flamingo.KhachHang.TaoKhachHang;
import com.example.flamingo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhachHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhachHangFragment extends Fragment implements KhachHangAdapter.KhachHangCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rvListCode;
    ArrayList<KhachHang> lstKH;
    KhachHangAdapter khachHangAdapter;
    ArrayAdapter<KhachHang> adapter;
    KhachHangDataQuery dataQuery;
    Button btnThem,btnHuy;
    EditText edTenKH, edcccd, edsdt;

    public KhachHangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KhachHangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KhachHangFragment newInstance(String param1, String param2) {
        KhachHangFragment fragment = new KhachHangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        rvListCode = view.findViewById(R.id.lv_danhsach);
        btnThem = view.findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), TaoKhachHang.class);
                startActivity(i);
            }
        });
        //lấy dữ liệu

        lstKH = KhachHangDataQuery.getAll(getContext());
        khachHangAdapter = new KhachHangAdapter(lstKH);
        khachHangAdapter.setCallback(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rvListCode.setAdapter(khachHangAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);
        return view;
    }

    @SuppressLint("MissingInflatedId")
    void updateKhachHangDialog(KhachHang phong)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.sua_khach_hang,null);
        alertDialog.setView(dialogView);
        edTenKH = dialogView.findViewById(R.id.edTenKH);
        edcccd = dialogView.findViewById(R.id.edcccd);
        edsdt = dialogView.findViewById(R.id.edsdt);


        edTenKH.setText(phong.getTenKH());
        edcccd.setText(phong.getcCCD());
        edsdt.setText(phong.getsDT());

        alertDialog.setPositiveButton("Đồng ý",(dialog,which) -> {
            phong.setTenKH(edTenKH.getText().toString());
            phong.setcCCD(edcccd.getText().toString());
            phong.setsDT(edsdt.getText().toString());
            int IdKH = KhachHangDataQuery.update(getContext(),phong);
            resetData();
            dialog.dismiss();
        });
        alertDialog.setNegativeButton("Huỷ",(dialog,which)->{
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }
    @Override
    public void onItemDeleteClicked(KhachHang phong, int position) {
        boolean rs = KhachHangDataQuery.delete(getContext(), phong.getIdKH());
        if(rs){
            Toast.makeText(getContext(),"Xoá thành công",Toast.LENGTH_LONG).show();
            resetData();
        }
        else
        {
            Toast.makeText(getContext(),"Xoá thất bại",Toast.LENGTH_LONG).show();
        }
    }
    void resetData()
    {
        lstKH.clear();
        lstKH.addAll(KhachHangDataQuery.getAll(getContext()));
        khachHangAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemEditClicked(KhachHang phong, int position) {
        updateKhachHangDialog(phong);
    }
}