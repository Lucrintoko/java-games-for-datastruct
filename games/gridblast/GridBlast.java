package games.gridblast;

import games.gridblast.controller.*;
import games.gridblast.model.GameSession;
import games.gridblast.view.*;
import java.awt.*;
import javax.swing.*;

public class GridBlast {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Grid Blast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);

        startGame(frame);

        frame.setVisible(true);
    }

    public static void startGame(JFrame frame) {

        frame.getContentPane().removeAll();

        GameSession session = new GameSession();
        InputState inputState = new InputState();

        GamePanel gamePanel = new GamePanel(session, inputState);
        PieceSelectionPanel piecePanel = new PieceSelectionPanel(session, inputState);
        ScorePanel scorePanel = new ScorePanel(session);
        TopBarPanel topBar = new TopBarPanel();

        GameController gameController =
                new GameController(session, inputState, gamePanel, piecePanel, scorePanel);

        PieceSelectionController pieceController =
                new PieceSelectionController(session, inputState, piecePanel, gamePanel);

        gamePanel.addMouseListener(gameController);
        gamePanel.addMouseMotionListener(gameController);
        piecePanel.addMouseListener(pieceController);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.setBackground(UIConstants.BACKGROUND_COLOR);
        topContainer.add(topBar, BorderLayout.NORTH);
        topContainer.add(scorePanel, BorderLayout.SOUTH);

        frame.add(topContainer, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(piecePanel, BorderLayout.EAST);

        piecePanel.setPreferredSize(new Dimension(220, 600));
        scorePanel.setPreferredSize(new Dimension(200, 60));

        frame.getContentPane().setBackground(UIConstants.BACKGROUND_COLOR);

        JPopupMenu pauseMenu = new JPopupMenu();

        JMenuItem resumeItem = new JMenuItem("Resume");
        JMenuItem restartItem = new JMenuItem("Restart");
        JMenuItem exitItem = new JMenuItem("Exit");

        pauseMenu.add(resumeItem);
        pauseMenu.add(restartItem);
        pauseMenu.add(exitItem);

        topBar.getMenuButton().addActionListener(e -> {
            JButton btn = topBar.getMenuButton();
            pauseMenu.show(btn, btn.getWidth() / 2, btn.getHeight());
        });

        resumeItem.addActionListener(e -> {});

        restartItem.addActionListener(e -> startGame(frame));

        exitItem.addActionListener(e -> System.exit(0));

        frame.revalidate();
        frame.repaint();
    }

    public static void showGameOver(JFrame frame) {

        frame.getContentPane().removeAll();

        GameOverPanel gameOver = new GameOverPanel();
        frame.add(gameOver, BorderLayout.CENTER);

        gameOver.getRestartButton().addActionListener(e -> startGame(frame));
        gameOver.getExitButton().addActionListener(e -> System.exit(0));

        frame.revalidate();
        frame.repaint();
    }
}
