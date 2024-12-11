
package models;

import java.io.Serializable;
import java.util.*;



class DecisionTree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Node root;
    private static final Random rand = new Random();
    private int maxDepth; // Add maxDepth parameter
    public Set<String> usedFeatures = new HashSet<>();

    public DecisionTree(int maxDepth) {
        this.maxDepth = maxDepth; // Initialize maxDepth
    }

    public void train(List<Map<String, Object>> data) {
        this.root = buildTree(data, 0); // Pass current depth during training
    }

    public int predict(Map<String, Double> input) {
        Node currentNode = root;
        while (!currentNode.isLeaf()) {
            String feature = currentNode.getFeature();
            double threshold = currentNode.getThreshold();
            if ((input.get(feature) <= threshold)) { //STH WRONG 
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return currentNode.getPrediction();
    }

    private Node buildTree(List<Map<String, Object>> data, int currentDepth) {
        if (data.isEmpty() || currentDepth >= maxDepth) {
            return createLeafNode(data); // Create a leaf node if max depth is reached
        }

        // Randomly select a subset of features
        List<String> features = new ArrayList<>(data.get(0).keySet());
        features.remove("stroke"); // Remove target variable
        int numFeatures = features.size();
        Collections.shuffle(features, rand);
        
     // Ensure no feature is selected more than once
        
//        List<String> selectedFeatures = new ArrayList<>();
//        for (String feature : features) {
//            	if (usedFeatures.contains(feature)) {
//            		selectedFeatures.add(feature);
//            		usedFeatures.add(feature);
//                    if (selectedFeatures.size() == numFeatures) break;
//                    
//            	}
//                
//        }
        
        String bestFeature = null;
        
        double bestThreshold = 0;
        double bestGini = Double.MAX_VALUE;

        for (String feature : features) {
            Set<Double> thresholds = new HashSet<>();
            for (Map<String, Object> row : data) {
                thresholds.add((double) row.get(feature));
            }
            for (double threshold : thresholds) {
                double gini = calculateGini(data, feature, threshold);
                if (gini < bestGini && !usedFeatures.contains(feature)) {
                    bestGini = gini;
                    bestFeature = feature;
                    bestThreshold = threshold;
                }
            }
        }

        if (bestFeature == null) {
            return createLeafNode(data); // Create a leaf node if no valid split is found
        }

        // Split data into left and right subsets
        List<Map<String, Object>> leftData = new ArrayList<>();
        List<Map<String, Object>> rightData = new ArrayList<>();
        for (Map<String, Object> row : data) {
            if ((double) row.get(bestFeature) <= bestThreshold) {
                leftData.add(row);
            } else {
                rightData.add(row);
            }
        }

        Node node = new Node();
        node.setFeature(bestFeature);
        usedFeatures.add(bestFeature);
        node.setThreshold(bestThreshold);
        node.setLeft(buildTree(leftData, currentDepth + 1));
        node.setRight(buildTree(rightData, currentDepth + 1));
        return node;
    }

    private Node createLeafNode(List<Map<String, Object>> data) {
        Node leaf = new Node();
        leaf.setLeaf(true);
        if (!data.isEmpty()) {
            int countStroke = 0;
            for (Map<String, Object> row : data) {
                countStroke += (int) row.get("stroke");
            }
            leaf.setPrediction(countStroke >= (data.size() / 2) ? 1 : 0);
        } else {
            leaf.setPrediction(0); // Default prediction
        }
        return leaf;
    }

    private double calculateGini(List<Map<String, Object>> data, String feature, double threshold) {
        List<Map<String, Object>> left = new ArrayList<>();
        List<Map<String, Object>> right = new ArrayList<>();
        for (Map<String, Object> row : data) {
            if ((double) row.get(feature) <= threshold) {
                left.add(row);
            } else {
                right.add(row);
            }
        }

        double giniLeft = calculateGiniForSubset(left);
        double giniRight = calculateGiniForSubset(right);

        double totalSize = data.size();
        return (left.size() / totalSize) * giniLeft + (right.size() / totalSize) * giniRight;
    }

    private double calculateGiniForSubset(List<Map<String, Object>> subset) {
        if (subset.isEmpty()) return 0.0;

        int[] counts = new int[2];
        for (Map<String, Object> row : subset) {
            counts[(int) row.get("stroke")]++;
        }

        double total = counts[0] + counts[1];
        double gini = 1.0;
        for (int count : counts) {
            double proportion = count / total;
            gini -= proportion * proportion;
        }
        return gini;
    }
    
    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int depth) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < depth; i++) {
            System.out.print("|	");
        }

        if (node.isLeaf()) {
            System.out.println("Prediction: " + node.getPrediction() );
        } else {
            System.out.println("Feature: " + node.getFeature() + " <= " + node.getThreshold()  );
            printTree(node.getLeft(), depth + 1);
            printTree(node.getRight(), depth + 1);
        }
    }
}

