package org.example.BallTree;

import java.util.*;

public class BallTree {

    public static double[] find_mean(int[][] vectors){
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
    public static double findRadius(double[] centroid,int[][] vectors){
        double radius=Double.MIN_VALUE;
        for(int i=0;i<vectors.length;i++){
            double temp=Distance.euclideanDistance(centroid,vectors[i]);
            radius=Math.max(radius,temp);
        }
        return radius;
    }

    public static List<int[][]> splitball(int[][] vectors){
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
        int[] fir=vectors[0];
        int[] sec=vectors[vectors.length-1];
        first.add(vectors[0]);
        second.add(vectors[vectors.length-1]);
        for(int i=1;i<vectors.length-1;i++){
            double dis1=Distance.euclideanDistance(fir,vectors[i]);
            double dis2=Distance.euclideanDistance(sec,vectors[i]);
            if(dis1>dis2){
                first.add(vectors[i]);
            }
            else{
                second.add(vectors[i]);
            }
        }
        int[][] first1=new int[first.size()][vectors[0].length];
        for(int i=0;i<first1.length;i++){
            first1[i]=first.get(i);
        }
        int[][] second1=new int[second.size()][vectors[0].length];
        for(int i=0;i<second1.length;i++){
            second1[i]=second.get(i);
        }
        List<int[][]> fin=new ArrayList<>();
        fin.add(first1);
        fin.add(second1);
        return fin;

    }
    public static BallTreeNode BallTreeBfs(int[][] vectors){
        Queue<BallTreeNode> balls=new ArrayDeque<>();
        double[] root_centroid=find_mean(vectors);
        double radius=findRadius(root_centroid,vectors);
        BallTreeNode root=new BallTreeNode(vectors,radius);
        balls.add(root);
        while (!balls.isEmpty()){
            BallTreeNode temp=balls.poll();
            int[][] vec=temp.vetors;
            List<int[][]> fin=splitball(vec);
            int[][] fir=fin.get(0);
            int[][] sec=fin.get(1);
            double[] c1=find_mean(fir);
            double[] c2=find_mean(sec);
            double r1=findRadius(c1,fir);
            double r2=findRadius(c2,sec);
            BallTreeNode left=new BallTreeNode(fir,r1);
            BallTreeNode right=new BallTreeNode(sec,r2);
            temp.left=left;
            temp.right=right;
            temp.clearArray();
            if(left.vetors.length>2){
                balls.add(left);
            }
            if(right.vetors.length>2){
                balls.add(right);
            }



        }
        return root;



    }
    public static void main(String[] args){
        int[][] vectors = {
                {3, 7, 2, 9, 1},{5, 8, 4, 0, 6}, {9, 2, 6, 3, 5}, {4, 1, 8, 7, 2}, {0, 5, 3, 6, 4},
                {7, 3, 1, 5, 8}, {2, 9, 7, 4, 0}, {6, 4, 0, 2, 9}, {1, 8, 5, 3, 7}, {5, 0, 9, 8, 3}
        };
        System.out.println(BallTreeBfs(vectors));

    }
}
