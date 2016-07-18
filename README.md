# java8-snippets
Snippets of the new functional features of Java 8

1.Streaming with arrays and index

```java
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int v = Integer.parseInt(sc.nextLine().trim());
    
        sc.nextLine();//discard this line
        final int [] arr = Stream.of(sc.nextLine().split(" ")).mapToInt (Integer::parseInt).toArray();
        IntStream.range(0, arr.length).filter(i -> arr[i] == v).findFirst().ifPresent(System.out::println);
    }
}
```

2.Streaming reverse list of integers read from stdin

```java
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();//ignore the first line
        StringBuffer result = new StringBuffer();
        Stream.of(sc.nextLine().split(" "))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toCollection(LinkedList::new))
            .descendingIterator()
            .forEachRemaining(n -> result.append(n + " "));
        sc.close();
        System.out.println(result.toString().trim());
    }
}
```

3.Functional prime number example

```java
    private static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, n).noneMatch(i -> n % i == 0);
    }
```

4.Predicates

```java
    Predicate<Integer> isDivisible = divisor -> number % divisor == 0;
    //how to use:
    return n> 1 && IntStream.range(2,n).noneMatch(i -> isDivisible(i));
```

5.Find first element greater than 3 and double it

```java
    List<Integer> values = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10);
    values.stream().filter(i -> i > 3).filter(i -> i % 2 == 0).map(i -> i * 2).findFirst().get();
```

6.Same version with Function object

```java
    //like above, except with a Function object
    Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> number -> number > pivot;
    values.stream().filter(isGreaterThan(3)).filter(i -> i % 2 == 0).map(i -> i * 2).findFirst().get();
    //or, with eager evaluation
    values.stream().filter(isGreaterThan.apply(3)).filter(i -> i % 2 == 0).map(i -> i * 2).findFirst().get();
    //a Function can also return a Function
```

7.Reduce

```java
    public static int totalValues (List<Integer> numbers, Predicate<Integer> selector) {
        return numbers.stream().filter(selector).reduce(0, Math::addExact);
    }
```
