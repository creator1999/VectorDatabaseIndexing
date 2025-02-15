package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class KD_Tree {
    public static void printTree(TreeNodes root, int depth) {
        if (root == null) return;

        printTree(root.Right, depth + 1);

        System.out.println("  ".repeat(depth) + Arrays.toString(root.arr) + " (Dim: " + root.SplitDimension + ")");

        printTree(root.Left, depth + 1);
    }
    public static void sort(int[][] vectors, int dimension, int fromIndex, int toIndex) {
        Comparator<int[]> com = (int[] a, int[] b) -> Integer.compare(a[dimension], b[dimension]);

        // Sorting only in the given range
        Arrays.sort(vectors, fromIndex, toIndex, com);

        return;
    }
    public static TreeNodes createTree(int dimesion,int[][] vectors,int left,int right,int offset){
            if(left>right){
                return null;
            }
            if(dimesion==offset){
                dimesion=0;
            }
            sort(vectors,dimesion,left,right+1);
            int middle=(left+right)/2;
            TreeNodes root=new TreeNodes(vectors[middle],dimesion);

            root.Left=createTree(dimesion+1,vectors,left,middle-1,offset);
            root.Right=createTree(dimesion+1,vectors,middle+1,right,offset);
            return root;


    }
    public static void main(String[] args){
//        This should be considered as Embeddings Vector in 5-D space.
        int[][] vectors = {
                {3, 7, 2, 9, 1},{5, 8, 4, 0, 6}, {9, 2, 6, 3, 5}, {4, 1, 8, 7, 2}, {0, 5, 3, 6, 4},
                {7, 3, 1, 5, 8}, {2, 9, 7, 4, 0}, {6, 4, 0, 2, 9}, {1, 8, 5, 3, 7}, {5, 0, 9, 8, 3}
        };
        int dimensions = vectors[0].length;
        TreeNodes root = createTree(0, vectors, 0, vectors.length - 1, dimensions);
        System.out.println("KD-Tree Structure:");
        printTree(root, 0);


    }
}
