package Y04_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Exam1_미로탈출 {
    // static int count = 0;
    static int N; // 세로
    static int M; // 가로
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        Exam1_미로탈출 exam = new Exam1_미로탈출();

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

        bfs(0, 0);
        System.out.println(map[N-1][M-1]);
    }

    public static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));

        // (0,1) (-1,0) (0,-1) (1,0)
        int[] dx = new int[]{0, -1, 0, 1};
        int[] dy = new int[]{1, 0, -1, 0};

        while(!q.isEmpty()) {
            Node node = q.poll();

            x = node.getX();
            y = node.getY();

            visited[x][y] = true; // 방문 처리

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i]; // 세로
                int ny = y + dy[i]; // 가로

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visited[nx][ny]) {
                        if (map[nx][ny] == 1) {
                            // count = count + 1;
                            map[nx][ny] = map[x][y] + 1;
                            q.offer(new Node(nx, ny));
                        }
                    }
                }
            }
        }

    }
}

class Node {

    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
