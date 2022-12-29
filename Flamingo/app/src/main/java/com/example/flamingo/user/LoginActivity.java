package com.example.flamingo.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flamingo.MainActivity;
import com.example.flamingo.R;
import com.example.flamingo.fragment.TrangChuFragment;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    Button btLogin, btRegisterC;
    EditText edUserNameC, edPasswordC;
    SharedPreferences.Editor editor;
    private final Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    DatabaseHelper helper;
    SQLiteDatabase database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");
        //
//        btLogin= findViewById(R.id.btnLogin);
//        btRegisterC= findViewById(R.id.btnRegister);
//        edUserNameC= findViewById(R.id.edUsernamelg);
//        edPasswordC= findViewById(R.id.edPasswordlg);
//        //
//        btLogin.setOnClickListener(nhanvaoLogin());
//        btRegisterC.setOnClickListener(nhanvaoRegister());
        helper = new DatabaseHelper(LoginActivity.this);
        /*
         * Phương thức “getWritableDatabase()” dùng
         * để tạo hoặc mở cơ sở dữ liệu để đọc và
         * ghi vào cơ sở dữ liệu.
         */
        database = helper.getWritableDatabase();
        anhxa();
        //
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        //
        taosukien();
    }
    private  void taosukien(){
        btLogin.setOnClickListener(view -> checkUserLogin());
        btRegisterC.setOnClickListener(funRegister());
        //
    }
    private void anhxa(){
        btLogin= findViewById(R.id.btnLogin);
        btRegisterC= findViewById(R.id.btnRegister);
        edUserNameC= findViewById(R.id.edUsernamelg);
        edPasswordC= findViewById(R.id.edPasswordlg);
    }
    private View.OnClickListener funRegister(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }
    private void checkUserLogin(){
        String username = edUserNameC.getText().toString();

        //
        if (helper.searchTK(username))
        {
            if(edPasswordC.getText().toString().equals(helper.searchpass(username))) {
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(),"Sai mật khẩu",Toast.LENGTH_LONG).show();
            }
        }
    }
    private View.OnClickListener nhanvaoLogin(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUserNameC.getText().toString();

                //
                if (helper.searchTK(username))
                {
                    if(edPasswordC.getText().toString().equals(helper.searchpass(username))) {
                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this, TrangChuFragment.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(),"Sai mật khẩu",Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
    }

    private View.OnClickListener nhanvaoRegister(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }
    boolean checkUserName(String username){
        if (username.isEmpty()) {
            edUserNameC.setError("vui lòng nhập");
            return false;
        }
        return true;
    }
    boolean checkPassWord(String password){
        if (password.isEmpty()) {
            edPasswordC.setError("vui lòng nhập");
            return false;
        }
        return true;
    }
}