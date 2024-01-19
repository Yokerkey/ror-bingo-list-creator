package rorbingo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class gui {
    public static void main(String args[]){


        ArrayList<String> racerList = racerListCreation();
        ArrayList<String> stagesList = stagesListCreation();
        ArrayList<String> removedRacers = new ArrayList<String>();
        ArrayList<String> removedStages = new ArrayList<String>();
        ArrayList<String> racerBingo = new ArrayList<String>();
        ArrayList<String> stagesBingo = new ArrayList<String>();
        ArrayList<JButton> buttonList = new ArrayList<JButton>();
        ArrayList<JButton> stagesButtonList = new ArrayList<JButton>();


        //Creating the frame
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        //Creating MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);


        //Creating panel at bottom and adding components
        JPanel panel = new JPanel(); //not visible
        //JLabel label = new JLabel("Enter Text");
        //JTextField tf = new JTextField(10); //up to 10 chars
        JButton create = new JButton("Create");
        JButton reset = new JButton("Reset");
        JButton menu = new JButton("Menu");
        panel.add(create);
        panel.add(reset);
        panel.add(menu);


        //Top Panel
        JPanel top = new JPanel();


        JButton racersButton = new JButton("Racers");
        JButton stagesButton = new JButton("Stages");
        top.add(racersButton);
        top.add(stagesButton);

        JPanel mid = new JPanel();

        //Adding components to frame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, mid);
        frame.getContentPane().add(BorderLayout.NORTH, top);
        frame.setVisible(true);



        //Listeners
        racersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                top.removeAll();
                top.updateUI();
                for (int i = 0; i < racerList.size(); i++) {
                    JButton racer_i = new JButton(racerList.get(i));
                    buttonList.add(racer_i);
                    mid.add(racer_i);

                    racer_i.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String racerName = racer_i.getText();
                            System.out.println(racerName);
                            racerBingo.add(racerName);
                            racerList.remove(racerName);
                            removedRacers.add(racerName);
                            mid.remove(racer_i);
                            mid.updateUI();
                        }
                    });
                    
                }
            }
        });

        stagesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                top.removeAll();
                top.updateUI();
                for (int i = 0; i < stagesList.size(); i++) {
                    JButton stage_i = new JButton(stagesList.get(i));
                    stagesButtonList.add(stage_i);
                    mid.add(stage_i);

                    stage_i.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String stageName = stage_i.getText();
                            System.out.println(stageName);
                            stagesBingo.add(stageName);
                            stagesList.remove(stageName);
                            removedStages.add(stageName);
                            mid.remove(stage_i);
                            mid.updateUI();
                        }
                    });
                    
                }
            }
        });

        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bingo.createList(50, racerBingo, stagesBingo);
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                racerBingo.clear();
                stagesBingo.clear();
                mid.removeAll();
                top.add(racersButton);
                top.add(stagesButton);
                top.updateUI();
                mid.updateUI();
                for (int i = 0; i < removedRacers.size(); i++) {
                    racerList.add(removedRacers.get(i));
                }
                for (int i = 0; i < removedStages.size(); i++) {
                    stagesList.add(removedStages.get(i));
                }
                removedStages.clear();
                removedRacers.clear();
            }
        });

        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mid.removeAll();
                top.add(racersButton);
                top.add(stagesButton);
                top.updateUI();
                mid.updateUI();
            }
        });


     }

    public static ArrayList<String> racerListCreation() {

        JSONParser parser = new JSONParser();
        ArrayList<String> racerList = new ArrayList<String>();

        try {

            Object obj = parser.parse(new FileReader("src/main/resources/bingos.json"));
            JSONObject jsonObject =  (JSONObject) obj;

            JSONArray racers = (JSONArray) jsonObject.get("Racers");
            for (int i = 0; i < racers.size(); i++) {
                racerList.add(racers.get(i).toString());

            }
        } catch (IOException|ParseException e) {
            e.printStackTrace();
        }

        return racerList;
    }

    public static ArrayList<String> stagesListCreation() {

        JSONParser parser = new JSONParser();
        ArrayList<String> stagesList = new ArrayList<String>();

        try {

            Object obj = parser.parse(new FileReader("src/main/resources/bingos.json"));
            JSONObject jsonObject =  (JSONObject) obj;

            JSONArray stages = (JSONArray) jsonObject.get("Stages");
            for (int i = 0; i < stages.size(); i++) {
                stagesList.add(stages.get(i).toString());

            }
        } catch (IOException|ParseException e) {
            e.printStackTrace();
        }

        return stagesList;
    }

}