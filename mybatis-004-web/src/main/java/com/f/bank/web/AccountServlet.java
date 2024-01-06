package com.f.bank.web;

import com.f.bank.exceptions.MoneyNotEnoughException;
import com.f.bank.exceptions.TransferException;
import com.f.bank.service.AccountService;
import com.f.bank.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author fzy
 * @date 2024/1/6 11:18
 */

@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    // 为了让这个对象在其他方法中也可以用，将其声明为属性
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取表单数据
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));
        try {
            // 调用service的转账方法完成转账（调业务层）
            accountService.transfer(fromActno, toActno, money);
            // 调用view完成展示结果
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (MoneyNotEnoughException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (TransferException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
