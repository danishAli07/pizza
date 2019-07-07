package com.synuptest.presenter;

import android.util.Log;

import com.synuptest.api.Api;
import com.synuptest.api.ApiClients;
import com.synuptest.model.ExcludeItemModel;
import com.synuptest.model.ExcludeList;
import com.synuptest.model.ListItemModel;
import com.synuptest.model.PizzaModel;
import com.synuptest.model.Variants;
import com.synuptest.model.Variation;
import com.synuptest.view.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    private final MainActivity mActivityContext;
    private final HashMap<String, Integer> mHashMap;
    private List<ListItemModel> mList;
    private List<ExcludeItemModel> mExcList;
    private String mDimId = null;
    private boolean mIsSameParent = false;
    private int mLastSelectPos = 1;

    public Presenter(MainActivity mainActivity) {
        this.mActivityContext = mainActivity;
        mHashMap = new HashMap<String,Integer>();
    }

    public void callApiRequest(){
        Api api = ApiClients.getApiClients().create(Api.class);
        Call<PizzaModel> call = api.callApi();
        call.enqueue(new Callback<PizzaModel>() {
            @Override
            public void onResponse(Call<PizzaModel> call, Response<PizzaModel> response) {
                Log.d("danny","onResponse  called");
                sendDataToView(response.body().getVariants());
            }

            @Override
            public void onFailure(Call<PizzaModel> call, Throwable t) {
                Log.d("danny","onFailure  call:"+call.toString());
                mActivityContext.onFailure(call);
            }
        });
    }

    private void sendDataToView(Variants variants) {
        mList = new ArrayList<>();
        mExcList = new ArrayList<>();
        for(int i = 0; i < variants.getVariantGroups().size(); i++) {
            ListItemModel testModel = new ListItemModel();
            testModel.setName(variants.getVariantGroups().get(i).getName());
            testModel.setParent(true);
            testModel.setPrice(0);
            testModel.setInStock(0);
            testModel.setSelected(false);
            testModel.setParentName("none");
            testModel.setParentId(variants.getVariantGroups().get(i).getGroupId());
            testModel.setChildId("none");
            testModel.setDisableId("none");
            mList.add(testModel);
            for (int j = 0; j < variants.getVariantGroups().get(i).getVariations().size(); j++){
                Variation model = variants.getVariantGroups().get(i).getVariations().get(j);
                ListItemModel testModel1 = new ListItemModel();
                testModel1.setName(model.getName());
                testModel1.setParent(false);
                testModel1.setPrice(model.getPrice());
                testModel1.setInStock(model.getInStock());
                testModel1.setSelected(false);
                testModel1.setParentName(variants.getVariantGroups().get(i).getName());
                testModel1.setParentId(variants.getVariantGroups().get(i).getGroupId());
                testModel1.setChildId(model.getId());
                testModel1.setDisableId("none");
                mList.add(testModel1);
            }
        }
        for(int i = 0; i<variants.getExcludeList().size();i++){
            List<ExcludeList> model = variants.getExcludeList().get(i);
            String childId = null,subChildId = null;
            ExcludeItemModel excludeItemModel = new ExcludeItemModel();
            for(int j = 0; j< model.size();j++) {
                if (j == 0) {
                    childId = model.get(j).getVariationId();
                } else {
                    subChildId = model.get(j).getVariationId();
                }
            }
            excludeItemModel.setChildId(childId);
            excludeItemModel.setSubChildId(subChildId);
            mExcList.add(excludeItemModel);
        }
        mActivityContext.onResponse(mList, mExcList);
    }

    public void refreshListData(int i){
        if(!mList.get(i).isParent()) {
            if (mHashMap.size() > 0 && mHashMap.containsKey(mList.get(i).getParentName())){
                mList.get(i).setSelected(true);
                mList.get(mHashMap.get(mList.get(i).getParentName())).setSelected(false);
                mHashMap.put(mList.get(i).getParentName(), i);
            } else {
                mHashMap.put(mList.get(i).getParentName(), i);
                mList.get(i).setSelected(true);
            }
        } else {
            mList.get(i).setSelected(false);
        }
        if(mList.get(mLastSelectPos).getParentName().equals(mList.get(i).getParentName())) {
            mIsSameParent = true;
            mLastSelectPos = i;
        } else {
            mIsSameParent = false;
        }
        mDimId = setDim(i);
        mActivityContext.refreshListDataResponse(i,mList,mDimId,mIsSameParent);
    }

    public String setDim(int i){
        String id = null;
        for(int k=0; k<mExcList.size();k++){
            if(mExcList.get(k).getChildId().equals(mList.get(i).getChildId())){
                id =  mExcList.get(k).getSubChildId();
                return id;
            }
        }
        if (mIsSameParent){
            return id;
        } else {
            return mDimId;
        }

    }
}
