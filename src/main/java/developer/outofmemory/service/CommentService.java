package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.dto.CommentDTO;
import developer.outofmemory.model.entity.Comment;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.model.vo.CommentVO;

import java.util.List;

public interface CommentService extends IService<Comment> {
    Comment create(CommentDTO dto, User user);


}
