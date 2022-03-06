package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.common.exception.ApiAsserts;
import developer.outofmemory.dao.PasswordDao;
import developer.outofmemory.dao.UserDao;
import developer.outofmemory.jwt.JwtUtil;
import developer.outofmemory.model.dto.LoginDTO;
import developer.outofmemory.model.dto.RegisterDTO;
import developer.outofmemory.model.entity.Password;
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
    @Autowired
    PasswordDao passwordDao;

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
                .build();
        userDao.insert(user);
        Password password = Password.builder()
                        .password(MD5Utils.getMD5(registerDTO.getPass()))
                        .userEmail(registerDTO.getEmail()).build();
        passwordDao.insert(password);
        return user;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Password> lqw = new LambdaQueryWrapper<Password>().
                eq(Password::getUserEmail, loginDTO.getEmail());
        Password password = passwordDao.selectOne(lqw);
        if(ObjectUtils.isEmpty(password)) {
            ApiAsserts.fail("该邮箱尚未注册");
        }
        //密码错误
        else if (!(MD5Utils.getMD5(loginDTO.getPassword()).equals(password.getPassword()))){
            ApiAsserts.fail("密码错误");
        }
        String token = null;
        token = JwtUtil.generateToken(String.valueOf(loginDTO.getEmail()));
        return token;
    }


}
