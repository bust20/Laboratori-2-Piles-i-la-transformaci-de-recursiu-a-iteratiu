import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoldenTest {

    // MAX = 20 para evitar demoras excesivas con la versión recursiva
    public static final int MAX = 50;

    @Test
    void golden() {
        for (int sum = 1; sum <= MAX; sum++) {
            var rec = Partitions.numPartitionsRec(sum);
            var ite = Partitions.numPartitionsIter(sum);
            assertEquals(rec, ite, "Falló en la partición para sum = " + sum);
        }
    }
}