package Partitions;

public class Factorial {
}



public static long factorialIter(int n) {
    long return_ = 0L;
    var stack = new ArrayStack<Context>();
    stack.push(new Context(n));
    while (!stack.isEmpty()) {
        var context = stack.top();
        switch (context.entryPoint) {
            case CALL -> {
                if (context.n == 0) { // simple
                    return_ = 1L;
                    stack.pop();
                } else {
                    context.entryPoint = EntryPoint.RESUME;
                    stack.push(new Context(context.n - 1));
                }
            }
            case RESUME -> {
                context.f = return_;
                return_ = context.f * context.n;
                stack.pop();
            }
        }
    }
    return return_;
}




