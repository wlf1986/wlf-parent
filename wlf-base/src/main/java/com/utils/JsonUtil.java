package com.utils;

import com.alibaba.fastjson.JSON;
import com.common.PageUtil;

import java.util.HashMap;
import java.util.Map;

public final class JsonUtil {

    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "400";


    private JsonUtil() {
    }

    public static String success() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", SUCCESS_CODE);
        return JSON.toJSON(map).toString();
    }

    public static String success(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (BlankUtil.isBlank(obj)) {
            return error("内容不存在");
        }

        map.put("code", SUCCESS_CODE);
        map.put("data", obj);
        Page page = PageUtil.getAndRemPage();
        if (page.getPage()) {
            map.put("pageNum", page.getPageNum());
            map.put("pageSize", page.getPageSize());
            map.put("pageAll", page.getPageAll());
        }

        return JSON.toJSON(map).toString();
    }

    public static String error(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ERROR_CODE);
        map.put("msg", msg);
        return JSON.toJSON(map).toString();
    }

}
