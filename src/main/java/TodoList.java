public class TodoList {
    public static  String[] models = new String[10];
    public static java.util.Scanner  scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {
        viewShowTodoList();
    }

    /*
    * Show Todo List
    * */
    public static void showTodoList() {
        System.out.println("========== Todo List =========");
        for(var i = 0; i <models.length; i++) {
            var todo = models[i];
            var no = i + 1;

            if(todo != null) {
                System.out.println(no + ". " + todo);
            }
        }
    }

    public static  void testShowTodoList() {
        models[0] = "Belajar Java Dasar";
        models[1] = "Studi Kasus Java Dasar : Aplikasi Todo List";

        showTodoList();
    }

    /*
    * Add Todo List
    * */
    public static void addTodoList(String todo) {
        var isFull = true;

        for (var i = 0; i < models.length; i++) {
            if(models[i] == null) {
                isFull = false;
                break;
            }
        }

        if(isFull) {
            var temp = models;
            models = new String[models.length * 2];

            for(var i = 0; i < temp.length; i++) {
                models[i] = temp[i];
            }
        }

        for(int i = 0; i < models.length; i++) {
            if(models[i] == null) {
                models[i] = todo;
                break;
            }
        }
    }

    public static void testAddTodoList() {
        for(var i = 1; i <= 12; i++) {
            addTodoList("Todo " + i);
        }

        showTodoList();
    }

    /*
    * Remove Todo List
    * */
    public static boolean removeTodoList(Integer number) {
        if(number > models.length) {
            return false;
        }else if(models[number - 1] == null) {
            return false;
        } else {
            for(int i = (number - 1); i < models.length; i++) {
                if(i == (models.length - 1)) {
                    models[i] = null;
                } else {
                    models[i] = models[i + 1];
                }
            }
            return true;
        }
    }

    public static void testRemoveTodoList() {
        addTodoList("Todo 1");
        addTodoList("Todo 2");
        addTodoList("Todo 3");
        addTodoList("Todo 4");
        addTodoList("Todo 5");

        var result = removeTodoList(20);
        System.out.println(result);

        result = removeTodoList(7);
        System.out.println(result);

        result = removeTodoList(2);
        System.out.println(result);

        showTodoList();
    }

    /*
    * Input Todo List
    * */
    public static String input(String todo) {
        System.out.print(todo + " : ");
        String data = scanner.nextLine();
        return data;
    }

    public static void testInput() {
        var data = input("Nama");
        System.out.println("Hi, " + data);
    }

    /*
    * Showing Page List of todo list
    * */

    public  static  void viewShowTodoList() {
        while(true) {
            showTodoList();

            System.out.println("============ Menu ============");
            System.out.println("1 For Add Todo List");
            System.out.println("2 For Remove Todo List");
            System.out.println("x For Out From Todo List");

            var input = input("chose 1, 2 or x");
            if(input.equals("1")) {
                viewAddTodoList();
            } else if(input.equals("2")) {
                viewRemoveTodoList();
            } else if(input.equals("x")){
                break;
            }else {
                System.out.println("Wrong Number..!");
            }
        }
    }

    public static void testViewShowTodoList() {
        addTodoList("Study java language");
        addTodoList("Case Study : Create Application Todo List");
        viewShowTodoList();
    }

    /*
    * Showing Page of Add Todo List
    * */
    public static void viewAddTodoList() {
        System.out.println("========== Add Todo List ==========");
        var data = input("New Todo (x for back)");

        if(data.equals("x")) {
            viewShowTodoList();
        } else {
            addTodoList(data);
        }
    }

    public static void testViewAddTodoList() {
        addTodoList("Study java language");
        addTodoList("Case Study : Create Application Todo List");

        viewAddTodoList();
        showTodoList();
    }

    /*
    * Showing Page of Remove Todo list
    * */
    public static void viewRemoveTodoList() {
        System.out.println("========== Remove Todo List ==========");
        var data = input("Number of Todo (x for go back)");

        if(data.equals("x")) {
            viewShowTodoList();
        } else {
            boolean success = removeTodoList(Integer.valueOf(data));
            if(!success) {
                System.out.println("Oops.. Failed to Delete Todo List");
            }
        }
    }

    public static void testViewRemoveTodoList() {
        addTodoList("Study java language");
        addTodoList("Case Study : Create Application Todo List");

        viewRemoveTodoList();
        showTodoList();
    }
}
