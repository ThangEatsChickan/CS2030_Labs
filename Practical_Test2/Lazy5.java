import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

class LazyList<T extends Comparable<T>> {
      List<Lazy<T>> list;
      //UnaryOperator <T> f;
      private LazyList(List<Lazy<T>> list)  {
          this.list = list;
          //this.f = f;
      }

      //@SuppressWarnings({"unchecked", "varargs"})
      static <T extends Comparable<T>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
          /*List <Lazy<T>> lazyList = new ArrayList<>(n);
          lazyList.add(Lazy.of(seed));
          return new LazyList<T> (lazyList, f);*/ 
          return new LazyList<T>(
              Stream.iterate(Lazy.of(seed), x -> x.map(f))
                   .limit(n)
                   .collect(Collectors.toList())
                   );
      }
 
      @SuppressWarnings({"unchecked", "varargs"})
      public T get(int i) {
         /*if(i > this.list.size()) {
         T seed = this.list.get(this.list.size() - 1).get();
         this.list.remove(this.list.size()-1);
         Stream.iterate(seed, x -> f.apply(x))
                .map(y -> Lazy.of(y))
                .limit(i - (this.list.size() -1))
                .forEach(this.list::add);
         }
          return this.list.get(i).get();*/
         Lazy lazy = (Lazy) this.list.get(i);
         return (T)lazy.get();
      }

      public int indexOf(T v) {
          return this.list.indexOf(Lazy.of(v));
      }
}
