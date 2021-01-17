package com.blacksea.djikistrajavaexample.djikistra;

import java.util.*;

public class Graph {
    private Set<Node> nodes = new HashSet<Node>();

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Graph calculateShortestPath(Node sourceNode) {
        sourceNode.setDistance(0);
        // first we need to keep calculatedDistanceNodes and unCalculatedDistanceNodes
        Set<Node> calculatedDistanceNodes = new HashSet<Node>();
        Set<Node> unCalculatedDistanceNodes = new HashSet<Node>();
        // than add source node as unCalculated nodes
        unCalculatedDistanceNodes.add(sourceNode);
        // now we need to loop for uncalculatedDistanceNodes
        while (unCalculatedDistanceNodes.size() != 0) {
            // now we need to calculate lowest distance for node
            Node currentNode = getLowestDistance(unCalculatedDistanceNodes);
            unCalculatedDistanceNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> nodeMap : currentNode.getSettledNodes().entrySet()) {
                Node settledNode = nodeMap.getKey();
                Integer edgeWeight = nodeMap.getValue();
                if (!calculatedDistanceNodes.contains(settledNode)) {
                    calculateMinimumDistance(settledNode, edgeWeight, currentNode);
                    unCalculatedDistanceNodes.add(settledNode);
                }
            }
            calculatedDistanceNodes.add(currentNode);
        }
        return this;
    }

    public void printShortestPathDistances(Node sourceNode) {
        List<Node> nodeList = prepareForPrint(sourceNode);
        System.out.println();
        for (Node node : nodeList) {
            System.out.println("   " + sourceNode.getName() + "    |   " + node.getName() + "  | " + "   " + node.getDistance());
        }
    }

    private Integer getEdgeWeightBySourceAndTarget(Node sourceNode,Node targetNode) {
        return sourceNode.getSettledNodes().get(targetNode);
    }

    private List<Node> prepareForPrint(Node sourceNode) {
        calculateShortestPath(sourceNode);
        System.out.print(" Source | Dest | Distance");
        List<Node> nodeList = new ArrayList<Node>(this.nodes);
        Collections.sort(nodeList);
        return nodeList;
    }

    private Node getLowestDistance(Set<Node> unCalculatedDistanceNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unCalculatedDistanceNodes) {
            if (node.getDistance() < lowestDistance) {
                lowestDistance = node.getDistance();
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private Node calculateMinimumDistance(Node currentNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < currentNode.getDistance()) {
            currentNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<Node>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            currentNode.setShortestPath(shortestPath);
        }
        return currentNode;
    }
}
