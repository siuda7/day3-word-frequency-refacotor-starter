import java.util.*;
import java.util.stream.Collectors;

//todo
// useless code, import, if else
// stream instead of for loop
// extract method in getResult
// use constant for string e.g "\\s" regex string
// temp field? inputList = list
// remove unused import
public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(SPACE);
                List<WordFrequency> wordFrequencyList = Arrays.stream(words).map(word -> new WordFrequency(word, 1)).toList();
                //get the wordToWordfrequenciesMap for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordfrequenciesMap = getWordFrequencyMap(wordFrequencyList);
                List<WordFrequency> wordFrequencyListTemp = wordToWordfrequenciesMap.entrySet().stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .collect(Collectors.toList());
                wordFrequencyListTemp = wordFrequencyListTemp;
                wordFrequencyListTemp.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());
                return wordFrequencyListTemp.stream()
                        .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                        .collect(Collectors.joining(LINE_BREAK));
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
