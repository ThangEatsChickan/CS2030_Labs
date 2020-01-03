import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Collections;

class ChildTrace<T> extends Trace<T> {

    protected ChildTrace(ArrayList<T> list) {
        super(list);
    }
    
    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    protected static <T> ChildTrace<T> of(T... objects) {
        ArrayList<T> objectList = new ArrayList<>();
        for (T t: objects) {
            objectList.add(t);
        }
        Collections.rotate(objectList, -1);
        return new ChildTrace<T>(objectList);
    }
}
