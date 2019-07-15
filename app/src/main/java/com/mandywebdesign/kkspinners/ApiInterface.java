package com.mandywebdesign.kkspinners;

import com.mandywebdesign.kkspinners.RetroModel.ContractApi;
import com.mandywebdesign.kkspinners.RetroModel.GetDocumentApi;
import com.mandywebdesign.kkspinners.RetroModel.LoginApi;
import com.mandywebdesign.kkspinners.RetroModel.OrderApi;
import com.mandywebdesign.kkspinners.RetroModel.PendingOrderApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    //login api
    @POST("login")
    Call<LoginApi> loginapi(
            @Query("email") String email,
            @Query("password") String password
    );

    // get all user contract here
    @GET("mycontracts")
    Call<ContractApi> contractApi(
            @Header("Authorization") String Toekn,
            @Header("Accept") String accpet
    );

    // get the all order list in the contract
    @GET("myorderlist")
    Call<OrderApi> orderApi(
            @Header("Authorization") String Toekn,
            @Query("id") String id
    );

    // get the pending order list
    @GET("mypendingorders")
    Call<PendingOrderApi> getPendingApi(
            @Header("Authorization") String Toekn,
            @Query("id") String id
    );

    // get the orderlist documents
    @GET("mydoclist")
    Call<GetDocumentApi> getDocumentApi(@Header("Authorization") String Toekn,
                                        @Query("id") String id);

}
