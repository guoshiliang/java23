package com.kaishengit.weixin;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.kaishengit.exception.WeiXinException;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.Cache;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/28 0028.
 */
@Component
public class WeiXinUtil {

    private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
    private static final String CREATE_DEPT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token={0}";
    private static final String DELETE_DEPT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token={0}&id={1}";

    public static final String TOKEN_TYPE_ADDRESS_BOOK ="addressBook";
    public static final String TOKEN_TYPE_NORMAL ="";


    private static Map<Integer,String> errorCodeMap = new HashMap<>();

    @Value("${wx.cropId}")
    private String cropId;
    @Value("${wx.secret}")
    private String secret;
    @Value("${wx.address.secret}")
    private String addressBookSecret;


    /**
     * Guava缓存 将AccessToken缓存7200秒
     */
    private LoadingCache<String,String> accessTokenCache = CacheBuilder.newBuilder()
        .expireAfterAccess(7200, TimeUnit.SECONDS)
        .build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                String url;
                if(TOKEN_TYPE_ADDRESS_BOOK.equals(s)){
                    url = MessageFormat.format(ACCESS_TOKEN_URL,cropId,addressBookSecret);
                }else {
                    url = MessageFormat.format(ACCESS_TOKEN_URL,cropId,secret);
                }

                String result = sendGetRequest(url);

                Map<String,Object> map = JSON.parseObject(result,HashMap.class);
                Integer errorCode = Integer.valueOf(map.get("errcode;").toString());

                if(errorCode.equals(0)){
                    return map.get("access_token").toString();
                }else{
                    throw new WeiXinException("获取AccessToken异常，错误码:" + errorCode + " 错误原因：" + errorCodeMap.get(errorCode));
                }

            }
        });

    /**
     * 获取企业微信的AccessToken
     * @return
     */
    public String getAccessToken(String type){
        try {
            return accessTokenCache.get(type);
        } catch (ExecutionException e) {
            throw new WeiXinException();
        }
    }

    /**
     * 创建微信部门
     * @param deptId 部门ID
     * @param parentId 部门的父ID
     * @param name 部门名称
     */
    public void createDept(int deptId,int parentId,String name){
        String url = MessageFormat.format(CREATE_DEPT_URL,getAccessToken(TOKEN_TYPE_ADDRESS_BOOK));

        Map<String,Object> param = Maps.newHashMap();
        param.put("name",name);
        param.put("parentid",parentId);
        param.put("id",deptId);

        String result = sendPostRequest(url,param);
        Map<String,Object> map = JSON.parseObject(result,HashMap.class);
        Integer errorCode = Integer.valueOf(map.get("errcode").toString());
        if(!errorCode.equals(0)){
            throw new WeiXinException("创建部门异常，错误码:" + errorCode + " 错误原因：" + errorCodeMap.get(errorCode));
        }
    }
    /**
     * 删除部门
     * @param deptId
     */
    public void setDeleteDeptById(String deptId){
        String url = MessageFormat.format(DELETE_DEPT_URL,getAccessToken(TOKEN_TYPE_ADDRESS_BOOK),deptId);
        String result = sendGetRequest(url);
        Map<String,Object> map = JSON.parseObject(result,HashMap.class);
        Integer errorCode = Integer.valueOf(map.get("errcode").toString());
        if(!errorCode.equals(0)){
            throw new WeiXinException("删除部门异常，错误码:" + errorCode + " 错误原因：" + errorCodeMap.get(errorCode));
        }
    }


    private String sendPostRequest(String url,  Object obj) {
        //准备Post发送的数据
        String json = JSON.toJSONString(obj); // Object -> JSON
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),json);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).post(requestBody).build(); //创建Post请求
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException ex) {
            throw new RuntimeException("请求"+url+"异常",ex);
        }




    }


    private String sendGetRequest(String url) {
        //1. 创建HttpClient
        OkHttpClient httpClient = new OkHttpClient();
        //2. 创建请求对象
        Request request = new Request.Builder().url(url).build();
        try {
            //3. 发出请求 并获取结果
            Response response = httpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException ex) {
            throw new RuntimeException("请求"+url+"异常",ex);
        }


    }


}
