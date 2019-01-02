package com.bwie.android.zhangyaozhong20190101.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.android.zhangyaozhong20190101.R;
import com.bwie.android.zhangyaozhong20190101.entity.GoodsEntity;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsEntity.DataBean.TuijianBean.ListBeanX> list;

    public MyAdapter(Context context, List<GoodsEntity.DataBean.TuijianBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.googs_item_layout, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.iv_goods);
            holder.textView1 = view.findViewById(R.id.tv_title);
            holder.textView2 = view.findViewById(R.id.tv_peice);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        String images = list.get(position).getImages();
        String[] imgArr = images.split("!");
        if (imgArr != null && imgArr.length > 0) {
            Glide.with(context)
                    .load(imgArr[0])
                    .into(holder.imageView);
        }
        holder.textView1.setText(list.get(position).getTitle());
        holder.textView2.setText(list.get(position).getPrice() + "");

        return view;
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView textView1, textView2;
    }
}
