package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.entity.Follow;
import org.springframework.stereotype.Service;

@Service
public interface FollowService extends IService<Follow> {
    void follow(String userName, String userId);
    void unFollow(String userName, String userId);
    Boolean hasFollow(String userName, String userId);
}
