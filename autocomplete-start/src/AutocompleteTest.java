import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class AutocompleteTest {

   /** A test that always fails. **/
   @Test public void defaultTest() {
       String[] myStrings = {"word", "words", "ape"};
       String[] myTestStrings = {"word", "words", "apple", "WORD", "worD", "ape"};
       double[] myWeights = {100.0, 50.0, 75.0};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       for (String string : myTestStrings) {
           if (string.equals("apple")) {
               Assert.assertFalse(myTrie.contains(string));
               continue;
           }
           Assert.assertTrue(myTrie.contains(string));
       }
       ArrayList<Double> maxWeights = new ArrayList<>();
       maxWeights = myTrie.returnMaxWeights("word");
       for (int i = 0; i < maxWeights.size(); i++) {
           System.out.println(maxWeights.get(i));
       }
   }
   
   @Test public void testProblemStatementExample() {
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay"};
       //String[] myTestStrings = {"word", "words", "apple", "WORD", "worD", "ape"};
       double[] myWeights = {3, 2, 5, 1, 4};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       ArrayList<Double> maxWeights = new ArrayList<>();
       ArrayList<Double> myReturnedWeights = new ArrayList<>();
       ArrayList<String> myInfo = new ArrayList<>();
       ArrayList<String> myWords = new ArrayList<>();
       maxWeights = myTrie.returnMaxWeights("apes");
       myReturnedWeights = myTrie.returnWeights("apes");
       myInfo = myTrie.returnMyInfo("apes");
       myWords = myTrie.returnMyWord("apes");
       for (int i = 0; i < maxWeights.size(); i++) {
           System.out.println(maxWeights.get(i) + " " + myReturnedWeights.get(i)
                              + " " + myInfo.get(i) + " "
                              + myWords.get(i));
       }
       
       System.out.println(myTrie.contains("all"));
   }
}
