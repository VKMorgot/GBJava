package course2.lesson3.lesson_c;

import java.util.*;

public class Collections {

    static class Box implements Comparable {
        // Comparable - нужен, чтобы мы могли сортировать элементы класса
        int width;
        int height;
        int num;

        Box(int width, int height, int num) {
            this.width = width;
            this.height = height;
            this.num = num;
        }

        @Override
        public String toString() {
            return String.format("Box(%d, %d (%d))", width, height, num);
        }

        @Override
        public int hashCode() {
            return Objects.hash(width, height);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Box)) return false;
            Box other = (Box) obj;
            return width == other.width && height == other.height;
        }

        private int square() {
            return height * width;
        }

        @Override
        public int compareTo(Object o) {
//            Box other = (Box) o;
//            int sq = square();
//            int oSq = other.square();
//            return Integer.compare(sq, oSq);  //  то же самое, что и блок if-else ниже
//            if (sq < oSq) return -1;
//            else if (sq > oSq) {
//                return 1;
//            } else return 0;
//            return sq - oSq;
            return square() - ((Box) o).square();
        }
    }

    public static void main(String[] args) {

        // =================List========================

//        ArrayList<Box> array = new ArrayList<>();
//        array.add(new Box(1, 1));
//        array.add(new Box(2, 2));
//        array.add(new Box(3, 3));
        Box b = new Box(1, 2, 1);
        Box[] boxes = {
                new Box(1, 2, 2),
                new Box(1, 5, 5),
                new Box(1, 4, 4),
                new Box(1, 3, 3),
                b
        };
//        List<Box> array = new LinkedList<>(Arrays.asList(boxes));
//        ArrayList<Box> array = new ArrayList<>(Arrays.asList(boxes));
        List<Box> array = new ArrayList<>(Arrays.asList(boxes));
        System.out.println(array);
        System.out.println(array.contains(b));
        System.out.println("----------------");

        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
        System.out.println("--------------");

        Iterator<Box> iter = array.iterator();
        while (iter.hasNext()) {
            Box bb = iter.next();
            System.out.println(bb);
        }
        System.out.println("-----------------");

        for (Box box : array) {
            System.out.println(box);
        }
        System.out.println("-----------------");

        // =================Set=============================
        // hashCode - некая цифры, которая получается в результате некоторых вычислений
        // и манипуляций со значениями полей объектов
        System.out.println(b.equals(array.get(0)));
        System.out.println(Integer.toHexString(b.hashCode()));
        System.out.println(Integer.toHexString(array.get(0).hashCode()));

//        HashSet<Box> set = new HashSet<>(Arrays.asList(boxes));
        HashSet<Box> set = new LinkedHashSet<>(Arrays.asList(boxes));
        System.out.println(set);
        set.add(b);
        System.out.println(set);
        System.out.println("-----------------");

        TreeSet<Box> tree = new TreeSet<>(Arrays.asList(boxes));
        System.out.println(tree);
        System.out.println("-----------------");

        // =====================Map========================
        Map<String, Integer> mouths = new HashMap<>();
        mouths.put("January", 1);
        mouths.put("February", 2);
        mouths.put("March", 3);
        mouths.put("April", 4);

        System.out.println(mouths);
        mouths.put("January", 10);
        System.out.println(mouths);

        Set<String> s = mouths.keySet();
        for (String k: s) {
            System.out.printf("%s:%d ", k, mouths.get(k));
        }

    }
}
