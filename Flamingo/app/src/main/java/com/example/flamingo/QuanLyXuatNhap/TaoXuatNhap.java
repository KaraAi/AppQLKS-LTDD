package com.example.flamingo.QuanLyXuatNhap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flamingo.MainActivity;
import com.example.flamingo.R;

import java.util.ArrayList;

public class TaoXuatNhap extends AppCompatActivity {
    Button btThem, btnHuy;
    RecyclerView rvListCode;
    ArrayList<XuatNhap> lstXN;
    XuatNhapAdapter xuatNhapAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_xuat_nhap);
        btnHuy = findViewById(R.id.buttonthem);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TaoXuatNhap.this, MainActivity.class);
                i.putExtra("Check",4);
                startActivity(i);
            }
        });
        EditText edXuatNhap = (EditText) findViewById(R.id.edTenKH);
        EditText edTenVP= (EditText) findViewById(R.id.edsdt);
        EditText edNN = (EditText) findViewById(R.id.edcccd);
        EditText edSL = (EditText) findViewById(R.id.edSL);
        btThem = findViewById(R.id.btnThem);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xuatNhap = edXuatNhap.getText().toString();
                String tenVP = edTenVP.getText().toString();

                String nNhap = edNN.getText().toString();
                String soLuong = edSL.getText().toString();
                if (xuatNhap.isEmpty()) {
                    Toast.makeText(TaoXuatNhap.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
                } else {
                    XuatNhap xn = new XuatNhap(0, xuatNhap, tenVP, nNhap, soLuong);
                    long id = XuatNhapDataQuery.insert(TaoXuatNhap.this, xn);
                    if (id > 0) {
                        Toast.makeText(TaoXuatNhap.this, "Thêm Phòng thành công", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(TaoXuatNhap.this, MainActivity.class);
                        i.putExtra("Check",4);
                        startActivity(i);
                    }
                }
            }
        });
    }


}