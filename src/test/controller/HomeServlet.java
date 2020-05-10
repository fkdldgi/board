package test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String top=(String)req.getAttribute("top");
		String content=(String)req.getAttribute("content");
		if(top==null) {
			top="/header.jsp";
		}
		if(content==null) {
			content="/home.jsp";
		}
		req.setAttribute("top",top);
		req.setAttribute("content",content);
		getServletContext().setAttribute("cp",req.getContextPath());
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}










