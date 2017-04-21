package cn.kyrioscraft.service.impl;

import cn.kyrioscraft.data.model.entity.User;
import cn.kyrioscraft.data.repository.mybatis.UserMapper;
import cn.kyrioscraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author kyrioscraft
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }
}
