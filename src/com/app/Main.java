package com.app;

public class Main {

    // https://leetcode.com/problems/minimum-height-trees/discuss/76104/C++-Solution.-O(n)-Time-O(n)-Space/79548
    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[][] edges3 = {
                {0, 3},
                {1, 3},
                {2, 3},
                {3, 6},
                {6, 7},
                {0, 4},
                {4, 5}
        };
        int n3 = 8;

        System.out.print(solution2.findMinHeightTrees(n3, edges3));
    }
}
