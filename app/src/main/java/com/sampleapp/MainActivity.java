package com.sampleapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sampleapp.adapter.FlowerAdapter;
import com.sampleapp.application.MyApplication;
import com.sampleapp.model.Flowers;
import com.sampleapp.presenters.FlowerPresenter;
import com.sampleapp.service.ApiService;
import com.sampleapp.service.FlowerApiInterface;
import com.sampleapp.urils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements FlowerApiInterface {

    @Inject
    ApiService apiService;

    private FlowerPresenter androidPresenter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private FlowerAdapter mAdapter;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getmApiComponent().bind(this);
        configViews();

        androidPresenter = new FlowerPresenter(this);
        androidPresenter.onCreate();
        if (Utils.isNetworkAvailable(MainActivity.this)) {
            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setIndeterminate(true);
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setMessage("Please wait...");
            if (!isFinishing()) {
                mDialog.show();
            }
            androidPresenter.fetchData();
        } else {
            Toast.makeText(MainActivity.this, "App run with network connection only", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void configViews() {
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FlowerAdapter(this, getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        androidPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        androidPresenter.onPause();
        if (!isFinishing() && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onCompleted() {
        if (!isFinishing() && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        if (!isFinishing() && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiData(List<Flowers> androidModelList) {
        mAdapter.addFlowers(androidModelList);
    }

    @Override
    public Observable<List<Flowers>> getData() {
        return apiService.getApiData();
    }
}
