package com.example.pravin.walmartshops.model.network;

import com.example.pravin.walmartshops.model.ShopListResponse;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface NetworkService {

    @GET("/v1/stores")
    Observable<ArrayList<ShopListResponse>> getShopList(@Query("apiKey") String apiKey,
                                                        @Query("zip") String zipCode,
                                                        @Query("format") String format);
}

