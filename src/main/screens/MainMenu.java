package main.screens;

import main.algorithms.*;
import main.algorithms.SortingAlgorithmInterface;
import main.MainApp;

import static main.MainApp.HEIGHT_SCREEN;
import static main.MainApp.WIDTH_SCREEN;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalCheckBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainMenu extends Screen {

    private static final Color BACKGROUND = Color.BLACK;

    private final ArrayList<CheckBoxAlgorithm> checkBoxAlgorithms;


    public MainMenu(MainApp app){
        super(app);
        checkBoxAlgorithms = new ArrayList<>();
        setUp();
    }

    private void addCheckBox(SortingAlgorithmInterface sai, JPanel panel){
        JCheckBox box = new JCheckBox("",true);
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.setOpaque(false);
        box.setForeground(Color.WHITE);

        checkBoxAlgorithms.add(new CheckBoxAlgorithm(sai,box));
        panel.add(box);
    }


    private void initContainer(JPanel panel){
        panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
        panel.setBackground(BACKGROUND);
    }

    public void setUp(){
        JPanel sortAlgContainer = new JPanel();
        JPanel optionsContainer = new JPanel();

        initContainer(this);
        initContainer(optionsContainer);
        initContainer(sortAlgContainer);

        JPanel backgroContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage bimg = ImageIO.read(new File("assets/cosa.png"));
                    g.drawImage(bimg, 0, 0, this.getWidth(), this.getHeight(), null);
                } catch (IOException e) {
                    System.out.println("Error loading background image");
                    e.printStackTrace();
                }
            }
        };


        sortAlgContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        addCheckBox(new BubbleSort(),       sortAlgContainer);
        addCheckBox(new SelectionSort(),    sortAlgContainer);
        addCheckBox(new CycleSort(),        sortAlgContainer);
        addCheckBox(new StoogeSort(),       sortAlgContainer);
        addCheckBox(new QuickSort(),        sortAlgContainer);
        addCheckBox(new PancakeSort(),      sortAlgContainer);
        addCheckBox(new MergeSort(),        sortAlgContainer);
        addCheckBox(new InsertionSort(),    sortAlgContainer);
        addCheckBox(new HeapSort(),         sortAlgContainer);
        addCheckBox(new GnomeSort(),        sortAlgContainer);
        addCheckBox(new CountingSort(),     sortAlgContainer);
        addCheckBox(new RadixSort(),        sortAlgContainer);
        addCheckBox(new IterativeMergeSort(), sortAlgContainer);

        JCheckBox soundCheckBox = new JCheckBox("Play Sounds");
        soundCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);


        soundCheckBox.setForeground(Color.BLACK);

        optionsContainer.add(soundCheckBox);

        JButton startButton = new JButton("Begin Visual Sorter");
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);

        startButton.addActionListener((ActionEvent e) -> {
            ArrayList<SortingAlgorithmInterface> algorithms = new ArrayList<>();
            for (CheckBoxAlgorithm cb : checkBoxAlgorithms) {
                if (cb.isSelected()) {
                    algorithms.add(cb.getAlgorithm());
                }
            }
            app.pushScreen(
                    new SortingScreen(
                            algorithms,
                            soundCheckBox.isSelected(),
                            app
                    ));
        });
        startButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        backgroContainer.add(optionsContainer);
        backgroContainer.add(Box.createRigidArea(new Dimension(5,0)));
        backgroContainer.add(sortAlgContainer);

        int gap = 15;
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(backgroContainer);
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(startButton, BorderLayout.NORTH);


    }



    @Override
    public void onOpen() {
        checkBoxAlgorithms.forEach(CheckBoxAlgorithm::unselect);
    }


    private class CheckBoxAlgorithm {
        private final SortingAlgorithmInterface algorithm;
        private final JCheckBox box;

        public CheckBoxAlgorithm(SortingAlgorithmInterface algorithm, JCheckBox box) {
            this.algorithm = algorithm;
            this.box = box;
            this.box.setText(algorithm.getName());
        }

        public void unselect() {
            box.setSelected(false);
        }


        public boolean isSelected() {
            return box.isSelected();
        }

        public SortingAlgorithmInterface getAlgorithm() {
            return algorithm;
        }
    }


}
