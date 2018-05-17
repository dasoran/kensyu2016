package org.docksidestage.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.docksidestage.core.DocksideServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoServlet extends DocksideServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Servlet Coming!");

        // 関連クラス初期化
        TodoView todoView = new TodoView();
        TodoManipulation todoManipulation = new TodoManipulation();

        // URLからパラメータ取得
        String add = req.getParameter("add");
        String check = req.getParameter("check");
        String delete = req.getParameter("delete");

        boolean isDeleteError = false;
        if ("true".equals(add)) {
            // TODO追加！！
            String value = req.getParameter("value");
            todoManipulation.addTodo(value);
        } else if ("true".equals(check)) {
            // TODOのチェック変更
            int checkId = Integer.parseInt(req.getParameter("value"));
            todoManipulation.toggleCheck(checkId);
        } else if ("true".equals(delete)) {
            // TODO削除
            int deleteId = Integer.parseInt(req.getParameter("value"));
            isDeleteError = todoManipulation.deleteTodo(deleteId);
        }

        // viewへ
        todoView.renderTodoPage(resp, todoManipulation.getAllTodo(), isDeleteError);
    }
}
