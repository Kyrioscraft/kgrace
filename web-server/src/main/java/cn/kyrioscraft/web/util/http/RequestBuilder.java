package cn.kyrioscraft.web.util.http;


import cn.kyrioscraft.web.util.http.arch.HttpRequest;


public interface RequestBuilder {

    HttpRequest http(String url);

    HttpRequest https(String url);

}