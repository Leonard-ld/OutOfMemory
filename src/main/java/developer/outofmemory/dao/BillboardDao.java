package developer.outofmemory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Billboard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillboardDao extends BaseMapper<Billboard> {
}
