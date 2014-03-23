package org.eslion.graphs;

/**
 * BFS or DFS
 */
public interface TraversalStrategy {
    void traverse(TraversalHandler<Node> handler, Node[] graph, Node start, boolean directed);
}
