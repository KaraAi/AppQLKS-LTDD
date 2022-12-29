package com.example.flamingo.KhachHang;

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
import com.example.flamingo.Phòng.Phong;
import com.example.flamingo.Phòng.PhongAdapter;
import com.example.flamingo.R;

import java.util.ArrayList;

public class TaoKhachHang extends AppCompatActivity {

    Button btThem, btnHuy;
    RecyclerView rvListCode;
    ArrayList<Phong> lstPhong;
    PhongAdapter phongAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_khach_hang);
        btnHuy = findViewById(R.id.buttonthem);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TaoKhachHang.this, MainActivity.class);
                i.putExtra("Check",3);
                startActivity(i);
            }
        });
        EditText edtenKH = (EditText) findViewById(R.id.edTenKH);
        EditText edSdt = (EditText) findViewById(R.id.edsdt);
        EditText edCccd = (EditText) findViewById(R.id.edcccd);
        btThem = findViewById(R.id.btnThem);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKH = edtenKH.getText().toString();
                String sDT = edSdt.getText().toString();

                String cccd = edCccd.getText().toString();
                if (tenKH.isEmpty()) {
                    Toast.makeText(TaoKhachHang.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
                } else {
                    KhachHang phong = new KhachHang(0,tenKH,sDT,cccd);
                    long id = KhachHangDataQuery.insert(TaoKhachHang.this, phong);
                    if (id > 0) {
                        Toast.makeText(TaoKhachHang.this, "Thêm Khách Hàng thành công", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(TaoKhachHang.this, MainActivity.class);
                        i.putExtra("Check",3);
                        startActivity(i);
                    }
                }
            }
        });
    }
}