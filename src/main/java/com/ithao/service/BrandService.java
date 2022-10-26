package com.ithao.service;

import com.ithao.pojo.PageBean;
import com.ithao.pojo.brand;

import java.util.List;

public interface BrandService {


    List<brand> selectAll();

    void add(brand brand);

    void delete(int id);

    void deleteById(int[] ids);

    void update(brand brand);

    PageBean<brand> selectByPage(int currentPage,int pageSize);
    PageBean<brand>  selectByPageAndCondition(int currentPage,int pageSize,brand brand);

 }
