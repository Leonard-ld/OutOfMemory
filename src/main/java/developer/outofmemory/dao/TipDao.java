package developer.outofmemory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Billboard;
import developer.outofmemory.model.entity.Tip;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TipDao extends BaseMapper<Tip>  {
}
