package after;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class RelationLog2 {
    boolean isDel;
    ModelConfRelation modelConfRelation;

    static RelationLog2 create() {
        Random random = new Random();
        RelationLog2 relationLog = new RelationLog2();
        relationLog.isDel = random.nextBoolean();
        relationLog.modelConfRelation = ModelConfRelation.create();
        return relationLog;
    }

    public long groupValue() {
        return Objects.hash(modelConfRelation.id1)
                + Objects.hash(modelConfRelation.id2)
                + Objects.hash(modelConfRelation.used)
                + Objects.hash(modelConfRelation.reverseUsed);
    }

    public boolean isReversedLog(RelationLog2 modelConfRelationLog) {
        boolean ret = false;
        if (this.isDel != modelConfRelationLog.isDel) {
            if (modelConfRelationLog.modelConfRelation.id1.equals(this.modelConfRelation.id1)
                    && modelConfRelationLog.modelConfRelation.id2.equals(this.modelConfRelation.id2)
                    && modelConfRelationLog.modelConfRelation.used == this.modelConfRelation.used) {
                ret = true;
            } else if (modelConfRelationLog.modelConfRelation.id1.equals(this.modelConfRelation.id2)
                    && modelConfRelationLog.modelConfRelation.id2.equals(this.modelConfRelation.id1)
                    && modelConfRelationLog.modelConfRelation.used == this.modelConfRelation.reverseUsed) {
                ret = true;
            }
        }

        return ret;
    }

    static class ModelConfRelation {
        String id1;
        String id2;
        int used;

        int reverseUsed;

        static ModelConfRelation create() {
            ModelConfRelation modelConfRelation = new ModelConfRelation();
            modelConfRelation.id1 = UUID.randomUUID().toString().substring(34);
            modelConfRelation.id2 = UUID.randomUUID().toString().substring(34);
            modelConfRelation.used = new Random().nextInt() % 10;
            modelConfRelation.reverseUsed = Amdb.getReverseUsed(modelConfRelation.used);
            return modelConfRelation;
        }
    }

    static class Amdb {
        public static int getReverseUsed(int used) {
            return used++;
        }
    }
}
