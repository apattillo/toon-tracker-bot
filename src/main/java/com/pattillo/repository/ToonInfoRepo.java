package com.pattillo.repository;

import com.pattillo.entity.ToonInfo;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ToonInfoRepo {

    // Just write to a file for now, DB seems way too heavy for this
    private final HashMap<String, ToonInfo> characters = loadCharacterData();
    private final String characterDataFile = "character_data.txt";

    public void addToon(ToonInfo newToon) {
        characters.put(newToon.getToonName(), newToon);
        saveCharacterData();
    }

    public void updateToon(ToonInfo toon) {
        ToonInfo existingToon = characters.get(toon.getToonName());
        existingToon.setToonLevel(toon.getToonLevel());
        saveCharacterData();
    }

    public void deleteToonByName(String toonName) {
        characters.remove(toonName);
        saveCharacterData();
    }

    public List<ToonInfo> getToons() {
        List<ToonInfo> toons = new ArrayList();
        for (String toonName : characters.keySet()) {
            ToonInfo toonInfo = characters.get(toonName);
            toons.add(toonInfo);
        }
        return toons;
    }

    public ToonInfo getToonByName(String toonName) {
        return characters.get(toonName);
    }

    private HashMap<String, ToonInfo> loadCharacterData() {
        HashMap<String, ToonInfo> loadedCharacters = new HashMap<>();
        File file = new File(characterDataFile);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(characterDataFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String ownerId = data[0];
                String ownerName = data[1];
                String toonName = data[2];
                String toonClass = data[3];
                String toonLevel = data[4];
                loadedCharacters.put(toonName, new ToonInfo(ownerId, ownerName, toonName, toonClass, toonLevel));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedCharacters;
    }

    private void saveCharacterData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(characterDataFile));
            for (String toonName : characters.keySet()) {
                ToonInfo toonInfo = characters.get(toonName);
                writer.write(toonInfo.getOwnerId() + "," + toonInfo.getOwnerName() + "," + toonName + "," + toonInfo.getToonClass() + "," + toonInfo.getToonLevel() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
