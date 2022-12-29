package com.example.flamingo.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.flamingo.R;
import com.example.flamingo.user.Utils;
import com.example.flamingo.user.user;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NguoiDungFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NguoiDungFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SharedPreferences sharedPreferences;
    //Khai báo biến Gson (Biến thư viện chuyển file java sang xml)
    private final Gson gson = new Gson();
    TextView tvUserNameC;

    public NguoiDungFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NguoiDungFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NguoiDungFragment newInstance(String param1, String param2) {
        NguoiDungFragment fragment = new NguoiDungFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nguoi_dung, container, false);
        //Ánh xạ
        tvUserNameC = view.findViewById(R.id.tvUserName);
        //Khai báo biến shareget
        SharedPreferences shareget = getActivity().getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        String userPref = shareget.getString(Utils.KEY_USER, null);
        //Lấy thông tin từ user trong tủ sharedPreferences và chuyển sang dạng json
        user user = gson.fromJson(userPref, user.class);
        //Nếu user=null -> chưa có user đăng ký
        if (user == null) {
            tvUserNameC.setText("Không có dữ liệu");
        } else {
            // \n :xuống dòng
            String info = "Tên đăng nhập:" + user.getUserName() + "\n";
            info += "Email: " + user.getEmail() + "\n" +"Chức vụ: " + user.getChucvu();
            tvUserNameC.setText(info);
        }
        return view;
    }
}