package com.example.weidushangcheng.network;

import com.example.weidushangcheng.api.Api;
import com.example.weidushangcheng.bean.BannerBean;
import com.example.weidushangcheng.bean.CarBean;
import com.example.weidushangcheng.bean.CircleBean;
import com.example.weidushangcheng.bean.HomeBean;
import com.example.weidushangcheng.bean.LoginBean;
import com.example.weidushangcheng.bean.MyCircleBean;
import com.example.weidushangcheng.bean.MyFootBean;
import com.example.weidushangcheng.bean.MyUpdateNameBean;
import com.example.weidushangcheng.bean.MyUpdatePwdBean;
import com.example.weidushangcheng.bean.RegBean;
import com.example.weidushangcheng.bean.SelectSelfBean;
import com.example.weidushangcheng.bean.ShopDetails;
import com.example.weidushangcheng.bean.ShopDetailsBean;
import com.example.weidushangcheng.bean.SouSuoBean;
import com.example.weidushangcheng.bean.TongBuCartBean;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IRequest {
    /*
     *  接口 bean类
     */

    //登录
    @FormUrlEncoded
    @POST(Api.Login)
    Observable<LoginBean> getLoginbean(@Field("phone") String acc, @Field("pwd") String pwd);

    //注册
    @FormUrlEncoded
    @POST(Api.REGISTER)
    Observable<RegBean> getRegbean(@Field("phone") String acc, @Field("pwd") String pwd);

    //首页---HOME
    @GET(Api.HOME)
    Observable<HomeBean> getHomebean();

    //首页---Banner
    @GET(Api.XBANNER_IMAGE)
    Observable<BannerBean> getHomeBannerbean();//首页---Banner

    //首页---搜索
    @GET(Api.GOODS_SOU)
    Observable<SouSuoBean> souGoods(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);

    /**
     * 商品详情
     *
     * @param commodityId
     * @return
     */
    @GET(Api.SHOP_DETAILS)
    Observable<ShopDetails> goodsDateils(@Query("commodityId") String commodityId);

    //同步
    @Multipart
    @PUT(Api.AddToCart)
    Observable<TongBuCartBean> tbCar(@Header("userId") String userId, @Header("sessionId") String sessionId, @Part("data") RequestBody data);

    //圈子---
    @GET(Api.CIRCLE)
    Observable<CircleBean> getCirclebean(@Query("page") int page, @Query("count") int count);

    //购物车---
    @GET(Api.CAR)
    Observable<CarBean> getCarBean(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //我的---修改昵称
    @PUT(Api.MY_UPDATENAME)
    Observable<MyUpdateNameBean> getMyUpdateName(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("nickName") String nickName);

    //我的---修改昵称
    @PUT(Api.MY_UPDATENAME)
    Observable<MyUpdatePwdBean> getMyUpdatePwd(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("oldPwd") String oldpwd, @Query("newPwd") String newPwd);

    //查询用户信息
    @GET(Api.SHOW_SELECT_ID_URL)
    Observable<SelectSelfBean> getSelf(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //我的圈子
    @GET(Api.SHOW_SELECT_CIRCLE_URL)
    Observable<MyCircleBean> getMyCircle(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //我的足迹
    @GET(Api.SHOW_SELECT_CIRCLE_URL)
    Observable<MyFootBean> getMyFoot(@Header("userId") int userId, @Header("sessionId") String sessionId);

}
