import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Задача 3. Дерево станций. Соседние одновременно брать нельзя, ценность может быть отрицательной.
public class StationSet {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        long[] value = new long[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
            value[i] = Long.parseLong(st.nextToken());

        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] parent = new int[n];
        int[] order = new int[n];
        int[] stack = new int[n];
        int sp = 0;
        int sz = 0;
        stack[sp++] = 0;
        parent[0] = 0;
        while (sp > 0) {
            int u = stack[--sp];
            order[sz++] = u;
            for (int v : adj[u]) {
                if (v == parent[u])
                    continue;
                parent[v] = u;
                stack[sp++] = v;
            }
        }
        parent[0] = -1;

        long[] take = new long[n];
        long[] skip = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            long with = value[u];
            long without = 0;
            for (int v : adj[u]) {
                if (v == parent[u])
                    continue;
                without += Math.max(take[v], skip[v]);
                with += skip[v];
            }
            take[u] = with;
            skip[u] = without;
        }

        out.println(Math.max(take[0], skip[0]));
        out.flush();
    }
}
