package developer.outofmemory.controller;

import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.dao.CommentDao;
import developer.outofmemory.model.dto.CommentDTO;
import developer.outofmemory.model.entity.Comment;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.model.vo.CommentVO;
import developer.outofmemory.service.CommentService;
import developer.outofmemory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static developer.outofmemory.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    CommentDao commentDao;

    @PostMapping
    public ApiResult<Comment> add_comment(@RequestHeader(value = USER_NAME) String userName,
                                          @RequestBody CommentDTO dto) {
        User user = userService.getUserByUsername(userName);
        Comment comment = commentService.create(dto, user);
        return ApiResult.success(comment);
    }
    @GetMapping("/{postId}")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@PathVariable String postId) {
        List<CommentVO> commentVOList = commentDao.getCommentsByTopicID(postId);
        return ApiResult.success(commentVOList);
    }
}
