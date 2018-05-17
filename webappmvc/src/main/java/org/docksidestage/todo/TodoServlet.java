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
        // staticにしてもマルチスレッド的に問題なさそうなら毎回インスタンス作らないほうが効率よさそう
        TodoView todoView = new TodoView();
        TodoMain todoMain = new TodoMain();

        // TODO追加！！
        addTodo(todoMain, req);

        // todoリストの状態変更
        toggleCheck(todoMain, req);
        Boolean isDeleteError = deleteTodo(todoMain, req);

        // viewへ
        todoView.renderTodoPage(resp, todoMain.getAllTodo(), isDeleteError);
    }

    public void addTodo(TodoMain todoMain, HttpServletRequest req) {
        String add = req.getParameter("add");
        if (add != null && add.equals("true")) {
            String value = req.getParameter("value");
            todoMain.addTodo(value);
        }
    }

    public void toggleCheck(TodoMain todoMain, HttpServletRequest req) {
        String check = req.getParameter("check");
        if (check != null && check.equals("true")) {
            Integer checkId = Integer.parseInt(req.getParameter("value"));
            todoMain.toggleCheck(checkId);
        }
    }

    public Boolean deleteTodo(TodoMain todoMain, HttpServletRequest req) {
        String delete = req.getParameter("delete");
        Boolean isDeleteError = false;
        if (delete != null && delete.equals("true")) {
            Integer deleteId = Integer.parseInt(req.getParameter("value"));
            isDeleteError = todoMain.deleteTodo(deleteId);
        }
        return isDeleteError;
    }
}
