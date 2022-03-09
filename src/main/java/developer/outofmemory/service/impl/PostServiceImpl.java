package developer.outofmemory.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import developer.outofmemory.dao.PostDao;
import developer.outofmemory.dao.PostTagDao;
import developer.outofmemory.dao.TagDao;
import developer.outofmemory.model.dto.CreateDTO;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.PostTag;
import developer.outofmemory.model.entity.Tag;
import developer.outofmemory.model.entity.User;
import developer.outofmemory.model.vo.PostVO;
import developer.outofmemory.model.vo.ProfileVO;
import developer.outofmemory.service.PostService;
import developer.outofmemory.service.PostTagService;
import developer.outofmemory.service.TagService;
import developer.outofmemory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostDao, Post> implements PostService {


    private TagDao tagDao;
    private PostTagDao postTagDao;
    @Lazy
    private TagService tagService;
    private PostTagService postTagService;
    private UserService userService;


    @Override
    public Page<Post> selectPostsByTagId(Page<Post> postPage, String id) {
        //查询tagId对应的PostsId
        List<String> postIds = postTagDao.getTopicIdsByTagId(id);
        LambdaQueryWrapper<Post> lqw = new LambdaQueryWrapper();
        lqw.in(Post::getId, postIds);
        return this.page(postPage, lqw);
    }

    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        setPostTags(iPage);
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post create(CreateDTO dto, User user) {
        // 封装
        Post post = Post.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .lastCommentTime(new Date())
                .build();
        this.baseMapper.insert(post);


        // 标签
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<Tag> tags = tagService.insertTags(dto.getTags());
            // 处理post与tag的关联
            postTagService.createPostTag(post.getId(), tags);
        }

        return post;
    }


    /**
     * 为页面中的post添加tags数据
     * @param iPage
     */
    @Override
    public void setPostTags(Page<PostVO> iPage) {
        iPage.getRecords().forEach(post -> {
            List<PostTag> postTags = postTagService.selectByPostId(post.getId());
            if (!postTags.isEmpty()) {
                List<String> tagIds = postTags.stream().map(PostTag::getTagId).collect(Collectors.toList());
                List<Tag> tags = tagDao.selectBatchIds(tagIds);
                post.setTags(tags);
            }
        });
    }

    @Override
    public Map<String, Object> viewPost(String id) {
        Map<String, Object> map = new HashMap<>(16);
        Post post = this.baseMapper.selectById(id);
        Assert.notNull(post, "当前话题不存在,或已被作者删除");
        // 查询话题详情
        post.setView(post.getView() + 1);
        this.baseMapper.updateById(post);
        // emoji转码
        post.setContent(EmojiParser.parseToUnicode(post.getContent()));
        map.put("post", post);
        // 标签
        QueryWrapper<PostTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PostTag::getPostId, post.getId());
        Set<String> set = new HashSet<>();
        for (PostTag articleTag : postTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<Tag> tags = tagService.listByIds(set);
        map.put("tags", tags);

        // 作者

        ProfileVO user = userService.getUserProfile(post.getUserId());
        map.put("user", user);

        return map;
    }

    @Override
    public List<Post> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }

    @Override
    public Boolean deletePostById(String id){
        //删除post
        this.baseMapper.deleteById(id);
        //删除post-tag
        return postTagService.deleteByPostId(id);
    }



}
