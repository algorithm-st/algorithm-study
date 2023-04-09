// 다이나믹 프로그래밍은 문제를 작은 부분 문제로 쪼갠 다음, 그 부분 문제들을 해결하여 원래 문제의 해결책을 찾는 알고리즘 기법.

function solution(N, number) {
  const dp = new Array(9).fill(0).map((el) => new Set()); //2차원 배열을 생성 / 중복된 값이 들어가지 않도록 Set으로 설정
  for (let i = 1; i < 9; i++) {
    //8의 이하의 숫자
    dp[i].add("1".repeat(i) * N); //값 초기화 Set개체의 맨 뒤에 value 의 새 요소 추가 // repeat  문자열을 주어진 횟수만큼 반복해 붙인 새로운 문자열 반환

    for (let j = 1; j < i; j++) {
      for (const arg1 of dp[j]) {
        for (const arg2 of dp[i - j]) {
          dp[i].add(arg1 + arg2); //dp[j]와 dp[i-j]를 조합해서 dp[i]를 구함 (+, -, *, / 요소)
          dp[i].add(arg1 - arg2);
          dp[i].add(arg1 * arg2);
          dp[i].add((arg1 / arg2) >> 0);
        }
      }
    }
    console.log("2", dp[i]);
    if (dp[i].has(number)) return i; // dp[i]에 number가 포함되어 있으면 i반환 포함되어있지 않으면 -1반환
  }

  return -1; // 최솟값이 8보다 큰 경우
}

console.log(solution(5, 12));
