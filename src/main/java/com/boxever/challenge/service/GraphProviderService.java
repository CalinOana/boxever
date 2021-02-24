package com.boxever.challenge.service;

import com.boxever.challenge.model.Node;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


import static com.boxever.challenge.model.Node.addNeighbourWithSameWeight;

@Service
public class GraphProviderService {
    public List<Node> provideGraph() {
        Node dub = new Node("DUB");
        Node lhr = new Node("LHR");
        Node cdg = new Node("CDG");
        Node bos = new Node("BOS");
        Node bkk = new Node("BKK");
        Node ord = new Node("ORD");
        Node las = new Node("LAS");
        Node nyc = new Node("NYC");
        Node lax = new Node("LAX");
        Node syd = new Node("SYD");

        addNeighbourWithSameWeight(dub, lhr, 1);
        addNeighbourWithSameWeight(dub, cdg, 2);
        addNeighbourWithSameWeight(cdg, bos, 6);
        addNeighbourWithSameWeight(cdg, bkk, 9);
        addNeighbourWithSameWeight(ord, las, 2);
        addNeighbourWithSameWeight(lhr, nyc, 5);
        addNeighbourWithSameWeight(nyc, las, 3);
        addNeighbourWithSameWeight(bos, lax, 4);
        addNeighbourWithSameWeight(lhr, bkk, 9);
        addNeighbourWithSameWeight(bkk, syd, 11);
        addNeighbourWithSameWeight(lax, las, 2);
        addNeighbourWithSameWeight(dub, ord, 6);
        addNeighbourWithSameWeight(lax, syd, 13);
        addNeighbourWithSameWeight(las, syd, 14);

        return Arrays.asList(dub, lhr, cdg, bos, bkk, ord, las, nyc, lax, syd);
    }
}
