package org.docksidestage.todo;

/**
 * @author dasoran
 */
public class TodoElement {

    public TodoElement(String name) {
        this.name = name;
        this.checked = false;
    }

    public String name;
    public Boolean checked;
}
