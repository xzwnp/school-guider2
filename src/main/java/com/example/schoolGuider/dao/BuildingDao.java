package com.example.schoolGuider.dao;

import com.example.schoolGuider.bean.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao.graphDao
 *
 * @author xzwnp
 * 2021/12/18
 * 22:57
 * @Description ：保存和读取building数据
 * Steps：
 */
@Repository
public interface BuildingDao extends JpaRepository<Building, Integer> {

    @Query("select b from Building b where b.name=:name")
    List<Building> findByName(@Param("name") String name);

    @Query("select b from Building b where b.id=:id")
    Building findById(@Param("id") int id);
}
