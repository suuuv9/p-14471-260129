package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App { //핵심 로직이 App 클래스가 가지게 됨.

    Scanner sc = new Scanner(System.in);
    int lastId = 0; //가장 최근에 작성된 번호, 처음에는 입력된 번호가 없기 때문에 초기값 0으로 변경

    WiseSaying[] wiseSayings = new WiseSaying[10]; // 명언들을 저장할 배열,  최대 10개
    int lastWiseSayingIndex = -1; // 마지막으로 저장된 인덱스, 아직 없으면 -1

    public void run() { //함수

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            }
            else if (cmd.equals("등록")) {
                actionWrite(); //등록이라고 입력되면 actionWrite 함수 부르기
            }
            else if (cmd.equals("목록")) {
                actionList();
            }
        }
    }

    private void actionList() { //목록 함수
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        List<WiseSaying> wiseSayingList = findList(); // 최신 등록 가져오기

        for(WiseSaying wiseSaying : wiseSayingList) { //for(a b : c) c의 개수 만큼 반복을 실행, 순차적으로 앞부터 꺼내서 실행
            System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
        }
    }

    private List<WiseSaying> findList() {

        List<WiseSaying> wiseSayingList = new ArrayList<>();
        for (int i = lastWiseSayingIndex; i >= 0; i--) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            wiseSayingList.add(foundedWiseSaying);
        }
        return wiseSayingList;
    }

    private void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        write(content, author);
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }

    private void write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(); // 새 명언 객체 생성

        wiseSaying.id = ++lastId; // id 등록할 때마다 하나씩 증가
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings[++lastWiseSayingIndex] = wiseSaying; // 다음 칸으로 이동 후 배열에 저장
    }
}
