package com.example.popupwindows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(MainActivity.class);


    private Button btn1;
    private List<ItemData> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn_dianji);
        log.debug("initViews");
        //初始化数据
        initData();


        /**
         * 点击事件
         */
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBottomSheetDialog2();
            }
        });
    }


    private void initData() {
        list = new ArrayList<ItemData>();
        ItemData itemData = new ItemData();
        itemData.setTitle("11111");
        itemData.setChecked(false);
        list.add(itemData);

        ItemData itemData2 = new ItemData();
        itemData2.setTitle("22222");
        itemData2.setChecked(false);
        list.add(itemData2);

        ItemData itemData3 = new ItemData();
        itemData3.setTitle("33333");
        itemData3.setChecked(false);
        list.add(itemData3);
    }

    //展示BottomSheetDialog，列表形式
    @SuppressLint("MissingInflatedId")
    private void initBottomSheetDialog2() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        //创建recyclerView
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_bottomsheet,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.dialog_imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dialog_recycleView);

        //RecyclerView recyclerView = new RecyclerView(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        MainAdapter recyclerAdapter = new MainAdapter(this,list);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View item, int position) {
                for(ItemData data: list){
                    if (data.getTitle().equals(list.get(position).getTitle())){
                        data.setChecked(true);
                    }else {
                        data.setChecked(false);
                    }
                }
                btn1.setText(list.get(position).getTitle());
                Toast.makeText(MainActivity.this, "item "+list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();

            }
        });

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

}