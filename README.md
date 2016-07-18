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

4.
