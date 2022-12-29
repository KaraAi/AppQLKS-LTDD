package com.example.flamingo.fragment;

import android.annotation.SuppressLint;
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

import com.example.flamingo.HoaDon.HoaDon;
import com.example.flamingo.HoaDon.HoaDonAdapter;
import com.example.flamingo.HoaDon.HoaDonDataQuery;
import com.example.flamingo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link XuatHoaDonFragment} factory method to
 * create an instance of this fragment.
 */

public class XuatHoaDonFragment extends Fragment implements HoaDonAdapter.HoaDonCallback {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        Button buttonm;
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;
    RecyclerView rvListCode;
    ArrayList<HoaDon> lstHoaDon;
    HoaDonAdapter hoaDonAdapter;
    ArrayAdapter<HoaDon> adapter;
    HoaDonDataQuery dataQuery;
    Button btnThem,btnHuy;
    EditText edSoPhong, edHoTen, edcmnd, edtime, eddate, edsdt;

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment XuatHoaDonFragment.
         */
        // TODO: Rename and change types and number of parameters
        public XuatHoaDonFragment newInstance(String param1, String param2) {
            XuatHoaDonFragment fragment = new XuatHoaDonFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        Button mbutton;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);

//                mbutton.findViewById(R.id.button4);
//                mbutton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent i = new Intent(getActivity(), TaoHoaDon.class);
//                    }
//                });
            }

        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_xuat_hoa_don, container, false);
            rvListCode = view.findViewById(R.id.lv_danhsach);

            //lấy dữ liệu

            lstHoaDon = HoaDonDataQuery.getAll(getContext());
            hoaDonAdapter = new HoaDonAdapter(lstHoaDon);
            hoaDonAdapter.setCallback(this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            rvListCode.setAdapter(hoaDonAdapter);
            rvListCode.setLayoutManager(linearLayoutManager);
            return view;

        }
    @SuppressLint("MissingInflatedId")
    void updateHoaDonDialog(HoaDon hd)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_sua_hoa_don,null);
        alertDialog.setView(dialogView);

        edSoPhong = dialogView.findViewById(R.id.edTenKH);
        edHoTen = dialogView.findViewById(R.id.edsdt);
        edcmnd = dialogView.findViewById(R.id.edtCCCD);
        edtime = dialogView.findViewById(R.id.editTextTime);
        eddate = dialogView.findViewById(R.id.editTextDate);
        edsdt = dialogView.findViewById(R.id.edtSDT);

        edSoPhong.setText(hd.getSoPhong());
        edHoTen.setText(hd.getHoTenKH());
        edcmnd.setText(hd.getCmnd());
        edtime.setText(hd.getGio());
        eddate.setText(hd.getNgayThang());
        edsdt.setText(hd.getSdt());

        alertDialog.setPositiveButton("Đồng ý",(dialog,which) -> {
            hd.setSoPhong(edSoPhong.getText().toString());
            hd.setHoTenKH(edHoTen.getText().toString());
            hd.setCmnd(edcmnd.getText().toString());;
            hd.setGio(edtime.getText().toString());
            hd.setNgayThang(eddate.getText().toString());
            hd.setSdt(edsdt.getText().toString());
            int MaHD = HoaDonDataQuery.update(getContext(),hd);
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
        lstHoaDon.clear();
        lstHoaDon.addAll(HoaDonDataQuery.getAll(getContext()));
        hoaDonAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClicked(HoaDon hd, int position) {
        boolean rs = HoaDonDataQuery.delete(getContext(), hd.getMaHD());
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
    public void onItemEditClicked(HoaDon hd, int position) {
        updateHoaDonDialog(hd);
    }
}
