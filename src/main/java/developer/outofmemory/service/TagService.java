package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.model.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {
    /**
     * 插入标签
     *
     * @param tagNames
     * @return
     */
    List<Tag> insertTags(List<String> tagNames);


}
