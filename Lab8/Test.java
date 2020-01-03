import java.util.Optional;
import java.util.function.Function;

class Test {
    public static void main(String[] args) {
        int x = 100;
        int y = 40;
        String z = null;
        Optional<Integer> containerX = Optional.ofNullable(x);
        Optional<Integer> containerY = Optional.ofNullable(y);
        Optional<String> stringZ = Optional.ofNullable(z);
        System.out.println(stringZ.isPresent());
        if(containerX.isPresent()) {
             System.out.println(containerX.get().get());
            //containerX.ifPresent(t -> System.out.println(t));
        }
    }
}
