package com.example.schoolGuider.Config;

import com.example.schoolGuider.bean.Building;
import com.example.schoolGuider.bean.Edge;
import com.example.schoolGuider.bean.Graph;
import com.example.schoolGuider.dao.BuildingDao;
import com.example.schoolGuider.dao.EdgeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * com.example.schoolGuider.Config
 *
 * @author xzwnp
 * 2021/12/21
 * 17:48
 * @Description ：
 * Steps：
 */
@Configuration
public class GraphConfig {
    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private EdgeDao edgeDao;

    @Bean(name = "graph")
    public Graph initGraph() {
        Graph graph = new Graph((int)buildingDao.count());
        //插入结点
        List<Building> buildingList = buildingDao.findAll();
        buildingList.forEach(building -> graph.insertVertex(building));
        //插入边
        List<Edge> edgeList = edgeDao.findAll();
        edgeList.forEach(edge -> graph.insertEdge(edge.getFront(), edge.getRear(), edge.getWeight()));
        return graph;
    }

}
