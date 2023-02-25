package src.ui;

import src.Validation;
import src.presenter.Presenter;

import java.io.IOException;
import java.util.Scanner;

public class Console implements View {
    private Scanner scanner;
    private Presenter presenter;
    private Menu menu;
    private boolean work;

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        Validation valid = new Validation();
        scanner = new Scanner(System.in);
        menu = new Menu(this);
        work = true;
        while (work) {
            showMenu();
            // подумай тут ещё разок
            System.out.print("Укажите номер команды из меню: ");
            String command = scanner.next();
            if (valid.isNextInt(command)) {
                try {
                    menu.execute(Integer.parseInt(command));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Некорретно выбран элемент меню");
            }
        }
    }

    private void showMenu() {
        System.out.println(menu.printMenu());
    }

    public void finish() {
        work = false;
    }

    @Override
    public void print(String text) {

    }

    public void addHuman() {
        presenter.addHuman();
    }

    public void showHumans() {
        presenter.showHumans();
    }

    public void saveTree() {
        try {
            presenter.saveTree();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Не удалось сохранить файл");
        }

    }

    public void delHuman() {
        presenter.delHuman();
    }

    public void findHumans() {
        presenter.findHumans();
    }

    public void infoHuman() {
        presenter.infoHuman();
    }
}