package liga.medical.medicalmonitoring.core.antisolid;

import liga.medical.medicalmonitoring.core.antisolid.antil.Dog;

//Нарушение логики атаки
public class AntiL extends Dog {
    public AntiL() {
        super();
    }

    public String attack() {
        throw new RuntimeException("Unknown command");
    }
}
