import java.util.*;

//todo
// useless code, import, if else
// rename, getResult, input, arr exception msg
// stream instead of for loop
// extract method in getResult
// use constant for string e.g "\\s" regex string
// temp field? inputList = list
// remove unused import
public class WordFrequencyGame {
    public String getWordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");
                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencyList.add(wordFrequency);
                }
                //get the wordToWordfrequenciesMap for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordfrequenciesMap = getWordFrequencyMap(wordFrequencyList);
                List<WordFrequency> wordFrequencyListTemp = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> wordFrequencyEntry : wordToWordfrequenciesMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(wordFrequencyEntry.getKey(), wordFrequencyEntry.getValue().size());
                    wordFrequencyListTemp.add(wordFrequency);
                }
                wordFrequencyListTemp = wordFrequencyListTemp;
                wordFrequencyListTemp.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());
                StringJoiner wordFrequencyJoiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencyListTemp) {
                    String wordFrequencyString = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    wordFrequencyJoiner.add(wordFrequencyString);
                }
                return wordFrequencyJoiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordFrequncyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       wordFrequncyMap.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordFrequncyMap.containsKey(wordFrequency.getWord())) {
                ArrayList wordFrequncyList = new ArrayList<>();
                wordFrequncyList.add(wordFrequency);
                wordFrequncyMap.put(wordFrequency.getWord(), wordFrequncyList);
            } else {
                wordFrequncyMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordFrequncyMap;
    }


}
