package com.ll;

import java.util.Scanner;

public class App {

    private Scanner sc = new Scanner(System.in);
    private int lastId = 0;

    private WiseSaying[] wiseSayings = new WiseSaying[10];
    private int lastWiseSayingIndex = -1;

    public void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정")) { //명언 수정하기
                actionModify(cmd);
            }
        }
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

    // 목록 출력하기
    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        WiseSaying[] foundedWiseSayings = findList();

        for (WiseSaying wiseSaying : foundedWiseSayings) {
            System.out.printf("%d / %s / %s\n",
                    wiseSaying.id,
                    wiseSaying.author,
                    wiseSaying.content);
        }
    }

    // 명언 삭제
    private void actionDelete(String cmd) {

        String idStr = cmd.split("=")[1]; // id 문자열 추출
        int id = Integer.parseInt(idStr); // 숫자로 변환

        boolean rst = delete(id);

        if (!rst) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    // 명언 수정
    private void actionModify(String cmd) {

        String idStr = cmd.split("=")[1]; // id 문자열 추출
        int id = Integer.parseInt(idStr); // 숫자로 변환

        WiseSaying wiseSaying = findById(id); //해당 id의 명언 찾기

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.print("명언(기존) : %s\n".formatted(wiseSaying.content));
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가(기존) : %s\n".formatted(wiseSaying.author));
        System.out.print("작가 : ");
        String author = sc.nextLine();

        modify(wiseSaying, content, author); //실제 수정 처리
    }

    // 실제 저장
    private void write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings[++lastWiseSayingIndex] = wiseSaying;
    }

    // 명언 삭제 로직
    private boolean delete(int deleteTarget) {

        int foundIndex = findIndexById(deleteTarget);

        if (foundIndex == -1) return false;

        for (int i = foundIndex; i < lastWiseSayingIndex; i++) {
            wiseSayings[i] = wiseSayings[i + 1];
        }

        lastWiseSayingIndex--;
        return true;
    }

    // 명언 수정 로직
    private void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.content = content;
        wiseSaying.author = author;
    }

    private WiseSaying findById(int id) {

        int foundedIndex = findIndexById(id);

        if (foundedIndex == -1) {
            return null;
        }

        return wiseSayings[foundedIndex];
    }

    // id에 해당하는 배열 인덱스 찾기
    private int findIndexById(int id) {

        for (int i = 0; i <= lastWiseSayingIndex; i++) {
            WiseSaying foundedWiseSaying = wiseSayings[i];

            if (id == foundedWiseSaying.id) {
                return i;
            }
        }

        return -1;
    }

    // 최신 등록된 명언이 먼저 나오도록 배열 만들기
    private WiseSaying[] findList() {

        WiseSaying[] foundedWiseSayings = new WiseSaying[lastWiseSayingIndex + 1];
        int foundedWiseSayingIndex = -1;

        for (int i = lastWiseSayingIndex; i >= 0; i--) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            foundedWiseSayings[++foundedWiseSayingIndex] = foundedWiseSaying;
        }

        return foundedWiseSayings;
    }
}
