package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.util.AttributeParameterHolder.*;
import static com.example.util.PageNavigation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final Users usersRepo = Users.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object user = request.getSession().getAttribute(SESSION_ATTRIBUTE_USER);

        if (user == null) {
            response.sendRedirect(LOGIN_PAGE);
        } else {
            response.sendRedirect(HELLO_PAGE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(REQUEST_PARAMETER_LOGIN);
        String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);

        boolean rightLogin = usersRepo.getUsers().contains(login);
        boolean rightPassword = password != null && !password.trim().isEmpty();

        if (rightLogin && rightPassword) {
            request.getSession().setAttribute(SESSION_ATTRIBUTE_USER, login);
            response.sendRedirect(HELLO_PAGE);
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}