package com.qf.v2.common.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

public  abstract  class BaseServiceImpl<T> implements BaseService<T> {
    public abstract BaseDao<T> getBaseDao();

    public int deleteByPrimaryKey(Integer id) {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return getBaseDao().insert(record);
    }

    public int insertSelective(T record) {
        return getBaseDao().insertSelective(record);
    }

    public T selectByPrimaryKey (Integer id){
            return getBaseDao().selectByPrimaryKey(id);
        }

    public int updateByPrimaryKeySelective (T record){
            return getBaseDao().updateByPrimaryKeySelective(record);
        }

    public int updateByPrimaryKey (T record){
            return getBaseDao().updateByPrimaryKey(record);
        }

    public List<T> selectProducts(){
        return getBaseDao().selectProducts();
    }

    public PageInfo<T> getPageInfo(int pageNum,int pageSize){
        return getBaseDao().getPageInfo(pageNum,pageSize);
    }
    }
