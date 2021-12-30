package com.example.schoolGuider;

import com.example.schoolGuider.bean.Graph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.Collection;

/**
 * com.example.schoolGuider
 *
 * @author xzwnp
 * 2021/12/29
 * 21:15
 * @Description ：
 * Steps：
 */
@SpringBootTest
public class GraphTest {
    @Autowired
    Graph graph;

    @Test
    public void pathTest() {
        System.out.println(graph.getUnRepeatedPath(1));
    }

    @Test
    public void test2() {
        graph.getAllPaths(5,18).forEach(path -> {
            path.forEach(vertex -> System.out.print(vertex.getName() + "->"));
            System.out.println();
        });

    }
}
