package com.grandblue.dingding.service;

import lombok.Data;

import java.util.Map;

@Data
public class result {

    private String message;

    private Map<String,Integer> valueList;
}
