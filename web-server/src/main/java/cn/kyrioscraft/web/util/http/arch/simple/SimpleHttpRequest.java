package cn.kyrioscraft.web.util.http.arch.simple;


import cn.kyrioscraft.web.util.http.arch.Response;
import org.apache.http.HttpResponse;

import java.io.IOException;

public class SimpleHttpRequest extends AbstractHttpRequest {
    public SimpleHttpRequest(String url) {
        super(url);
    }

    @Override
    protected Response getResultValue(HttpResponse res) throws IOException {
        return new SimpleResponse(res);
    }
}