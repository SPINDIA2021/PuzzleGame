package com.freecode.game.data;


import com.freecode.game.network.NetworkCall;
import com.freecode.game.network.ServiceCallBack;

public interface DataSource {


    void getAdKeys( ServiceCallBack myAppointmentPresenter, NetworkCall networkCall);


}

