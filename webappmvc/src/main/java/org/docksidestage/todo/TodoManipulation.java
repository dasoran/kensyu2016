package org.docksidestage.todo;

import java.util.List;

/**
 * TODOを操作するクラス。データ実体の管理もする。
 * @author dasoran
 */
public class TodoManipulation {

    // DBとか永続化された情報アクセス
    private TodoList todoList;

    public TodoManipulation() {
        todoList = TodoList.getInstance();
    }

    public void addTodo(String todoName) {
        todoList.add(todoName);
    }

    public void toggleCheck(int id) {
        boolean toggledCheck = todoList.get(id).checked ^ true;
        todoList.setCheck(id, toggledCheck);
    }

    public Boolean deleteTodo(int id) {
        boolean nowCheck = todoList.get(id).checked;
        if (nowCheck) {
            todoList.delete(id);
        } else {
            return true;
        }
        return false;
    }

    public List<TodoElement> getAllTodo() {
        return todoList.getAll();
    }
}
