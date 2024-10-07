package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LeftFragment leftFragment;
    private TextView tv_recommend, tv_must_buy;
    private RightFragment rightFragment;

    private String[] names1 = {"三荤五素一份米饭", "豪华双人套餐",
            "双人套餐（含两份米饭）"};
    private String[] sales1 = {"月售520 好评度80%", "月售184 好评度68%",
            "月售114 好评度60%"};
    private String[] prices1 = {"￥23", "￥41", "￥32"};
    private int[] imgs1 = {R.drawable.recom_one, R.drawable.recom_two,
            R.drawable.recom_three};

    private String[] names2 = {"单人经典套餐", "双人经典套餐", "三人经典套餐"};
    private String[] sales2 = {"月售26 好评度70%", "月售12 好评度50%",
            "月售4 好评度40%"};
    private String[] prices2 = {"￥44", "￥132", "￥180"};
    private int[] imgs2 = {R.drawable.must_buy_one, R.drawable.must_buy_two,
            R.drawable.must_buy_three};
    private Map<String, List<FoodBean>> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        init();
        clickEvent();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();//获取fragmentManager

        leftFragment = (LeftFragment) fragmentManager.findFragmentById(R.id.left);

        tv_recommend = leftFragment.getView().findViewById(R.id.tv_recommend);
        tv_must_buy = leftFragment.getView().findViewById(R.id.tv_must_buy);
    }

    private void setData(){
        map=new HashMap<>();
        List<FoodBean> list1=new ArrayList<>();
        List<FoodBean> list2=new ArrayList<>();
        for (int i=0;i<names1.length;i++){
            FoodBean bean=new FoodBean();
            bean.setName(names1[i]);
            bean.setSales(sales1[i]);
            bean.setPrice(prices1[i]);
            bean.setImg(imgs1[i]);
            list1.add(bean);
        }

        map.put("1",list1);
        for (int i=0;i<names2.length;i++){
            FoodBean bean=new FoodBean();
            bean.setName(names2[i]);
            bean.setSales(sales2[i]);
            bean.setPrice(prices2[i]);
            bean.setImg(imgs2[i]);
            list2.add(bean);
        }
        map.put("2",list2);
    }

    private void clickEvent() {
        tv_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchData(map.get("1"));
                tv_recommend.setBackgroundColor(Color.WHITE);
                tv_must_buy.setBackgroundResource(R.color.gray);
            }
        });
        tv_must_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchData(map.get("2"));
                tv_must_buy.setBackgroundColor(Color.WHITE);
                tv_recommend.setBackgroundResource(R.color.gray);
            }
        });

        switchData(map.get("1"));
    }

    public void switchData(List<FoodBean> list) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        rightFragment = new RightFragment().getInstance(list);

        fragmentTransaction.replace(R.id.right, rightFragment);
        fragmentTransaction.commit();
    }
}