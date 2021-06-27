package com.grandblue.dingding.controller;


import com.alibaba.fastjson.JSONObject;
import com.grandblue.dingding.service.DingService;
import com.grandblue.dingding.service.Output;
import com.grandblue.dingding.service.Test;
import com.grandblue.dingding.service.result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import java.security.SecureRandom;
import java.util.*;


@Slf4j
@Controller
public class DingController {

    @Autowired
    private DingService dingService;


    @PostMapping("/history")
    @ResponseBody
    public result history(@RequestBody String s) {
        result r = new result();
        r.setMessage("success");
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        map.put("2021-06-26 14:39:00", 12);
        map.put("2021-06-26 14:39:10", 2);
        map.put("2021-06-26 14:39:20", 4);
        map.put("2021-06-26 14:39:30", 7);
        map.put("2021-06-26 14:39:40", 9);
        map.put("2021-06-26 14:39:50", 0);
        map.put("2021-06-26 14:39:60", 0);
        map.put("2021-06-26 14:39:70", 8);
        map.put("2021-06-26 14:39:80", 8);
        map.put("2021-06-26 14:40:00", 7);
        map.put("2021-06-26 14:40:10", 7);
        map.put("2021-06-26 14:40:20", 7);
        map.put("2021-06-26 14:40:30", 7);
        map.put("2021-06-26 14:40:40", 7);
        map.put("2021-06-26 14:40:50", 7);
        map.put("2021-06-26 14:40:60", 7);
        map.put("2021-06-26 14:40:70", 7);
        map.put("2021-06-26 14:40:80", 7);
        map.put("2021-06-26 14:40:90", 7);
        map.put("2021-06-26 14:41:00", 7);
        map.put("2021-06-26 14:41:10", 7);
        map.put("2021-06-26 14:41:20", 7);
        map.put("2021-06-26 14:42:30", 7);
        map.put("2021-06-26 14:42:40", 7);
        map.put("2021-06-26 14:42:50", 7);
        map.put("2021-06-26 14:42:60", 7);
        map.put("2021-06-26 14:42:70", 7);
        r.setValueList(map);
        return r;
    }

    @PostMapping("/request")
    @ResponseBody
    public Test request(@RequestBody JSONObject jsonObject) {
        Test test = new Test();
        test.setId("3");
        Output output = new Output();
        test.setOutput(output);
        return test;

    }
}
