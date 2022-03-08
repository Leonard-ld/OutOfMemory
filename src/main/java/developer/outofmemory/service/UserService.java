package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.dto.LoginDTO;
import developer.outofmemory.model.dto.RegisterDTO;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.model.vo.ProfileVO;


public interface UserService extends IService<User> {
    User register(RegisterDTO registerDTO);

    User getUserByUsername(String username);

    String login(LoginDTO loginDTO);

    ProfileVO getUserProfile(String userId);
}
