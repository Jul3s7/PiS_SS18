import java.util.*;

public class Calculator implements RPNCalculator {
    private int result;

    private Stack<Integer> stack = new Stack<>();

    @Override
    public void add() {
        int x = stack.pop();
        int y = stack.pop();
        result = x + y;
    }

    @Override
    public void sub() {
        int x = stack.pop();
        int y = stack.pop();
        result =  x - y;
    }

    @Override
    public int mul() {
        int x = stack.pop();
        int y = stack.pop();
        return x * y;
    }

    @Override
    public int mod() {
        int x = stack.pop();
        int y = stack.pop();
        return 0;
    }

    @Override
    public int div() {
        int x = stack.pop();
        int y = stack.pop();
        if (x == 0 || y == 0){
            return 0;
        } else {
            return x / y;
        }
    }

    @Override
    public int neg(int x) {
        x = stack.pop();
        return x * -1;
    }

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public void setResult(int result) {
        this.result = result;
    }

    private void push(int x){
        stack.push(x);
    }

    public static void main(String arg[]){

        Calculator calc = new Calculator();
        calc.push(3);
        calc.push(5);
        calc.add();
        System.out.println(calc.getResult());

    }
}
