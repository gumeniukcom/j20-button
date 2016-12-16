package su.vigo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import su.vigo.Resources.Resources;

public class FirstFrame extends JFrame implements MouseMotionListener {

    private final JButton button;
    private final JButton buttonYes;
    private final JLabel labelMsg;

    private final Random random = new Random();
    private static final int DIFF_STEP = 20;


    public FirstFrame() throws HeadlessException {
        super(Resources.string("application.title"));

        labelMsg = new JLabel(Resources.string("label.message"), JLabel.CENTER);
        button = new JButton(Resources.string("button.push.me.text"));
        buttonYes = new JButton(Resources.string("button.yes.text"));

        initComponents();
    }

    private void initComponents() {


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);

        setContentPane(createContentPane());

        button.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                setBtnPosition(button.getX() + 100 * (random.nextInt(2) - 2), button.getY() + 100 * (random.nextInt(2) - 2));
            }
        });
        button.setBounds(100, 200, 100, 100);
        buttonYes.setBounds(300, 200, 100, 100);

        addMouseMotionListener(this);


        labelMsg.setBounds(0, 0, 300, 100);
        add(button, BorderLayout.SOUTH);
        add(buttonYes, BorderLayout.SOUTH);
        add(labelMsg, BorderLayout.NORTH);

        pack();
    }


    private Container createContentPane() {
        Dimension dimension = new Dimension(500, 500);
        JPanel panel = new JPanel();
        panel.setPreferredSize(dimension);
        panel.setLayout(null);

        return panel;
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + " " + button.getX() + " " + button.getWidth());

        int btnX = button.getX() + button.getWidth() / 2;
        int btnY = button.getY() + button.getHeight() / 2;

//        int diff = (int) Math.sqrt(Math.pow((btnX - e.getX()), 2) + Math.pow((btnY - e.getY()), 2));


        if (checkPosition(button.getX(), button.getY(), e.getX(), e.getY(), button.getWidth())) {

            if ((button.getX() + button.getWidth() + DIFF_STEP < e.getX()) && (btnX - DIFF_STEP < e.getX())) {
                setBtnPosition(button.getX() - DIFF_STEP, button.getY());
            } else if (btnX - DIFF_STEP > e.getX()) {
                setBtnPosition(button.getX() + DIFF_STEP, button.getY());
            } else if ((btnY + button.getHeight() + DIFF_STEP > e.getY()) && (btnY - DIFF_STEP > e.getY())) {
                setBtnPosition(button.getX(), button.getY() + DIFF_STEP);
            } else if (btnY - DIFF_STEP < e.getY()) {
                setBtnPosition(button.getX(), button.getY() - DIFF_STEP);
            }

        }
    }

    private boolean checkPosition(int x, int y, int ex, int ey, int checkDiff) {

        int btnX = x + button.getWidth() / 2;
        int btnY = y + button.getHeight() / 2;

        int diff = (int) Math.sqrt(Math.pow((btnX - ex), 2) + Math.pow((btnY - ey), 2));

        return diff < checkDiff;
    }

    private void checkBtnPositionOnLayout() {

        if (button.getX() < 0) {
            setBtnPosition(button.getX() + 150*(random.nextInt(2)+1), button.getY());
        }

        if (button.getX() + button.getWidth() > this.getWidth()) {
            setBtnPosition(button.getX() - 150*(random.nextInt(2)+1), button.getY());
        }

        if (button.getY() < 0) {
            setBtnPosition(button.getX(), button.getY() + 150*(random.nextInt(2)+1));
        }

        if (button.getY() + button.getHeight() > this.getHeight()) {
            setBtnPosition(button.getX(), button.getY() - 150*(random.nextInt(2)+1));
        }
    }

    private void setBtnPosition(int x, int y) {
        button.setBounds(x, y, button.getWidth(), button.getHeight());
        checkBtnPositionOnLayout();
    }

}
