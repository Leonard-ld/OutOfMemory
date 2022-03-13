package developer.outofmemory.service.impl;

import developer.outofmemory.common.api.ApiResult;
import developer.outofmemory.model.dto.CreateDTO;
import developer.outofmemory.model.entity.Post;
import developer.outofmemory.service.SearchService;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceimpl implements SearchService{

    @Autowired
    RestHighLevelClient client;
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public ApiResult<Object> searchByKeyWord(String keyWord, Integer pageNum, Integer pageSize)  throws Exception{
        int from = (pageNum - 1) * pageSize;
        SearchRequest searchRequest = new SearchRequest("post");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(from).size(pageSize);
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyWord,"title","content"));
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit s:
             searchHits) {
            list.add(s.getSourceAsMap());
        }
        return ApiResult.success(list);
    }

    @Override
    public Boolean addDocument(Post post) throws Exception{

        IndexRequest indexRequest = new IndexRequest("post");
        indexRequest.id(post.getId());
        Map<String, Object> json = new HashMap<>();
        json.put("title", post.getTitle());
        json.put("content", post.getContent());
        json.put("create_time", dateformat.format(post.getCreateTime()));
        indexRequest.source(json);
        client.index(indexRequest, RequestOptions.DEFAULT);
        return true;
    }

    @Override
    public Boolean deleteDocument(String id) throws Exception{
        DeleteRequest deleteRequest = new DeleteRequest("post");
        deleteRequest.id(id);
        client.delete(deleteRequest, RequestOptions.DEFAULT);
        return true;
    }

    @Override
    public Boolean updateDocument(Post post) throws Exception{
        UpdateRequest updateRequest = new UpdateRequest("post", post.getId());
        Map<String, Object> json = new HashMap<>();
        json.put("title", post.getTitle());
        json.put("content", post.getContent());
        json.put("create_time", dateformat.format(post.getCreateTime()));
        updateRequest.doc(json);
        client.update(updateRequest, RequestOptions.DEFAULT);
        return true;
    }
}
