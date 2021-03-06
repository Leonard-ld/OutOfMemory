package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.dao.PostDao;
import developer.outofmemory.dao.PostTagDao;
import developer.outofmemory.dao.TagDao;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.PostTag;
import developer.outofmemory.model.entity.Tag;
import developer.outofmemory.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements TagService{


    @Override
    public List<Tag> insertTags(List<String> tagNames) {
        List<Tag> tagList = new ArrayList<>();
        for (String tagName : tagNames) {
            Tag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName, tagName));
            if (tag == null) {
                tag = Tag.builder().name(tagName).build();
                this.baseMapper.insert(tag);
            } else {
                tag.setPostCount(tag.getPostCount() + 1);
                this.baseMapper.updateById(tag);
            }
            tagList.add(tag);
        }
        return tagList;
    }


}
