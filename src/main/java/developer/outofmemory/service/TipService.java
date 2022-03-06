package developer.outofmemory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import developer.outofmemory.model.entity.Billboard;
import developer.outofmemory.model.entity.Tip;


public interface TipService extends IService<Tip> {
    public Tip getTip();
}
