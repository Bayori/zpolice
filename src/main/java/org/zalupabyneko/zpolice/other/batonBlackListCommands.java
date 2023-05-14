package org.zalupabyneko.zpolice.other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class batonBlackListCommands {
    public static List<String> blackCommands = new ArrayList<>(Arrays.asList("/crawl"));
    public static void batonList() {
        String path = "plugins\\ZPolice\\batonBlackListCommands.yml";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                blackCommands.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
