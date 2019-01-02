package com.bwie.android.zhangyaozhong20190101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.bwie.android.zhangyaozhong20190101.adapter.MyAdapter;
import com.bwie.android.zhangyaozhong20190101.contract.GoodsContract;
import com.bwie.android.zhangyaozhong20190101.entity.GoodsEntity;
import com.bwie.android.zhangyaozhong20190101.presenter.GoodsPresenter;
import com.bwie.android.zhangyaozhong20190101.view.ParticularsActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GoodsContract.IgoodsView {
    @BindView(R.id.mgv)
    GridView gridView;
    private GoodsPresenter goodsPresenter;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();

        goodsPresenter.showGoods(params);
    }

    private void initView() {
        ButterKnife.bind(this);
        goodsPresenter = new GoodsPresenter(this);
    }

    @Override
    public void successShow(String result) {
        Gson gson = new Gson();
        GoodsEntity goodsEntity = gson.fromJson(result, GoodsEntity.class);
        final List<GoodsEntity.DataBean.TuijianBean.ListBeanX> list = goodsEntity.getData().getTuijian().getList();
        myAdapter = new MyAdapter(MainActivity.this, list);
        //设置适配器
        gridView.setAdapter(myAdapter);
        /**
         * 点击事件
         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> params = new HashMap<>();


                int pid = list.get(i).getPid();
                params.put("pid", pid + "");
                goodsPresenter.showGoods(params);
                startActivity(new Intent(MainActivity.this, ParticularsActivity.class));
            }
        });
        /**
         * 长按删除
         */
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                myAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, list.get(i).getTitle() + "删除成功", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void errorShow(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        goodsPresenter.destory();
    }
}
