package interfaces;

@FunctionalInterface
public interface SqaureFunction<T> {

    T sqaure(T x);

    default void print(T x){
        System.out.println(this.sqaure(x));
    }

}
