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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flamingo.QuanLyXuatNhap.TaoXuatNhap;
import com.example.flamingo.QuanLyXuatNhap.XuatNhap;
import com.example.flamingo.QuanLyXuatNhap.XuatNhapAdapter;
import com.example.flamingo.QuanLyXuatNhap.XuatNhapDataQuery;
import com.example.flamingo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyXuatNhapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyXuatNhapFragment extends Fragment implements XuatNhapAdapter.XuatNhapCallback {

    private Spinner spinner;
    private List<String> list;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rvListCode;
    ArrayList<XuatNhap> lstXN;
    XuatNhapAdapter xuatNhapAdapter;
    ArrayAdapter<XuatNhap> adapter;
    XuatNhapDataQuery dataQuery;
    Button btnThem,btnHuy;
    EditText edXN, edTenVP, edNgay, edSL;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyXuatNhapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuanLyXuatNhapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuanLyXuatNhapFragment newInstance(String param1, String param2) {
        QuanLyXuatNhapFragment fragment = new QuanLyXuatNhapFragment();
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

//        list = new ArrayList<>();
//        list.add("Nhâp vật tư");
//        list.add("Xuất vật tư");
//        list.add("Nhập thết bị");
//        list.add("Xuất thiết bị");
//        spinner = (Spinner).findViewById(R.id.spinner);
//        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.fragment_them_hoa_don, list);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_xuat_nhap, container, false);
        rvListCode = view.findViewById(R.id.lv_danhsach);
        btnThem = view.findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), TaoXuatNhap.class);
                startActivity(i);
            }
        });
        //lấy dữ liệu

        lstXN = XuatNhapDataQuery.getAll(getContext());
        xuatNhapAdapter = new XuatNhapAdapter(lstXN);
        xuatNhapAdapter.setCallback(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rvListCode.setAdapter(xuatNhapAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);
        return view;
    }

    @SuppressLint("MissingInflatedId")
    void updateXuatNhapDialog(XuatNhap xn)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.sua_xuat_nhap,null);
        alertDialog.setView(dialogView);
        edXN = dialogView.findViewById(R.id.edTenKH);
        edNgay = dialogView.findViewById(R.id.edcccd);
        edTenVP = dialogView.findViewById(R.id.edsdt);
        edSL = dialogView.findViewById(R.id.edSL);

        edXN.setText(xn.getXuatNhap());
        edNgay.setText(xn.getNgayXN());
        edTenVP.setText(xn.getTenVatPham());
        edSL.setText(xn.getSoLuong());

        alertDialog.setPositiveButton("Đồng ý",(dialog,which) -> {
            xn.setXuatNhap(edXN.getText().toString());
            xn.setNgayXN(edNgay.getText().toString());
            xn.setTenVatPham(edTenVP.getText().toString());
            xn.setSoLuong(edSL.getText().toString());
            int IdXN = XuatNhapDataQuery.update(getContext(),xn);
            resetData();
            dialog.dismiss();
        });
        alertDialog.setNegativeButton("Huỷ",(dialog,which)->{
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }
    void resetData()
    {
        lstXN.clear();
        lstXN.addAll(XuatNhapDataQuery.getAll(getContext()));
        xuatNhapAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemDeleteClicked(XuatNhap xn, int position) {
        boolean rs = XuatNhapDataQuery.delete(getContext(), xn.getIdXN());
        if(rs){
            Toast.makeText(getContext(),"Xoá thành công",Toast.LENGTH_LONG).show();
            resetData();
        }
        else
        {
            Toast.makeText(getContext(),"Xoá thất bại",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemEditClicked(XuatNhap xn, int position) {
        updateXuatNhapDialog(xn);
    }
}