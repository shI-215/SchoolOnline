package com.zijing.schoolonline;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zijing.schoolonline.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class ApplicationParam extends Application {

    public static ApplicationParam myContext;
    public static String SP_NAME = "user";

    public static final String REGISTER_VALUE = "注册";
    public static final String FINDV_ALUE = "找回密码";

    public static final String AIR_RECHARGE_VALUE = "空调充值";
    public static final String ELECT_RECHARGE_VALUE = "电费充值";
    public static final String WATER_RECHARGE_VALUE = "水费充值";

    public static final String LOGIN_ERROR = "登录失效，请重新登陆";

    //    国家
    public static final String MOB_COUNTRY = "86";

    //    用户手机
    public static String USER_PHONE = "";
    //    宿舍信息
    public static String ROOM_INFORMATION = "";

    //    URL
    public static final String SCHOOL_URL = "http://192.168.43.196:8080/School";//开发版
    //    public static final String SCHOOL_URL = "http://121.41.88.233:20200/School";//生产版
    //    用户
    public static final String USER_LOGIN_API = SCHOOL_URL + "/user/userLogin";
    public static final String USER_REGISTER_API = SCHOOL_URL + "/user/userRegister";
    public static final String USER_FIND_API = SCHOOL_URL + "/user/userFind";
    public static final String USER_LOGOUT_API = SCHOOL_URL + "/user/userLogOut";
    public static final String USER_GETUSER_API = SCHOOL_URL + "/user/getUser";
    public static final String USER_BINDINGROOM_API = SCHOOL_URL + "/user/bindingRoom";
    //    宿舍
    public static final String ROOM_GETAREA_API = SCHOOL_URL + "/room/selectRoomArea";
    public static final String ROOM_GETDOORPLATE_API = SCHOOL_URL + "/room/selectRoomDoorplate";
    public static final String ROOM_GETROOMINFO_API = SCHOOL_URL + "/room/selectRoomInfo";
    //水费
    public static final String WATER_RECHARGE_API = SCHOOL_URL + "/water/rechargeWaterMoney";
    public static final String WATER_GETMONEY_API = SCHOOL_URL + "/water/getWaterBalanceEnquiry";
    //电费
    public static final String ELECT_RECHARGE_API = SCHOOL_URL + "/elect/rechargeElectMoney";
    public static final String ELECT_GETMONEY_API = SCHOOL_URL + "/elect/getAirBalanceEnquiry";
    //空调费
    public static final String AIR_RECHARGE_API = SCHOOL_URL + "/air/rechargeAirMoney";
    public static final String AIR_GETMONEY_API = SCHOOL_URL + "/air/getAirBalanceEnquiry";
    //    用户所有记录查询
    public static final String GETUSERALLRECHARGE_API = SCHOOL_URL + "/recharge/selectUserAllRecharge";

    @Override
    public void onCreate() {
        super.onCreate();
        myContext = this;
        ToastUtil.init(this);
        initOkhttp();
    }

    private void initOkhttp() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
