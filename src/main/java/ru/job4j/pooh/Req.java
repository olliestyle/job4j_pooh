package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Req {
    private final String method;
    private final String mode;
    private final String queue;
    private final Map<String, String> params;

    public Req(String method, String mode, String queue, Map<String, String> params) {
        this.method = method;
        this.mode = mode;
        this.queue = queue;
        this.params = params;
    }

    public static Req of(String content) {
        String[] arr = content.split("\n");
        String method = arr[0].split("/")[0].trim();
        String mode = arr[0].split("/")[1];
        Map<String, String> params = new ConcurrentHashMap<>();
        if ("POST".equals(method)) {
            String paramsKey = arr[arr.length - 1].split("=")[0];
            String paramsValue = arr[arr.length - 1].split("=")[1];
            params.putIfAbsent(paramsKey, paramsValue);
        }
        String queue = "";
        if ("queue".equals(mode.toLowerCase())) {
            queue = arr[0].split("/")[2].split(" ")[0];
        }
        if ("topic".equals(mode.toLowerCase())) {
            queue = arr[0].split("/")[2].split(" ")[0];
            params.putIfAbsent("userId", arr[0].split("/")[3].split(" ")[0]);
        }
        return new Req(method, mode,  queue, params);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String queue() {
        return queue;
    }

    public String param(String key) {
        return params.get(key);
    }

    public Map<String, String> getParams() {
        return this.params;
    }
}