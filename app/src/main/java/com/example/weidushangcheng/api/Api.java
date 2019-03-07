package com.example.weidushangcheng.api;

public class Api {
    public static final String BOTH = "http://172.17.8.100/";
    //登录
    public static final String Login = "small/user/v1/login";
    //注册
    public static final String REGISTER = "small/user/v1/register";
    //轮播
    public static final String XBANNER_IMAGE = "small/commodity/v1/bannerShow";
    //首页数据展示
    public static final String HOME = "small/commodity/v1/commodityList";
    //圈子数据展示
    public static final String CIRCLE = "small/circle/v1/findCircleList";
    //查询购物车
    public static final String CAR = "small/order/verify/v1/findShoppingCart";
    //首页搜索
    public static final String GOODS_SOU = "small/commodity/v1/findCommodityByKeyword";
    //首页详情
    public static final String SHOP_DETAILS = "small/commodity/v1/findCommodityDetailsById";
    //同步 添加购物车
    public static final String AddToCart = "small/order/verify/v1/syncShoppingCart";
    //我的修改昵称
    public static final String MY_UPDATENAME = "small/user/verify/v1/modifyUserNick";
    //我的修改密码modifyUserPwd
    public static final String MY_UPDATEPWD = "small/user/verify/v1/modifyUserPwd";
    //查询用户的接口
    public static final String SHOW_SELECT_ID_URL = "user/verify/v1/getUserById";
    //展示我的圈子的接口
    public static final String SHOW_SELECT_CIRCLE_URL = "circle/verify/v1/findMyCircleById?page=%d&count=%d";
    //发布圈子的接口
    public static final String SHOW_NEXT_CIRCLE_URL = "circle/verify/v1/releaseCircle";
    //删除圈子的接口
    public static final String SHOW_DELETE_CIRCLE_URL = "circle/verify/v1/deleteCircle?circleId=%d";
    //我的足迹的接口
    public static final String SHOW_FOOT_SHOP_URL = "commodity/verify/v1/browseList?page=%d&count=%d";
}
