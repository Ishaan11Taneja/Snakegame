import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Snakegame extends JPanel implements ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int DOT_SIZE = 20;
    private final int ALL_DOTS = 900;
    private final int DELAY = 140;

    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];

    private int dots;
    private int appleX;
    private int appleY;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = false;
    private boolean gameOver = false;
    private Timer timer;
    private JButton startButton;
    private JFrame frame;

    // Constructor
    public Snakegame(JFrame frame) {
        this.frame = frame;
        initGame();
    }

    // Initialize the game
    private void initGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setLayout(new BorderLayout());

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inGame) {
                    inGame = true;
                    startButton.setEnabled(false);
                    requestFocusInWindow();
                    addKeyListener(new TAdapter());
                    setBorder(BorderFactory.createLineBorder(Color.white));
                    dots = 3;
                    for (int i = 0; i < dots; i++) {
                        x[i] = 60 - i * DOT_SIZE;
                        y[i] = 60;
                    }
                    locateApple();
                    timer = new Timer(DELAY, Snakegame.this);
                    timer.start();
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    // Draw game components
    private void doDrawing(Graphics g) {
        if (inGame) {
            g.setColor(Color.red);
            g.fillRect(appleX, appleY, DOT_SIZE, DOT_SIZE);

            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x[i], y[i], DOT_SIZE, DOT_SIZE);
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            if (gameOver) {
                String msg = "Game Over";
                Font small = new Font("Helvetica", Font.BOLD, 24);
                FontMetrics metr = getFontMetrics(small);

                g.setColor(Color.white);
                g.setFont(small);
                g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
            } else {
                String title = "Snake Game";
                Font titleFont = new Font("Helvetica", Font.BOLD, 36);
                FontMetrics titleMetr = getFontMetrics(titleFont);

                g.setColor(Color.white);
                g.setFont(titleFont);
                g.drawString(title, (WIDTH - titleMetr.stringWidth(title)) / 2, HEIGHT / 2);
            }
        }
    }

    // Check if snake eats apple
    private void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            dots++;
            locateApple();
        }
    }

    // Move the snake
    private void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }
        if (rightDirection) {
            x[0] += DOT_SIZE;
        }
        if (upDirection) {
            y[0] -= DOT_SIZE;
        }
        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    // Check for collisions
    private void checkCollision() {
        for (int i = dots; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
                gameOver = true;
            }
        }
        if (y[0] >= HEIGHT || y[0] < 0 || x[0] >= WIDTH || x[0] < 0) {
            inGame = false;
            gameOver = true;
        }
        if (!inGame) {
            timer.stop();
            startButton.setEnabled(true);
            Timer resetTimer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetGame();
                }
            });
            resetTimer.setRepeats(false);
            resetTimer.start();
        }
    }

    // Reset the game
    private void resetGame() {
        inGame = false;
        gameOver = false;
        frame.setTitle("Snake Game");
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 60 - i * DOT_SIZE;
            y[i] = 60;
        }
        locateApple();
        repaint();
    }

    // Place the apple within window boundaries
    private void locateApple() {
        Random rand = new Random();
        appleX = rand.nextInt((WIDTH / DOT_SIZE) - 1) * DOT_SIZE;
        appleY = rand.nextInt((HEIGHT / DOT_SIZE) - 1) * DOT_SIZE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
            repaint();
        }
    }

    // Handle keyboard input
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Snakegame game = new Snakegame(frame);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
