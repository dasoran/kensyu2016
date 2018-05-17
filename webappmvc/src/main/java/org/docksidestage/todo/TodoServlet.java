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

        // DBとか永続化された情報アクセス
        TodoList todoList = TodoList.getInstance();

        // 関連クラス初期化
        TodoView todoView = new TodoView();

        // TODO追加！！
        String add = req.getParameter("add");
        if (add != null && add.equals("true")) {
            String value = req.getParameter("value");
            todoList.add(value);
        }

        // todoリストの状態変更
        String check = req.getParameter("check");
        if (check != null && check.equals("true")) {
            Integer checkId = Integer.parseInt(req.getParameter("value"));
            Boolean toggledCheck = todoList.get(checkId).checked ^ true;
            todoList.setCheck(checkId, toggledCheck);
        }
        String delete = req.getParameter("delete");
        Boolean isDeleteError = false;
        if (delete != null && delete.equals("true")) {
            Integer deleteId = Integer.parseInt(req.getParameter("value"));
            Boolean nowCheck = todoList.get(deleteId).checked;
            if (nowCheck) {
                todoList.delete(deleteId);
            } else {
                isDeleteError = true;
            }
        }

        // viewへ
        todoView.renderTodoPage(resp, todoList.getAll(), isDeleteError);
    }

}
