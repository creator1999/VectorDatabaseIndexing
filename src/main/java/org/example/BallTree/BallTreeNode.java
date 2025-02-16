package org.example.BallTree;

public class BallTreeNode {
    int[][] vetors;
    double radius;
    BallTreeNode left,right;

    public BallTreeNode(int[][] index, double radius){
        this.vetors=index;
        this.radius=radius;
    }
    public void clearArray(){
        vetors=new int[0][0];
    }
}
