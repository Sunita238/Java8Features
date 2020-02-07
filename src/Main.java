import interfaces.SqaureFunction;

import java.time.LocalTime;
import java.util.*;
import java.util.function.DoubleSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        /*//task 1
        System.out.println("Hello World!");

        for (int i=1;i<=10;i++) {
            System.out.println("print document" + i);
        }
        
        System.out.println("End main");
*/
        /*String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        System.out.println("-------\nRunning sequential\n-------");
        run(Arrays.stream(strings).sequential());
        System.out.println("-------\nRunning parallel\n-------");
        run(Arrays.stream(strings).parallel());*/

        List<Integer> integers = new ArrayList<>();
        for (int i =1;i<=40;i++)integers.add(i);
        /*integers.parallelStream().forEach(a -> {
            System.out.println(Thread.currentThread().getName() + " is working on " + a);
        });
*/
        integers.parallelStream().filter(isAmultipleofFour()).forEach(a -> {
            System.out.println(Thread.currentThread().getName() + " finding multiple of 4 = " + a );
        });

        System.out.println("=====================================Map example============================================");
        integers.parallelStream().map(a -> a*2).forEach( a -> {
            System.out.println(Thread.currentThread().getName() + " multiply * 2 = " + a);
        });

        System.out.println("=====================================limit example==========================================");
        integers.parallelStream().limit(10).map(a -> a*2).forEach( a -> {
            System.out.println(Thread.currentThread().getName() + " multiply * 2 = " + a);
        });

        System.out.println("=====================================sorted example=========================================");
        List<String> stringArray = new ArrayList<>();
        stringArray.add("sorry");
        stringArray.add("thank you");
        stringArray.add("welcome");
        stringArray.add("no problem");
        stringArray.add("hello");
        stringArray.add("good morning");
        stringArray.add("bye");
        stringArray.parallelStream().sorted().forEachOrdered(System.out::println);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(34);
        integerList.add(23);
        integerList.add(7);
        integerList.add(80);
        integerList.add(56);
        integerList.parallelStream().sorted().forEachOrdered(s -> {
            System.out.println(Thread.currentThread().getName() + " Sorting list " + s);
        });

        System.out.println(integerList.stream().anyMatch(a -> a == 80));

        integerList.stream().mapToInt(j -> j*3).parallel().forEach(s -> {
            System.out.println(Thread.currentThread().getName() + " map to int :: " + s);
        });

        System.out.println("==================map to double example======================");
        integerList.stream().mapToDouble(j -> j).map(j-> j/3).forEach(s -> {
            System.out.println(Thread.currentThread().getName() + " divided by 3 :: " + s);
        });

        System.out.println("==================using doublesupplierexample======================");
        List<Double> radiusLIst = Arrays.asList(2D, 3D,5D,4D,8D,6D);
        radiusLIst.parallelStream().mapToDouble(j -> squareCircle(j)).sorted().forEachOrdered( s -> {
            System.out.println("Area of square is == " + s);
        });

        System.out.println(integerList.stream().filter(i -> i == 200).findAny());

        System.out.println("================distinct example=====================");
        List<Integer> randomList = Arrays.asList(1,2,5,4,3,2,6,8,1,2,9,0,3,2,5,6,7,8);
        randomList.stream().distinct().sorted().forEach(System.out::println);

        System.out.println("================distinct using collector.toSet=====================");
        randomList.stream().collect(Collectors.toSet()).forEach(System.out::println);

        System.out.println("================Collect example=====================");
        List<Integer> finalList = randomList.stream().filter(i -> i%2 == 0).collect(Collectors.collectingAndThen(Collectors.toList(),Collections::unmodifiableList));
        //finalList.add(10);
        finalList.forEach(System.out::println);

        System.out.println("================skip example=====================");
        Stream.of(1,2,3,4,5,6,7,8,9).skip(2).forEach(System.out::println);

        System.out.println("=================reduce examples========================");
        Supplier <Stream<Integer>> streamSupplier = () -> Stream.of(1,2,3,4,5,6,7,8,9);
        Optional<Integer>op = streamSupplier.get().map(i -> i * 2).peek(System.out::println).reduce((integer, integer2) ->  integer + integer2);
        System.out.println("SUM = " + op.get());

        Optional<Integer>op1 = streamSupplier.get().map(j -> j * 2).peek(System.out::println).reduce((integer, integer2) ->  integer < integer2 ? integer : integer2);
        System.out.println("MIN = " + op1.get());

        Optional<Integer>op2 = streamSupplier.get().map(j -> j * 2).peek(System.out::println).reduce((integer, integer2) ->  integer > integer2 ? integer : integer2);
        System.out.println("MAX = " + op2.get());

        System.out.println("============Peek example==============");
        //used for debugging
        Stream.of("one","two","three","four").map(s -> s.toUpperCase()).peek(System.out::println).filter( s -> s.length() >= 4).peek(System.out::println).collect(Collectors.toList());

        System.out.println("=============flatmap======================");
        List<List<Integer>> intDList = Arrays.asList(integerList,randomList);
        List outList = intDList.stream().flatMap(o -> o.stream()).peek(System.out::println).distinct().peek(System.out::println).sorted().collect(Collectors.toList());
        outList.forEach(System.out::println);


        System.out.println("================statistics example===============");
        IntSummaryStatistics statistics = randomList.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Average = "+ statistics.getAverage());
        System.out.println("count = " + statistics.getCount());
        System.out.println("Max = " + statistics.getMax());
        System.out.println("Min = " + statistics.getMin());
        System.out.println("Sum = " + statistics.getSum());

        statistics.accept(50);

        System.out.println("Average = "+ statistics.getAverage());
        System.out.println("count = " + statistics.getCount());
        System.out.println("Max = " + statistics.getMax());
        System.out.println("Min = " + statistics.getMin());
        System.out.println("Sum = " + statistics.getSum());

        System.out.println("--------------Function INterface----------------");

        SqaureFunction<Integer> sf = (x) -> x * x;
        sf.print(20);

        System.out.println("===============Optional====================");

    }

    public static Predicate<Integer> isAmultipleofFour() {
        return i -> i % 4 == 0 ;
    }

    public static Double squareCircle(double d) {

        DoubleSupplier pi = () -> {return (3.14D);};
        return pi.getAsDouble() * d * d;

    }


    public static void run (Stream<String> stream) {

        stream.forEach(s -> {
            System.out.println(LocalTime.now() + " - value: " + s +
                    " - thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
