package com.boxever.challenge.service;

import com.boxever.challenge.model.DijkstraNode;
import com.boxever.challenge.model.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Service
public class UserInputLooper {

    public void doConsoleLoop(List<Node> graph) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            System.out.println("Please type route with the form <airport1>-<airport2> (example: DUB-SYD), or type quit to exit, followed by enter");

            final String typedString = reader.readLine();

            System.out.println("You entered : " + typedString);

            if (checkIfUserWantsToQuit(typedString, "quit")) {
                break;
            }

            final String[] input = typedString.split("-");

            Optional<Node> source = graph.stream().filter(node -> node.getLabel().equalsIgnoreCase(input[0])).findAny();
            Optional<Node> destination = graph.stream().filter(node -> node.getLabel().equalsIgnoreCase(input[1])).findAny();

            if (source.isEmpty() || destination.isEmpty()) {
                printNodeNotFoundMessage(graph);
                continue;
            }

            DijkstraNode route = DijkstraService.getShortestPath(source.get(), destination.get());

            handleRoutePrinting(route);
        }
    }

    private boolean checkIfUserWantsToQuit(String typedString, String quit) {
        return typedString.equalsIgnoreCase(quit);
    }

    private void printNodeNotFoundMessage(List<Node> graph) {
        System.out.println("One or more of the airports entered are not found! Please supply only from the following list: ");
        graph.forEach(node -> System.out.print(node.getLabel() + " "));
        System.out.println();
    }

    private void handleRoutePrinting(DijkstraNode route) {
        if (route == null) {
            System.out.println("Path not found between source and destination!");
        } else {
            printRoute(route);
        }
    }
    private void printRoute(DijkstraNode route) {
        Stack<String> stuffToPrint = new Stack<>();
        stuffToPrint.push("time: " + route.getMinCost());

        while (route.getPreviousNode() != null) {
            final DijkstraNode previousNode = route.getPreviousNode();
            stuffToPrint.push(previousNode.getCurrentNode().getLabel() + " -- " + route.getCurrentNode().getLabel() + " ( " + (route.getMinCost() - previousNode.getMinCost()) + " )");
            route = previousNode;
        }
        while (!stuffToPrint.isEmpty()) {
            System.out.println(stuffToPrint.pop());
        }
    }
}
