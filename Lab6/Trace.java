import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Collections;

class Trace<T> {
    protected T t;
    protected ArrayList<T> dataList = new ArrayList<>();
   
    protected Trace(ArrayList<T> list) {
        this.dataList = list;
    }

    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    protected static <T> Trace<T> of(T... objects) { //objects is sth like collection.
        ArrayList<T> objectList = new ArrayList<>();
        for (T t: objects) {
            objectList.add(t);
        }
        Collections.rotate(objectList, -1);
        return new Trace<T>(objectList);
    }

    protected T get() {
        t = this.dataList.get(this.dataList.size() - 1);
        return t;
    } 

    @SuppressWarnings({"unchecked", "varargs"})
    protected Trace<T> map(Function<? super T, ? extends T> func) {
        ArrayList<T> newArray = new ArrayList<>(this.dataList);
        T value = func.apply(newArray.get(newArray.size() - 1));
        newArray.add(value); 
        return new Trace<T>(newArray); 
    }
    
    protected Trace<T> flatMap(Function<? super T, ? extends Trace<? extends T>> func) {
        Trace<? extends T> newTrace = func.apply(this.dataList.get(this.dataList.size() - 1));
        ArrayList<? extends T> dataList = newTrace.dataList; 
        ArrayList<T> newList = new ArrayList<>(this.dataList); 
        for (T t: dataList) {
            newList.add(t);
        }
        ArrayList<T> uniqueNewList = newList.stream()
            .distinct()
            .collect(Collectors.toCollection(ArrayList::new));
        return new Trace<T>(uniqueNewList);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    protected Trace<T> back(int rollBack) {
        int dataRemoved = this.dataList.size() - rollBack;
        if (dataRemoved < 1) {
            this.dataList.subList(1, this.dataList.size()).clear();
        } else {
            for (int i = 0; i < rollBack; i++) {
                this.dataList.remove(this.dataList.size() - 1);
            } 
        }
        return new Trace<T>(this.dataList);
    }


    protected ArrayList<T> history() {
        ArrayList<T> historyArray = new ArrayList<T>(this.dataList);
        return historyArray;
    }

    protected boolean equals(Trace obj) {
        if (this == obj) {
            return true;
        } else if (this.dataList == obj.dataList) {
            return true;
        } else if (this.history().equals(obj.history())) {
            return true;
        } else {
            return false;
        }
    }
}
