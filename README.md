# java8-snippets
Snippets of the new functional features of Java 8

1. Streaming with arrays and index

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
