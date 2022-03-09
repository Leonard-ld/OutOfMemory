package developer.outofmemory.controller;

import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.common.exception.ApiAsserts;
import developer.outofmemory.model.dto.LoginDTO;
import developer.outofmemory.model.dto.RegisterDTO;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.model.vo.ProfileVO;
import developer.outofmemory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static developer.outofmemory.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @GetMapping(value = "/info")
    public ApiResult<User> getUser(@RequestHeader(value = USER_NAME) String userName) {
        User user = userService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @GetMapping("/{id}")
    public ApiResult<ProfileVO> userProfile(@PathVariable String id){
        ProfileVO profileVO = userService.getUserProfile(id);
        return ApiResult.success(profileVO);
    }

    @PostMapping("/register")
    public ApiResult<User> register(@Valid @RequestBody RegisterDTO registerDTO){
        User user = userService.register(registerDTO);
        if (ObjectUtils.isEmpty(user))
            return ApiResult.failed("账号注册失败");
        return ApiResult.success(user);
    }


    @PostMapping("/login")
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO loginDTO){

        String token = userService.login(loginDTO);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("账号或密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map, "登录成功");
    }


    @PutMapping
    public ApiResult<User> modify(@RequestBody User user, @RequestHeader(name = USER_NAME) String userName){
        if (!user.getUsername().equals(userName))
            ApiAsserts.fail("请求错误");
        userService.updateById(user);
        return ApiResult.success(user, "修改成功");
    }

    @RequestMapping(value = "/logout")
    public ApiResult<Object> logOut() {
        return ApiResult.success(null, "注销成功");
    }
}
