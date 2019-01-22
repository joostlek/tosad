package nl.hu.tosad.webserver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/testServlet"})
public class testServlet extends HttpServlet {
        public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int value = Integer.parseInt(req.getParameter("v"));
        int value2 = Integer.parseInt(req.getParameter("v1"));

        PrintWriter out = res.getWriter();
        out.print(value + value2);
    }
}
