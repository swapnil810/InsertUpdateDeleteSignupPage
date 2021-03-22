package com.swapnil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.swapnil.MainActivity;
import com.swapnil.R;
import com.swapnil.db.RoomDB;
import com.swapnil.model.MainData;
import com.swapnil.myutills.MyConstant;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etFirstName, etLastName, etEmail, etRole, etPass, etPasswordConfirmation;
    private Button btnSubmit;
    private String strFstName, strLstName, strEmail, strRole, strPass, strConfirmPass;
    List<MainData> dataList = new ArrayList<>();
    RoomDB database;
    private TextView tvLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
        OnClickListener();
    }

    /**
     * This method is used for performed click operation
     */
    private void OnClickListener() {
        btnSubmit.setOnClickListener(this);
        tvLoginPage.setOnClickListener(this);
    }

    /**
     * This method is used to initialized view
     */
    private void initView() {
        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();

        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etEmail = findViewById(R.id.et_email);
        etPass = findViewById(R.id.et_pass);
        etRole = findViewById(R.id.et_role);
        etPasswordConfirmation = findViewById(R.id.et_password_confirmation);
        btnSubmit = findViewById(R.id.btn_submit);
        tvLoginPage = findViewById(R.id.tv_open_login_page);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_open_login_page:
                Intent openNewPge = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(openNewPge);
                finishAffinity();
                break;

            case R.id.btn_submit:
                strFstName = etFirstName.getText().toString().trim();
                strLstName = etLastName.getText().toString().trim();
                strEmail = etEmail.getText().toString().trim();
                strRole = etRole.getText().toString().trim();
                strPass = etPass.getText().toString().trim();
                strConfirmPass = etPasswordConfirmation.getText().toString().trim();

                if (isValid()) {
                    MainData data = new MainData(strFstName, strLstName, strEmail, strRole, strPass, strConfirmPass);
                    database.mainDao().insert(data);

                    etFirstName.setText("");
                    etLastName.setText("");
                    etEmail.setText("");
                    etRole.setText("");
                    etPass.setText("");
                    etPasswordConfirmation.setText("");
                    Toast.makeText(this, "Your Registration is successfully !!", Toast.LENGTH_SHORT).show();
                    //  dataList.clear();
                    // dataList.addAll(database.mainDao().getAll());
                }

                break;

        }
    }


    /**
     * Validation checks for entered information by user
     */
    private boolean isValid() {
        if (strFstName != null && strFstName.equalsIgnoreCase("")) {
            etFirstName.setError(getResources().getString(R.string.empty_first_name));
            etFirstName.requestFocus();
            return false;
        } else if (strLstName != null && strLstName.equalsIgnoreCase("")) {
            etLastName.setError(getResources().getString(R.string.empty_last_name));
            etLastName.requestFocus();
            return false;
        } else if (strEmail != null && strEmail.equalsIgnoreCase("")) {
            etEmail.setError(getResources().getString(R.string.empty_email));
            etEmail.requestFocus();
            return false;
        } else if (!MyConstant.isValidEmail(strEmail)) {
            etEmail.setError(getResources().getString(R.string.please_enter_valid_email_id));
            etEmail.requestFocus();
            return false;
        } else if (strRole != null && strRole.equalsIgnoreCase("")) {
            etRole.setError(getResources().getString(R.string.empty_role));
            etRole.requestFocus();
            return false;
        } else if (strPass != null && strPass.equalsIgnoreCase("")) {
            etPass.setError(getResources().getString(R.string.empty_pass));
            etPass.requestFocus();
            return false;
        } else if (strPass.length() < 6) {
            etPass.setError(getResources().getString(R.string.valid_pass));
            etPass.requestFocus();
            return false;
        } else if (strConfirmPass != null && strConfirmPass.equalsIgnoreCase("")) {
            etPasswordConfirmation.setError(getResources().getString(R.string.empty_pass_confirm));
            etPasswordConfirmation.requestFocus();
            return false;
        } else if (!strPass.equals(strConfirmPass)) {
            etPasswordConfirmation.setError(getResources().getString(R.string.password_do_not));
            etPasswordConfirmation.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}