package developer.outofmemory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.model.dto.CreateDTO;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.model.vo.PostVO;
import developer.outofmemory.service.PostService;
import developer.outofmemory.service.SearchService;
import developer.outofmemory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static developer.outofmemory.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    @Resource
    private PostService postService;
    @Resource
    private UserService userService;
    @Autowired
    private SearchService searchService;


    @GetMapping("/searchDeprecated")
    public ApiResult<Object> searchPlus(@RequestParam("keyword") String keyword,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize) throws Exception {
        return searchService.searchByKeyWord(keyword, pageNum, pageSize);
    }

    @GetMapping("/search")
    public ApiResult<Page<PostVO>> searchList(@RequestParam("keyword") String keyword,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ApiResult.success(results);
    }


    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = postService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    //发表帖子
    @PostMapping
    public ApiResult<Post> create(@RequestHeader(value = USER_NAME) String userName
            , @RequestBody CreateDTO createDTO) throws Exception {
        User user = userService.getUserByUsername(userName);
        Post post = postService.create(createDTO, user);
        //searchService.addDocument(post);
        return ApiResult.success(post);
    }

    /**
     * 浏览帖子
     * @param id 帖子ID
     * @return
     */
    @GetMapping("/{id}")
    public ApiResult<Map<String, Object>> detail(@PathVariable String id) {
        Map<String, Object> map = postService.viewPost(id);
        return ApiResult.success(map);
    }

    @GetMapping("/recommend/{id}")
    public ApiResult<List<Post>> getRecommend(@PathVariable String id) {
        List<Post> topics = postService.getRecommend(id);
        return ApiResult.success(topics);
    }

    //更新帖子
    @PutMapping
    public ApiResult<Post> update(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody Post post) throws Exception {
        User user = userService.getUserByUsername(userName);
        Assert.isTrue(user.getId().equals(post.getUserId()), "出错啦，刷新一下吧");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        postService.updateById(post);
        //searchService.updateDocument(post);
        return ApiResult.success(post);
    }

    //删除帖子
    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@RequestHeader(value = USER_NAME) String userName, @PathVariable("id") String id) throws Exception {
        User user = userService.getUserByUsername(userName);
        Post byId = postService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(user.getId()), "你为什么可以删除别人的话题？？？");
        postService.deletePostById(id);
        //searchService.deleteDocument(id);
        return ApiResult.success(null,"删除成功");
    }

}
