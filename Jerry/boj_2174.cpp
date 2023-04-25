#include<iostream>
using namespace std;
using pii = pair<int, int>;

int A, B, N, M;
int m[102][102]{};
int x[102]{}, y[102]{}, d[102]{};
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int main() {
    ios_base::sync_with_stdio();
    cin.tie();

    cin >> A >> B;
    cin >> N >> M;
    char dir;
    for (int i = 1; i <= N; i++) {
        cin >> x[i] >> y[i];
        cin >> dir;
        m[x[i]][y[i]] = i;
        if (dir == 'N') d[i] = 0;
        else if (dir == 'E') d[i] = 1;
        else if (dir == 'S') d[i] = 2;
        else d[i] = 3;
    }

    int n, cnt;
    char ord;
    for (int i = 0; i < M; i++) {
        cin >> n;
        cin >> ord;
        cin >> cnt;
        if (ord == 'L') {
            d[n] = (d[n] + 104 - cnt) % 4;
            continue;
        }
        if (ord == 'R') {
            d[n] = (d[n] + cnt) % 4;
            continue;
        }
        if (ord == 'F') {
            int chk = 0;
            for (int j = 1; j <= cnt; j++) {
                int nx = x[n] + dx[d[n]] * j;
                int ny = y[n] + dy[d[n]] * j;
                if (nx < 1 || ny < 1 || nx > A || ny > B) {
                    chk = -1;
                    break;
                }
                if (m[nx][ny] > 0) {
                    chk = m[nx][ny];
                    break;
                }
            }
            if (chk == -1) {
                cout << "Robot " << n << " crashes into the wall";
                return 0;
            }
            if (chk != 0) {
                cout << "Robot " << n << " crashes into robot " << chk;
                return 0;
            }
            int nx = x[n] + dx[d[n]] * cnt;
            int ny = y[n] + dy[d[n]] * cnt;
            m[nx][ny] = m[x[n]][y[n]];
            m[x[n]][y[n]] = 0;
            x[n] = nx;
            y[n] = ny;
        }
    }
    cout << "OK";
}
