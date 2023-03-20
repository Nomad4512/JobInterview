package org.example;

public class $1OverloadOverride {

    //1. 오버로딩
    // : 같은 이름의 메소드(method) 또는 생성자를 매개변수의 개수나 타입을 다르게 지정함으로써 2개 이상 정의하는 것을 의미한다

    void cat() {
        System.out.println("매개변수 없음");
    }

    void cat(int a, int b) {
        System.out.println("매개변수 : " + a + ", " + b);
    }

    void cat(String c){
        System.out.println("매개변수 : " + c);
    }

    public static void main(String[] args) {
        $1OverloadOverride oo = new $1OverloadOverride();
        System.out.println(">>>오버로딩 테스트 시작");

        oo.cat();
        oo.cat(1,2);
        oo.cat("야옹");

        System.out.println(">>>오버로딩 테스트 끝");
        System.out.println("========================================");
        System.out.println(">>>오버라이딩 테스트 시작");

        Child child = new Child();
        child.name = "철수";
        child.age = 13;
        child.job = "의사";

        child.intro();
        System.out.println(">>>오버라이딩 테스트 끝");
    }

}

class Father {
    public String name;
    public int age;

    public void intro(){
        System.out.println("나는 "+name+"입니다. 나이는 "+age+"세 입니다.");
    }
}

class Child extends Father {
    String job;

    @Override
    public void intro() {
        super.intro();
        System.out.println("그리고, 제 직업은 "+job+"입니다.");
    }
}
