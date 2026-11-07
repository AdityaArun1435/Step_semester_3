package datastructures.assigment_problems;

public class FireDrillExits {
    static int[][] exitDistance(char[][] plan) {
        int rows = plan.length;
        int cols = plan[0].length;
        int[][] dist = new int[rows][cols];

        // queue of cells stored in two arrays (row and column)
        int[] queueRow = new int[rows * cols];
        int[] queueCol = new int[rows * cols];
        int head = 0;
        int tail = 0;

        // put every exit in the queue with distance 0, everything else starts at -1
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dist[r][c] = -1;
                if (plan[r][c] == 'E') {
                    dist[r][c] = 0;
                    queueRow[tail] = r;
                    queueCol[tail] = c;
                    tail++;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};   // up, down, left, right
        int[] dc = {0, 0, -1, 1};

        // one BFS that starts from all exits at once
        while (head < tail) {
            int r = queueRow[head];
            int c = queueCol[head];
            head++;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // open room inside the grid that has not been reached yet
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && plan[nr][nc] == '.' && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queueRow[tail] = nr;
                    queueCol[tail] = nc;
                    tail++;
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);
        for (int r = 0; r < result.length; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < result[r].length; c++) {
                sb.append(result[r][c]);
                if (c < result[r].length - 1) sb.append(" ");
            }
            System.out.println(sb);
        }
        // 2 -1 1 0
        // 1 2 2 -1
        // 0 -1 3 4
    }
}
