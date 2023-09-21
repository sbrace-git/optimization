import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        long start = System.currentTimeMillis();
        main.mergeRelationLogs();
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - start) / 1000.0F + "s");
        System.out.println("relationLogs size = " + Main.relationLogs.size());
    }

    final public static LinkedList<RelationLog> relationLogs = new LinkedList<>();

    static {
        //init
        int size = 50000;
        for (int i = 0; i < size; i++) {
            relationLogs.add(RelationLog.create());
        }
    }

    private void mergeRelationLogs() {
        Set<Integer> delIndexes = new HashSet<>();
        ArrayList<RelationLog> tmpRelationLogs;
        tmpRelationLogs = new ArrayList<>(relationLogs);

        for (int index1 = 0; index1 < tmpRelationLogs.size(); ++index1) {
            if (!delIndexes.contains(index1)) {
                RelationLog modelConfRelationLog1 = tmpRelationLogs.get(index1);

                for (int index2 = index1 + 1; index2 < tmpRelationLogs.size(); ++index2) {
                    if (!delIndexes.contains(index2)) {
                        RelationLog modelConfRelationLog2 = tmpRelationLogs.get(index2);
                        if (modelConfRelationLog1.isReversedLog(modelConfRelationLog2)) {
                            delIndexes.add(index1);
                            delIndexes.add(index2);
                            break;
                        }
                    }
                }
            }
        }

        if (!delIndexes.isEmpty()) {
            List<Integer> toRemove = new ArrayList<>(delIndexes);
            //倒叙，方便relationLogs.remove()操作
            Collections.sort(toRemove, Collections.reverseOrder());
            Iterator<Integer> var11 = toRemove.iterator();
            while (var11.hasNext()) {
                Integer delIndex = var11.next();
                relationLogs.remove(delIndex.intValue());
            }
        }

    }
}