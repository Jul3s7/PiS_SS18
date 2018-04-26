public interface RPNCalculator {

    //TODO Grundrechenarten
    //TODO Vorzeichenwechsel (+/-)
    //TODO Fakultät
    //TODO Löschen der Eingabe, löschen des obersten Wertes, alles löschen

    public void add();
    public void sub();
    public int mul();
    public int mod();
    public int div();
    public int neg(int x);

    public int getResult();
    public void setResult(int result);


}
