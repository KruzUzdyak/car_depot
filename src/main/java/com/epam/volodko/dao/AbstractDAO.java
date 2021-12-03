package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;

import java.util.List;

public interface AbstractDAO<T> {

    T retrieveById(int id) throws DAOException;

    int save(T entity) throws DAOException;

    List<T> findAll() throws DAOException;

    int update(T entity) throws DAOException;

    int deleteByID(int id) throws DAOException;

}
