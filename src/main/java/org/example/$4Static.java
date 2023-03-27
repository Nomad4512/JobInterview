package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class $4Static {
    // 서술방향 : static 사용방법 - 주의사항 - 사용하면 좋은 경우와 안좋은 경우(동시성 문제 발생) - 동시성문제 해결법(동기화처리, thread-safe)
    //          - 해결법 선택시 고려사항

    /*
    Java에서 static 키워드는 다음과 같은 멤버에 사용될 수 있다.

    static 변수: 클래스 변수로도 불리며, 클래스가 로딩될 때 생성되고 프로그램이 종료될 때까지 유지된다. 인스턴스 변수와 달리 객체를 생성하지 않아도 사용할 수 있으며, 모든 객체가 동일한 값을 참조한다.
    static 메소드: 객체를 생성하지 않아도 호출할 수 있는 메소드이다. static 메소드에서는 클래스 변수만 사용할 수 있고, 인스턴스 변수나 메소드를 사용할 수 없다.
    static 블록: 클래스가 로딩될 때 한 번만 실행되는 코드 블록으로, 주로 클래스 변수를 초기화하는 데 사용된다.
    static 내부 클래스: 클래스 내부에 정의된 정적 클래스로서, 객체를 생성하지 않아도 사용할 수 있다. 인스턴스 내부 클래스와 달리 인스턴스 변수와 메소드에 접근할 수 없다.

    예를 들어, static 변수와 메소드를 사용한 코드는
     */
    public static final double PI = 3.14159265;

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    static {
        System.out.println("Static 클래스가 로딩되었습니다.");
    }

    public static void main(String[] args) {
        System.out.println("static 덧셈 : 2 + 3 = "+$4Static.add(2,3));
        System.out.println("static 뺄셈 : 10 - 3 = "+$4Static.subtract(10,3));
        System.out.println();

        System.out.println("USD_JPY 환율변환 1000달러 : "+$4Static.convert("USD","JPY",
                1000)+"Yen");

        String str="";
        if($4Static.isEmpty(str)){
            System.out.println("str은 null 또는 문자열이 비어있습니다.");
        }else{
            System.out.println("str은 입력값이 존재합니다.");
        }
        System.out.println("toLowerCase : from APPLE to "+$4Static.toLowerCase("APPLE"));
        System.out.println();

        $4Static.increment();
        $4Static.increment();
        System.out.println("synchronized static 증가 2번 결과 : "+$4Static.getCount());

        $4Static.increment2();
        $4Static.increment2();
        $4Static.increment2();
        System.out.println("AtomicInteger 증가 3번한 결과 : "+$4Static.getCount2());

    }
    /*
     static을 사용하면 객체를 생성하지 않고도 호출이 가능하다. 또한, static 블록에서 클래스 로딩 시점에 실행되는 코드를 작성할 수 있다.
     하지만 Static영역에 있는 멤버들은 프로그램의 종료시까지 메모리가 할당된 채로 존재하기 때문에 주의해야할 점이 있다.

     1. static을 너무 남발하게 되면 만들고자 하는 시스템 성능에 악영향을 줄 수 있다.
     2. static 변수는 모든 객체가 공유하기 때문에, 데이터 무결성을 보장하기 어려울 수 있다. 예기치 않은 결과를 방지하기 위해 적절한 동기화 기법을 사용해야 한다.
     3. static 메소드에서 인스턴스 변수와 메소드를 사용할 수 없으므로, 객체의 상태에 따라 결과가 달라지는 메소드를 구현할 수 없다.
     4. static을 남용하면 객체지향 원칙에 위배되어 코드의 유지보수가 어려워질 수 있다. 상황에 따라 적절한 사용이 필요하다.

    따라서 이러한 경우에는 static을 사용하는 것이 좋고,
    1. 공통으로 사용되는 설정값이나 상수를 저장할 때: 모든 객체가 동일한 값을 참조하도록 하여 메모리 사용량을 줄일 수 있다.
    2. 객체 생성에 시간이 많이 소요되거나 리소스가 많이 사용되는 경우: 객체를 생성하지 않고도 필요한 기능을 제공하는 static 메소드를 사용하여 성능을 향상시킬 수 있다
    */
    public static final String API_BASE_URL = "https://api.example.com/"; // 공통 설정
    public static final int MAX_CONNECTIONS = 1000; // 상수 저장
    ////////////////////////////////////////////////////////////////////////
    // 성능 최적화를 위한 캐싱 : 계산 비용이 높거나 데이터베이스에서 가져오는 값과 같이 변하지 않는 데이터를 캐싱하는 것이 좋다.
    private static Map<String, Double> exchangeRates;

    private static Map<String, Double> loadExchangeRates() {
        Map<String, Double> rates = new HashMap<>();

        rates.put("USD_EUR", 0.85);
        rates.put("USD_JPY", 110.25);
        rates.put("EUR_USD", 1.18);
        rates.put("EUR_JPY", 129.64);
        rates.put("JPY_USD", 0.0091);
        rates.put("JPY_EUR", 0.0077);

        return rates;
    }

    static {
        exchangeRates = loadExchangeRates();
    }

    public static double convert(String fromCurrency, String toCurrency, double amount) {
        double rate = exchangeRates.get(fromCurrency + "_" + toCurrency);
        return amount * rate;
    }
    ////////////////////////////////////////////////////////////////////////
    // 유틸리티 메소드 및 상태가 없는 작업 : 객체의 상태와 상관없이 동일한 결과를 반환하는 메소드는 static으로 선언하여 객체 생성 없이 사용할 수 있다.

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }
    /*
    이런 경우에는 피해야 한다.
    1. 객체의 상태에 따라 다른 결과를 반환하는 메소드의 경우: 객체의 상태를 공유하지 않기 때문에 static 메소드로 구현하면 정확한 결과를 얻을 수 없다.
    2. 멀티스레드 환경에서 여러 객체가 동시에 접근해야 하는 경우: static 변수는 동기화 문제를 일으킬 수 있다.
       여러 스레드가 동시에 static 변수에 접근하면 예기치 않은 결과가 발생할 수 있다. 이런 상황에서는 synchronized 키워드를 사용하여 동기화를
       처리하거나, Thread-safe한 클래스를 사용하여 해결해야 한다.
    */

    /*
    동시성문제를 해결하기 위한 방법은
    1. 동기화 처리 : Java에서 동기화는 synchronized 키워드를 사용하여 구현할 수 있다. synchronized 키워드는 메소드 또는 코드 블록에 적용할 수
                  있으며, 한 번에 하나의 스레드만 해당 메소드 또는 코드 블록을 실행하도록 한다. */
    private static int count = 0;

    public synchronized static void increment() {
        count++;
    }

    public synchronized static int getCount() {
        return count;
    }
    /*
    2. Thread-safe한 클래스 사용: Java 표준 라이브러리에는 동시성 문제를 해결하기 위한 Thread-safe 클래스들이 포함되어 있다. 예를 들어,
                               java.util.concurrent.atomic 패키지에는 원자적 연산을 제공하는 클래스들이 있다. 이 클래스들은 동시성 문제를
                               해결하기 위해 최적화된 메소드를 사용한다. */
    private static AtomicInteger count2 = new AtomicInteger(0);

    public static void increment2() {
        count2.incrementAndGet();
    }

    public static int getCount2() {
        return count2.get();
    }

    /*
    동기화 처리와 Thread-safe 클래스는 상황에 맞게 선택하여 동시성 문제를 해결할 수 있다. 다음의 고려사항들을 통해 적절한 방법을 선택할 수 있다.

    1. 코드의 복잡성: 동기화 처리는 코드의 복잡성을 증가시킬 수 있다. synchronized 키워드를 사용하면 코드가 길어지고 가독성이 떨어질 수 있으며,
                    적용 범위에 따라 데드락(Deadlock)과 같은 문제가 발생할 수 있습니다. || Thread-safe 클래스는 별도의 동기화 코드 없이 동시성을
                    처리할 수 있어 코드가 간결해진다. 코드의 복잡성이 크게 증가하지 않는 상황에서는 Thread-safe 클래스를 사용하는 것이 더 좋다.
    2. 범용성: 동기화 처리는 다양한 경우에 적용할 수 있다. 객체의 메소드, 코드 블록 등에 synchronized 키워드를 사용하여 동시성을 처리할 수 있다.
                    || 반면, Thread-safe 클래스는 특정 상황에만 적용할 수 있으며, 사용할 수 있는 클래스의 종류가 제한적이다. 범용적으로 동시성
                    문제를 해결해야 하는 경우에는 동기화 처리가 더 적합하다.
    3. 성능: 동기화 처리는 성능에 영향을 줄 수 있다. synchronized 키워드를 사용하면 한 번에 하나의 스레드만 해당 메소드나 코드 블록을 실행할 수 있기
                    때문에 병렬 처리가 제한된다. || Thread-safe 클래스는 원자적 연산을 사용하여 성능을 최적화하므로, 성능이 중요한 상황에서는
                    Thread-safe 클래스를 사용하는 것이 더 좋다.
    4. 개발 및 유지보수: 동기화 처리는 개발 및 유지보수에 더 많은 시간과 노력이 필요할 수 있다. 데드락과 같은 문제를 해결하고, 성능 최적화를 위해 추가
                     작업이 필요할 수 있다. || Thread-safe 클래스를 사용하면 이러한 문제를 줄일 수 있으며, 개발 및 유지보수에 드는 시간과 노력을
                     줄일 수 있다.

     */
}
