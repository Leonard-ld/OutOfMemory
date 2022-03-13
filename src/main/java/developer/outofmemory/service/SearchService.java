package developer.outofmemory.service;

import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.model.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


public interface SearchService {

    ApiResult<Object> searchByKeyWord(String keyWord,Integer pageNum, Integer pageSize) throws Exception;
    Boolean addDocument(Post post) throws Exception;
    Boolean deleteDocument(String id) throws Exception;
    Boolean updateDocument(Post post) throws Exception;


}
