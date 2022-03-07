package developer.outofmemory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
public interface TagDao extends BaseMapper<Tag> {

}
