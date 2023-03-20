package org.example;

public class InheritanceImplementation {

    /*
    상속과 구현은 자바와 객체 지향 프로그래밍의 두 가지 기본 개념이다.

    1. 상속
    상속은 기존 클래스를 확장하여 새 클래스를 만드는 개념으로, 새 클래스가 부모 클래스의 속성과 메서드를 상속할 수 있도록 한다.
    Java에서 상속은 extends 키워드를 사용하여 이루어집니다.

    상속의 이점:

    ** 코드 재사용 가능성: 상속은 새 클래스가 부모 클래스의 메서드 및 속성을 재사용할 수 있도록 하여 코드 재사용성을 향상시킨다.
    ** 유지보수성: 계층 구조와 클래스 간의 명확한 관계를 제공하여 코드를 더 쉽게 유지 관리할 수 있게 한다.

    ////////////////////////////////////////////////////////////////////////////////////

    2. 구현:
    구현은 인터페이스에 정의된 방법에 대한 구체적인 구현을 제공하는 프로세스를 의미한다.
    자바에서 클래스는 implements 키워드를 사용하여 하나 이상의 인터페이스를 구현할 수 있다.

    구현의 이점:

    ** 추상화: 인터페이스는 추상화를 제공하여 클래스가 관련 없는 여러 기능을 구현할 수 있도록 한다.
    ** 느슨한 결합: 클래스는 동일한 인터페이스를 구현하는 다른 클래스로 쉽게 대체될 수 있기 때문에 인터페이스는 느슨하게 결합된 설계를 만드는 데 도움이 된다.
    */
    public static void main(String[] args) {
        Car2 car = new Car2("제네시스", "GV80", 4);
        System.out.println("브랜드: " + car.getBrand());
        System.out.println("모델: " + car.getModel());
        System.out.println("바퀴 수: " + car.getNumberOfDoors());
        car.startEngine();
        car.move();
        car.stopEngine();
    }
}

// Interface
interface Drivable {
    void startEngine();
    void stopEngine();
}

// Parent class
class Vehicle2 {
    private String brand;
    private String model;

    public Vehicle2(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public void move() {
        System.out.println("차가 이동중입니다.");
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
}

// Child class that also implements the Drivable interface
class Car2 extends Vehicle2 implements Drivable {
    private int numberOfDoors;
    private boolean engineRunning = false;

    public Car2(String brand, String model, int numberOfDoors) {
        super(brand,model);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public void startEngine() {
        engineRunning = true;
        System.out.println("시동을 겁니다.");
    }

    @Override
    public void stopEngine() {
        engineRunning = false;
        System.out.println("시동을 끕니다.");
    }
}