package developer.outofmemory.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Promotion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromotionDao extends BaseMapper<Promotion> {
}
