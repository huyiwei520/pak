package com.pak.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by huyiwei on 2018/9/26.
 */
public class HttpClientUtils {
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONArray httpGet(String url) {
        JSONArray jsonResult = null;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                jsonResult = JSONArray.parseArray(strResult);
            } else {
                System.out.println(System.currentTimeMillis()/1000+"get请求提交失败1:" + url);
            }
        } catch (IOException e) {
            System.out.println(System.currentTimeMillis()/1000+"get请求提交失败2:" + url);
        }
        return jsonResult;
    }

    public String doPostKey(String url, Map<String,String> map){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
                httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"UTF-8");
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public String doPostObj(String url, String json){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            // 设置请求的header
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//            JSONObject jsonObj = JSON.parseObject(json);
            StringEntity entity = new StringEntity(json, "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static String doPostObj(){
        String url = "https://wxapp.ruyigou.com/api/addOrder";
        String json = "{\"cert_number\":\"131127198709080522\",\"nonce_str\":\"kptlfd9hj66iddggz2hoksco813fqdkq\",\"mobile\":\"17110678601\",\"name\":\"张方方\",\"sign\":\"DFF9585952849E7A8E3BEAEE8BB400B1\",\"take_shop_code\":\"1001\",\"appkey\":\"zbxcxzs\",\"ucuid\":\"2268806\",\"appoint_bottle\":2,\"cid\":\"9800318603080\",\"timestamp\":1645116383,\"ut\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1Y3VpZCI6MjI2ODgwNiwicmVmcmVzaF9leHAiOjE2NTI0NDIyNTAsImlzcyI6InVzZXJfYXBpIiwiaWF0IjoxNjQ0NjY2MjQ5LCJleHAiOjE2NDUyNzEwNTB9.QlE3BfvpgYNADKhLVVev24NCqxSh2Wsu_twQG84_KE0\"}";
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(new HttpHost("182.204.158.155", 4331)).build();
            httpPost.setConfig(requestConfig);
            // 设置请求的header
            httpPost.addHeader("Host", "wxapp.ruyigou.com");
            httpPost.addHeader("Connection", "keep-alive");
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            httpPost.addHeader("Referer", "https://servicewechat.com/wx0ae5e7e4b7ffd8ba/192/page-frame.html");
            httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity entity = new StringEntity(json, "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(result+"================================");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
