package com.ll;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int id = 1; //명언 관리 변수 추가

        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSaying = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
                System.out.println(id + "번 명언이 등록되었습니다."); //추가
                id++;
            }
            else if(cmd.equals("종료")) {
                break;
            }
        }
    }
}