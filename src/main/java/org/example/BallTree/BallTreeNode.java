package org.example.BallTree;

public class BallTreeNode {
    int[][] vetors;
    double[] centroid;
    double radius;
    BallTreeNode left,right;

    public BallTreeNode(int[][] index, double radius,double[] centroid){
        this.vetors=index;
        this.radius=radius;
        this.centroid=centroid;
    }
    public void clearArray(){
        vetors=new int[0][0];
    }
}
