# Java_Project

실행 방법 :<br>
Build Path에 lib에 폴더에 있는 mysql-connector을 추가해주세요<br>
game패키지에 있는 Login 클래스에서 실행을(컨트롤+F11) 하고 아이디: test 비밀번호 : 1234를 입력하면됩니다.<br>

1. 게임 설명<br>
버블보블 게임 : 플레이어 캐릭터가 버블을 쏴 적 캐릭터를 죽이고 모든 적 캐릭터를 죽이면 끝나는 게임<br>
움직이는 키 : 방향키 <br>
공격 키 : 스페이스 바 <br><br>
![1](https://user-images.githubusercontent.com/117806984/208880777-f589a9fa-d787-497b-9fd4-74d948aee5b3.jpg)<br>
![2](https://user-images.githubusercontent.com/117806984/208880977-c0e24567-fb50-4476-b1ac-6a01eea5f5e4.png)
<br>

2. 작동원리<br>
① 방향키 입력으로 플레이어 캐릭터가 움직일 수 있게  left(),right(),up(),down()메서드 좌표값 x,y 값에 변화를 주어 플레이어 캐릭터가 움직일 수 있도록 구현되어 있습니다.<br><br>
② 아래와 같이 닿으면 앞으로 전진이 되지 않는 부분은 빨간색으로 플레이어가 올라갈 수 있는 부분은 파란색으로 칠한 이미지를 만들고, 색상을 감지하는 getRGB()함수를 사용하여, getX()와 getY의 값(플레이어의 좌표 값)을 변경하여 기준점을 이동시켜, 기준점 좌표가 벽에 충돌하면 즉, getRGB()함수를 사용하여 색상이 빨간색이나, 파란색으로 바뀌면 플레이어의 움직이는 기능을 구현한  left(),right(),up(),down() 각 각의 메서드를 조건에 맞게 false값으로 바꾸어 빨간 부분은  더 이상 전진이 안 되도록, 파란 부분은 올라갈 수 있도록 구현되어 있습니다.
![설명이미지](https://user-images.githubusercontent.com/117806984/208656903-df9e93a1-7288-4fec-afdd-95970a45caf0.png)<br><br>
③  버블은 플레이어의 뱡향의 값과 플레이어의 x,y 각 각의 좌표 값을 받아 그에 맞는 방향으로 버블이 날아갈 수 있게 구현되어 있습니다.<br><br>
④ 적 캐릭터는 자동으로 1층으로 내려갔다가 다시 맨 위 까지 올라가도록 구현되어 있습니다.<br><br>

3. 이슈 및 해결책<br>
mysql으로 데이터베이스를 연결하고 싶었는데 어떻게 할지 몰라서 유튜브와 구글에 검색 후 구현을 해봤습니다.<br><br>





4. 추가하고 싶은 기능
- 적을 다 제거하면  다음 레벨 맵으로 패널이 바뀌는 기능 <br>
- 마지막 맵의 적을 다 제거하면 브금이 바뀌고 클리어라는 문구가 뜨는 패널이 나오는 기능<br>
- 제한 시간 타이머 기능<br>
- 데이터베이스 리팩토링<br><br>

5. 추가 구현한 부분
- 로그인창과, 회원가입창을 생성 후 데이터베이스와 연동<br>
- 캐릭터가 죽을 시 배경음악이 변경되도록 구현<br><br>


6. 출처<br>
https://www.youtube.com/playlist?list=PL93mKxaRDidGqGOsNQ1DqTwB0xA_ON-nY<br>
https://github.com/PoorMan1225/java-bubble-game-v2<br>
https://www.youtube.com/watch?v=fCjPrmea-H0<br>
