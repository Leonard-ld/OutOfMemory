package developer.outofmemory.controller;

import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.model.dto.RegisterDTO;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResult<User> register(@Valid @RequestBody RegisterDTO dto){
        User user = userService.register(dto);
        if (ObjectUtils.isEmpty(user))
            return ApiResult.failed("账号注册失败");
        return ApiResult.success(user);
    }
}
