package com.ll;

public class Main {

    public static void main(String[] args) {// main에는 무조건 static이 들어가야함. 클래스가 함수를 만들때 없어도 됨!(예를 들어 App 클래스)

        // App 객체를 만들어서 프로그램 실행 요청
        App app = new App();
        app.run(); // App 클래스 안에 있는 run함수 실행

        }
    }