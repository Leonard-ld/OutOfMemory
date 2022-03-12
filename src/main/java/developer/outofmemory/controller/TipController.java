package developer.outofmemory.controller;

import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.model.entity.Tip;
import developer.outofmemory.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tip")
public class TipController extends BaseController{

    @Autowired
    private TipService tipService;

    @GetMapping
    public ApiResult<Tip> show(){
        return ApiResult.success(tipService.getTip());

    }
}
