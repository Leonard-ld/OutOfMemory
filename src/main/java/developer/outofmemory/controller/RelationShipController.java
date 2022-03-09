package developer.outofmemory.controller;


import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static developer.outofmemory.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/relationship")
public class RelationShipController {

    @Autowired
    FollowService followService;

    @GetMapping("/{userId}")
    public ApiResult<Map<String, Object>> hasFollow(@PathVariable String userId, @RequestHeader(value = USER_NAME) String userName){
        Boolean hasFollow = followService.hasFollow(userName, userId);
        Map map = new HashMap<String, Boolean>();
        map.put("hasFollow", hasFollow);
        return ApiResult.success(map);
    }

    @PostMapping
    public ApiResult<Object> follow(@RequestBody Map<String,String> id, @RequestHeader(value = USER_NAME) String userName){

        followService.follow(userName, id.get("id"));
        return ApiResult.success(null, "关注成功");
    }

    @DeleteMapping("/{userId}")
    public ApiResult<Object> unFollow(@PathVariable String userId, @RequestHeader(value = USER_NAME) String userName){

        followService.unFollow(userName, userId);
        return ApiResult.success(null, "取消关注成功");
    }


}
