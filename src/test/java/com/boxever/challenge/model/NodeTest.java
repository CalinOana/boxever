package com.boxever.challenge.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.boxever.challenge.model.Node.addNeighbourWithSameWeight;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeTest {

    @Test
    @DisplayName("Given 2 nodes when doing addNeighbourWithSameWeight assert edges are added properly")
    void addNeighbourWithSameWeightPositive() {
        Node dub = new Node("LAX");
        Node lax = new Node("LAX");

        addNeighbourWithSameWeight(dub, lax, 6);

        assertEquals(6, dub.getEdges().get(lax));
        assertEquals(6, lax.getEdges().get(dub));
    }
}
