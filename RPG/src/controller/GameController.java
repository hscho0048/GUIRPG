package controller;

import model.Player;
import model.Opponent;
import view.GameView;
import javax.swing.*;
import java.util.Random;

public class GameController {
    private Player player;
    private Opponent opponent;
    private GameView view;
    private Random random;

    public GameController(Player player, Opponent opponent, GameView view) {
        this.player = player;
        this.opponent = opponent;
        this.view = view;
        this.random = new Random();
    }

    public void playerAttack() {
        int damage = random.nextInt(10) + 5; // 5~15 사이의 공격력
        opponent.takeDamage(damage);
        view.updateStatus("플레이어가 " + damage + " 데미지를 입혔습니다!");
        view.updateOpponentInfo();
        
        if (opponent.getHealth() <= 0) {
            view.updateStatus("플레이어가 승리했습니다!");
            view.disableAttackButton();
        } else {
            view.disableAttackButton();
            new Timer(1000, e -> opponentTurn()).start(); // 1초 후 상대의 턴
        }
    }

    private void opponentTurn() {
        boolean isAttack = opponent.decideToAttack();
        if (isAttack) {
            int damage = random.nextInt(10) + 5;
            player.takeDamage(damage);
            view.updateStatus("상대가 " + damage + " 데미지를 입혔습니다!");
            view.updatePlayerInfo();
        } else {
            view.updateStatus("상대가 방어를 선택했습니다!");
        }

        if (player.getHealth() <= 0) {
            view.updateStatus("플레이어가 패배했습니다!");
            view.disableAttackButton();
        } else {
            view.enableAttackButton();
            view.updateStatus("플레이어의 턴입니다!");
        }
    }
}
