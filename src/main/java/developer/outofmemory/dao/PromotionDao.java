package developer.outofmemory.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Promotion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionDao extends BaseMapper<Promotion> {
}
