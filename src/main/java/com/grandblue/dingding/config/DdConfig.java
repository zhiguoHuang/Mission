package com.grandblue.dingding.config;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGetJsapiTicketResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.grandblue.dingding.util.CacheUtil;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.Random;

@Slf4j
public class DdConfig{


    /**
     * 调整到1小时50分钟
     */
    public static final long cacheTime = 1000 * 60 * 55 * 2;

    /**
     * 计算dd.config的签名参数
     *
     * @param jsticket  通过微应用appKey获取的jsticket
     * @param nonceStr  随机字符串
     * @param timeStamp 当前时间戳
     * @param url       调用dd.config的当前页面URL
     * @return
     * @throws Exception
     */
    public static String sign(String jsticket, String nonceStr, long timeStamp, String url) throws Exception {
        String plain = "jsapi_ticket=" + jsticket + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp + "&url=" + url;
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            sha1.reset();
            sha1.update(plain.getBytes("UTF-8"));
            return byteToHex(sha1.digest());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // 字节数组转化成十六进制字符串
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 因为ios端上传递的url是encode过的，android是原始的url。开发者使用的也是原始url,
     * 所以需要把参数进行一般urlDecode
     *
     * @param url
     * @return
     * @throws Exception
     */
    private static String decodeUrl(String url) throws Exception {
        URL urler = new URL(url);
        StringBuilder urlBuffer = new StringBuilder();
        urlBuffer.append(urler.getProtocol());
        urlBuffer.append(":");
        if (urler.getAuthority() != null && urler.getAuthority().length() > 0) {
            urlBuffer.append("//");
            urlBuffer.append(urler.getAuthority());
        }
        if (urler.getPath() != null) {
            urlBuffer.append(urler.getPath());
        }
        if (urler.getQuery() != null) {
            urlBuffer.append('?');
            urlBuffer.append(URLDecoder.decode(urler.getQuery(), "utf-8"));
        }
        return urlBuffer.toString();
    }

    public static String getRandomStr(int count) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 获取accessToken
     * @return
     */
    public static String getAccessToken() {

        //先重缓存中获取access_token,过期则重新请求获取
        long curTime = System.currentTimeMillis();
        String accessToken = String.valueOf(CacheUtil.getInstance().getCacheData("accesstoken"));
        String timeTemp = String.valueOf(CacheUtil.getInstance().getCacheData("begin_time"));
        long time;
        if(timeTemp==null || timeTemp=="null"){
            time = 0;
        }else {
            time = Long.valueOf(timeTemp);
        }

        if (accessToken == null || curTime - time >= cacheTime) {
            try {
                DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
                OapiGettokenRequest req = new OapiGettokenRequest();
                req.setAppkey(Env.APP_KEY);
                req.setAppsecret(Env.APP_SECRET);
                req.setHttpMethod("GET");
                OapiGettokenResponse rsp = client.execute(req);
                String body = rsp.getBody();
                JSONObject jsonObject = JSONObject.parseObject(body);
                accessToken = jsonObject.getString("access_token");

                // 缓存 accessToken
                CacheUtil.getInstance().addCacheData("accesstoken",accessToken);
                CacheUtil.getInstance().addCacheData("begin_time",curTime);


            } catch (ApiException e) {
                log.error("获取Access Token失败", e);
            }
        }else{
            return accessToken;
        }
        return accessToken;
    }


    /**
     * 获取JsapiTicket
     * @return
     */
    public static String getJsapiTicket(String accessToken){
        String ticket =null;
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/get_jsapi_ticket");
            OapiGetJsapiTicketRequest req = new OapiGetJsapiTicketRequest();
            req.setHttpMethod("GET");
            OapiGetJsapiTicketResponse rsp = client.execute(req,accessToken);
            String body = rsp.getBody();

            JSONObject jsonObject = JSONObject.parseObject(body);
            ticket = jsonObject.getString("ticket");

        } catch (ApiException e) {
            log.error("获取ticket失败",e);
        }

        return ticket;
    }


}