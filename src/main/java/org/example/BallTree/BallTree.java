package org.example.BallTree;

import java.util.*;

public class BallTree {

    public double[] find_mean(int[][] vectors){
        double[] centroid =new double[vectors[0].length];
        for(int i=0;i<vectors.length;i++){
            for(int j=0;j<vectors[0].length;j++){
                centroid[j]+=vectors[i][j];
            }
        }
        for(int i=0;i<centroid.length;i++){
            centroid[i]/=vectors.length;
        }
        return centroid;
    }
    public double findRadius(double[] centroid,int[][] vectors){
        double radius=Double.MAX_VALUE;
        for(int i=0;i<vectors.length;i++){
            double temp=Distance.euclideanDistance(centroid,vectors[i]);
            radius=Math.min(radius,temp);
        }
        return radius;
    }

    public int[][] splitball(int[][] vectors){
        Comparator<int[]> cmt=(a,b)->{
            int sum=0;
            int sum1=0;
            for(int i=0;i<a.length;i++){
                sum+=(a[i]*a[i]);
                sum1+=(b[i]*b[i]);
            }
            return Integer.compare(sum,sum1);

        };
        Arrays.sort(vectors,cmt);
        List<int[]> first=new ArrayList<>();
        List<int[]> second=new ArrayList<>();
        int[] fir=new int[vectors[0].length];
        int[] sec=new int[vectors[0].length];
        first.add(vectors[0]);
        second.add(vectors[vectors.length-1]);
        for(int i=0;i<vectors.length;i++){
            double dis1=Distance.euclideanDistance(fir,vectors[i]);
            double dis2=Distance.euclideanDistance(sec,vectors[i]);
            if(dis1>dis2){
                first.add(vectors[i]);
            }
            else{
                second.add(vectors[i]);
            }
        }


    }
    public void BallTreeBfs(int[][] vectors){
        Queue<BallTreeNode> balls=new ArrayDeque<>();
        double[] root_centroid=find_mean(vectors);
        double radius=findRadius(root_centroid,vectors);
        BallTreeNode root=new BallTreeNode(vectors,radius);
        balls.add(root);
        while (!balls.isEmpty()){
            BallTreeNode temp=balls.poll();
            int[][] vec=temp.vetors;
            int[][] splitted=splitball(vec);
            int[] fir=splitted[0];
            int[] sec=splitted[1];

        }



    }
    public static void main(String[] args){
        int[][] vectors = {
                {3, 7, 2, 9, 1},{5, 8, 4, 0, 6}, {9, 2, 6, 3, 5}, {4, 1, 8, 7, 2}, {0, 5, 3, 6, 4},
                {7, 3, 1, 5, 8}, {2, 9, 7, 4, 0}, {6, 4, 0, 2, 9}, {1, 8, 5, 3, 7}, {5, 0, 9, 8, 3}
        };

    }
}
