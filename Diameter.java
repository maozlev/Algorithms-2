import java.util.*;
import java.lang.*;

//ID: 204594758

/**
 find the diameter of graph
 diameter is the greatest distance between nodes
 input - matrix of neighbors
 output - diameter
 */

public class Diameter {

    public int get_diam() {
        return diameter(adj, n)-1;
    }

    static int diameter(List<Integer>[] adj, int n) {
        maxCount = Integer.MIN_VALUE;

        /* DFS from a random node and then see
        farthest node X from it*/
        dfs(1, n, adj);

        /* DFS from X and check the farthest node
        from it */
        dfs(x, n, adj);

        return maxCount;
    }

    static void dfs(int node, int n, List<Integer>[] adj) {
        boolean[] visited = new boolean[n + 1];
        int count = 0;

        // Mark all the vertices as not visited
        Arrays.fill(visited, false);

        // Increment count by 1 for visited node
        dfsUtil(node, count + 1, visited, adj);

    }

    static void dfsUtil(int node, int count, boolean[] visited, List<Integer>[] adj) {
        visited[node] = true;
        count++;
        List<Integer> l = adj[node];
        for(Integer i: l) {
            if(!visited[i]){
                if (count >= maxCount) {
                    maxCount = count;
                    x = i;
                }
                dfsUtil(i, count, visited, adj);
            }
        }
    }

    private static int n = 0;
    static int x;
    static int maxCount;
    static LinkedList<Integer>[] adj;
    static int[] diameter1, visited;
    static Queue<Integer> q;

    public Diameter(boolean[][] adj_matrix) {
        n = Math.max(adj_matrix.length, adj_matrix[0].length);
        diameter1 = new int[n];
        visited = new int[n];
        adj = new LinkedList[n];
        q = new LinkedList<>();
        int i = 0;
        while (i < adj.length ) {
            adj[i] = new LinkedList<>();
            i++;
        }
        i=0;
        int j = -1;
        while( i < n) {
            j=0;
            while(j < n) {
                if(adj_matrix[i][j]){
                    adj[i].add(j);
                }
                j++;
            }
            i++;
        }
    }

    public int findDiameter() {
        int init = bfs(1);
        q.clear();
        int i=0;
        while(i <= n-1){
            visited[i] = 0;
            diameter1[i] = 0;
            i++;
        }
        int val = bfs(init);
        return diameter1[val];
    }

    /**
    The Function to do bfs traversal.
     It uses iterative approach to do bfs
     */

    static int bfs(int init) {
        // Pushing each node in queue
        q.add(init);

        // Mark the traversed node visited
        visited[init] = 1;
        while (!q.isEmpty()) {
            int u = q.peek();
            q.remove();
            int i = 0;
            while( i < adj[u].size()) {
                if (visited[adj[u].get(i)] == 0) {
                    visited[adj[u].get(i)] = 1;
                    // Considering weight of edges equal to 1
                    diameter1[adj[u].get(i)] += diameter1[u] + 1;
                    q.add(adj[u].get(i));
                }
                i++;
            }
        }

        int in = 0;
        int i=0;
        while( i <= n-1) {
            if(diameter1[i] > diameter1[in])
                in = i;
            i++;
        }

        // return index of max value in diameter
        return in;
    }

    public static void main(String[] args) {
            boolean[][] matrixTest1 = {
                    {false, true, false, false, false, false},
                    {true, false, true, true, false, false},
                    {false, true, false, false, false, false},
                    {false, true, false, false, true, true},
                    {false, false, false, true, false, false},
                    {false, false, false, true, false, false}
            };

            long start1 = System.nanoTime();
            Diameter l1 = new Diameter(matrixTest1);
            System.out.println("Test 1: " + ((l1.get_diam() == 3) ? "Pass." : "Fail."));
            long end1 = System.nanoTime();
            System.out.println("time DFS recurs: "+(end1-start1));

            long start = System.nanoTime();
            Diameter l = new Diameter(matrixTest1);
            System.out.println("Test 1: " + ((l.findDiameter() == 3) ? "Pass." : "Fail."));
            long end = System.nanoTime();
            System.out.println("time bfs iterative: "+(end-start));

            boolean t = true, f = false;
            boolean[][] matrixTest2 = {
                    {false, false, false, true, false, false, true, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, true, false, false},
                    {false, false, false, false, false, false, false, false, true, false, false, true},
                    {true, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, true, false, false, true, true, false, false},
                    {false, false, false, false, true, false, true, true, false, false, false, false},
                    {true, false, false, false, false, true, false, false, false, false, false, false},
                    {false, false, false, false, false, true, false, false, false, false, false, false},
                    {false, false, true, false, true, false, false, false, false, false, true, false},
                    {false, true, false, false, true, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, true, false, false, false},
                    {false, false, true, false, false, false, false, false, false, false, false, false}};

            long start2 = System.nanoTime();
            Diameter d = new Diameter(matrixTest2);
            System.out.println("Test 2: " + ((d.get_diam() == 7) ? "Pass." : "Fail."));
            long end2 = System.nanoTime();
            System.out.println("Dfs recurs time: "+(end2-start2));

            long start3 = System.nanoTime();
            Diameter d1 = new Diameter(matrixTest2);
            System.out.println("Test 2: " + ((d1.findDiameter() == 7) ? "Pass." : "Fail."));
            long end3 = System.nanoTime();
            System.out.println("Bfs iterative time: "+(end3-start3));

            boolean[][] matrixTest3 = {
                    {f, t, f, f, f, f, t, f, f, f, f, f, f, f, f, f, f},
                    {t, f, f, f, f, t, f, f, f, f, f, t, f, f, f, f, f},
                    {f, f, f, f, t, f, f, f, f, t, f, f, f, f, f, f, f},
                    {f, f, f, f, t, f, f, t, f, f, f, f, f, f, f, f, f},
                    {f, f, t, t, f, t, f, f, f, f, f, f, f, f, f, f, f},
                    {f, t, f, f, t, f, f, f, f, f, f, f, f, f, f, f, f},
                    {t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f},
                    {f, f, f, t, f, f, f, f, f, f, f, f, t, t, f, f, f},
                    {f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f},
                    {f, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f},
                    {f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f},
                    {f, t, f, f, f, f, f, f, t, f, t, f, f, f, f, f, f},
                    {f, f, f, f, f, f, f, t, f, f, f, f, f, f, f, f, f},
                    {f, f, f, f, f, f, f, t, f, f, f, f, f, f, t, f, f},
                    {f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f},
                    {f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t},
                    {f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f}};

            long start4 = System.nanoTime();
            Diameter p = new Diameter(matrixTest3);
            System.out.println("Test 3: " + ((p.get_diam() == 10) ? "Pass." : "Fail."));
            long end4 = System.nanoTime();
            System.out.println("Dfs recurs time: "+(end4-start4));

            long start5 = System.nanoTime();
            Diameter p1 = new Diameter(matrixTest3);
            System.out.println("Test 3: " + ((p1.findDiameter() == 10) ? "Pass." : "Fail."));
            long end5 = System.nanoTime();
            System.out.println("Bfs iterative time: "+(end5-start5));


        boolean[][]matrix2 = {
                //0 1 2 3 4 5 6 7 8 9 10 11
                {f,f,f,t,f,f,t,f,f,f,f,f},//0
                {f,f,f,f,f,f,f,f,f,t,f,f},//1
                {f,f,f,f,f,f,f,f,t,f,f,t},//2
                {t,f,f,f,f,f,f,f,f,f,f,f},//3
                {f,f,f,f,f,t,f,f,t,t,f,f},//4
                {f,f,f,f,t,f,t,t,f,f,f,f},//5
                {t,f,f,f,f,t,f,f,f,f,f,f},//6
                {f,f,f,f,f,t,f,f,f,f,f,f},//7
                {f,f,t,f,t,f,f,f,f,f,t,f},//8
                {f,t,f,f,t,f,f,f,f,f,f,f},//9
                {f,f,f,f,f,f,f,f,t,f,f,f},//10
                {f,f,t,f,f,f,f,f,f,f,f,f}};//11

        long start6 = System.nanoTime();
        Diameter P = new Diameter(matrix2);
        System.out.println("Test 4: " + ((P.get_diam() == 7) ? "Pass." : "Fail."));
        long end6 = System.nanoTime();
        System.out.println("Dfs recurs time: "+(end6-start6));

        long start7 = System.nanoTime();
        Diameter P1 = new Diameter(matrix2);
        System.out.println("Test 4: " + ((P1.findDiameter() == 7) ? "Pass." : "Fail."));
        long end7 = System.nanoTime();
        System.out.println("Bfs iterative time: "+(end7-start7));
        }
    }

