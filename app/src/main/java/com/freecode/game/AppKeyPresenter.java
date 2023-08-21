package com.freecode.game;

import android.content.Context;

import androidx.annotation.NonNull;

import com.freecode.game.data.DataSource;
import com.freecode.game.data.remote.RemoteDataSource;
import com.freecode.game.network.BaseResponse;
import com.freecode.game.network.IApi;
import com.freecode.game.network.NetworkCall;
import com.freecode.game.network.ServiceCallBack;

import java.util.ArrayList;

import retrofit2.Response;

public class AppKeyPresenter  implements AppKeyContact.Presenter, ServiceCallBack {

    private final DataSource loginDataSource;
    private final AppKeyContact.View mLoginView;
    private Context context;

    public AppKeyPresenter(@NonNull RemoteDataSource listDataSource, AppKeyContact.View loginFragment) {
        loginDataSource = listDataSource;
        mLoginView = loginFragment;
        mLoginView.setPresenter(this);

    }

    @Override
    public void onSuccess(int tag, Response<BaseResponse> baseResponse) {
        if (tag == IApi.COMMON_TAG) {
            BaseResponse response = baseResponse.body();
            if (response != null) {
                if (response.isResponseStatus()==true) {


                    ArrayList<AdKeysResponse> userData = (ArrayList<AdKeysResponse>) response.getResponsePacket();

                    mLoginView.adKeysResponse(userData);


                } else {
                    Util.showAlertBox(context, response.getResponseMessage(), null);
                }

            }
        }

    }

    @Override
    public void getAdKeys(Context context) {
        NetworkCall networkCall = new NetworkCall(context);
        this.context = context;
        loginDataSource.getAdKeys(  this, networkCall);
    }



    @Override
    public void onFail(int requestTag, Throwable t) {

    }
}
