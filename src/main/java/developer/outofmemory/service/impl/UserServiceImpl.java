package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.common.exception.ApiAsserts;
import developer.outofmemory.dao.UserDao;
import developer.outofmemory.model.dto.RegisterDTO;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.service.UserService;
import developer.outofmemory.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User register(RegisterDTO registerDTO) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>().
                eq(User::getEmail, registerDTO.getEmail());
        if(!ObjectUtils.isEmpty(userDao.selectOne(lqw))) {
            ApiAsserts.fail("邮箱已注册");
        }
        User user = User.builder()
                .email(registerDTO.getEmail())
                .username(registerDTO.getName())
                .createTime(new Date())
                .status(true)
                .password(MD5Utils.getMD5(registerDTO.getPass()))
                .build();
        userDao.insert(user);
        return user;
    }
}
