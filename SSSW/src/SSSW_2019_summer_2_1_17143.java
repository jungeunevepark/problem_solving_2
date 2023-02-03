import java.util.*;
import java.io.*;

class Shark {
    public int r;
    public int c;
    public int s;
    public int d;
    public int z;

    public Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }

}

public class SSSW_2019_summer_2_1_17143 {
    static int R;
    static int C;
    static int M;
    static int result;
    static List<Shark> s;
    static int king;
    static Shark[][] sea;
    static int[] dx = { 0, -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 0, 1, -1 }; // 입력 인덱스를 맞추려고

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sea = new Shark[R][C];

        int T = Integer.parseInt(st.nextToken());
        s = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            Shark sk = new Shark(r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            s.add(sk);
            sea[r][c] = sk;
        }
        while (king < C) {
            move_shark();
        }
        sb.append(result);
        System.out.println(sb.toString());

    }

    static void move_shark() {

        // 낚시왕의 이동 & 상어 사라짐
        for (int i = 0; i < R; i++) {
            if (sea[i][king] != null) {
                Shark sk = sea[i][king];
                result += sk.z;
                s.remove(sk);
                sea[i][king] = null;
                break;
            }
        }
        // 상어의 이동
        int nx, ny;
        int size = s.size();
        for (int i = size - 1; i >= 0; i--) {
            Shark sk = s.get(i);
            if (sea[sk.r][sk.c] == sk)
                sea[sk.r][sk.c] = null;
            for (int k = 0; k < sk.s; k++) {
                nx = sk.r + dx[sk.d];
                ny = sk.c + dy[sk.d];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    if (sk.d % 2 == 0)
                        sk.d -= 1;
                    else
                        sk.d += 1;
                    nx = sk.r + dx[sk.d];
                    ny = sk.c + dy[sk.d];
                }
                sk.r = nx;
                sk.c = ny;
            }
            if (sea[sk.r][sk.c] != null && s.indexOf(sea[sk.r][sk.c]) > i) {
                if (sea[sk.r][sk.c].z > sk.z) {
                    s.remove(i);
                } else {
                    s.remove(sea[sk.r][sk.c]);
                    sea[sk.r][sk.c] = sk;
                }
            } else {
                sea[sk.r][sk.c] = sk;
            }

        }
        king++;
    }
}