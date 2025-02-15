package org.example;

public class TreeNodes {
    int[] arr;
    public TreeNodes Left;
    public TreeNodes Right;
    int SplitDimension;
    public TreeNodes(int[] arr,int splitDimension){
        this.arr=arr;
        this.SplitDimension=splitDimension;
    }
}
