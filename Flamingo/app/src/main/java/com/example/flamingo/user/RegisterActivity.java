package com.example.flamingo.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flamingo.R;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    private EditText edUserNameC;
    private EditText edPasswordC;
    private EditText edConfirmPasswordC;
    private EditText edEmailC;
    private EditText edPhoneNumberC;
    private RadioGroup rbSex;
    private EditText edChucvu;
    private EditText edadmin;
    private Button btnRegister;
    private ImageButton imbBack;
    public DatabaseHelper helper;
    SQLiteDatabase database;

    public SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    private final Gson gson= new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Register");
//        actionBar.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Register");
        //khai bao share pre
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);

        //lay du lieu
        anhxadulieu();
        taosukien();
        editor = sharedPreferences.edit();
        helper = new DatabaseHelper(RegisterActivity.this);
    }

    void anhxadulieu()
    {
        edUserNameC = findViewById(R.id.edUserNameRe);
        edPasswordC = findViewById(R.id.edPasswordRe);
        edConfirmPasswordC = findViewById(R.id.edit_confirm_pasword);
        edEmailC=findViewById(R.id.edEmail);
        edPhoneNumberC= findViewById(R.id.edPhone);
        rbSex = findViewById(R.id.rgsex);
        edChucvu = findViewById(R.id.edPhone2);
        edadmin = findViewById(R.id.edPhone3);
        btnRegister = findViewById(R.id.btRegister);
        imbBack = findViewById(R.id.imbBack);
    }
    void taosukien()
    {
        btnRegister.setOnClickListener(view -> sukienRegister());
        imbBack.setOnClickListener(view -> finish());
    }
    void sukienRegister()
    {
        String userName = edUserNameC.getText().toString().trim();
        String password = edPasswordC.getText().toString().trim();
        String confirmPassword = edConfirmPasswordC.getText().toString().trim();
        String email = edEmailC.getText().toString().trim();
        String phone= edPhoneNumberC.getText().toString().trim();
        int sex =1 ;
        String chucvu = edChucvu.getText().toString().trim();
        String admin = edadmin.getText().toString().trim();
        String pwadmin = "12345";
        boolean isValid = checkUserName(userName) && checkPassword(password, confirmPassword) &&checkPasswordadmin(admin, pwadmin);

        if (isValid){
            // neu du lieu hop le, tao doi tuong user de luu vo share preference.
            user userNew = new user();

            userNew.setUserName(userName);
            userNew.setPassWord(password);
            userNew.setEmail(email);
            userNew.setPhone(phone);
            // layas rdio button is dang dc check
            int sexSelected = rbSex.getCheckedRadioButtonId();
            if (sexSelected==R.id.rbFemale){
                sex=0;
            }
            userNew.setSex(sex);
            userNew.setChucvu(chucvu);
            //vi user laf object nen convert qua s vs format laf json
            String userStr = gson.toJson(userNew);
            editor.putString(Utils.KEY_USER, userStr);
            editor.commit();

            helper.insertUser(userNew);
            //dung toast de show thong bao dang ky thang cong
            Toast.makeText(RegisterActivity.this, "Dang ky tai khoan thanh cong", Toast.LENGTH_LONG).show();
            finish();
        }
    }
    private boolean checkUserName(String userName){
        if (userName.isEmpty()){
            edUserNameC.setError("vui l??ng nh???p t??n ????ng nh???p");
            return false;
        }
        if (userName.length()<=5){
            edUserNameC.setError("t??n ????ng nh???p ph???i ??t nh???t 6 k?? t???");
            return false;
        }
        return true;
    }
    private boolean checkPassword(String password, String confirmPassword) {
        if (password.isEmpty()) {
            edPasswordC.setError("vui l??ng nhap m???t kh???u");
            return false;
        }
        if (password.length() <= 5) {
            edPasswordC.setError("m???t kh???u ph???i l???n h??n 5 k?? t???");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            edConfirmPasswordC.setError("x??c nh???n m???t kh???u kh??ng kh???p");
            return false;
        }
        return true;
    }
    private boolean checkPasswordadmin(String admin, String pwadmin) {
        if (admin.isEmpty()) {
            edadmin.setError("vui l??ng nhap m???t kh???u");
            return false;
        }
        if (!admin.equals(pwadmin)) {
            edadmin.setError("m???t kh???u kh??ng ????ng");
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home)
        {
            finish();
            return true;
        }
        return true;
    }
}