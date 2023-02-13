// n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
// 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.
// 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때,
// 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

//vertex : 메시의 삼각형은 우리가 모델링하는 물체의 기본적인 구성 성분이 되며, 메시 삼각형을 폴리곤(polygon) 혹은 기본형(primitive) 이라고도 부른다.
// 폴리곤에서 두 개의 변이 만나는 지점을 정점(vertex) 이라고 한다.

function solution(n, edges) {
  // 인접리스트 생성 (노드 크기만큼의 2차언 배열을 선언하는 과정)
  const connects = new Array(n).fill().map((_) => []);
  for (const e of edges) {
    connects[e[0] - 1].push(e[1] - 1);
    connects[e[1] - 1].push(e[0] - 1);
  }
  // -1을 하는 이유는 배열의 index는 0부터 시작하는 반면
  // 주어진 노드 번호는 1부터 시작하기 때문이다.

  // BFS
  const visited = [1];
  //deps임과 동시에 반환값에 개수로 사용할 것이므로 바로 1로 시작하게끔 초기화
  const queue = [0];

  while (queue.length) {
    const node = queue.shift(); // 삭제
    for (const n of connects[node]) {
      // 연결된 노드 중 지금 차례의 노드(next)가
      // 아직 방문하지 않은 상태라면
      if (!visited[n]) {
        // 방문처리함과 동시에 그 값을 현재 deps + 1로 삽입
        visited[n] = visited[node] + 1;
        queue.push(n); // 추가
      }
    }
  }

  const max = Math.max(...visited);
  return visited.filter((el) => el == max).length;
}
