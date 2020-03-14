package com.atguigu.elastic.repository;

import com.atguigu.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 视频23 检索-整合SpringDataElasticsearch
 */
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

    //参照
    // https://docs.spring.io/spring-data/elasticsearch/docs/3.2.0.RELEASE/reference/html/
    public List<Book> findByBookNameLike(String bookName);

}
