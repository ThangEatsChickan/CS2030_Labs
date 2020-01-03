package cs2030.mystream;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.Optional;
import java.util.ArrayList;

public class InfiniteListImpl<T> implements InfiniteList<T>{
    //private final Supplier<Optional<T>> head;
    //private final Supplier<InfiniteListImpl<T>> tail;
    private final CachedSupplier<Optional<T>> head;
    private final CachedSupplier<InfiniteListImpl<T>> tail;
//    protected InfiniteListImpl(Supplier<Optional<T>> head, Supplier<InfiniteListImpl<T>> tail) {
//        this.head = head; 
//        this.tail = tail;
//    }

   protected InfiniteListImpl(Supplier<Optional<T>> head, Supplier<InfiniteListImpl<T>> tail) {
        this.head = new CachedSupplier<Optional<T>>(head); 
        this.tail = new CachedSupplier<InfiniteListImpl<T>>(tail);
    }

   protected InfiniteListImpl(CachedSupplier<Optional<T>> head, Supplier<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = new CachedSupplier<InfiniteListImpl<T>>(tail);
    }

   public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        return new InfiniteListImpl<T> (() -> Optional.of(s.get()),
                () -> InfiniteListImpl.generate(s));
    }

   public InfiniteListImpl<T> get() {
        //this.head.get().ifPresent(System.out::println);
        this.head.get().ifPresent(x -> System.out.println(x));
        return this.tail.get();
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, Function<? super T,? extends T> next) {
        return new InfiniteListImpl<T> (() -> Optional.of(seed),
                () -> InfiniteListImpl.iterate(next.apply(seed), next));
    }

    //public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {/  public <R> InfiniteListImpl<R> map(Function<T, R> mapper) { 
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) { 
            return new InfiniteListImpl<R> (
                () -> head.get().map(mapper),   // map mtd from optional
                () -> tail.get().map(mapper)    // map mtd from class
            );
    }

   public InfiniteListImpl<T> filter(Predicate<? super T> pred) {
        return new InfiniteListImpl<T>(
                () -> head.get().filter(pred),
                () -> tail.get().filter(pred));
    }

   //public void forEach(Consumer<? super T> action) {
   public void forEach(Consumer<? super T> action) {
       InfiniteListImpl <T> curr = this;
       while (curr.isEmptyList() == false) {  //change this while statement to indicate end of stream
    	   curr.head.get().ifPresent(action);
           curr = curr.tail.get();
       }
   }
   
  public Object[] toArray() {
	  InfiniteListImpl<T> current = this;
//	  long listSize = this.count();
//	  Object[] objectArray = new Object[(int)listSize];
//	  this.forEach(objectArray::add);
//	  return objectArray;
	  ArrayList<Object> objList = new ArrayList<>();
	  while(!current.isEmptyList()) {
		  current.head.get().ifPresent(objList::add);
		  current = current.tail.get();
	  }
	  return objList.toArray();
  }
    public InfiniteListImpl<T> limit(long n) {
        if(n<1) {
            return new EmptyList<T>();
        } else if (n==1 && this.head.get().isPresent()) {
        	return new InfiniteListImpl<T>(this.head, () -> new EmptyList<T>());
        } else {
    	    return new InfiniteListImpl<T>(this.head, ()-> {
        	InfiniteListImpl<T> myTail = this.tail.get();
        	if(myTail.isEmptyList()){
          		//return myTail;
        		return new EmptyList<T>();
        	}else{
          		return myTail.limit(n-(head.get().isPresent()? 1 : 0));}});
            }
        }
        /*if(n > 0 && this.head.get().isPresent()) {
            return new InfiniteListImpl<T>(
                () -> this.head.get(),
                () -> this.tail.get().limit(n - 1));
        } else if (n > 0 && this.head.get().isEmpty()) {
            return new InfiniteListImpl<T>(	
            	() -> this.head.get().empty(),
            	() -> this.tail.get().limit(n - 1));
        } else {
        	return new InfiniteListImpl<T>(
        		() -> this.head.get(),
        		() -> this.tail.get());
        }*/
            
      public long count() {
//    	  long counter = 0;
//    	  InfiniteListImpl<T> current = this;
//    	  while(!current.tail.get().isEmptyList()) {
//    		  if(current.head.get().isPresent()) {
//    			  counter++;
//    			  current = current.tail.get();
//    		  } else {
//    			  current = current.tail.get();
//    		  }
//    	  }
//    	  return counter;
    	  return this.toArray().length;
      }
    
      public Optional<T> reduce (BinaryOperator<T> accumulator) {
    	  InfiniteListImpl<T> current = this;
    	  Optional<T> value = Optional.empty();
    	  while(!current.isEmptyList()) {
    		  if(current.head.get().isPresent()) {
    			  if(value.isEmpty()) {
    			      value = current.head.get();
    			  } else {
    				  value = Optional.of(accumulator.apply(value.get(), current.head.get().get()));
    			  }
    		  }
    		  current = current.tail.get(); //Pass the remaining tail up the list. 
    	  }
    	  return value;
      }
    public <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator) { 
    	InfiniteListImpl<T> current = this; // In this method, dun need set for Optional
    	U result = identity;
    	T value;
    	while(!current.isEmptyList()) {
    		if(current.head.get().isPresent()) {
    				value = current.head.get().get();
    			    result = accumulator.apply(result, value); 
    		}
    		current = current.tail.get();
    	}
    	return result;
    }
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
    	InfiniteListImpl<T> current = this;
    	CachedSupplier<Boolean> predicateCheck = new CachedSupplier<>(() -> predicate.test(current.head.get().get()));
    	return new InfiniteListImpl<T>(() ->  {
    		if(current.head.get().isPresent()) {
    		    if(predicateCheck.get()) {
    			    return current.head.get();
    		    }
    		}
    		return Optional.empty();
    	}, () -> {
    		if(current.head.get().isPresent()) {
    			if(!predicateCheck.get()) {
    				return new EmptyList<T>();
    			}
    		}
    		InfiniteListImpl<T> myTail = current.tail.get();
    		if(myTail.isEmptyList()) {
    			return myTail;
    		}
    		return myTail.takeWhile(predicate);
    	});
//    	if(current.isEmptyList()) {
//    		return new EmptyList<T>();
//    	}
//    	return new InfiniteListImpl<T>(
//    			() -> Optional.empty(),
//    			() ->  {
//    		    if(current.head.get().isPresent()) {
//    		    	if(predicate.test(current.head.get().get())) {
//    		    		return new InfiniteListImpl<T>(current.head,
//    		    				() -> current.tail.get().takeWhile(predicate));
//    		    	} else {
//    		    		return new EmptyList<T>();
//    		    	}
//    		    } else {
//    		    	return current.tail.get().takeWhile(predicate);
//    		    }
//    	});
    }
//    	if(current.isEmptyList()) {
//    		return new EmptyList<T>();
//    	}
//    	while(!current.isEmptyList()) {
//    		if(current.head.get().isPresent()) 
//    		    if(predicate.test(current.head.get().get())){
//    		    	return new InfiniteListImpl<T>(current.head,
//    		    			current.tail);
//    		    } else {
//    		        return new EmptyList<T>();
//    		    }
//    		    current = current.tail.get();
//    		} else {
//    			return new EmptyList<T>();
//    		}

    public boolean isEmptyList() {
    	return this instanceof EmptyList;
    }
}
