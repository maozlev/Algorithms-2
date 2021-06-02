import java.util.*;
import java.lang.*;

/** 
 find the diameter of graph
 diameter is the greatest distance between nodes
 input - matrix of neighbors
 output - diameter
 */
  public class Diameter {

    private boolean [][] mat;
    private final int n;
    static int x;
    static int maxCount;
    private final List<Integer>[] adj;

    public Diameter(boolean[][] adj_matrix) {
        n = Math.max(adj_matrix.length, adj_matrix[0].length);
        adj = new List[n];
        for (int i = 0; i < adj.length ; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < adj_matrix.length; i++) {
            for (int j = 0; j <adj_matrix[0].length ; j++) {
                if(adj_matrix[i][j]){
                    adj[i].add(j);
                }
            }
        }
    }

    public int get_diam() {
        return diameter(adj, n)-1;
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

    static void dfs(int node, int n, List<Integer>[] adj) {
        boolean[] visited = new boolean[n + 1];
        int count = 0;

        // Mark all the vertices as not visited
        Arrays.fill(visited, false);

        // Increment count by 1 for visited node
        dfsUtil(node, count + 1, visited, adj);

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

    public static void main(String[] args) {
            boolean[][] matrixTest1 = {
                    {false, true, false, false, false, false},
                    {true, false, true, true, false, false},
                    {false, true, false, false, false, false},
                    {false, true, false, false, true, true},
                    {false, false, false, true, false, false},
                    {false, false, false, true, false, false}
            };

            Diameter l = new Diameter(matrixTest1);
            long start = System.nanoTime();
            System.out.println("Test 1: " + ((l.get_diam() == 3) ? "Pass." : "Fail."));
            long end = System.nanoTime();
            System.out.println(end-start);
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

            Diameter d = new Diameter(matrixTest2);
            long start1 = System.nanoTime();
            System.out.println("Test 2: " + ((d.get_diam() == 7) ? "Pass." : "Fail."));
            long end1 = System.nanoTime();
            System.out.println(end1-start1);
            boolean[][] matrixTest3 = {{f, t, f, f, f, f, t, f, f, f, f, f, f, f, f, f, f},
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
            Diameter p = new Diameter(matrixTest3);
            long start2 = System.nanoTime();
            System.out.println("Test 3: " + ((p.get_diam() == 10) ? "Pass." : "Fail."));
            long end2 = System.nanoTime();
            System.out.println(end2-start2);


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
        long start4 = System.nanoTime();
        Diameter D= new Diameter(matrix2);
        int res = D.get_diam();
        System.out.println("ans = "+res);//ans = 7
        System.out.println("time = "+(System.nanoTime()-start));
        }
    }

