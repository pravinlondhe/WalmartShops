package com.example.pravin.walmartshops;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.pravin.walmartshops.model.ShopListResponse;
import com.example.pravin.walmartshops.model.network.NetworkService;
import com.example.pravin.walmartshops.model.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.pravin.walmartshops.BuildConfig.API_KEY;
import static com.example.pravin.walmartshops.BuildConfig.ZIP_CODE;


public class ShopListService extends Service {

    private final static String mBaseUrl = "http://api.walmartlabs.com";
    private final static String TAG = ShopListService.class.getSimpleName();
    private List<ShopListResponse> mList = new ArrayList<>();

    private Binder binder = new MyBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "Started command");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Service binded");
        return binder;
    }

    public List<ShopListResponse> getShopList() {
        NetworkService service = RetrofitClient.getRetrofitClient(mBaseUrl).create(NetworkService.class);
        service.getShopList(API_KEY, ZIP_CODE, "json")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ArrayList<ShopListResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onNext(ArrayList<ShopListResponse> shopListResponses) {
                        Log.d(TAG, "onNext:" + shopListResponses);
                        mList.addAll(shopListResponses);
                    }
                });
        return mList;
    }

    public class MyBinder extends Binder {
        ShopListService getService() {
            return ShopListService.this;
        }
    }
}
