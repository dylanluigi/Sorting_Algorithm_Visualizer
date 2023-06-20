package main.screens;

import main.MainApp;
import static main.MainApp.HEIGHT_SCREEN;
import static main.MainApp.WIDTH_SCREEN;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {

    protected MainApp app;
    public Screen(MainApp app){
        this.app = app;
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(WIDTH_SCREEN, HEIGHT_SCREEN);
    }

    public abstract void onOpen();


}
