package developer.outofmemory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.model.entity.Billboard;
import developer.outofmemory.service.BillboardService;
import developer.outofmemory.service.impl.BillboardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/billboard")
public class BillboardController extends BaseController{

    @Autowired
    private BillboardServiceImpl billboardService;

    @GetMapping
    public ApiResult<Billboard> show(){
        LambdaQueryWrapper lqw = new LambdaQueryWrapper<Billboard>().eq(Billboard::isShow, true);
        List<Billboard> list = billboardService.list(lqw);
        return ApiResult.success(list.get(list.size() - 1));

    }
}
