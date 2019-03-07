package com.example.weidushangcheng.mvp.details.presenter;

import android.util.Log;

import com.example.weidushangcheng.DetailsActivity;
import com.example.weidushangcheng.mvp.details.model.DetailsModel;
import com.example.weidushangcheng.mvp.details.model.IDetailsModel;

public class DetailsPresenter implements IDetailsPresenter {
    DetailsActivity detailsActivity;
    private final DetailsModel detailsModel;

    public DetailsPresenter(DetailsActivity detailsActivity) {
        this.detailsActivity = detailsActivity;
        detailsModel = new DetailsModel();
    }


    @Override
    public void getPresenterData(String id) {
        detailsModel.getData(id, new IDetailsModel.ModelInterface() {
            @Override
            public void loadSuccess(Object o) {
                detailsActivity.getDetails(o);
            }

            @Override
            public void loadFaild(Throwable throwable) {

            }
        });
    }
}
