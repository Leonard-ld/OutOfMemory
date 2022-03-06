package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.dao.PromotionDao;
import developer.outofmemory.model.entity.Promotion;
import developer.outofmemory.service.PromotionService;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl extends ServiceImpl<PromotionDao, Promotion> implements PromotionService {
}
