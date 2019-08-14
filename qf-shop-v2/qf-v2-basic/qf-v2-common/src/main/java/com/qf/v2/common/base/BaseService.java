package com.qf.v2.common.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseService<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> selectProducts();

    PageInfo<T> getPageInfo(int pageNum, int pageSize);
}
