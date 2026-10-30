public class Partitions {


    private static class Context {
        // Parameters
        final int sumContext;
        final int numPartsContext;
        // Local variables
        int f1;
        int f2;
        // Entry point
        EntryPoint entryPoint;

        Context(int sumContext, int numPartsContext) {
            this.sumContext = sumContext;
            this.numPartsContext = numPartsContext;
            this.f1 = 0;
            this.f2 = 0;
            this.entryPoint = EntryPoint.CALL;
        }
    }

    private enum EntryPoint {
        CALL, RESUME1, RESUME2
    }


    /*ITERATIVO*/
    public static int numPartitionsIter(int sum) {
        // Precondition: sum > 0
        int count = 0;
        for (int numParts = 1; numParts <= sum; ++numParts) {
            count += numPartitionsIter(sum, numParts);
        }
        return count;
    }

    public static int numPartitionsIter(int sum, int numParts) {
        int return_ = 0;

        var stack = new ArrayStack<Context>();
        stack.push(new Context(sum, numParts));

        while (!stack.isEmpty()) {
            var context = stack.top();

            switch (context.entryPoint) {
                case CALL -> {
                    if (context.numPartsContext <= 0 || context.numPartsContext > context.sumContext) {
                        return_ = 0;
                        stack.pop();
                    } else if (context.numPartsContext == context.sumContext) {
                        return_ = 1;
                        stack.pop();
                    } else {
                        context.entryPoint = EntryPoint.RESUME1;
                        stack.push(new Context(context.sumContext - 1, context.numPartsContext - 1));
                    }
                }
                case RESUME1 -> {
                    context.f1 = return_;

                    context.entryPoint = EntryPoint.RESUME2;
                    stack.push(new Context(context.sumContext - context.numPartsContext, context.numPartsContext));
                }
                case RESUME2 -> {
                    context.f2 = return_;
                    return_ = context.f1 + context.f2;
                    stack.pop();
                }
            }
        }

        return return_;
    }


    /*RECURSIVO*/
    public static int numPartitionsRec(int sum) {
        // Precondition: sum > 0
        int count = 0;
        for (int numParts = 1; numParts <= sum; ++numParts) {
            count += numPartitionsRec(sum, numParts);
        }
        return count;
    }

    public static int numPartitionsRec(int sum, int numParts) {
        // Precondition: sum > 0
        int np1, np2;
        // CALL
        if (numParts <= 0 || numParts > sum) {
            return 0;
        } else if (numParts == sum) {
            return 1;
        } else {
            np1 = numPartitionsRec(sum - 1, numParts - 1);
            // RESUME1
            np2 = numPartitionsRec(sum - numParts, numParts);
            // RESUME2
            return np1 + np2;
        }
    }
}
