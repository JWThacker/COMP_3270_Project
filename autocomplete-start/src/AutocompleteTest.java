import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

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
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay", "all"};
       //String[] myTestStrings = {"word", "words", "apple", "WORD", "worD", "ape"};
       double[] myWeights = {3, 2, 5, 1, 4, 5};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       ArrayList<Double> maxWeights = new ArrayList<>();
       ArrayList<Double> myReturnedWeights = new ArrayList<>();
       ArrayList<String> myInfo = new ArrayList<>();
       ArrayList<String> myWords = new ArrayList<>();
       String wordToTest = "bay";
       maxWeights = myTrie.returnMaxWeights(wordToTest);
       myReturnedWeights = myTrie.returnWeights(wordToTest);
       myInfo = myTrie.returnMyInfo(wordToTest);
       myWords = myTrie.returnMyWord(wordToTest);
       for (int i = 0; i < maxWeights.size(); i++) {
           System.out.println(maxWeights.get(i) + " " + myReturnedWeights.get(i)
                              + " " + myInfo.get(i) + " "
                              + myWords.get(i));
       }
       
       System.out.println(myTrie.contains("all"));
   }
   
   @Test
   public void testMaxNode() {
       HashMap<Character, Node> myChild = new HashMap<Character, Node>();
       myChild.put('a', new Node('a', null, 5));
       myChild.get('a').myWeight = 9;
       myChild.put('b', new Node('b', null, 6));
       myChild.get('b').myWeight = 6;
       myChild.put('c', new Node('c', null, 7));
       myChild.get('c').myWeight = 7;
       myChild.put('d', new Node('d', null, 8));
       myChild.get('d').myWeight = 8;
       myChild.put('e', new Node('e', null, 9));
       myChild.get('e').myWeight = 9;
       
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay", "all"};
       double[] myWeights = {3, 2, 5, 1, 4, 5};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       Node myMaxNode = myTrie.maxNode(myChild.values());
       System.out.println(myMaxNode.toString());
       
       
   }
}
