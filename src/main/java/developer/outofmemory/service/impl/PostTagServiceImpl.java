package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.dao.PostTagDao;
import developer.outofmemory.model.entity.PostTag;
import developer.outofmemory.service.PostTagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagDao, PostTag> implements PostTagService{
    public List<PostTag> selectByPostId(String postId){
        QueryWrapper<PostTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PostTag::getPostId, postId);
        return this.baseMapper.selectList(wrapper);
    }
}
