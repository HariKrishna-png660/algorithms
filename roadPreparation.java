import java.io.*;
import java.util.*;

public class roadPreparation {
    static int par[];
    static int size[];

    public static int findPar(int u) {
        if (par[u] == u) {
            return u;
        }
        return par[u] = findPar(par[u]);
    }

    public static void merge(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int edges[][] = new int[m][3];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken()) - 1;
            edges[i][1] = Integer.parseInt(st.nextToken()) - 1;
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        int comp = n;
        long cost = 0;

        par = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int edge[] : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                merge(p1, p2);
                cost += w;
                comp--;
            }
        }

        if (comp == 1) {
            System.out.println(cost);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }
}
