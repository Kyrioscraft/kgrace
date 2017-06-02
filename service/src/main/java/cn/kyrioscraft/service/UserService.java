package cn.kyrioscraft.service;

import cn.kyrioscraft.data.model.entity.User;
import cn.kyrioscraft.data.model.mybatis.MUser;
import org.springframework.data.domain.Page;


import java.util.List;

/**
 * @Author kyrioscraft
 */
public interface UserService {
    List<MUser> getAll();
    User testJPAQuery();
    Page<User> testJPAPage();
}
