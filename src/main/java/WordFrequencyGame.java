import java.util.*;
import java.util.stream.Collectors;

//todo
// useless code, import, if else
// extract method in getResult
// temp field? inputList = list
public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String ONE = " 1";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE).length == 1) return sentence + ONE;
        try {
            List<WordFrequency> wordFrequencyList = getInitialWordFrequencies(sentence);
            List<WordFrequency> wordFrequencyListTemp = getWordFrequencies(wordFrequencyList);
            return joinWordFrequencyResults(wordFrequencyListTemp);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private static String joinWordFrequencyResults(List<WordFrequency> wordFrequencyListTemp) {
        wordFrequencyListTemp.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());
        return wordFrequencyListTemp.stream()
                .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordfrequenciesMap = getWordFrequencyMap(wordFrequencyList);
        return wordToWordfrequenciesMap.entrySet().stream()
                .map(wordFrequencyEntry -> new WordFrequency(wordFrequencyEntry.getKey(), wordFrequencyEntry.getValue().size()))
                .collect(Collectors.toList());
    }

    private static List<WordFrequency> getInitialWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE);
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
