package Y03_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Exam1_음료수_얼려먹기 {
    static int count = 0;
    static int N; // 세로
    static int M; // 가로
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        Exam1_음료수_얼려먹기 exam = new Exam1_음료수_얼려먹기();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /* 1) 세로 N, 가로 M 입력 받기 */
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        /* 2) 2차원 배열 셋팅 */
        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        dfs(0, 0);
        System.out.println(count);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true; // 방문 처리

        if (map[x][y] == 1) {
            count = count + 1;
        }

        // (0,1) (-1,0) (0,-1) (1,0)
        int[] dx = new int[]{0, -1, 0, 1};
        int[] dy = new int[]{1, 0, -1, 0};

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i]; // 세로
            int ny = y + dy[i]; // 가로

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (!visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }

    }
}
