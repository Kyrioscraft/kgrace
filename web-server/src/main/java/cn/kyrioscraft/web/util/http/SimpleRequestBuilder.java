package cn.kyrioscraft.web.util.http;


import cn.kyrioscraft.web.util.http.arch.HttpRequest;
import cn.kyrioscraft.web.util.http.arch.simple.SimpleHttpRequest;
import cn.kyrioscraft.web.util.http.arch.simple.SimpleHttpsRequest;

public class SimpleRequestBuilder implements RequestBuilder {


    @Override
    public HttpRequest http(String url) {
        if (!url.startsWith("http")) url = "http://" + url;
        return new SimpleHttpRequest(url);
    }

    public HttpRequest https(String url) {
        if (!url.startsWith("http")) url = "https://" + url;
        try {
            return new SimpleHttpsRequest(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
