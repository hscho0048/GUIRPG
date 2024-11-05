package model;

import java.util.Random;

public class Opponent {
    private String name;
    private int health;
    private Random random;

    public Opponent(String name, int health) {
        this.name = name;
        this.health = health;
        this.random = new Random();
    }

    public String getName() { return name; }
    public int getHealth() { return health; }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean decideToAttack() {
        return random.nextBoolean(); // true면 공격, false면 방어
    }
}
