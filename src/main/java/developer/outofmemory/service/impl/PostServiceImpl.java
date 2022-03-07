package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.dao.PostDao;
import developer.outofmemory.dao.TagDao;
import developer.outofmemory.dao.UserDao;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.PostTag;
import developer.outofmemory.model.entity.Tag;
import developer.outofmemory.model.vo.PostVO;
import developer.outofmemory.service.PostService;
import developer.outofmemory.service.PostTagService;
import developer.outofmemory.service.TagService;
import developer.outofmemory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends ServiceImpl<PostDao, Post> implements PostService {

    @Resource
    private TagDao tagDao;
    @Resource
    private UserDao userDao;
    @Resource
    private PostDao postDao;

    @Autowired
    @Lazy
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostTagService postTagService;


    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        Page<PostVO> iPage = postDao.selectListAndPage(page, tab);
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }

    private void setTopicTags(Page<PostVO> iPage) {
        iPage.getRecords().forEach(post -> {
            List<PostTag> postTags = postTagService.selectByPostId(post.getId());
            if (!postTags.isEmpty()) {
                List<String> tagIds = postTags.stream().map(PostTag::getTagId).collect(Collectors.toList());
                List<Tag> tags = tagDao.selectBatchIds(tagIds);
                post.setTags(tags);
            }
        });
    }


}
