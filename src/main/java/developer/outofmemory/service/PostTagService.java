package developer.outofmemory.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.entity.PostTag;

import java.util.List;

public interface PostTagService extends IService<PostTag> {
    List<PostTag> selectByPostId(String PostId);
}
