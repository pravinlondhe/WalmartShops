package com.example.pravin.walmartshops;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pravin.walmartshops.model.ShopListResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private List<ShopListResponse> mShopList = new ArrayList<>();
    private ShopListAdapter mAdapter;
    private TextView mStatusTextView;
    private ShopListService mService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ((ShopListService.MyBinder) iBinder).getService();
            Log.d(TAG, "Service connected");
            mShopList = mService.getShopList();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
            Log.d(TAG, "Service disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, ShopListService.class);
        startService(intent);
        ListView shopsListView = findViewById(R.id.lv_shop_list);
        mStatusTextView = findViewById(R.id.tv_status_msg);
        mAdapter = new ShopListAdapter(this, mShopList);
        shopsListView.setAdapter(mAdapter);
        if (mShopList.size() > 0) {
            mStatusTextView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, ShopListService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mShopList.size() > 0) {
                    updateShopList();
                    Log.d(TAG, "updated list");
                } else {
                    Log.d(TAG, "display error msg");
                    displayErrorMessage();
                }
            }
        }, 3000);
    }

    public void updateShopList() {
        mStatusTextView.setVisibility(View.GONE);
        Log.d(TAG, "List of size:" + mShopList.size());
        mAdapter.dataUpdated(mShopList);
    }

    public void displayErrorMessage() {
        mStatusTextView.setText(getString(R.string.toast_error_msg));
        Toast.makeText(this, getString(R.string.toast_error_msg), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
