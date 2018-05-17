package org.docksidestage.todo;

import java.util.List;

public class TodoMain {

    // DBとか永続化された情報アクセス
    TodoList todoList;

    public TodoMain() {
        todoList = TodoList.getInstance();
    }

    public void addTodo(String todoName) {
        todoList.add(todoName);
    }

    public void toggleCheck(Integer id) {
        Boolean toggledCheck = todoList.get(id).checked ^ true;
        todoList.setCheck(id, toggledCheck);
    }

    public Boolean deleteTodo(Integer id) {
        Boolean nowCheck = todoList.get(id).checked;
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
