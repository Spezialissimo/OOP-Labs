package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class Graph_impl<N> implements Graph<N> {

    final private Map<N, Set<N>> edges = new LinkedHashMap<>();
    private int numberOfNodes;

    @Override
    public void addNode(final N node) {        
        if (edges.putIfAbsent(node, new HashSet<>()).equals(null)) {
            numberOfNodes ++;
        }
    }

    @Override
    public void addEdge(final N source, final N target) {
        if (edges.containsKey(source)) {
            edges.get(source).add(target);
        }
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<N>(edges.keySet());
    }

    @Override
    public Set<N> linkedNodes(Object node) {        
        return edges.get(node);
    }

    @Override
    public List<N> getPath(final N source, final N target) {            
        return null;
    }
}
