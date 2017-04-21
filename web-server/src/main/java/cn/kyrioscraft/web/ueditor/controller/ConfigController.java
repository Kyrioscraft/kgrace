package cn.kyrioscraft.web.ueditor.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/4/12.
 */
@Controller
public class ConfigController {
    @Value("${ueditor.config}")
    private String configJson;
    @RequestMapping(value = "/ueditor/api")
    public @ResponseBody
    String ueUpload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //这里就是把controller.jsp代码copy下来
        request.setCharacterEncoding( "utf-8" );
        return configJson;
    }
}
