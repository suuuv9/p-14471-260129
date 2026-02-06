package com.ll;

import java.util.Scanner;

public class App {

    Scanner sc = new Scanner(System.in);
    int lastId = 0;

    WiseSaying[] wiseSayings = new WiseSaying[10];
    int lastWiseSayingIndex = -1;

    public void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            }
            else if (cmd.equals("등록")) {
                actionWrite();
            }
            else if (cmd.equals("목록")) {
                actionList();
            }
            else if (cmd.startsWith("삭제")) {  // "삭제?id=숫자" 형태 처리
                actionDelete(cmd);
                }
            }
        }


    //삭제 명령어 처리
    private void actionDelete(String cmd) {

        String idStr = cmd.split("=")[1]; // "삭제?id=1"에서 1만 추출하기
        int id = Integer.parseInt(idStr);

        delete(id);
        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private void delete(int deleteTarget) {

        int foundIndex = -1; // 삭제하고 싶은 명언이 저장된 위치

        for (int i = 0; i <= lastWiseSayingIndex; i++) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            if (deleteTarget == foundedWiseSaying.id) { //꺼낸 id가 우리가 지우고 싶은 것과 맞는 지
                foundIndex = i;
                break; // 찾았으면 반복 종료
            }
        }

        if (foundIndex == -1) return;

        for (int i = foundIndex; i < lastWiseSayingIndex; i++) {
            wiseSayings[i] = wiseSayings[i + 1]; //지우고 싶은 것을 [i + 1]로 덮어 씌우기
        }

        lastWiseSayingIndex--; //마지막 인덱스 줄여주기, 안 쓰니까!
    }

    //목록 출력하기
    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        WiseSaying[] foundedWiseSayings = findList();

        for (WiseSaying wiseSaying : foundedWiseSayings) {
            System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
        }
    }

    private WiseSaying[] findList() {

        // 현재 저장된 개수만큼 새 배열 만들기
        WiseSaying[] foundedWiseSayings = new WiseSaying[lastWiseSayingIndex + 1];
        int foundedWiseSayingIndex = -1;

        for (int i = lastWiseSayingIndex; i >= 0; i--) { // 최신 등록이 먼저 보이도록 역순으로 담기
            WiseSaying foundedWiseSaying = wiseSayings[i];
            foundedWiseSayings[++foundedWiseSayingIndex] = foundedWiseSaying;
        }

        return foundedWiseSayings;
    }

    // 명언 등록
    private void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        write(content, author);
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }


    //실제 저장 로직
    private void write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying();

        //새 id 받기
        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings[++lastWiseSayingIndex] = wiseSaying; //배열에 저장
    }
}