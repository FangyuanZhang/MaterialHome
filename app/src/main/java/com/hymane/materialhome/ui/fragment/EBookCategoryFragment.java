package com.hymane.materialhome.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hymane.materialhome.R;
import com.hymane.materialhome.api.presenter.impl.EBookPresenterImpl;
import com.hymane.materialhome.api.view.IEBookListView;
import com.hymane.materialhome.ui.adapter.EBookCategoryAdapter;
import com.hymane.materialhome.ui.widget.RecyclerViewDecoration.SpacesItemDecoration;

import butterknife.BindView;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/1/12
 * Description:
 */
public class EBookCategoryFragment extends BaseFragment implements IEBookListView {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private EBookCategoryAdapter mCategoryAdapter;
    private EBookPresenterImpl mEBookPresenter;

    public static EBookCategoryFragment newInstance() {

        Bundle args = new Bundle();

        EBookCategoryFragment fragment = new EBookCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.category_fragment, null, false);
        mEBookPresenter = new EBookPresenterImpl(this);
    }

    @Override
    protected void initEvents() {
        int spanCount = getResources().getInteger(R.integer.category_span_count);
        //添加装饰器
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(20));
        //设置布局管理器
        mLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return spanCount;
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置adapter
        mCategoryAdapter = new EBookCategoryAdapter();
        mRecyclerView.setAdapter(mCategoryAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initData(boolean isSavedNull) {
        if (isSavedNull) {
            mEBookPresenter.getCategoryList();
        }
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void refreshData(Object result) {

    }
}