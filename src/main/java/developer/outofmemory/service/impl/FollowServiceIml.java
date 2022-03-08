package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.common.exception.ApiAsserts;
import developer.outofmemory.dao.FollowDao;
import developer.outofmemory.model.entity.Follow;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.service.FollowService;
import developer.outofmemory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class FollowServiceIml extends ServiceImpl<FollowDao, Follow> implements FollowService {

    @Autowired
    UserService userService;

    @Override
    public void follow(String userName, String userId) {
        User user = userService.getUserByUsername(userName);
        if (userId.equals(user.getId())) {
            ApiAsserts.fail("在现实中多关注自己吧 😊");
        }
        Follow one = this.getOne(
                new LambdaQueryWrapper<Follow>()
                        .eq(Follow::getParentId, userId)
                        .eq(Follow::getFollowerId, user.getId()));
        if (!ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("出bug啦，刷新一下吧 🔄");
        }
        Follow follow = new Follow();
        follow.setParentId(userId);
        follow.setFollowerId(user.getId());
        this.save(follow);
    }

    @Override
    public void unFollow(String userName, String userId) {
        User user = userService.getUserByUsername(userName);
        LambdaQueryWrapper lqw = new LambdaQueryWrapper<Follow>()
                .eq(Follow::getParentId, userId)
                .eq(Follow::getFollowerId, user.getId());
        Follow one = this.getOne(lqw);
        if (ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("出bug啦，刷新一下吧 🔄");
        }
        this.remove(lqw);
    }

    @Override
    public Boolean hasFollow(String username, String userId) {
        User user = userService.getUserByUsername(username);
        if (!ObjectUtils.isEmpty(user)){
            LambdaQueryWrapper lqw = new LambdaQueryWrapper<Follow>()
                    .eq(Follow::getFollowerId, user.getId()).eq(Follow::getParentId, userId);
            if (!ObjectUtils.isEmpty(this.getOne(lqw)))
                return true;
        }
        return false;
    }
}
