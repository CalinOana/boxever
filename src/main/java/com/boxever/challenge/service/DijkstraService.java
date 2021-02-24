package com.boxever.challenge.service;

import com.boxever.challenge.model.DijkstraNode;
import com.boxever.challenge.model.Node;

import java.util.*;

public class DijkstraService {
    private DijkstraService() {
    }

    public static DijkstraNode getShortestPath(Node source, Node target) {
        Map<Node, DijkstraNode> result = new HashMap<>();
        PriorityQueue<DijkstraNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(DijkstraNode::getMinCost));

        DijkstraNode resultSource = new DijkstraNode(source);
        resultSource.setPreviousNode(null);
        resultSource.setMinCost(0);
        priorityQueue.add(resultSource);
        result.put(source, resultSource);

        Set<Node> alreadyVisitedNodes = new HashSet<>();
        while (!priorityQueue.isEmpty()) {

            DijkstraNode currentNode = priorityQueue.poll();
            Node u = currentNode.getCurrentNode();

            if (alreadyVisitedNodes.contains(u)) {
                continue;
            }
            alreadyVisitedNodes.add(u);
            Map<Node, Integer> edges = u.getEdges();

            for (Map.Entry<Node, Integer> e : edges.entrySet()) {

                Node currentNeighbour = e.getKey();
                int currentNeighbourWeight = e.getValue();
                if (alreadyVisitedNodes.contains(currentNeighbour)) {
                    continue;
                }

                DijkstraNode existingNode = result.get(currentNeighbour);
                final int totalCostToNeighbour = currentNode.getMinCost() + currentNeighbourWeight;

                if (existingNode == null || existingNode.getMinCost() > totalCostToNeighbour) {
                    DijkstraNode newNode = new DijkstraNode(currentNeighbour);
                    newNode.setMinCost(totalCostToNeighbour);
                    newNode.setPreviousNode(currentNode);
                    priorityQueue.add(newNode);
                    result.put(currentNeighbour, newNode);
                }
            }
        }

        return result.get(target);
    }
}
