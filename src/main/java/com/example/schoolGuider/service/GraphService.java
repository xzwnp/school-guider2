package com.example.schoolGuider.service;


import com.example.schoolGuider.bean.*;
import com.example.schoolGuider.dao.BuildingDao;
import com.example.schoolGuider.dao.EdgeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * service
 *
 * @author xzwnp
 * 2021/12/18
 * 22:51
 * @Description ：
 * Steps：
 */
@Service
public class GraphService {
    @Autowired
    private Graph graph;
    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private EdgeDao edgeDao;

    public List<Building> findAll() {
        System.out.println(graph.getVertex(0).getName());
        return buildingDao.findAll();
    }

    public List<Building> search(String name) {
        return buildingDao.findByName(name);
    }

    public String getShortestDist(int startIndex, int targetIndex) {
        return graph.dijkstra(graph.getVertex(startIndex), graph.getVertex(targetIndex));
    }

    public Building getBuilding(int id) {
        return buildingDao.findById(id);
    }

    public String getUnRepeatedPath(int index) {
        return graph.getUnRepeatedPath(index);
    }

    public ArrayList<String> getAllPaths(int startIndex, int endIndex) {
        ArrayList<ArrayList<Vertex>> VerticesList = graph.getAllPaths(startIndex, endIndex);
        ArrayList<String> paths = new ArrayList<>();
        VerticesList.forEach(vertices -> {
            StringBuffer sb = new StringBuffer();
            vertices.forEach(vertex -> sb.append(vertex.getName()).append("->"));
            paths.add(sb.toString());
        });
        return paths;
    }

}