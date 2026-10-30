import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 5. Путь вправо и вниз. Экран не больше k раз обнуляет отрицательную клетку.
public class ScreenRoute {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] cell = new long[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; j++)
                cell[i][j] = Long.parseLong(st.nextToken());
        }

        long neg = Long.MIN_VALUE / 4;
        long[][][] best = new long[n][m][k + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int used = 0; used <= k; used++)
                    best[i][j][used] = neg;

        best[0][0][0] = cell[0][0];
        if (cell[0][0] < 0 && k > 0)
            best[0][0][1] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    continue;
                for (int used = 0; used <= k; used++) {
                    long come = neg;
                    if (j > 0)
                        come = best[i][j - 1][used];
                    if (i > 0 && best[i - 1][j][used] > come)
                        come = best[i - 1][j][used];
                    if (come == neg)
                        continue;
                    long with = come + cell[i][j];
                    if (with > best[i][j][used])
                        best[i][j][used] = with;
                    if (cell[i][j] < 0 && used < k && come > best[i][j][used + 1])
                        best[i][j][used + 1] = come;
                }
            }
        }

        long answer = neg;
        for (int used = 0; used <= k; used++)
            if (best[n - 1][m - 1][used] > answer)
                answer = best[n - 1][m - 1][used];

        out.println(answer);
        out.flush();
    }
}
