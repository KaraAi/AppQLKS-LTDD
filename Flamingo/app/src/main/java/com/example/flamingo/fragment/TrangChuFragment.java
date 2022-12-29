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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import com.example.flamingo.Phòng.Phong;
import com.example.flamingo.Phòng.PhongAdapter;
import com.example.flamingo.Phòng.PhongDataQuery;
import com.example.flamingo.Phòng.TaoPhong;
import com.example.flamingo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment implements PhongAdapter.PhongCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rvListCode;
    ArrayList<Phong> lstPhong;
    PhongAdapter phongAdapter;
    ArrayAdapter<Phong> adapter;
    PhongDataQuery dataQuery;
    Button btnThem,btnHuy;
    EditText edSoPhong, edTinhTrang, edLoaiPhong, edTrangThai;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
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

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        rvListCode = view.findViewById(R.id.lv_danhsach);
        btnThem = view.findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), TaoPhong.class);
                startActivity(i);
            }
        });
        //lấy dữ liệu

        lstPhong = PhongDataQuery.getAll(getContext());
        phongAdapter = new PhongAdapter(lstPhong);
        phongAdapter.setCallback(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rvListCode.setAdapter(phongAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);
        return view;

    }


    void updatePhongDialog(Phong phong)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_sua_phong,null);
        alertDialog.setView(dialogView);
        edSoPhong = dialogView.findViewById(R.id.edTenKH);
        edLoaiPhong = dialogView.findViewById(R.id.edcccd);
        edTinhTrang = dialogView.findViewById(R.id.edsdt);
        edTrangThai = dialogView.findViewById(R.id.edSL);

        edSoPhong.setText(phong.getSoPhong());
        edLoaiPhong.setText(phong.getLoaiPhong());
        edTrangThai.setText(phong.getTrangThai());
        edTinhTrang.setText(phong.getTinhTrang());

        alertDialog.setPositiveButton("Đồng ý",(dialog,which) -> {
           phong.setSoPhong(edSoPhong.getText().toString());
           phong.setLoaiPhong(edLoaiPhong.getText().toString());
           phong.setTrangThai(edTrangThai.getText().toString());
           phong.setTinhTrang(edTinhTrang.getText().toString());
           int IdPhong = PhongDataQuery.update(getContext(),phong);
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
        lstPhong.clear();
        lstPhong.addAll(PhongDataQuery.getAll(getContext()));
        phongAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemDeleteClicked(Phong phong, int position) {
        boolean rs = PhongDataQuery.delete(getContext(), phong.getIdPhong());
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
    public void onItemEditClicked(Phong phong, int position) {
        updatePhongDialog(phong);
    }
}