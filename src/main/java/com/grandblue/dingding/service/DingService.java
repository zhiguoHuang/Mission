package com.grandblue.dingding.service;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.grandblue.dingding.config.DdConfig;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DingService {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 获取config
     * @param request
     * @return
     */
    public Map<String,Object> getConfig(HttpServletRequest request) {
        String urlString = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        String queryStringEncode = null;
        String url;
        if (queryString != null) {
            queryStringEncode = URLDecoder.decode(queryString);
            url = urlString + "?" + queryStringEncode;
        } else {
            url = urlString;
        }
        /**
         * 确认url与配置的应用首页地址一致
         */
        System.out.println(url);

        /**
         * 随机字符串
         */
        String nonceStr = DdConfig.getRandomStr(5);
        long timeStamp = System.currentTimeMillis() / 1000;
        String signedUrl = url;
        String accessToken = null;
        String ticket = null;
        String signature = null;

        try {
            accessToken = DdConfig.getAccessToken();
            ticket = DdConfig.getJsapiTicket(accessToken);
            signature = DdConfig.sign(ticket, nonceStr, timeStamp, signedUrl);

        } catch (Exception e) {
            log.error("计算dd.config的签名失败",e);
        }

        Map<String, Object> configValue = new HashMap<>();
        configValue.put("jsticket", ticket);
        configValue.put("signature", signature);
        configValue.put("nonceStr", nonceStr);
        configValue.put("timeStamp", timeStamp);
        configValue.put("corpId", Env.CORP_ID);
        configValue.put("agentId", Env.AGENT_ID);
        return configValue;
    }


    /**
     * 取userinfo
     * @param code
     * @param corpid
     * @return
     */
    public  Map<String,Object> getUserInfo(String code,String corpid){

        String userName = null;
        String userId = null;

        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
            OapiUserGetuserinfoRequest req = new OapiUserGetuserinfoRequest();
            req.setCode(code);
            req.setHttpMethod("GET");
            OapiUserGetuserinfoResponse rsp = client.execute(req, DdConfig.getAccessToken());
            String body = rsp.getBody();

            System.out.println(rsp.getBody());
            System.out.println(corpid);

            JSONObject jsonObject = JSONObject.parseObject(body);
            userName = jsonObject.getString("name");
            userId = jsonObject.getString("userid");

        } catch (ApiException e) {
            log.error("获取用户信息失败",e);
        }

        Map<String, Object> configValue = new HashMap<>();
        configValue.put("userId",userId);
        configValue.put("userName",userName);
        return configValue;
    }


    /**
     * 单点登录接口
     */
    public void login(){

        String targetURL ="http://10.2.4.145/LoginCkNoAD630.jsp";

        JSONObject jsonBody = new JSONObject();
      // jsonBody.put("code", );

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonBody, headers);

        //发送http请求
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(targetURL, request, String.class);
        String body =responseEntity.getBody();

    }
}
