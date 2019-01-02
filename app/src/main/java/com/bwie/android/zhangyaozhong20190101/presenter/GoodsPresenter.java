package com.bwie.android.zhangyaozhong20190101.presenter;

import com.bwie.android.zhangyaozhong20190101.contract.GoodsContract;
import com.bwie.android.zhangyaozhong20190101.model.GoodsModel;
import com.bwie.android.zhangyaozhong20190101.model.ShowCallback;

import java.util.HashMap;

public class GoodsPresenter extends GoodsContract.GoodsPresenter {
    private GoodsContract.IgoodsView igoodsView;
    private GoodsModel goodsModel;

    public GoodsPresenter(GoodsContract.IgoodsView igoodsView) {
        this.igoodsView = igoodsView;
        goodsModel = new GoodsModel();
    }

    @Override
    public void showGoods(HashMap<String, String> params) {
        if (goodsModel != null) {
            goodsModel.showGoods(params, new ShowCallback() {
                @Override
                public void success(String result) {
                    if (igoodsView != null) {
                        igoodsView.successShow(result);
                    }
                }

                @Override
                public void error(String msg) {
                    if (igoodsView != null) {
                        igoodsView.errorShow(msg);
                    }
                }
            });
        }
    }

    public void destory() {
        if (igoodsView != null) {
            igoodsView = null;
        }
    }
}
