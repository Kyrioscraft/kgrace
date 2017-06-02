package cn.kyrioscraft.service.impl;

import cn.kyrioscraft.data.model.entity.User;
import cn.kyrioscraft.data.model.mybatis.MUser;
import cn.kyrioscraft.data.repository.jpa.UserDao;
import cn.kyrioscraft.data.repository.mybatis.UserMapper;
import cn.kyrioscraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kyrioscraft
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDao userDao;
    @Override
    public List<MUser> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public User testJPAQuery() {
        User tu = userDao.findByUsername("admin");
        return tu;
    }

    @Override
    public Page<User> testJPAPage() {
        List<Sort.Order> list = new ArrayList<Sort.Order>();
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"createTime");
        list.add(order);
        Sort sort = new Sort(list);
        Pageable pageable = new PageRequest(0,10,sort);
        Page<User> findAll = userDao.findAll(pageable);
        return findAll;
    }
}
