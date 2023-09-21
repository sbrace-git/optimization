import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.remove(Integer.valueOf(1));
        list.remove(Integer.valueOf(2));
        System.out.println(list);
        // [a, b, c, d]
    }
}
