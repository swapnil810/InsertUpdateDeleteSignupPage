package com.swapnil.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swapnil.R;
import com.swapnil.adapter.MainAdapter;
import com.swapnil.model.MainData;

import java.util.List;

public class ShowDataOnListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static List<MainData> dataList;
    LinearLayoutManager linearLayoutManager;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_on_list);
        initView();

    }

    /**
     *
     */
    private void initView() {
        recyclerView = findViewById(R.id.recyler_view);
        adapter = new MainAdapter(ShowDataOnListActivity.this, dataList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}