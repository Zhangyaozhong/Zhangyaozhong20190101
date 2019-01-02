package com.bwie.android.zhangyaozhong20190101.model;

import com.bwie.android.zhangyaozhong20190101.api.GoodsApi;
import com.bwie.android.zhangyaozhong20190101.contract.GoodsContract;
import com.bwie.android.zhangyaozhong20190101.utils.OkhttpUtil;

import java.util.HashMap;

public class ParticularsModel implements GoodsContract.IgoodsModel {
    @Override
    public void showGoods(HashMap<String, String> params, final ShowCallback callback) {
        OkhttpUtil.getInstance().doPost(GoodsApi.PRODUCT_URL, params, new ShowCallback() {
            @Override
            public void success(String result) {
                if (callback!=null){
                    callback.success(result);
                }
            }

            @Override
            public void error(String msg) {
                if (callback!=null){
                    callback.success(msg);
                }
            }
        });
    }
}
