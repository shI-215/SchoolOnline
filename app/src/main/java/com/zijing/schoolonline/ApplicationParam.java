package com.zijing.schoolonline;

import android.app.Application;

public class ApplicationParam extends Application {

    public static final String REGISTER_VALUE = "注册";
    public static final String FINDV_ALUE = "找回密码";

    public static final String AIR_RECHARGE_VALUE = "空调充值";
    public static final String ELECT_RECHARGE_VALUE = "电费充值";
    public static final String WATER_RECHARGE_VALUE = "水费充值";

    public static final String AIR_RECORD_VALUE = "空调充值记录";
    public static final String ELECT_RECORD_VALUE = "电费充值记录";
    public static final String WATER_RECORD_VALUE = "水费充值记录";

    //    URL
    public static final String SCHOOL_URL = "http://192.168.43.196:8080/School";
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
    //水费
    public static final String WATER_RECHARGE_API = SCHOOL_URL + "/water/rechargeWaterMoney";
    //电费
    public static final String ELECT_RECHARGE_API = SCHOOL_URL + "/elect/rechargeElectMoney";
    //空调费
    public static final String AIR_RECHARGE_API = SCHOOL_URL + "/air/rechargeAirMoney";
}
