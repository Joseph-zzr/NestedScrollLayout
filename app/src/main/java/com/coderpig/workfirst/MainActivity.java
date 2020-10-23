package com.coderpig.workfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransitionImpl;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.webkit.TracingConfig;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.coderpig.workfirst.adapter.RecyclerviewAdapter;
import com.coderpig.workfirst.adapter.ViewPagerAdapter;
import com.coderpig.workfirst.fragment.RecyclerViewFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView scroll_Recyclerview;
    private TabLayout scroll_Tablayout;
    private ViewPager2 scroll_Viewpager;
    private SwipeRefreshLayout swiperefreshlayout;

    private ArrayList<String> strFatArrayList = new ArrayList<>();
    private RecyclerviewAdapter adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                swiperefreshlayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "二级联动", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initParentRecyclerView();
        initViewPager();
        initTablayout();
    }



    private void initView() {
        swiperefreshlayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        scroll_Recyclerview = findViewById(R.id.scroll_recyclerview);
        scroll_Tablayout = findViewById(R.id.scroll_tablayout);
        scroll_Viewpager = findViewById(R.id.scroll_viewpager);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            strFatArrayList.add("ParentView " + i);
        }
    }

    private void initParentRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new RecyclerviewAdapter(strFatArrayList);
        scroll_Recyclerview.setLayoutManager(layoutManager);
        scroll_Recyclerview.setAdapter(adapter);
    }

    private void initViewPager() {
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(this,getPageFragment());
        scroll_Viewpager.setAdapter(viewPagerAdapter);
    }

    private List<Fragment> getPageFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new RecyclerViewFragment());
        list.add(new RecyclerViewFragment());
        list.add(new RecyclerViewFragment());
        return list;
    }

    private void initTablayout() {
        final String[] str = new String[]{"Tab 1","Tab 2","Tab 3"};
        new TabLayoutMediator(scroll_Tablayout,scroll_Viewpager,new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(str[position]);
            }
        }).attach();

        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            Thread.sleep(1000);
                            Message m = new Message();
                            m.what = 0;
                            handler.sendMessageDelayed(m,1000);
//                            swiperefreshlayout.setRefreshing(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

}
