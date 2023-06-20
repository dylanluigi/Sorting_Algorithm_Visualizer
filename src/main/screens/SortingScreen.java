package main.screens;

import main.algorithms.SortingAlgorithmInterface;
import main.MainApp;
import main.SortArray;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SortingScreen extends Screen{

    private final SortArray sortArray;
    private final ArrayList<SortingAlgorithmInterface> sortQueue;

    public SortingScreen(ArrayList<SortingAlgorithmInterface> algorithms, boolean playSounds, MainApp app) {
        super(app);
        setLayout(new BorderLayout());
        sortArray = new SortArray(playSounds);
        add(sortArray, BorderLayout.CENTER);
        sortQueue = algorithms;
    }

    private void longSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void shuffleAndWait() {
        sortArray.shuffle();
        sortArray.resetColours();
        longSleep();
    }
    public void onOpen() {
        //This would block the EventDispatchThread, and so
        //it must run on a worker thread
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                for (SortingAlgorithmInterface algorithm : sortQueue) {
                    shuffleAndWait();

                    sortArray.setName(algorithm.getName());
                    sortArray.setAlgorithm(algorithm);

                    algorithm.runSort(sortArray);
                    sortArray.resetColours();
                    sortArray.highlightArray();
                    sortArray.resetColours();
                    longSleep();
                }
                return null;
            }

            @Override
            public void done() {
                app.popScreen();
            }
        };

        swingWorker.execute();
    }
}
