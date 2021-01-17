package com.blacksea.djikistrajavaexample.djikistra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node>{
    private String name; // Node Name
    private List<Node> shortestPath = new LinkedList<Node>(); // Shortest path to source node from this.
    private Integer distance = Integer.MAX_VALUE; // distance of the node. Default max of integer
    private Map<Node,Integer> settledNodes = new HashMap<Node, Integer>(); // Neighborhood nodes. Node object and distance keeping in map

    public void addDestination(Node destination,Integer distance) {
        this.settledNodes.put(destination,distance);
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getSettledNodes() {
        return settledNodes;
    }

    public void setSettledNodes(Map<Node, Integer> settledNodes) {
        this.settledNodes = settledNodes;
    }

    public int compareTo(Node o) {
        return name.compareTo(o.name);
    }
}
