package main.screens;

import main.algorithms.BubbleSort;
import main.algorithms.SortingAlgorithmInterface;
import main.MainApp;

import javax.imageio.ImageIO;
import javax.swing.*;
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
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND);
        checkBoxAlgorithms.add(new CheckBoxAlgorithm(sai,box));
        panel.add(box);
    }

    private void initContainer(JPanel panel){
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        panel.setBackground(BACKGROUND);
    }

    public void setUp(){
        JPanel sortAlgContainer = new JPanel();
        JPanel optionsContainer = new JPanel();
        JPanel backgroContainer = new JPanel();

        initContainer(this);
        initContainer(optionsContainer);
        initContainer(sortAlgContainer);

        backgroContainer.setBackground(BACKGROUND);
        backgroContainer.setLayout(new BoxLayout(backgroContainer,BoxLayout.LINE_AXIS));

        try{
            BufferedImage bimg = ImageIO.read(new File("resources/cosa.jpg"));
            JLabel label = new JLabel(new ImageIcon(bimg));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(label);
        }catch (IOException e){

        }

        sortAlgContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCheckBox(new BubbleSort(),       sortAlgContainer);
//        addCheckBox(new SelectionSort(),    sortAlgContainer);
//        addCheckBox(new CycleSort(),        sortAlgContainer);
//        addCheckBox(new StoogeSort(),       sortAlgContainer);
//        addCheckBox(new QuickSort(),        sortAlgContainer);
//        addCheckBox(new PancakeSort(),      sortAlgContainer);
//        addCheckBox(new MergeSort(),        sortAlgContainer);
//        addCheckBox(new InsertionSort(),    sortAlgContainer);
//        addCheckBox(new HeapSort(),         sortAlgContainer);
//        addCheckBox(new GnomeSort(),        sortAlgContainer);
//        addCheckBox(new CountingSort(),     sortAlgContainer);
//        addCheckBox(new RadixSort(),        sortAlgContainer);
//        addCheckBox(new IterativeMergeSort(), sortAlgContainer);

        JCheckBox soundCheckBox = new JCheckBox("Play Sounds");
        soundCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        soundCheckBox.setBackground(BACKGROUND);
        soundCheckBox.setForeground(Color.WHITE);

        optionsContainer.add(soundCheckBox);

        JButton startButton = new JButton("Begin Visual Sorter");
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
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backgroContainer.add(optionsContainer);
        backgroContainer.add(Box.createRigidArea(new Dimension(5,0)));
        backgroContainer.add(sortAlgContainer);

        int gap = 15;
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(backgroContainer);
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(startButton);


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
