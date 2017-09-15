
package com.user.service.impl;

import com.common.PageUtil;
import com.user.entity.User;
import com.user.entity.UserExample;
import com.user.mapper.UserMapper;
import com.user.service.UserService;
import com.utils.BlankUtil;
import com.utils.MongoUtil;
import com.utils.Page;
import com.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int addUser(User user) {
        MongoUtil.insert(mongoTemplate, user);
        User u = RedisUtil.add(user, redisTemplate, "id");
        return userMapper.insert(u);
    }

    @Override
    public int deleteUser(Long id) {
        MongoUtil.delete(mongoTemplate, new User(id));
        RedisUtil.delete(redisTemplate, id, User.class);
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateUserById(User user) {
        Boolean flag = MongoUtil.update(mongoTemplate, user);
        RedisUtil.update(user, redisTemplate, "id");
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> getUser() {

        List<User> list = MongoUtil.list(mongoTemplate, User.class);
        if (BlankUtil.isNotBlank(list))
            return list;
        list = RedisUtil.getList(redisTemplate, User.class);
        if (BlankUtil.isNotBlank(list))
            return list;
        Page page = PageUtil.getPage();
        UserExample userExample = new UserExample();
        userExample.setPageEnd(page.getPageStar() + page.getPageSize() - 1);
        userExample.setPageStar(page.getPageStar());
        return userMapper.selectByExample(userExample);
//        return userMapper.getUser();
    }


    @Override
    public User getUserById(Long id) {
        User user = (User) MongoUtil.getOne(mongoTemplate, id, User.class);
        if (user == null)
            user = RedisUtil.getOne(id, redisTemplate, User.class);
        return user == null ? userMapper.selectByPrimaryKey(id) : user;
    }
}
