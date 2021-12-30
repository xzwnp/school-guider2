package com.example.schoolGuider.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * com.example.schoolGuider.bean
 *
 * @author xzwnp
 * 2021/12/20
 * 20:32
 * @Description ：边信息
 * Steps：
 */
@Entity
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int front;
    private int rear;
    private int weight;

    public Edge(int front, int rear, int weight) {
        this.front = front;
        this.rear = rear;
        this.weight = weight;
    }

    public Edge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}