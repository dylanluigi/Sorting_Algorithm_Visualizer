package main;

import main.screens.MainMenu;
import main.screens.Screen;

import javax.swing.*;
import java.util.ArrayList;

public class MainApp extends JFrame {


    public static final int WIDTH_SCREEN = 1280;

    public static final int HEIGHT_SCREEN = 720;

    private final ArrayList<Screen> screens;


    public MainApp(){

        screens = new ArrayList<>();
        setTitle("Sort Array Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void start() {
        pushScreen(new MainMenu(this));
        pack();
    }

    public Screen getCurrentScreen(){
        return screens.get(screens.size()-1);
    }

    public void pushScreen(Screen screen){
        if (!screens.isEmpty()){
            remove(getCurrentScreen());
        }
        screens.add(screen);
        setContentPane(screen);
        validate();
        screen.onOpen();
    }

    public void popScreen() {
        if (!screens.isEmpty()) {
            Screen prev = getCurrentScreen();
            screens.remove(prev);
            remove(prev);
            if (!screens.isEmpty()) {
                Screen current = getCurrentScreen();
                setContentPane(current);
                validate();
                current.onOpen();
            }
            else {
                dispose();
            }
        }
    }




    public static void main(String... args) {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(() -> {
            new MainApp().start();
        });
    }
}