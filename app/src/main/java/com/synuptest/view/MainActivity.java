package com.synuptest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.synuptest.R;
import com.synuptest.adapter.TestAdapter;
import com.synuptest.model.ExcludeItemModel;
import com.synuptest.model.ListItemModel;
import com.synuptest.model.PizzaModel;
import com.synuptest.presenter.Presenter;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;


public class MainActivity extends AppCompatActivity {

    private Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TestAdapter mTestAdapter;
    private List<ListItemModel> mList;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.p_recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.GONE);
        mPresenter = new Presenter(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        callApi();
    }

    private void callApi() {
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter.callApiRequest();
    }

    public void onResponse(List<ListItemModel> mList, List<ExcludeItemModel> mExcList) {
        mProgressBar.setVisibility(View.GONE);
        loadData(mList, mExcList);
    }

    public void onFailure(Call<PizzaModel> call) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this,"Server error occur !",Toast.LENGTH_SHORT).show();
    }

    private void loadData(List<ListItemModel> list, List<ExcludeItemModel> mExcList) {
        mList = list;
        if (mList.size() > 0){
            mTestAdapter = new TestAdapter(this,mList, null,true);
            mRecyclerView.setAdapter(mTestAdapter);
        }
    }
    public void refreshListData(int i){
        mPresenter.refreshListData(i);
    }

    public void refreshListDataResponse(int i, List<ListItemModel> mList, String mDimId, boolean mIsSameParent) {
        mTestAdapter = new TestAdapter(this,mList,mDimId,mIsSameParent);
        mRecyclerView.setAdapter(mTestAdapter);
        mRecyclerView.smoothScrollToPosition(i);
        mTestAdapter.notifyDataSetChanged();
    }
}
