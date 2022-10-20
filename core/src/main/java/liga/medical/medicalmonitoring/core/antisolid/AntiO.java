package liga.medical.medicalmonitoring.core.antisolid;

//Ситуация когда есть функционал поиска и покупки игры, но если нужно будет добавить функционал например продажи копии,
//то нарушается принцип открытости/закрытости и необходимо делать изменения непосредственно в коде данного класса
public class AntiO {
    private final String STRATEGY = "Strategy";
    private final String ACTION = "Action";

    public void genresOfGame(String select, String nameOfGame) {
        if (select.equals(STRATEGY)) {
            System.out.println(String.format("Выбран жанр: %s", select));
            System.out.println(String.format("Поиск игры: %s", nameOfGame));
        } if (select.equals(ACTION)) {
            System.out.println(String.format("Выбран жанр: %s", select));
            System.out.println(String.format("Поиск игры: %s", nameOfGame));
        }
    }


}
