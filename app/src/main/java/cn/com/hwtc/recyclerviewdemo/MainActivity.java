package cn.com.hwtc.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.OnItemClickCallback, MyRecyclerViewAdapter.OnItemLongClickCallback {
    private static final String TAG = "RecyclerViewDemo " + MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        List<String> list = initData();
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(list);
        myRecyclerViewAdapter.setOnItemClickCallback(this);
        myRecyclerViewAdapter.setOnItemLongClickCallback(this);
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        //设置动画为默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置item分隔线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private List<String> initData() {
        if (null == mList) mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add("item" + i);
        }
        return mList;
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void onItemClick(View view, int position, String itemString) {
        Log.d(TAG, "onItemClick:" + position + "---" + itemString);
    }

    @Override
    public void onItemLongClick(View view, int position, String itemString) {
        Log.d(TAG, "onItemLongClick:" + position + "---" + itemString);
    }
}
