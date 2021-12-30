package com.example.schoolGuider.dao;

import com.example.schoolGuider.bean.Building;
import com.example.schoolGuider.bean.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * com.example.schoolGuider.dao
 *
 * @author xzwnp
 * 2021/12/20
 * 20:35
 * @Description ：
 * Steps：
 */
@Repository
public interface EdgeDao extends JpaRepository<Edge,Integer> {
    @Query("select count(Building) from Building")
    int GetNumOfBuildings();
}
