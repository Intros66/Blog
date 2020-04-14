package com.ssp.service;

import com.ssp.pojo.Blog;
import com.ssp.vo.BlogVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndCovert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogVo blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable);

    List<Blog> listRecommenedBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
