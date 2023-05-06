package com.xyz.roemahduren.presentation.screen;

import com.xyz.roemahduren.presentation.component.LabelLink;
import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.component.input.RoundedPasswordField;
import com.xyz.roemahduren.presentation.component.input.RoundedTextField;
import com.xyz.roemahduren.util.SwingUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author user
 */
public class RegisterScreen extends JFrame {

    /**
     * Creates new form RegisterScreen
     */
    public RegisterScreen() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        SwingUtil.centerWindow(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfEmail = new RoundedTextField();
        errorEmailLabel = new JLabel();
        errorPasswordLabel = new JLabel();
        emailLabel = new JLabel();
        passwordLabel = new JLabel();
        titleLabel = new JLabel();
        loginLabelText = new JLabel();
        registerBtn = new RoundedButton();
        loginLabelLink = new LabelLink();
        tfPassword = new RoundedPasswordField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(480, 500));
        setMinimumSize(new Dimension(480, 500));
        setSize(new Dimension(480, 500));

        errorEmailLabel.setForeground(new Color(220, 53, 69));

        errorPasswordLabel.setForeground(new Color(220, 53, 69));

        emailLabel.setForeground(new Color(49, 53, 59));
        emailLabel.setText("Email");

        passwordLabel.setForeground(new Color(49, 53, 59));
        passwordLabel.setText("Password");

        titleLabel.setFont(new Font("Helvetica Neue", 0, 18)); // NOI18N
        titleLabel.setForeground(new Color(49, 53, 59));
        titleLabel.setText("Create New Account");

        loginLabelText.setForeground(new Color(49, 53, 59));
        loginLabelText.setText("Already have an account?");

        registerBtn.setBorder(null);
        registerBtn.setForeground(new Color(255, 255, 255));
        registerBtn.setText("Register");
        registerBtn.setFont(new Font("Helvetica Neue", 1, 14)); // NOI18N

        loginLabelLink.setForeground(new Color(49, 53, 59));
        loginLabelLink.setText("Login");

        tfPassword.setText("roundedPasswordField1");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleLabel)
                    .addComponent(passwordLabel)
                    .addComponent(errorPasswordLabel)
                    .addComponent(errorEmailLabel)
                    .addComponent(emailLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginLabelText)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginLabelLink, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfEmail, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(registerBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addComponent(emailLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorEmailLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordLabel)
                .addGap(2, 2, 2)
                .addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorPasswordLabel)
                .addGap(18, 18, 18)
                .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabelText)
                    .addComponent(loginLabelLink, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public JLabel getErrorEmailLabel() {
        return errorEmailLabel;
    }

    public void setErrorEmailLabel(JLabel errorEmailLabel) {
        this.errorEmailLabel = errorEmailLabel;
    }

    public JLabel getErrorPasswordLabel() {
        return errorPasswordLabel;
    }

    public void setErrorPasswordLabel(JLabel errorPasswordLabel) {
        this.errorPasswordLabel = errorPasswordLabel;
    }

    public LabelLink getLoginLabelLink() {
        return loginLabelLink;
    }

    public void setLoginLabelLink(LabelLink loginLabelLink) {
        this.loginLabelLink = loginLabelLink;
    }

    public JLabel getLoginLabelText() {
        return loginLabelText;
    }

    public void setLoginLabelText(JLabel loginLabelText) {
        this.loginLabelText = loginLabelText;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public RoundedButton getRegisterBtn() {
        return registerBtn;
    }

    public void setRegisterBtn(RoundedButton registerBtn) {
        this.registerBtn = registerBtn;
    }

    public RoundedTextField getTfEmail() {
        return tfEmail;
    }

    public void setTfEmail(RoundedTextField tfEmail) {
        this.tfEmail = tfEmail;
    }

    public RoundedPasswordField getTfPassword() {
        return tfPassword;
    }

    public void setTfPassword(RoundedPasswordField tfPassword) {
        this.tfPassword = tfPassword;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel emailLabel;
    private JLabel errorEmailLabel;
    private JLabel errorPasswordLabel;
    private LabelLink loginLabelLink;
    private JLabel loginLabelText;
    private JLabel passwordLabel;
    private RoundedButton registerBtn;
    private RoundedTextField tfEmail;
    private RoundedPasswordField tfPassword;
    private JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
