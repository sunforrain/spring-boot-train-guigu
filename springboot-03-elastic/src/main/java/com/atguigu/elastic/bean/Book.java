package com.atguigu.elastic.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 视频23 检索-整合SpringDataElasticsearch
 * 要指定文档对应的索引和类型
 * 注意在ElasticSearch 6.x 版本中不支持一个索引多个type,只能一个index对应一个type,原来一个index可以有多个type的
 */
@Document(indexName = "atguigu2",type = "book")
public class Book {
    private Integer id;
    private String bookName;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
