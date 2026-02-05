package com.ll;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine(); //cmd라는 변수에 입력한 값이 들어오도록 함

            if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSaying = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
                System.out.println("1번 명언이 등록되었습니다.");
            }
            else if(cmd.equals("종료")) { //숫자는 ==로 비교하지만, 문자는 .equals로 비교!
                break;
            }
        }
    }
}