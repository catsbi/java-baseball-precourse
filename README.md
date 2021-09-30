# 숫자 야구 게임

## 요구 사항
1. `1` 부터 `9`까지 서로 다른 수로 이루어진 3자리의 수를 맞춰야 한다.
2. 같은 수가 같은 자리에 있으면 `스트라이크` 다른 자리에 있으면 `볼` 같은 수가 없으면 `포볼` 혹은 `낫싱`이라는 힌트를 얻고, 힌트를 이용해 상대방의 수를 맞추면 승리한다.
   - Ex: 상대방이 `425`일 때, `123`을 제시하면  `1 스트라이크`
   - Ex: 상대방이 `425`일 때, `456`을 제시하면 `1스트라이크` `1볼`
   - Ex: 상대방이 `425`일 때, `789`를 제시하면 `낫싱(혹은 포볼)`
3. 상대방(컴퓨터)는 `1 ~ 9`까지 서로 다른 임의의 수를 선택한다.
4. 플레이어는 정답이라 생각하는 3개의 숫자를 입력한다. 
5. 컴퓨터는 두 값을 비교해 결과를 출력한다. 
6. 게임은 반복되며, 3개의 숫자를 모두 맞추는 경우(`3 스트라이크`) 게임이 종료된다.
7. 게임이 종료되면 게임을 `재시작`하거나 `완전 종료`를 선택할 수 있다.

## 제약 조건
- 프로그램의 시작 메서드는 다음 경로의 `main()` 메서드이다
  >src/main/java/baseball/Application.java

- 프로그램은 JDK 8에서 실행 가능해야 한다.
- Random, Scanner 는 nextstep.util패키지에서 제공하는 API를 활용해야한다.
  > 랜덤 값 추출: nextstep.utils.Randoms.pickNumberInRange();
  > 
  > 사용자 값 입력: nextstep.utils.Console.readLine()

- (Optional) 제공된 소스코드를 참조해 Test Case를 추가하자.

## 구현해야 하는 기능

- 컴퓨터는 임의의 숫자 3개를 만든다. 
- 유저가 입력한 임의의 숫자 3개로 객체를 만든다. 
- 입력한 숫자를 컴퓨터가 생성한 숫자와 비교한다
  - 동일한 숫자가 있고, 위치도 동일하면 스트라이크
  - 동일한 숫자가 있고, 위치가 다르면 볼
  - 동일한 숫자가 없으면 패스
  - 동일한 숫자가 없을 경우 낫싱
