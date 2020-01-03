/open Trace.java ChildTrace.java
Function<Object,Integer> f = x -> x.hashCode()
Trace<Number> t = Trace.of(23.6, 1)
t.map(f).get() != null
Function<Object, ChildTrace<Integer>> g = x -> ChildTrace.of(x.hashCode())
t.flatMap(g).get() != null
/exit
