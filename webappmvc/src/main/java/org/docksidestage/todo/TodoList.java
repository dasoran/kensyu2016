package org.docksidestage.todo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * このクラスはDBへのデータ保存を擬似的にカプセル化したもの。今回はメモリ上にデータがあるが、本当はDBなどに保存する
 * @author dasoran
 */
public class TodoList {

    private static TodoList todoList = new TodoList();

    private List<TodoElement> innerList = new CopyOnWriteArrayList<TodoElement>();

    public static TodoList getInstance() {
        return todoList;
    }

    public void add(String todo) {
        innerList.add(new TodoElement(todo));
    }

    public void setCheck(int id, boolean status) {
        innerList.get(id).checked = status;
    }

    public void delete(int id) {
        innerList.remove(id);
    }

    public TodoElement get(int id) {
        return innerList.get(id);
    }

    public List<TodoElement> getAll() {
        return innerList;
    }

}
