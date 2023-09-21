package after;

import java.util.*;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        Main2 main = new Main2();
        long start = System.currentTimeMillis();
        main.mergeRelationLogs2();
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - start) / 1000.0F + "s");
        System.out.println("relationLogs size = " + Main2.relationLogs.size());
    }

    public static LinkedList<RelationLog2> relationLogs = new LinkedList<>();

    static {
        //init
        int size = 50000;
        for (int i = 0; i < size; i++) {
            relationLogs.add(RelationLog2.create());
        }
    }

    private void mergeRelationLogs2() {
        relationLogs = relationLogs.stream().collect(Collectors.groupingBy(RelationLog2::groupValue))
                .values()
                .stream()
                .map(this::dropSameItem)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private List<RelationLog2> dropSameItem(List<RelationLog2> list) {
        for (int i = 0; i < list.size(); i++) {
            RelationLog2 outRelationLog2 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                RelationLog2 inRelationLog2 = list.get(j);
                if (outRelationLog2.isReversedLog(inRelationLog2)) {
                    list.remove(j);
                    list.remove(i);
                    dropSameItem(list);
                    return list;
                }
            }
        }
        return list;
    }
}