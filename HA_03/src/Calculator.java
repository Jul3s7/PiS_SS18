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
    public void mul() {
        int x = stack.pop();
        int y = stack.pop();
        result = x * y;
    }

    @Override
    public void mod() {
        int x = stack.pop();
        int y = stack.pop();
        //modulo berechnen
    }

    @Override
    public void div() {
        int x = stack.pop();
        int y = stack.pop();
        if (x != 0 || y != 0){
            result = x/y;
        }
    }

    @Override
    public void neg(int x) {
        x = stack.pop();
        result = x * -1;
    }

    @Override
    public void fakul(int i) {

    }

    @Override
    public void deleteLastDigit() {

    }

    @Override
    public void deleteDigit() {

    }

    @Override
    public void deleteStack() {

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
        calc.sub();
        System.out.println(calc.getResult());

    }
}
