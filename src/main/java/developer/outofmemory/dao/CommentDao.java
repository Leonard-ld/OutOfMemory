package developer.outofmemory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import developer.outofmemory.model.entity.Comment;
import developer.outofmemory.model.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao extends BaseMapper<Comment> {
    List<CommentVO> getCommentsByTopicID(String postId);
}
