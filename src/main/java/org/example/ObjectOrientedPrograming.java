package org.example;

public class ObjectOrientedPrograming {

    // 1. 객체 지향 프로그래밍(Object-Oriented Programming, OOP)이란 컴퓨터 프로그램을 어떤 데이터를 입력받아 순서대로 처리하고
    // 결과를 도출하는 명령어들의 목록으로 보는 시각에서 벗어나 여러 독립적인 부품들의 조합, 즉 객체들의 유기적인 협력과 결합으로 파악하고자 하는
    // 컴퓨터 프로그래밍의 패러다임을 의미한다.

    // 2. 객체 지향 프로그래밍의 4가지 특징
    // 추상화(Abstraction)는 사물이나 표상을 어떤 성질, 공통성, 본질에 착안하여 그것을 추출하여 파악하는 것이다.
    // 자바에서 추상화를 구현할 수 있는 문법 요소로는 추상 클래스(abstract class)와 인터페이스(interface)가 있다.

    public static void main(String[] args) {
        Car car = new Car();
        car.startEngine();
        car.moveForward();
        car.moveBackward();
        System.out.println("=====================");

        MotorBike mt = new MotorBike();
        mt.startEngine();
        mt.moveForward();
        mt.moveBackward();
    }
}

class Car implements Vehicle{
    // 속성 정의
    String company;
    String model;
    String color;
    int wheels;
    boolean isConvertible;

    @Override
    public void startEngine() {
        System.out.println("시동을 겁니다.");
    }

    @Override
    public void moveForward() {
        System.out.println("차가 앞으로 전진합니다.");
    }

    @Override
    public void moveBackward() {
        System.out.println("차가 뒤로 후진합니다.");
    }

    // 기능 정의
    /*
    void startEngine(){
        System.out.println("시동을 겁니다.");
    }

    void moveForward() {
        System.out.println("차가 앞으로 전진합니다.");
    }

    void moveBackward() {
        System.out.println("차가 뒤로 후진합니다.");
    }
    */
}

interface Vehicle {
    public abstract void startEngine();
    void moveForward();
    void moveBackward();
}

class MotorBike implements Vehicle{

    @Override
    public void startEngine() {
        System.out.println("시동을 겁니다.");
    }

    @Override
    public void moveForward() {
        System.out.println("오토바이가 앞으로 전진합니다.");
    }

    @Override
    public void moveBackward() {
        System.out.println("오토바이가 뒤로 후진합니다.");
    }
}