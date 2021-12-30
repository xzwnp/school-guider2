package com.example.schoolGuider.bean;

/**
 * bean
 *
 * @author xzwnp
 * 2021/12/20
 * 19:02
 * @Description ：
 * Steps：
 */
public abstract class Vertex {


    int id;
    String name;

    public Vertex(String name) {
        this.name = name;
    }

    public Vertex() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
