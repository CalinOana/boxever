package com.boxever.challenge.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Node {
    @Getter
    private final String label;
    @Getter
    private final Map<Node, Integer> edges;

    public Node(String label) {
        this.label = label;
        edges = new HashMap<>();
    }

    public void addNeighbour(Node node, int distance) {
        edges.put(node, distance);
    }

    public static void addNeighbourWithSameWeight(Node node1, Node node2, int distance) {
        node1.addNeighbour(node2, distance);
        node2.addNeighbour(node1, distance);
    }
}
