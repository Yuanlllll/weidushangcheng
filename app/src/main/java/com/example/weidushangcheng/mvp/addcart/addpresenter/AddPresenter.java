package com.example.weidushangcheng.mvp.addcart.addpresenter;

import com.example.weidushangcheng.DetailsActivity;
import com.example.weidushangcheng.mvp.addcart.model.AddModel;
import com.example.weidushangcheng.mvp.addcart.model.IAddModel;
import okhttp3.RequestBody;

public class AddPresenter implements IAddPresenter {


    private DetailsActivity detailsActivity;
    private final AddModel addModel;

    public AddPresenter(DetailsActivity detailsActivity) {
        this.detailsActivity = detailsActivity;
        addModel = new AddModel();
    }

    @Override
    public void getTbPresenter(String userId, String sessionId, RequestBody data) {
        addModel.getTbData(userId, sessionId, data, new IAddModel.ModelInterface() {
            @Override
            public void onSuccess(Object o) {
                detailsActivity.Success(o);
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }
}
