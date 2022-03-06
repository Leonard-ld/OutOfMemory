package developer.outofmemory.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@TableName("password")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Password {
    @TableField("email")
    private String userEmail;

    @TableField("password")
    private String password;
}
