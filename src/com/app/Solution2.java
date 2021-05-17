package com.app;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution2 {

    // For an undirected graph with tree characteristics, we can choose any node as the root.
    // The result graph is then a rooted tree. Among all possible rooted trees,
    // those with minimum height are called minimum height trees (MHTs).
    // Given such a graph, write a function to find all the MHTs and return a list of their root labels.


    // The basic idea is "keep deleting leaves layer-by-layer, until reach the root."
    // Specifically, first find all the leaves, then remove them.
    // After removing, some nodes will become new leaves. So we can continue remove them.
    // Eventually, there is only 1 or 2 nodes left. If there is only one node left, it is the root.
    // If there are 2 nodes, either of them could be a possible root.

    // Good solution! Inspired by your idea and noticed that only the remaining neighbor of a leaf is needed
    // while deleting it, I use an INT to record the xor of one node's all neighbors.

    // n - number of nodes. Nodes [0, n - 1]
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] xorEdges = new int[n];
        int[] degrees = new int[n];
        List<Integer> result = new ArrayList<>();

        int edgeNum = edges.length;
        if (edgeNum == 0) {
            result.add(0);
            return result;
        }

        for (int[] e : edges) {
            degrees[e[0]]++;
            degrees[e[1]]++;
            xorEdges[e[0]] = xorEdges[e[0]] ^ e[1];
            xorEdges[e[1]] = xorEdges[e[1]] ^ e[0];
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                queue.add(i);
            }
        }

        int i;
        for (; edgeNum > 1; ) {
            queue.add(-1);
            for (; ; ) {
                i = queue.removeFirst();
                if (i == -1) {
                    break;
                }
                degrees[i]--;
                int j = xorEdges[i]; // j - число, в котором закодированы через XOR все (оставшиеся) грани узла i
                xorEdges[j] ^= i;
                degrees[j]--;
                if (degrees[j] == 1) {
                    queue.add(j);
                }
                edgeNum--;
            }
        }

        result.addAll(queue);

        return result;
    }
}