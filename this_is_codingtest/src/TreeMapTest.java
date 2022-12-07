import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
    public static TreeMap<Integer, String> mapTest = new TreeMap<>();

    static {
        mapTest.put(1,  "A");
        mapTest.put(5,  "C");
        mapTest.put(10, "E");
        mapTest.put(4,  "B");
        mapTest.put(9,  "D");
    }

    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();

        map.put(1, "ABC");
        map.put(2, "DEF");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        for (Integer i : map.keySet()) {
            System.out.println(i + " : " + map.get(i));
        }

        Iterator<Map.Entry<Integer, String>> entries = map.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<Integer, String> entry = entries.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        Iterator<Integer> keys = map.keySet().iterator();

        while (keys.hasNext()) {
            int key = keys.next();
            System.out.println(key + " : " + map.get(key));
        }

        System.out.println("ceilingEntry() : " + mapTest.ceilingEntry(8)); // 9=D
        System.out.println("ceilingKey() : " + mapTest.ceilingKey(8)); // 9
        System.out.println("floorEntry() : " + mapTest.floorEntry(8)); // 5=C
        System.out.println("floorKey() : " + mapTest.floorKey(8)); // 5

        System.out.println(mapTest);
    }
}
