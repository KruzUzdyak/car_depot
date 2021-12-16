package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.RefuelRecord;

import java.util.List;

public interface RefuelRecordDAO {

    RefuelRecord findById(int id) throws DAOException;

    List<RefuelRecord> findAll() throws DAOException;

    int saveNew(RefuelRecord record) throws DAOException;

    int deleteById(int id) throws DAOException;

    int update(RefuelRecord record) throws DAOException;
}
