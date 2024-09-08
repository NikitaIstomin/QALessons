import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueWordsCounter {
    public static void ShowTask1() {
        String[] wordsArray = {
                "Lorem", "ipsum", "dolor", "sit", "amet",
                "sit", "lmao", "sit", "sample text", "ipsum",
                "sus", "kek", "sample text", "kek", "sit"
        };

        Map<String, Integer> wordCountMap = new HashMap<>();

        for (String word : wordsArray) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        System.out.println("Уникальные слова и их количество:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}