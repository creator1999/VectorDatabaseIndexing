package org.example.KDTree;

public class SearchKDTree {
    public  double euclideanDistance(int[] a, int[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }
    public TreeNodes vectorSearch(TreeNodes root, TreeNodes Best,double dist,int[] target){
        if(root==null){
            return Best;
        }
        double dis=euclideanDistance(target,root.arr);
        if(dis<dist){
            dist=dis;
            Best=root;
        }
        TreeNodes next=null;
        if(target[root.SplitDimension]<root.arr[root.SplitDimension]){
            next=root.Left;
        }
        else{
            next=root.Right;
        }
        Best=vectorSearch(next,Best,dist,target);
        return Best;


    }
}
