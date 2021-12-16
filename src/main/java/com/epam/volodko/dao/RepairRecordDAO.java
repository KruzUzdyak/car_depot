package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.RepairRecord;

import java.util.List;

public interface RepairRecordDAO {

    RepairRecord findById(int id) throws DAOException;

    List<RepairRecord> findAll() throws DAOException;

    int saveNew(RepairRecord record) throws DAOException;

    int deleteById(int id) throws DAOException;

    int update(RepairRecord record) throws DAOException;
}
