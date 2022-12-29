package com.example.flamingo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flamingo.Phòng.Phong;
import com.example.flamingo.Phòng.PhongAdapter;
import com.example.flamingo.Phòng.PhongDataQuery;
import com.example.flamingo.fragment.HoTroFragment;
import com.example.flamingo.fragment.KhachHangFragment;
import com.example.flamingo.fragment.NguoiDungFragment;
import com.example.flamingo.fragment.QuanLyXuatNhapFragment;
import com.example.flamingo.fragment.TrangChuFragment;
import com.example.flamingo.fragment.VeChungToiFragment;
import com.example.flamingo.fragment.XuatHoaDonFragment;
import com.example.flamingo.user.LoginActivity;
import com.example.flamingo.HoaDon.TaoHoaDon;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

;


public class MainActivity extends AppCompatActivity{

    BottomNavigationView mnbottom;
    AppBarConfiguration mntopright;
    RecyclerView rvListCode;
    ArrayList<Phong> lstPhong;
    PhongAdapter phongAdapter;
    PhongDataQuery dataQuery;
    Button btnThem,btnHuy;
    FloatingActionButton add;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvListCode = findViewById(R.id.lv_dsphong);
        mnbottom = findViewById(R.id.navMenu);


        ActionBar actionBar = getSupportActionBar();
        Intent intent = getIntent();
        String s1 = String.valueOf(intent.getIntExtra("Check",1));
        String s2 = String.valueOf(intent.getIntExtra("Check",2));
        String s3 = String.valueOf(intent.getIntExtra("Check",3));
        String s4 = String.valueOf(intent.getIntExtra("Check",4));
        if(s1 != null && s1.equalsIgnoreCase("1"))
        {
            s1="";

            Fragment fragment = new TrangChuFragment();
            loadFragment(fragment);
            actionBar.setTitle("Danh Sách Phòng ");
            actionBar.setDisplayHomeAsUpEnabled(true);

            mnbottom.setOnItemSelectedListener(getListener());
        }
        else if(s2 != null && s2.equalsIgnoreCase("2"))
        {

            Fragment fragment = new XuatHoaDonFragment();
            loadFragment(fragment);
            actionBar.setTitle("Danh Sách Hoá Đơn ");
            actionBar.setDisplayHomeAsUpEnabled(true);
            mnbottom.setOnItemSelectedListener(getListener());
        }
        else if(s3 != null && s3.equalsIgnoreCase("3"))
        {

            Fragment fragment = new KhachHangFragment();
            loadFragment(fragment);
            actionBar.setTitle("Danh Sách Khách hàng ");
            actionBar.setDisplayHomeAsUpEnabled(true);
            mnbottom.setOnItemSelectedListener(getListener());
        }
        else if(s4 != null && s4.equalsIgnoreCase("4"))
        {

            Fragment fragment = new QuanLyXuatNhapFragment();
            loadFragment(fragment);
            actionBar.setTitle("Quản Lý Xuất Nhập ");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getTitle());
            mnbottom.setOnItemSelectedListener(getListener());
        }

        add =  findViewById(R.id.fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TaoHoaDon.class);
                startActivity(i);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fmNew;
        switch (item.getItemId()) {
            case R.id.Nguoidung:
                getSupportActionBar().setTitle(item.getTitle());
                fmNew = new NguoiDungFragment();
                loadFragment(fmNew);
                return true;
            case R.id.HoTro:
                getSupportActionBar().setTitle(item.getTitle());
                fmNew = new HoTroFragment();
                loadFragment(fmNew);
                return true;
            case R.id.VeChungToi:
                getSupportActionBar().setTitle(item.getTitle());
                fmNew = new VeChungToiFragment();
                loadFragment(fmNew);
                return true;
            case R.id.DangXuat:
                Intent t = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(t);
                return true;

        }
        return true;
    }

    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fmNew;
                switch (item.getItemId()) {
                    case R.id.mnTrangChu:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new TrangChuFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnKhachHang:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new KhachHangFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnXuatHoaDon:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new XuatHoaDonFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnQuanLyXuaNhap:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new QuanLyXuatNhapFragment();
                        loadFragment(fmNew);
                        return true;

                }
                return true;
            }
        };
    }
    void loadFragment(Fragment fmNew)
    {
     //  getSupportActionBar().setTitle(this.getTitle());
        FragmentTransaction fmTran= getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mntopright, menu);
        return true;
    }


}