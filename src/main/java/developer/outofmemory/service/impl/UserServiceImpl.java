package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import developer.outofmemory.common.exception.ApiAsserts;
import developer.outofmemory.dao.UserDao;
import developer.outofmemory.jwt.JwtUtil;
import developer.outofmemory.model.dto.LoginDTO;
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
        LambdaQueryWrapper<User> nameWrapper = new LambdaQueryWrapper<>();
        nameWrapper.eq(User::getUsername, registerDTO.getName());
        User user = baseMapper.selectOne(nameWrapper);
        if (!ObjectUtils.isEmpty(user)) {
            ApiAsserts.fail("用户名已存在！");
        }
        else {
            LambdaQueryWrapper<User> emailWrapper = new LambdaQueryWrapper<>();
            emailWrapper.eq(User::getEmail, registerDTO.getEmail());
            user = baseMapper.selectOne(emailWrapper);
            if (!ObjectUtils.isEmpty(user)) {
                ApiAsserts.fail("邮箱已注册！");
            }
        }


        User user2Add = User.builder()
                .username(registerDTO.getName())
                .alias("OutOfMemory_"+registerDTO.getName())
                .password(MD5Utils.getMD5(registerDTO.getPass()))
                .email(registerDTO.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        userDao.insert(user2Add);
        return user2Add;
    }

    @Override
    public User getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public String login(LoginDTO loginDTO) {
        String token = null;
        User user = getUserByUsername(loginDTO.getUsername());
        String encodePwd = MD5Utils.getMD5(loginDTO.getPassword());
        if(!encodePwd.equals(user.getPassword())) {
            ApiAsserts.fail("密码错误！");
        }
        token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
        return token;
    }


}
