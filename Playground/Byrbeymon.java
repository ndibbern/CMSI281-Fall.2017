public class Burnymon implements MinForneymon {

    // [?] #1
    private int health;
    private String name;

    // [?] #2
    Burnymon (String n) {
        health = 15;
        name = n;
    }

    // [?] #3
    public int takeDamage (int dmg, String type) {
        health -= dmg;
        return health;
    }

    public String toString () {
        return name + " " + name;
    }

    // [?] #4
    public int getHealth () {return health;}
    public String getName () {return name;}

}
