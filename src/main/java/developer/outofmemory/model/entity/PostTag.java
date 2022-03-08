package developer.outofmemory.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@TableName("post_tag")
@Accessors(chain = true)
public class PostTag implements Serializable {
    private static final long serialVersionUID = -5028599844989220715L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("tag_id")
    private String tagId;

    @TableField("post_id")
    private String postId;

    public PostTag() {}

    public PostTag(String tagId, String postId) {
        this.tagId = tagId;
        this.postId = postId;
    }
}
