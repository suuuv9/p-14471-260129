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
            else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
                }
            }
        }


    //삭제 명령어 처리
    private void actionDelete(String cmd) {

        String idStr = cmd.split("=")[1];
        int id = Integer.parseInt(idStr);

        boolean rst = delete(id); //실제 삭제 시도

        if(!rst) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    // 못 찾으면 false, 찾으면 배열 당기고 true
    private boolean delete(int deleteTarget) {

        int foundIndex = -1;

        for (int i = 0; i <= lastWiseSayingIndex; i++) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            if (deleteTarget == foundedWiseSaying.id) {
                foundIndex = i;
                break;
            }
        }

        if(foundIndex == -1) return false;

        for (int i = foundIndex; i < lastWiseSayingIndex; i++) {
            wiseSayings[i] = wiseSayings[i + 1];
        }

        lastWiseSayingIndex--;
        return true;

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

        for (int i = lastWiseSayingIndex; i >= 0; i--) {
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