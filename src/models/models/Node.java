
package models;


import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;

class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    private String feature;
    private double threshold;
    private Node left;
    private Node right;
    private int prediction;
    private boolean isLeaf;

    public Node() {
        this.isLeaf = false;
        this.prediction = 0;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
//
//
