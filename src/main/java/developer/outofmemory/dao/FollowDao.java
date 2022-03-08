package developer.outofmemory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Follow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowDao extends BaseMapper<Follow> {
}
