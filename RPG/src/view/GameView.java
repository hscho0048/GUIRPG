package view;

import javax.swing.*;
import java.awt.*;
import model.Player;
import model.Opponent;
import controller.GameController;

public class GameView extends JFrame {
    private JLabel playerImageLabel, opponentImageLabel;
    private JLabel playerInfoLabel, opponentInfoLabel;
    private JButton attackButton;
    private Player player;
    private Opponent opponent;
    private GameController controller;

    public GameView(String playerName) {
        // 초기화
        player = new Player(playerName, 100, 1);
        opponent = new Opponent("상대", 100);
        controller = new GameController(player, opponent, this);

        setTitle("RPG 전투");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 플레이어 이미지와 정보
        playerImageLabel = new JLabel(new ImageIcon("resources/playerImage.jpg"));
        playerInfoLabel = new JLabel("플레이어: " + player.getName() + " | 체력: " + player.getHealth());
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.add(playerImageLabel);
        playerPanel.add(playerInfoLabel);

        // 상대방 이미지와 정보
        opponentImageLabel = new JLabel(new ImageIcon("resources/opponentImage.jpg"));
        opponentInfoLabel = new JLabel("상대: " + opponent.getName() + " | 체력: " + opponent.getHealth());
        JPanel opponentPanel = new JPanel();
        opponentPanel.setLayout(new BoxLayout(opponentPanel, BoxLayout.Y_AXIS));
        opponentPanel.add(opponentImageLabel);
        opponentPanel.add(opponentInfoLabel);

        // 전투 버튼
        attackButton = new JButton("공격");
        attackButton.addActionListener(e -> controller.playerAttack());

        // 상단에 전투 상태 표시
        JPanel statusPanel = new JPanel();
        JLabel statusLabel = new JLabel("플레이어의 턴입니다!");
        statusPanel.add(statusLabel);

        // 메인 패널에 추가
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2)); // 좌우로 플레이어와 상대방 배치
        mainPanel.add(playerPanel);
        mainPanel.add(opponentPanel);

        add(statusPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(attackButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void updateStatus(String status) {
        ((JLabel)((JPanel)getContentPane().getComponent(0)).getComponent(0)).setText(status);
    }

    public void updatePlayerInfo() {
        playerInfoLabel.setText("플레이어: " + player.getName() + " | 체력: " + player.getHealth());
    }

    public void updateOpponentInfo() {
        opponentInfoLabel.setText("상대: " + opponent.getName() + " | 체력: " + opponent.getHealth());
    }

    public void disableAttackButton() {
        attackButton.setEnabled(false);
    }

    public void enableAttackButton() {
        attackButton.setEnabled(true);
    }
}

