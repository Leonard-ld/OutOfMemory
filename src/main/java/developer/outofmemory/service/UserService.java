package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.dto.LoginDTO;
import developer.outofmemory.model.dto.RegisterDTO;
import developer.outofmemory.model.entity.User;


public interface UserService extends IService<User> {
    User register(RegisterDTO registerDTO);
    String login(LoginDTO loginDTO);
}
