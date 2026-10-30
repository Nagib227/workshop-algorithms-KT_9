import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 2. С (1, 1) в (n, m) только вправо и вниз, # не проходим.
public class GridWays {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++)
            map[i] = in.readLine().trim().toCharArray();

        int[][] ways = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '#')
                    continue;
                if (i == 0 && j == 0) {
                    ways[0][0] = 1;
                    continue;
                }
                long sum = 0;
                if (i > 0)
                    sum += ways[i - 1][j];
                if (j > 0)
                    sum += ways[i][j - 1];
                ways[i][j] = (int) (sum % MOD);
            }
        }

        out.println(ways[n - 1][m - 1]);
        out.flush();
    }
}
