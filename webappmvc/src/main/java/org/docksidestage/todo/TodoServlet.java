package org.docksidestage.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.docksidestage.core.DocksideServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dasoran
 */
public class TodoServlet extends DocksideServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Servlet Coming!");

        // DBとか永続化された情報アクセス
        TodoList todoList = TodoList.getInstance();

        // ヘッダ系出力
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<style>");
        out.println("#todo-list li.completed label {");
        out.println("color: #d9d9d9;");
        out.println("text-decoration: line-through;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"todoapp\">");

        // TODO追加！！
        String add = req.getParameter("add");
        if (add != null && add.equals("true")) {
            String value = req.getParameter("value");
            todoList.add(value);
        }
        out.println("<header id=\"header\">");
        out.println("<h1>todos</h1>");
        out.println("<form action=\"/todo\" method=\"get\">");
        out.println("<input type=\"hidden\" name=\"add\" value=\"true\">");
        out.println("<input id=\"value\" name=\"value\" placeholder=\"What needs to be done?\" />");
        out.println("<input type=\"submit\" value=\"送信\" />");
        out.println("</form>");
        out.println("</header>");

        // todoリストの状態変更
        String check = req.getParameter("check");
        if (check != null && check.equals("true")) {
            Integer checkId = Integer.parseInt(req.getParameter("value"));
            Boolean toggledCheck = todoList.get(checkId).checked ^ true;
            todoList.setCheck(checkId, toggledCheck);
        }
        String delete = req.getParameter("delete");
        if (delete != null && delete.equals("true")) {
            Integer deleteId = Integer.parseInt(req.getParameter("value"));
            Boolean nowCheck = todoList.get(deleteId).checked;
            if (nowCheck) {
                todoList.delete(deleteId);
            } else {
                out.println("<p>削除する前にチェックしてください</p>");
            }
        }

        // todoリストを表示
        out.println("<section id=\"main\">");
        out.println("<ul id=\"todo-list\">");
        List<TodoElement> existTodo = todoList.getAll();
        for (int i = 0; i < existTodo.size(); i++) {
            TodoElement todo = existTodo.get(i);
            out.println("<li>");
            if (todo.checked) {
                out.println("<a href=\"/todo?check=true&value=" + i + "\">✓</a>");
            } else {
                out.println("<a href=\"/todo?check=true&value=" + i + "\">・</a>");
            }
            out.println("<label>" + todo.name + "</label>");
            out.println("<a href=\"/todo?delete=true&value=" + i + "\">削除</a>");
            out.println("</li>");
        }
        out.println("</ul>");
        out.println("</section>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
