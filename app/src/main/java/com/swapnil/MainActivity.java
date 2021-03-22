package com.swapnil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.swapnil.activity.RegistrationActivity;
import com.swapnil.activity.ShowDataOnListActivity;
import com.swapnil.db.RoomDB;
import com.swapnil.model.MainData;
import com.swapnil.myutills.MyConstant;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private String strEmail, strPass;
    private EditText edtEmail, etPassword;
    private TextView tvOpenReg;
    List<MainData> dataList = new ArrayList<>();
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        OnClickListener();
    }

    /**
     * This method is used for performed click operation
     */
    private void OnClickListener() {
        btnSubmit.setOnClickListener(this);
        tvOpenReg.setOnClickListener(this);
    }

    /**
     * This method is used to initialezed view
     */
    private void initView() {
        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();

        edtEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSubmit = findViewById(R.id.btn_login);
        tvOpenReg = findViewById(R.id.tv_open_reg);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                strEmail = edtEmail.getText().toString().trim();
                strPass = etPassword.getText().toString().trim();
                if (isValid()) {
                    for (int i = 0; i < dataList.size(); i++) {
                        if (strEmail.equals(dataList.get(i).getEmail()) &&
                                strPass.equals(dataList.get(i).getPassword())) {
                            Intent intent = new Intent(getBaseContext(), ShowDataOnListActivity.class);
                            startActivity(intent);
                            Toast.makeText(this, "Loged in successfully!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Please do match email id or password.", Toast.LENGTH_SHORT).show();
                        }
                        ShowDataOnListActivity.dataList = dataList;
                    }
                }
                break;

            case R.id.tv_open_reg:
                Intent openNewPge = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(openNewPge);
                break;

        }
    }

    /**
     * This method is used to validate a particular filed
     *
     * @return
     */
    private boolean isValid() {
        if (strEmail != null && strEmail.equalsIgnoreCase("")) {
            edtEmail.setError(getResources().getString(R.string.empty_email));
            edtEmail.requestFocus();
            return false;
        } else if (!MyConstant.isValidEmail(strEmail)) {
            edtEmail.setError(getResources().getString(R.string.please_enter_valid_email_id));
            edtEmail.requestFocus();
            return false;
        } else if (strPass != null && strPass.equalsIgnoreCase("")) {
            etPassword.setError(getResources().getString(R.string.empty_pass));
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}