const input = require("fs") //입출력
  .readFileSync("dev/stdin")
  .toString()
  .trim()
  .split("\n");

for (let i = 0; i < input.length; i++) {
  let text = input[i];
  let count = 0;
  let backSide = "0"; // '0' = 배(뒷면)
  let result = text.indexOf(backSide); // 요소가 있으면 1 반환 없으면 -1 반환

  while (result !== -1) {
    // 배가 나왔을 경우
    count++;
    result = text.indexOf(backSide, result + 1); // 다음 요소 서치
  }
  //A = 도, B : 개, C : 걸, D : 윷,C : 모
  switch (count) {
    case 0:
      console.log("E");
      break;
    case 1:
      console.log("A");
      break;
    case 2:
      console.log("B");
      break;
    case 3:
      console.log("C");
      break;
    case 4:
      console.log("D");
      break;
    default:
      break;
  }
}
