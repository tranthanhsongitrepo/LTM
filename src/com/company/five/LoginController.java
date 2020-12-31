package com.company.five;

import java.sql.*;

public class LoginController {
    private Connection connection;
    public LoginController() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?useSSL=false", "root", "password");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean checkLogin(LoginModel loginModel) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM LTM.tbl_nguoiChoi WHERE tenDangNhap = ? AND matKhau = ?");
            ps.setString(1, loginModel.getUsername());
            ps.setString(2, loginModel.getPassword());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;

    }
}
