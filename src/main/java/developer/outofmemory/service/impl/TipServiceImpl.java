package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.dao.TipDao;
import developer.outofmemory.model.entity.Tip;
import developer.outofmemory.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipServiceImpl extends ServiceImpl<TipDao, Tip> implements TipService {

    @Override
    public Tip getTip() {
        int sum = this.count();
        int id = (int)Math.floor(Math.random()*1000) % sum;
        return this.getById(id);
    }
}
