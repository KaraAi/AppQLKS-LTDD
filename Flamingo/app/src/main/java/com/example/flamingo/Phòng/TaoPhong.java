package com.example.flamingo.Phòng;

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

public class TaoPhong extends AppCompatActivity {
    Button btThem, btnHuy;
    RecyclerView rvListCode;
    ArrayList<Phong> lstPhong;
    PhongAdapter phongAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_phong);
        btnHuy = findViewById(R.id.buttonthem);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TaoPhong.this, MainActivity.class);
                i.putExtra("Check",1);
                startActivity(i);
            }
        });


        EditText edSoPhong = (EditText) findViewById(R.id.edTenKH);
        EditText edTinhTrang = (EditText) findViewById(R.id.edsdt);
        EditText edLoaiPhong = (EditText) findViewById(R.id.edcccd);
        EditText edTrangThai = (EditText) findViewById(R.id.edSL);
        btThem = findViewById(R.id.btnThem);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soPhong = edSoPhong.getText().toString();
                String tinhTrang = edTinhTrang.getText().toString();

                String loaiPhong = edLoaiPhong.getText().toString();
                String trangThai = edTrangThai.getText().toString();
                if (soPhong.isEmpty()) {
                    Toast.makeText(TaoPhong.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
                } else {
                    Phong phong = new Phong(0, soPhong, tinhTrang, loaiPhong, trangThai);
                    long id = PhongDataQuery.insert(TaoPhong.this, phong);
                    if (id > 0) {
                        Toast.makeText(TaoPhong.this, "Thêm Phòng thành công", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(TaoPhong.this, MainActivity.class);
                        i.putExtra("Check",1);
                        startActivity(i);
                    }
                }
            }
        });
    }
    void resetData()
    {
        lstPhong.clear();
        lstPhong.addAll(PhongDataQuery.getAll(TaoPhong.this));
        phongAdapter.notifyDataSetChanged();
    }


}