package com.example.flamingo.HoaDon;

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

public class TaoHoaDon extends AppCompatActivity {
    Button btThem, btnHuy;
    RecyclerView rvListCode;
    ArrayList<HoaDon> lstHoaDon;
    HoaDonAdapter hoaDonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_hoa_don);
        btnHuy = findViewById(R.id.buttonthem);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TaoHoaDon.this, MainActivity.class);
                i.putExtra("Check",2);
                startActivity(i);
            }
        });
        EditText edSoPhong = (EditText) findViewById(R.id.edTenKH);
        EditText edHoTen = (EditText) findViewById(R.id.edsdt);
        EditText edcmnd = (EditText) findViewById(R.id.edtCCCD);
        EditText edtime = (EditText) findViewById(R.id.editTextTime);
        EditText eddate = (EditText) findViewById(R.id.editTextDate);
        EditText edsdt = (EditText) findViewById(R.id.edtSDT);
        btThem = findViewById(R.id.btnThem);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soPhong = edSoPhong.getText().toString();
                String hoTenKH = edHoTen.getText().toString();

                String gio = edtime.getText().toString();
                String ngayThang = eddate.getText().toString();
                String cmnd = edcmnd.getText().toString();
                String sdt = edsdt.getText().toString();
                if (soPhong.isEmpty()) {
                    Toast.makeText(TaoHoaDon.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
                } else {
                    HoaDon hoaDon = new HoaDon(0, soPhong, hoTenKH, gio, ngayThang ,cmnd, sdt);
                    long id = HoaDonDataQuery.insert(TaoHoaDon.this, hoaDon);
                    if (id > 0) {
                        Toast.makeText(TaoHoaDon.this, "Thêm hoá đơn thành công", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(TaoHoaDon.this, MainActivity.class);
                        i.putExtra("Check",2);
                        startActivity(i);
                    }
                }
            }
        });
    }
    void resetData()
    {
        lstHoaDon.clear();
        lstHoaDon.addAll(HoaDonDataQuery.getAll(TaoHoaDon.this));
        hoaDonAdapter.notifyDataSetChanged();
    }

}