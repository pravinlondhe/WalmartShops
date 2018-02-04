package com.example.pravin.walmartshops;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pravin.walmartshops.model.ShopListResponse;

import java.util.ArrayList;
import java.util.List;


public class ShopListAdapter extends BaseAdapter {

    private final static String TAG = ShopListAdapter.class.getSimpleName();
    private List<ShopListResponse> mShopList = new ArrayList<>();
    private Context mContext;

    public ShopListAdapter(Context context, List<ShopListResponse> list) {
        this.mShopList = new ArrayList<>(list);
        this.mContext = context;
    }

    public void dataUpdated(List<ShopListResponse> list) {
        mShopList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mShopList.size();
    }

    @Override
    public Object getItem(int item) {
        return item;
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        StudentListViewHolder viewHolder;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_shop, viewGroup, false);
            viewHolder = new StudentListViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (StudentListViewHolder) view.getTag();
        }
        viewHolder.setData(mShopList.get(position));
        return view;
    }

    private static class StudentListViewHolder {
        private TextView mStoreNameTv;
        private TextView mStoreAddressTv;
        private TextView mStorePhoneTv;

        StudentListViewHolder(View view) {
            mStoreNameTv = view.findViewById(R.id.tv_store_name);
            mStoreAddressTv = view.findViewById(R.id.tv_store_address);
            mStorePhoneTv = view.findViewById(R.id.tv_store_phone_no);
        }

        public void setData(ShopListResponse data) {
            String name = data.getNo() + "-" + data.getName();
            String address = data.getStreetAddress() + ",\n" + data.getCity()
                    + ",\n" + data.getCountry() + "-" + data.getZip();
            String phone = data.getPhoneNumber();
            Log.d(TAG, "Name:" + name + " Address:" + address + " Phone:" + phone);
            mStoreNameTv.setText(name);
            mStoreAddressTv.setText(address);
            mStorePhoneTv.setText(phone);
        }
    }
}
