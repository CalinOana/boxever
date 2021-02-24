package com.boxever.challenge.service;

import com.boxever.challenge.DataMock;
import com.boxever.challenge.model.DijkstraNode;
import com.boxever.challenge.model.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraServiceTest {

    @Test
    @DisplayName("Given graph that has valid path between 2 nodes when calling getShortestPath assert returns DijkstraNode with correct values")
    void getShortestPathPositiveTest() {
        final List<Node> nodes = DataMock.mockGraph();
        final Node source = nodes.stream().filter(node -> node.getLabel().equalsIgnoreCase("DUB")).findAny().get();
        final Node destination = nodes.stream().filter(node -> node.getLabel().equalsIgnoreCase("SYD")).findAny().get();

        final DijkstraNode shortestPath = DijkstraService.getShortestPath(source, destination);
        assertNotNull(shortestPath);

        assertEquals("SYD",shortestPath.getCurrentNode().getLabel());
        assertEquals(21,shortestPath.getMinCost());

        assertEquals("BKK",shortestPath.getPreviousNode().getCurrentNode().getLabel());
        assertEquals(10,shortestPath.getPreviousNode().getMinCost());

        assertEquals("LHR",shortestPath.getPreviousNode().getPreviousNode().getCurrentNode().getLabel());
        assertEquals(1,shortestPath.getPreviousNode().getPreviousNode().getMinCost());

        assertEquals("DUB",shortestPath.getPreviousNode().getPreviousNode().getPreviousNode().getCurrentNode().getLabel());
        assertEquals(0,shortestPath.getPreviousNode().getPreviousNode().getPreviousNode().getMinCost());
    }

    @Test
    @DisplayName("Given graph that has invalid path between 2 nodes when calling getShortestPath assert does not throw")
    void getShortestPathNegativeTest() {
        final List<Node> nodes = DataMock.mockGraph();
        final Node source = nodes.stream().filter(node -> node.getLabel().equalsIgnoreCase("DUB")).findAny().get();

        final DijkstraNode shortestPath =assertDoesNotThrow(()-> DijkstraService.getShortestPath(source, new Node("bla")));
        assertNull(shortestPath);
    }
}
