package com.ivanart555.nakirunakcrm.services.generic;

import com.ivanart555.nakirunakcrm.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, K extends Serializable> {

    List<T> findAll() throws ServiceException;

    T findById(K id) throws ServiceException;

    int save(T t);

    void deleteById(K id) throws ServiceException;
}
