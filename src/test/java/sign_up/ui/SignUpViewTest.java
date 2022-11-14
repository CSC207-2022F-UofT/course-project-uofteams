package sign_up.ui;

import javax.swing.*;

public class SignUpViewTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SignUpView();
            }
        });
    }
}
