import java.util.Queue;
import java.util.LinkedList;

public class DamageTracker {

    private Queue<DamageRecord> history;

    DamageTracker () {
        // Queue implemented as a doubly LinkedList
        history = new LinkedList<DamageRecord>();
    }

    public void recordDamage (int dmg, String name) {
        history.add(new DamageRecord(dmg, name));
    }

    public void recordHeal (int healing) {
        if (history.isEmpty()) { return; }
        int remainder = healing - history.peek().dmg;
        if (remainder >= 0) {
            history.remove();
            recordHeal(remainder);
        } else {
            history.peek().dmg = Math.abs(remainder);
        }
    }

    public void dumpHistory () {
        while (!history.isEmpty()) {
            DamageRecord current = history.poll();
            System.out.println(current.player + ": " + current.dmg);
        }
    }

    private class DamageRecord {
        int dmg;
        String player;
        DamageRecord (int d, String name) {
            dmg = d;
            player = name;
        }
    }

    public static void main (String[] args) {
        DamageTracker tracky = new DamageTracker();
        tracky.recordDamage(20, "xXx1337SoldierxXx");
        tracky.recordDamage(30, "G4m3rG1rl");
        tracky.dumpHistory(); // xXx1337SoldierxXx: 20
                              // G4m3rG1rl: 30
        System.out.println("-------------------");

        tracky.recordDamage(20, "xXx1337SoldierxXx");
        tracky.recordDamage(30, "G4m3rG1rl");
        tracky.recordHeal(20); // Heal the first damage dealt
        tracky.dumpHistory(); // G4m3rG1rl: 30
        System.out.println("-------------------");

        tracky.recordDamage(20, "xXx1337SoldierxXx");
        tracky.recordDamage(30, "G4m3rG1rl");
        tracky.recordHeal(30); // Heal the first damage dealt, and some of the second
        tracky.dumpHistory(); // G4m3rG1rl: 20

        System.out.println("-------------------");
        tracky.recordDamage(20, "xXx1337SoldierxXx");
        tracky.recordDamage(30, "G4m3rG1rl");
        tracky.recordDamage(20, "Snipes4LuLz");
        tracky.recordHeal(60); // Heal the first damage dealt, and the second, then some of the third
        tracky.dumpHistory(); // Snipes4Lulz: 10

        System.out.println("-------------------");
        tracky.recordDamage(20, "xXx1337SoldierxXx");
        tracky.recordDamage(30, "G4m3rG1rl");
        tracky.recordHeal(40); // Heal the first two damages dealt
        tracky.recordDamage(90, "Snipes4LuLz");
        tracky.dumpHistory(); // G4m3rG1rl: 10
                              // Snipes4Lulz: 90
    }

}
