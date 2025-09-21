import java.io.*;
import java.util.*;
public class closing {
    static int[] parent, size;
    static boolean[] open;
    static ArrayList<Integer>[] adj;
    static int components;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new FileWriter("closing.out"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] order = new int[n];
        for (int i = 0; i < n; i++) order[i] = Integer.parseInt(br.readLine());

        // DSU setup
        parent = new int[n + 1];
        size = new int[n + 1];
        open = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        components = 0;
        String[] ans = new String[n];

        // reverse open order
        for (int i = n - 1; i >= 0; i--) {
            int barn = order[i];
            open[barn] = true;
            components++;

            // union with open neighbors
            for (int nb : adj[barn]) {
                if (open[nb]) union(barn, nb);
            }

            if (components == 1) ans[i] = "YES";
            else ans[i] = "NO";
        }

        for (String s : ans) out.println(s);
        out.close();
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (size[a] < size[b]) { int tmp = a; a = b; b = tmp; }
            parent[b] = a;
            size[a] += size[b];
            components--;
        }
    }
}
