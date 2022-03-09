package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.PostTag;
import developer.outofmemory.model.entity.Tag;

import java.util.List;

public interface PostTagService extends IService<PostTag> {
    List<PostTag> selectByPostId(String PostId);
    Boolean deleteByPostId(String postId);

    void createPostTag(String id, List<Tag> tags);


}
