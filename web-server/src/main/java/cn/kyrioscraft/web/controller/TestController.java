package cn.kyrioscraft.web.controller;

import cn.kyrioscraft.data.model.entity.User;
import cn.kyrioscraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestController {
    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("/test")
    public User test(HttpServletRequest request){
        List<User> all = userService.getAll();
        Map<String , Object> map = new HashMap<>();
        map.put("test",all);
        String teststr = "这是测试字符串";
        User user1 = all.get(0);
        User user = new User();
        user.setId(9);
        user.setPassword("123");
        user.setUsername("safd");
        return user1;
    }
}
