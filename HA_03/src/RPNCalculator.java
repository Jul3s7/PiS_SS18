public interface RPNCalculator {

    //TODO Fakultät
    //TODO Löschen der Eingabe, löschen des obersten Wertes, alles löschen

    void add();
    void sub();
    void mul();
    void mod();
    void div();
    void neg(int x);

    void fakul(int i);
    void deleteLastDigit();
    void deleteDigit();
    void deleteStack();


    int getResult();
    void setResult(int result);


}
