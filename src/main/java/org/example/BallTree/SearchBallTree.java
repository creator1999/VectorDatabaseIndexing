package org.example.BallTree;

public class SearchBallTree {
    private int[] bestVector;
    private double bestDistance;

    public int[] findNearest(BallTreeNode root, int[] target) {
        bestVector = null;
        bestDistance = Double.POSITIVE_INFINITY;
        searchHelper(root, target);
        return bestVector;
    }

    private void searchHelper(BallTreeNode node, int[] target) {
        if (node == null) return;

        // Process leaf nodes
        if (node.left==null && node.right==null) {
            for (int[] vector : node.vetors) {
                double currentDistance = Distance.euclideanDistance(vector, target);
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance;
                    bestVector = vector;
                }
            }
            return;
        }

        // Process internal nodes
        double distToCentroid = Distance.euclideanDistance(node.centroid, target);
        double minPossibleDistance = distToCentroid - node.radius;

        // Prune if no possible improvement
        if (minPossibleDistance >= bestDistance) return;

        // Search closer child first
        double leftDistance = Distance.euclideanDistance(node.left.centroid, target);
        double rightDistance = Distance.euclideanDistance(node.right.centroid, target);

        if (leftDistance < rightDistance) {
            searchHelper(node.left, target);
            double rightMinDistance = rightDistance - node.right.radius;
            if (rightMinDistance < bestDistance) {
                searchHelper(node.right, target);
            }
        } else {
            searchHelper(node.right, target);
            double leftMinDistance = leftDistance - node.left.radius;
            if (leftMinDistance < bestDistance) {
                searchHelper(node.left, target);
            }
        }
    }
}