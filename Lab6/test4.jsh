/open Trace.java
Function<Integer,Trace<Integer>> f = x -> Trace.of(x).map(y -> y + 1)
Function<Integer,Trace<Integer>> g = x -> Trace.of(x).map(y -> y * 10)
Trace.of(1).flatMap(f).get()
Trace.of(1).flatMap(f).history()
Trace.of(1).flatMap(f).equals(f.apply(1))
Trace.of(1).equals(Trace.of(1).flatMap(x -> Trace.of(x)))
Trace.of(1).flatMap(f).flatMap(g).get()
Trace.of(1).flatMap(f).flatMap(g).history()
Function<Integer,Trace<Integer>> h = x -> f.apply(x).flatMap(g)
Trace.of(1).flatMap(h).equals(Trace.of(1).flatMap(f).flatMap(g))
Trace<Long> log2(Long n) {
       return (n == 1) ? Trace.of(1L) : Trace.of(n, n).flatMap(y -> log2(y/2));
       }
Trace.of(4905L).flatMap(x -> log2(x)).history()
/exit
