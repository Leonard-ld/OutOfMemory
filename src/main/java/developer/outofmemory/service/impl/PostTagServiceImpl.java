package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.dao.PostTagDao;
import developer.outofmemory.dao.TagDao;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.PostTag;
import developer.outofmemory.model.entity.Tag;
import developer.outofmemory.service.PostService;
import developer.outofmemory.service.PostTagService;
import developer.outofmemory.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@AllArgsConstructor
@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagDao, PostTag> implements PostTagService{

    public List<PostTag> selectByPostId(String postId){
        LambdaQueryWrapper<PostTag> wrapper = new LambdaQueryWrapper<PostTag>();
        wrapper.eq(PostTag::getPostId, postId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public Boolean deleteByPostId(String postId) {
        LambdaQueryWrapper<PostTag> wrapper = new LambdaQueryWrapper<PostTag>();
        wrapper.eq(PostTag::getPostId, postId);
        return this.baseMapper.delete(wrapper) > 0;
    }

    @Override
    public void createPostTag(String postId, List<Tag> tags) {
        // 先删除topicId对应的所有记录
        this.baseMapper.delete(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, postId));


        for (Tag tag:
             tags) {
            //将post-tag插入数据库
            this.save(new PostTag(tag.getId(), postId));
        }
    }

}
