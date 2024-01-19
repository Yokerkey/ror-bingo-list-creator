package rorbingo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class bingo {
   public static void main(String[] args) {

    } 

    public static ArrayList<String> createList(Integer bingosAmount, ArrayList<String> racerBingo, ArrayList<String> stagesBingo) {
        JSONParser parser = new JSONParser();
        ArrayList<String> bingos = new ArrayList<String>();
        ArrayList<String> bingoList = new ArrayList<String>();
        ArrayList<String> monsterList = new ArrayList<String>();
        ArrayList<String> eliteList = new ArrayList<String>();
        ArrayList<String> conditionList = new ArrayList<String>();
        Random rand = new Random();

        Boolean bossItems = true;
        Boolean damage = true;
        Boolean lockbox = true;
        Boolean voidSeeds = true;

        try {     
            Object obj = parser.parse(new FileReader("src/main/resources/bingos.json"));

            JSONObject jsonObject =  (JSONObject) obj;

            JSONArray monsters = (JSONArray) jsonObject.get("Monsters");
            for (int i = 0; i < monsters.size(); i++) {
                monsterList.add(monsters.get(i).toString());

              }
            //System.out.println(monsterlist);
            JSONArray elite = (JSONArray) jsonObject.get("Elite");
            for (int i = 0; i < elite.size(); i++) {
                eliteList.add(elite.get(i).toString());

              }
            
            JSONArray conditions = (JSONArray) jsonObject.get("Conditions");
            for (int i = 0; i < conditions.size(); i++) {
                conditionList.add(conditions.get(i).toString());
            }

            JSONObject tiles = (JSONObject) jsonObject.get("Bingos");
            HashMap<String, JSONObject> tilesList = new HashMap<String, JSONObject>();
            tilesList.putAll(tiles);
            //System.out.println("tilesList: " + tilesList);
            for (Map.Entry<String, JSONObject> entry : tilesList.entrySet()) {  //String key : tilesList.keySet()
                String key = entry.getKey();
                JSONObject value = entry.getValue();
                //TODO: add for-if for rarity and complexity
                String condition = (String) value.get("Condition");
                if (condition == null) {
                    bingoList.add(key);
                } else if (condition.equals("boss item amount") && bossItems) {
                    bossItems = false;
                    bingoList.add(key);
                } else if (condition.equals("damage") && damage) {
                    damage = false;
                    bingoList.add(key);
                } else if (condition.equals("lockbox") && lockbox) {
                    lockbox = false;
                    bingoList.add(key);
                } else if (condition.equals("void seeds") && voidSeeds) {
                    voidSeeds = false;
                    bingoList.add(key);
                } else if (condition.equals("Abyssal Depths") && stagesBingo.contains("Abyssal Depths")) {
                    bingoList.add(key);
                } else if (condition.equals("Abandoned Aqueduct") && stagesBingo.contains("Abandoned Aqueduct")) {
                    bingoList.add(key);
                } else if (condition.equals("Distant Roost") && stagesBingo.contains("Distant Roost")) {
                    bingoList.add(key);
                } else if (condition.equals("Forest and Roost") && stagesBingo.contains("Siphoned Forest") && stagesBingo.contains("Distant Roost")) {
                    bingoList.add(key);
                }
                //TODO
                //bingoList.add(key);
                System.out.println(key);
                System.out.println(value);
                System.out.println(condition);
                //TODO: needs to be changed once we use weightings
            }
            //System.out.println("bingoList: " + bingoList);

            //bingosAmount - Elite Monster
            bingosAmount = bingosAmount-3;

            //bingosAmount - Racers
            bingosAmount = bingosAmount-racerBingo.size();

            for (int i = 0; i < 3; i++) {
                bingos.add(eliteList.get(rand.nextInt(eliteList.size())) + " " + monsterList.get(rand.nextInt(monsterList.size())));
            }

            //for-loop as many times as bingosAmount over bingoList
            for (int i = 0; i < bingosAmount; i++) {
                String random = bingoList.get(rand.nextInt(bingoList.size()));
                bingoList.remove(random);
                System.out.println("Bingo: " + random);
                //System.out.println("bingoList: " + bingoList);
                bingos.add(random);
            }

            for (int i = 0; i < racerBingo.size(); i++) {
                bingos.add(racerBingo.get(i) + " wins a loadout");
            }
            //System.out.println(bingos);
            createFile.main(null, bingos);
            return bingos;



            /* 
            String city = (String) jsonObject.get("city");
            System.out.println(city);

            String job = (String) jsonObject.get("job");
            System.out.println(job);

            // loop array
            JSONArray cars = (JSONArray) jsonObject.get("cars");
            Iterator<String> iterator = cars.iterator();
            while (iterator.hasNext()) {
             System.out.println(iterator.next());
            }*/
        } catch (IOException|ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}