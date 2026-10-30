import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 4. Склеиваем соседние группы. Цена склейки — сумма размеров.
public class FragmentCost {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        long[] a = new long[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Long.parseLong(st.nextToken());

        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++)
            pref[i + 1] = pref[i] + a[i];

        long[][] cost = new long[n][n];
        for (int len = 2; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;
                long best = Long.MAX_VALUE;
                for (int mid = left; mid < right; mid++) {
                    long cur = cost[left][mid] + cost[mid + 1][right];
                    if (cur < best)
                        best = cur;
                }
                cost[left][right] = best + pref[right + 1] - pref[left];
            }
        }

        out.println(cost[0][n - 1]);
        out.flush();
    }
}
