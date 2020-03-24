package com.zijing.schoolonline;

import android.app.Application;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.ogh.library.OghApp;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zijing.schoolonline.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class App extends Application {

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILED = 200;
    public static final int STATUS_ERROR = 400;

    public static App myContext;
    public static String SP_NAME = "user";

    public static final String REGISTER_VALUE = "注册";
    public static final String FINDV_ALUE = "找回密码";

    public static final String AIR_RECHARGE_VALUE = "空调充值";
    public static final String ELECT_RECHARGE_VALUE = "电费充值";
    public static final String WATER_RECHARGE_VALUE = "水费充值";

    //    国家
    public static final String MOB_COUNTRY = "86";

    //    URL
//    public static final String SCHOOL_URL = "http://192.168.43.196:8080/School";//开发版
    public static final String SCHOOL_URL = "http://www.48days.top/School";//生产版

    //    用户
    public static final String USER_LOGIN_API = SCHOOL_URL + "/user/userLogin";
    public static final String USER_REGISTER_API = SCHOOL_URL + "/user/userRegister";
    public static final String USER_FIND_API = SCHOOL_URL + "/user/userFind";
    public static final String USER_LOGOUT_API = SCHOOL_URL + "/user/userLogOut";
    public static final String USER_ALTERPHONE_API = SCHOOL_URL + "/user/alterPhone";
    public static final String USER_GETUSER_API = SCHOOL_URL + "/user/getUser";
    public static final String USER_BINDINGROOM_API = SCHOOL_URL + "/user/bindingRoom";
    //修改用户头像
    public static final String USER_IMAGE_API = SCHOOL_URL + "/user/alterUserPicture";
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
        OghApp.init(this);
    }

    private void initOkhttp() {
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
