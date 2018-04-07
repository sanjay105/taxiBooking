package com.dbs.cabservices.cabservices.wsUtils;

/**
 * Created by user on 30/11/16.
 */

public interface IResponseCallBack {
    public void onSuccess(int requestCode, String response);

    public void onError(int requestCode, String error);
}
