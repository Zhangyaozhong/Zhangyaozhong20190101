package com.bwie.android.zhangyaozhong20190101.contract;

import com.bwie.android.zhangyaozhong20190101.model.ShowCallback;

import java.util.HashMap;

public interface ParticularsContract {
    /**
     * p层的抽象方法
     */
     abstract class ParticularsPresenter{
        public abstract void showGoods(HashMap<String, String> params);
     };

    /**
     * M层的回调接口
     */
    interface IparticularsModel{
         void showGoods(HashMap<String, String> params, ShowCallback callback);
     }
    /**
     * V层的回调接口
     */
    interface IparticularsView{
        void successShow(String result);
        void errorShow(String msg);
    }
}
