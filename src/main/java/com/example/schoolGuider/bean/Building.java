package com.example.schoolGuider.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * bean
 *
 * @author xzwnp
 * 2021/12/18
 * 22:36
 * @Description ：顶点表示主要建筑，存放建筑
 * 的编号、名称、简介等信息
 * Steps：
 */
@Entity
public class Building extends Vertex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String introduction;     //建筑的简介

    public Building(int id, String name, String introduction) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
    }

    public Building(String name, String introduction) {
        this.name = name;
        this.introduction = introduction;
    }

    public Building(String name) {
        super(name);
    }

    public Building() {
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
