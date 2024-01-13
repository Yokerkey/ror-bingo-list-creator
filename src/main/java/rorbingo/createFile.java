package rorbingo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class createFile {
    public static void main(String[] args, ArrayList<String> bingos) {  

        ObjectMapper mapper = new ObjectMapper();

        try {  

            // Writing to a file   
            mapper.writeValue(new File("src/main/resources/createBingos.json"), bingos );

        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    }
}
