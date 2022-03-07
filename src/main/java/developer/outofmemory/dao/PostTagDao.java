package developer.outofmemory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.PostTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagDao extends BaseMapper<PostTag> {
}
