package developer.outofmemory.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.dao.CommentDao;
import developer.outofmemory.model.dto.CommentDTO;
import developer.outofmemory.model.entity.Comment;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

    @Override
    public Comment create(CommentDTO dto, User user) {
        Comment comment = Comment.builder()
                .userId(user.getId())
                .content(dto.getContent())
                .postId(dto.getPost_id())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(comment);
        return comment;
    }
}
