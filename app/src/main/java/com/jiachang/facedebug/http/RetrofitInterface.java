package com.jiachang.facedebug.http;

import com.alibaba.fastjson.JSON;
import com.jiachang.facedebug.bean.sendFileBean;
import com.jiachang.facedebug.bean.setConfigBean;
import com.jiachang.facedebug.bean.setNetInfoBean;
import com.jiachang.facedebug.bean.setPersonBean;

import java.io.File;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Mickey.Ma
 * @date 2020-01-07
 * @description
 */
public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/person/create")
    Call<setPersonBean> setCreatePerson(
            @Field("pass") String pass,
            @Field("person") String person
    );

    @FormUrlEncoded
    @POST("/uploadImg")
    Call<sendFileBean> sendPhoto(
            @Field("file") File file
    );

    @FormUrlEncoded
    @POST("/setNetInfo")
    Call<Response> setNetInfo(
            @Field("pass") String pass,
            @Field("isDHCPMod") int isDHCPMod,
            @Field("ip") String ip,
            @Field("gateway") String gateway,
            @Field("subnetMask")String subnetMask,
            @Field("DNS")String DNS
    );

    @FormUrlEncoded
    @POST("/restartDevice")
    Call<Response> restartDevice(@Field("pass")String pass);

    @FormUrlEncoded
    @POST("/device/reset")
    Call<Response> reset(
            @Field("pass")String pass,
            @Field("delete")Boolean delete
    );

    @FormUrlEncoded
    @POST("/setPassWord")
    Call<Response> setPassWord(
            @Field("oldPass")String oldPass,
            @Field("newPass")String newPass
    );

    @FormUrlEncoded
    @POST("/changeLogo")
    Call<Response> changeLogo(
            @Field("pass") String pass,
            @Field("imgBase64") String imgBase64
    );

    @FormUrlEncoded
    @POST("/setConfig")
    Call<setConfigBean> setConfig(
            @Field("pass") String pass,
            @Field("config") String config
    );
}
