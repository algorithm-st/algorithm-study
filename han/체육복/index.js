// 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다. 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
// 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
// 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다. 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
// 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
// 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

// n = 전체 학생의 수
// lost = 체육복을 도난당한 학생들의 번호가 담긴 배열
// reserve = 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열

function solution(n, lost, reserve) {
  let answer = 0;

  //모든 학생들이 체육복을 1개씩 가지고 있도록 설정
  const students = {};
  for (let i = 1; i <= n; i++) {
    students[i] = 1;
  }

  lost.forEach((v) => (students[v] -= 1)); //도난당한 학생의 체육복 개수를 줄이고
  reserve.forEach((v) => (students[v] += 1)); // 여벌의 옷이 있는 학생의 체욕복 개수를 늘림

  //체육복을 빌려줄 수 있는 학생들은 빌려줌
  for (let i = 1; i <= n; i++) {
    if (students[i] === 2 && students[i - 1] === 0) {
      students[i - 1]++;
      students[i]--;
    } else if (students[i] === 2 && students[i + 1] === 0) {
      students[i + 1]++;
      students[i]--;
    }
  }
  //총 학생들중 체육복이 있는 학생들 수를 세고 반환
  for (let i in students) {
    if (students[i] >= 1) {
      answer++;
    }
  }

  return answer;
}

console.log(solution(5, [2, 4], [1, 3, 5]));
