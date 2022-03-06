package developer.outofmemory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import developer.outofmemory.dao.BillboardDao;
import developer.outofmemory.model.entity.Billboard;
import developer.outofmemory.service.BillboardService;
import org.springframework.stereotype.Service;

@Service
public class BillboardServiceImpl extends ServiceImpl<BillboardDao, Billboard> implements BillboardService {
}
