package com.company.five;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JPasswordField;

import javax.swing.JTextField;

import javax.swing.JButton;
public class LoginView extends JFrame implements ActionListener {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private LoginModel model;

    public LoginView() {
        super("Login MVC");
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtPassword.setEchoChar('*');
        btnLogin = new JButton("Login");
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Username:"));
        content.add(txtUsername);
        content.add(new JLabel("Password:"));
        content.add(txtPassword);
        content.add(btnLogin);
        this.setContentPane(content);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new LoginController().checkLogin(LoginView.this.getUser())) {
                    LoginView.this.showMessage("Success");
                }
                else {
                    LoginView.this.showMessage("Fail");
                }
            }
        });
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public LoginModel getUser() {
        model = new LoginModel(txtUsername.getText(),
                txtPassword.getText());
        return model;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void addLoginListener(ActionListener log) {
        btnLogin.addActionListener(log);
    }
}