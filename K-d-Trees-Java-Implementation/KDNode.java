package com.bigame.kdtree;

/**
 * @author burakisik
 */
public class KDNode {
    private String name;
    private int height;
    private int weight;
    public KDNode left;
    public KDNode right;


    public KDNode(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.left = null;
        this.right = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
