package cn.kyrioscraft.web.controller;


import cn.kyrioscraft.data.model.entity.User;
import cn.kyrioscraft.data.model.mybatis.MUser;
import cn.kyrioscraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author kyrioscraft on 2017/4/20.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("/test")
    public MUser test(HttpServletRequest request){
        List<MUser> all = userService.getAll();
        Map<String , Object> map = new HashMap<>();
        map.put("test",all);
        String teststr = "这是测试字符串";
        MUser user1 = all.get(0);
        MUser user = new MUser();
        user.setPassword("123");
        user.setUsername("safd");
        return user1;
    }

    @ResponseBody
    @RequestMapping("/jpa")
    public User jpa(HttpServletRequest request){
        User tuser = userService.testJPAQuery();
        return tuser;
    }
    @ResponseBody
    @RequestMapping("/jpaPage")
    public Page<User> getJpaPage(){
        Page<User> tusers = userService.testJPAPage();
        return tusers;
    }
}
