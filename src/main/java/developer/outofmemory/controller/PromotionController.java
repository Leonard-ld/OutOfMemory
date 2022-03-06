package developer.outofmemory.controller;

import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.model.entity.Promotion;
import developer.outofmemory.model.entity.Tip;
import developer.outofmemory.service.PromotionService;
import developer.outofmemory.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController extends BaseController{

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/show")
    public ApiResult<List<Promotion>> show(){
        List<Promotion> list = promotionService.list();
        return ApiResult.success(list);

    }
}
