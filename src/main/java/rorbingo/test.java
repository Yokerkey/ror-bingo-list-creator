package rorbingo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;
import java.util.random.*;

public class test {
   public static void main(String[] args) {

        JSONParser parser = new JSONParser();
        ArrayList<String> bingos = new ArrayList<String>();
        ArrayList<String> bingoList = new ArrayList<String>();
        ArrayList<String> monsterList = new ArrayList<String>();
        ArrayList<String> eliteList = new ArrayList<String>();
        Random rand = new Random();

        try {     
            Object obj = parser.parse(new FileReader("rorbingo/src/main/resources/bingos.json"));

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

            JSONObject tiles = (JSONObject) jsonObject.get("Bingos");
            HashMap<String, Array> tilesList = new HashMap<String, Array>();
            tilesList.putAll(tiles);
            //System.out.println("tilesList: " + tilesList);
            for (String key : tilesList.keySet()) {
                bingoList.add(key);
                //TODO: needs to be changed once we use weightings
            }
            //System.out.println("bingoList: " + bingoList);

            for (int i = 0; i < 3; i++) {
                bingos.add(eliteList.get(rand.nextInt(eliteList.size())) + " " + monsterList.get(rand.nextInt(monsterList.size())));
            }

            for (int i = 0; i < 20; i++) {
                String random = bingoList.get(rand.nextInt(bingoList.size()));
                bingoList.remove(random);
                //System.out.println("Bingo: " + random);
                //System.out.println("bingoList: " + bingoList);
                bingos.add(random);
            }
            System.out.println(bingos);



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
        }
    } 
}
