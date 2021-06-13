package view;
import service.TodoListService;
import util.InputUtil;

public class TodoListView {
    private TodoListService todoListService;

    public TodoListView(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    public void showTodoList() {
        while(true) {
            todoListService.showTodoList();
            System.out.println("MENU :");
            System.out.println("1. Add Todo");
            System.out.println("2. Remove Todo");
            System.out.println("x. Exit");

            var input = InputUtil.input("Pilih");

            if(input.equals("1")) {
                addTodoList();
            } else if (input.equals("2")) {
                removeTodoList();
            } else if (input.equals("x")) {
                break;
            } else {
                System.out.println("Input tidak dikenali");
            }
        }
    }

    public void addTodoList() {
        System.out.println("Add Todo List");
        var input = InputUtil.input("Input Todo (x untuk batal)");

        if (input.equals("x")) {
            showTodoList();
        } else {
            todoListService.addTodoList(input);
        }
    }

    public void removeTodoList() {
        System.out.println("Delete Todo List");
        var number = InputUtil.input("Nomor yang akan dihapus (x untuk batal)");

        if (number.equals("x")) {
            showTodoList();
        } else {
            todoListService.removeTodoList(Integer.valueOf(number));
        }
    }
}
