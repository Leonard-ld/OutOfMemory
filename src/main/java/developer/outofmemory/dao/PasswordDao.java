package developer.outofmemory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Password;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PasswordDao extends BaseMapper<Password> {
}
