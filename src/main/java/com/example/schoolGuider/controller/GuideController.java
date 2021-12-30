package com.example.schoolGuider.controller;

import com.example.schoolGuider.bean.Building;
import com.example.schoolGuider.bean.Vertex;
import com.example.schoolGuider.service.GraphService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.schoolGuider.Controller
 *
 * @author xzwnp
 * 2021/12/21
 * 16:53
 * @Description ：
 * Steps：
 */
@Api("主要功能控制器")
@RestController
@RequestMapping("/guide")
public class GuideController {
    @Autowired
    GraphService graphService;

    @ApiOperation("获取所有的景点,返回值是一个包括所有建筑对象(建筑序号,建筑名,建筑描述)的JSON对象")
    @GetMapping("/getAll")
    public List<Building> getAll() {
        return graphService.findAll();
    }

    @ApiOperation("根据建筑名查找建筑,返回值为所有建筑对象")
    @GetMapping("/findByName/{name}")
    public List<Building> getBuilding(@ApiParam("建筑名") @PathVariable("name") String name) {
        return graphService.search(name);
    }

    @ApiOperation("根据建筑序号查找建筑")
    @GetMapping("findById/{id}")
    public Building getBuilding(@PathVariable("id") int id) {
        return graphService.getBuilding(id);
    }

    @ApiOperation("输入起点,获取一条不重复走完所有建筑的路径,最后回到起点,(不可能遍历所有结点)")
    @GetMapping("unRepeatedPath/{startIndex}")
    public String getRecommendPath(@PathVariable("startIndex") int start) {
        return graphService.getUnRepeatedPath(start);
    }

    @ApiOperation("获取起点到终点的最短路线")
    @GetMapping("shortestDist/{startIndex}/{endIndex}")
    public String getShortestDist(@PathVariable("startIndex") int startIndex, @PathVariable("endIndex") int endIndex) {
        return graphService.getShortestDist(startIndex, endIndex);
    }
    @ApiOperation("获取从A点到B点的所有可行路径,返回一个列表,内含所有路径,请注意判空")
    @GetMapping("getAllPaths/{startIndex}/{endIndex}")
    public ArrayList<String> getAllPaths(int startIndex, int endIndex){
        return graphService.getAllPaths(startIndex,endIndex);
    }

}
