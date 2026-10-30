import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// Задача 6. Ровно k пунктов дерева, и они должны быть связными.
public class ConnectedGroup {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] value = new long[n + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= n; i++)
            value[i] = Long.parseLong(st.nextToken());

        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] parent = new int[n + 1];
        int[] order = new int[n];
        int[] stack = new int[n];
        int sp = 0;
        int sz = 0;
        stack[sp++] = 1;
        parent[1] = 1;
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
        parent[1] = 0;

        long neg = Long.MIN_VALUE / 4;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++)
            Arrays.fill(dp[i], neg);

        long[] next = new long[k + 1];
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            dp[u][1] = value[u];
            for (int c : adj[u]) {
                if (c == parent[u])
                    continue;
                Arrays.fill(next, neg);
                for (int have = 1; have <= k; have++) {
                    if (dp[u][have] == neg)
                        continue;
                    if (dp[u][have] > next[have])
                        next[have] = dp[u][have];
                    for (int add = 1; have + add <= k; add++) {
                        if (dp[c][add] == neg)
                            continue;
                        long sum = dp[u][have] + dp[c][add];
                        if (sum > next[have + add])
                            next[have + add] = sum;
                    }
                }
                System.arraycopy(next, 0, dp[u], 0, k + 1);
            }
        }

        long answer = neg;
        for (int u = 1; u <= n; u++)
            if (dp[u][k] > answer)
                answer = dp[u][k];

        out.println(answer);
        out.flush();
    }
}
