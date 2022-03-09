package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.dto.CreateDTO;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.PostTag;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.model.vo.PostVO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;

public interface PostService extends IService<Post> {

    /**
     * 获取首页话题列表
     *
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> getList(Page<PostVO> page, String tab);
    Post create(CreateDTO createDTO, User user);
    void setPostTags(Page<PostVO> iPage);

    Map<String, Object> viewPost(String id);

    List<Post> getRecommend(String id);

    Boolean deletePostById(String id);

    Page<Post> selectPostsByTagId(Page<Post> postPage, String id);
}