package org.docksidestage.todo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author dasoran
 */
public class TodoList {

    private static TodoList todoList = new TodoList();

    // static つけるべきかどうかわからない
    private List<TodoElement> innerList = new CopyOnWriteArrayList<TodoElement>();

    public static TodoList getInstance() {
        return todoList;
    }

    public void add(String todo) {
        innerList.add(new TodoElement(todo));
    }

    public void setCheck(Integer id, Boolean status) {
        innerList.get(id).checked = status;
    }

    public void delete(Integer id) {
        innerList.remove(id.intValue());
    }

    public TodoElement get(Integer id) {
        return innerList.get(id);
    }

    public List<TodoElement> getAll() {
        return innerList;
    }

}
