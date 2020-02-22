package com.letopo.common.model;

import org.springframework.http.HttpStatus;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-18 10:35
 * 业务对象
 */
public class R extends ConcurrentHashMap<String,Object> {

    public R() {
        this.put("code", HttpStatus.OK.value());
        this.put("msg","success !!");
    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        if (msg == null) return R.ok();
        return R.ok().put("msg", msg);
    }

    public static R ok(Object data) {
        if (data == null) data = "请求数据未找到...";
        return R.ok().put("data", data);
    }

    public static R ok(String msg, Object data) {
        if (data == null) data = "请求数据未找到...";
        return R.ok(msg).put("data", data);
    }

    public static R error(int code, String error) {
        if (error == null) error = "未知错误";
        return R.ok()
                .put("code", code)
                .put("msg", "")
                .put("error", error);
    }

    public static R error(int code, String msg, String error) {
        if (error == null) error = "未知错误";
        if (msg == null) msg = "未知错误";
        return R.ok()
                .put("code", code)
                .put("msg", msg)
                .put("error", error);

    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
