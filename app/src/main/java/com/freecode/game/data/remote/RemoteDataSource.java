package com.freecode.game.data.remote;





import com.freecode.game.AdKeysResponse;
import com.freecode.game.data.DataSource;
import com.freecode.game.network.BaseResponse;
import com.freecode.game.network.IApi;
import com.freecode.game.network.NetworkCall;
import com.freecode.game.network.ServiceCallBack;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }





    @Override
    public void getAdKeys(ServiceCallBack myAppointmentPresenter, NetworkCall networkCall) {
        try{

            RequestBody api = RequestBody.create(MediaType.parse("text/plain"), "getapikeys");


            Call<BaseResponse<ArrayList<AdKeysResponse>>> responceCall = networkCall.getRetrofit(true, true).getAdKeys(api);
            networkCall.setServiceCallBack(myAppointmentPresenter);
            networkCall.setRequestTag(IApi.COMMON_TAG);
            responceCall.enqueue(networkCall.requestCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}


