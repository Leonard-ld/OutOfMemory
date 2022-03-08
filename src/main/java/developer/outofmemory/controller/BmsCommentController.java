package developer.outofmemory.controller;


import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



@RestController
@RequestMapping("/comment")
public class BmsCommentController extends BaseController {

/*    @Resource
    private IBmsCommentService bmsCommentService;
    @Resource
    private UserService umsUserService;

    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicid) {
        List<CommentVO> lstBmsComment = bmsCommentService.getCommentsByTopicID(topicid);
        return ApiResult.success(lstBmsComment);
    }
    @PostMapping("/add_comment")
    public ApiResult<BmsComment> add_comment(@RequestHeader(value = USER_NAME) String userName,
                                             @RequestBody CommentDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsComment comment = bmsCommentService.create(dto, user);
        return ApiResult.success(comment);
    }*/
}
