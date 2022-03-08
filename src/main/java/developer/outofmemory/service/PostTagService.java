package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.entity.PostTag;
import developer.outofmemory.model.entity.Tag;

import java.util.List;

public interface PostTagService extends IService<PostTag> {
    List<PostTag> selectByPostId(String PostId);


    void createPostTag(String id, List<Tag> tags);
}
