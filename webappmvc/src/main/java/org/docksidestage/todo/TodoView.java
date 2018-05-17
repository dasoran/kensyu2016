package org.docksidestage.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 画面を作るクラス
 * @author dasoran
 */
public class TodoView {

    public void renderTodoPage(HttpServletResponse resp, List<TodoElement> existTodo, Boolean isDeleteError)
            throws ServletException, IOException {

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
        out.println("<header id=\"header\">");
        out.println("<h1>todos</h1>");
        out.println("<form action=\"/todo\" method=\"get\">");
        out.println("<input type=\"hidden\" name=\"add\" value=\"true\">");
        out.println("<input id=\"value\" name=\"value\" placeholder=\"What needs to be done?\" />");
        out.println("<input type=\"submit\" value=\"送信\" />");
        out.println("</form>");
        out.println("</header>");

        // deleteのエラー
        if (isDeleteError) {
            out.println("<p>削除する前にチェックしてください</p>");
        }

        // todoリスト表示
        out.println("<section id=\"main\">");
        out.println("<ul id=\"todo-list\">");
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

        // フッター
        out.println("</ul>");
        out.println("</section>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
