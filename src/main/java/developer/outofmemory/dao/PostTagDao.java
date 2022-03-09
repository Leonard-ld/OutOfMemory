package developer.outofmemory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.PostTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface PostTagDao extends BaseMapper<PostTag> {
    List<String> getTopicIdsByTagId(String id);
}
