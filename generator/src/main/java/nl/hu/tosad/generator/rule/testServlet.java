package nl.hu.tosad.generator.rule;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/test"})
public class testServlet extends HttpServlet {
        public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
            res.setStatus(HttpServletResponse.SC_NO_CONTENT);
            int value1 = Integer.parseInt(req.getParameter("v1"));
            int value2 = Integer.parseInt(req.getParameter("v2"));
            int value3 = Integer.parseInt(req.getParameter("v3"));


            System.out.println(value1 + value2 + value3);
            PrintWriter out = res.getWriter();
            out.print(value1);
            out.print(value2);
            out.print(value3);
        }
}
