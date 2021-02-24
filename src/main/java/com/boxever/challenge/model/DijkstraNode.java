package com.boxever.challenge.model;

import lombok.Data;

@Data
public class DijkstraNode {
    private final Node currentNode;
    private int minCost;
    private DijkstraNode previousNode;

    public DijkstraNode(Node currentNode) {
        this.currentNode = currentNode;
    }
}
