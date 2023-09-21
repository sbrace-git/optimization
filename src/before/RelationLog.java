package before;

import java.util.Random;
import java.util.UUID;

public class RelationLog {
    boolean isDel;
    ModelConfRelation modelConfRelation;

    static RelationLog create() {
        Random random = new Random();
        RelationLog relationLog = new RelationLog();
        relationLog.isDel = random.nextBoolean();
        relationLog.modelConfRelation = ModelConfRelation.create();
        return relationLog;
    }

    public boolean isReversedLog(RelationLog modelConfRelationLog) {
        boolean ret = false;
        if (this.isDel != modelConfRelationLog.isDel) {
            if (modelConfRelationLog.modelConfRelation.id1.equals(this.modelConfRelation.id1)
                    && modelConfRelationLog.modelConfRelation.id2.equals(this.modelConfRelation.id2)
                    && modelConfRelationLog.modelConfRelation.used == this.modelConfRelation.used) {
                ret = true;
            } else if (modelConfRelationLog.modelConfRelation.id1.equals(this.modelConfRelation.id2)
                    && modelConfRelationLog.modelConfRelation.id2.equals(this.modelConfRelation.id1)
                    && modelConfRelationLog.modelConfRelation.used == Amdb.getReverseUsed(this.modelConfRelation.used)) {
                ret = true;
            }
        }

        return ret;
    }

    static class ModelConfRelation {
        String id1;
        String id2;
        int used;

        static ModelConfRelation create() {
            ModelConfRelation modelConfRelation = new ModelConfRelation();
            modelConfRelation.id1 = UUID.randomUUID().toString().substring(34);
            modelConfRelation.id2 = UUID.randomUUID().toString().substring(34);
            modelConfRelation.used = new Random().nextInt() % 10;
            return modelConfRelation;
        }
    }

    static class Amdb {
        public static int getReverseUsed(int used) {
            return used++;
        }
    }
}
