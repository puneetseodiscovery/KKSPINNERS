package com.example.kkspinners;

import com.example.kkspinners.RetroModel.ContractApi;
import com.example.kkspinners.RetroModel.GetDocumentApi;
import com.example.kkspinners.RetroModel.LoginApi;
import com.example.kkspinners.RetroModel.OrderApi;
import com.example.kkspinners.RetroModel.PendingOrderApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("login")
    Call<LoginApi> loginapi(
            @Query("email") String email,
            @Query("password") String password
    );

    @GET("mycontracts")
    Call<ContractApi> contractApi(
            @Header("Authorization") String Toekn,
            @Header("Accept") String accpet
    );

    @GET("myorderlist")
    Call<OrderApi> orderApi(
            @Header("Authorization") String Toekn,
            @Query("id") String id
    );

    @GET("mypendingorders")
    Call<PendingOrderApi> getPendingApi(
            @Header("Authorization") String Toekn,
            @Query("id") String id
    );

    @GET("mydoclist")
    Call<GetDocumentApi> getDocumentApi(@Header("Authorization") String Toekn,
                                        @Query("id") String id);

}
