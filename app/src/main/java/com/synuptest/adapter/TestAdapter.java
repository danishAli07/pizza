package com.synuptest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.synuptest.R;
import com.synuptest.model.ListItemModel;
import com.synuptest.view.MainActivity;

import java.util.List;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ChildViewHolder> {


    private final Context mContext;
    private final List<ListItemModel> mVariations;
    private final MainActivity mActivity;
    private final boolean mIsSameParentClicked;
    private String mDimId;


    public TestAdapter(Context tContext, List<ListItemModel> mList, String s,boolean isSameParent) {
        this.mContext = tContext;
        this.mVariations = mList;
        this.mDimId = s;
        this.mIsSameParentClicked = isSameParent;
        mActivity = (MainActivity) tContext;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_layout, viewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildViewHolder viewHolder, final int i) {
        if (mVariations.get(i).isParent()){
            viewHolder.mParentTitle.setText(mVariations.get(i).getName());
            viewHolder.mMainCardView.setVisibility(View.GONE);
            viewHolder.mParentTitle.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mMainCardView.setVisibility(View.VISIBLE);
            viewHolder.mParentTitle.setVisibility(View.GONE);
            viewHolder.mName.setText(mVariations.get(i).getName());
            viewHolder.mPrice.setText("Rs : " + String.valueOf(mVariations.get(i).getPrice()));
            if (mVariations.get(i).getInStock() == 1) {
                viewHolder.mStock.setText("In Stock : Yes");
            } else {
                viewHolder.mStock.setText("In Stock : No");
            }
            if (mDimId != null && mDimId.equals(mVariations.get(i).getChildId())) {
                viewHolder.mMainCardView.setAlpha(0.3f);
                viewHolder.mMainCardView.setEnabled(false);
                viewHolder.mMainCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
            } else {
                if (mIsSameParentClicked) {
                    viewHolder.mMainCardView.setAlpha(1f);
                    viewHolder.mMainCardView.setEnabled(true);
                }
            }
            if (mVariations.get(i).isSelected()){
                viewHolder.mMainCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.golden));
            } else {
                viewHolder.mMainCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
            }
            viewHolder.mMainCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.refreshListData(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mVariations.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder{

        protected TextView mName;
        protected TextView mPrice;
        protected TextView mStock;
        protected RelativeLayout mMainRL;
        protected CardView mMainCardView;
        protected TextView mParentTitle;


        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.c_name_tv);
            mPrice = itemView.findViewById(R.id.c_price);
            mStock = itemView.findViewById(R.id.c_stock);
            mMainRL = itemView.findViewById(R.id.c_main_rl);
            mMainCardView = itemView.findViewById(R.id.c_main_cv);
            mParentTitle = itemView.findViewById(R.id.p_title_tv);
        }
    }
}
