import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// Задача 1. Режем строку на палиндромы. Нужно как можно меньше кусков.
public class PalindromeParts {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s = in.readLine().trim();
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        for (int i = 0; i < n; i++)
            pal[i][i] = true;
        for (int i = 0; i + 1 < n; i++)
            pal[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                pal[i][j] = s.charAt(i) == s.charAt(j) && pal[i + 1][j - 1];
            }
        }

        int[] parts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parts[i] = i;
            for (int j = 0; j < i; j++) {
                if (pal[j][i - 1] && parts[j] + 1 < parts[i])
                    parts[i] = parts[j] + 1;
            }
        }

        out.println(parts[n]);
        out.flush();
    }
}
