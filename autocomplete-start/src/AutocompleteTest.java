import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.*;

public class AutocompleteTest {
   
   @Test public void testProblemStatementExample() {
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay"};
       double[] myWeights = {3, 2, 5, 1, 4};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       
       ArrayList<Double> actualMaxWeights = new ArrayList<>();
       ArrayList<Double> actualReturnedWeights = new ArrayList<>();
       ArrayList<String> actualMyInfo = new ArrayList<>();
       ArrayList<String> actualMyWords = new ArrayList<>();
       
       ArrayList<Double> expectedMaxWeights = new ArrayList<>();
       ArrayList<Double> expectedReturnedWeights = new ArrayList<>();
       ArrayList<String> expectedMyInfo = new ArrayList<>();
       ArrayList<String> expectedMyWords = new ArrayList<>();
       
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(3.0);
       expectedMaxWeights.add(3.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(3.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("a");
       expectedMyInfo.add("l");
       expectedMyInfo.add("l");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("all");
       
       String wordToTest = "all";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
       
       actualMaxWeights.clear();
       actualReturnedWeights.clear();
       actualMyInfo.clear();
       actualMyWords.clear();
      
       expectedMaxWeights.clear();
       expectedReturnedWeights.clear();
       expectedMyInfo.clear();
       expectedMyWords.clear();
      
       wordToTest = "ape";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(5.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(1.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("a");
       expectedMyInfo.add("p");
       expectedMyInfo.add("e");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("ape");
      
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
     
       actualMaxWeights.clear();
       actualReturnedWeights.clear();
       actualMyInfo.clear();
       actualMyWords.clear();
      
       expectedMaxWeights.clear();
       expectedReturnedWeights.clear();
       expectedMyInfo.clear();
       expectedMyWords.clear();
       
       wordToTest = "ape";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(5.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(1.0);
       expectedReturnedWeights.add(5.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("a");
       expectedMyInfo.add("p");
       expectedMyInfo.add("e");
       expectedMyInfo.add("s");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("ape");
       expectedMyWords.add("apes");
       
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
       
       
       actualMaxWeights.clear();
       actualReturnedWeights.clear();
       actualMyInfo.clear();
       actualMyWords.clear();
      
       expectedMaxWeights.clear();
       expectedReturnedWeights.clear();
       expectedMyInfo.clear();
       expectedMyWords.clear();
       
       wordToTest = "bat";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(2.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(2.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("b");
       expectedMyInfo.add("a");
       expectedMyInfo.add("t");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("bat");
       
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
       
       actualMaxWeights.clear();
       actualReturnedWeights.clear();
       actualMyInfo.clear();
       actualMyWords.clear();
      
       expectedMaxWeights.clear();
       expectedReturnedWeights.clear();
       expectedMyInfo.clear();
       expectedMyWords.clear();
       
       wordToTest = "bay";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       expectedMaxWeights.add(5.0);
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(4.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(4.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("b");
       expectedMyInfo.add("a");
       expectedMyInfo.add("y");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("bay");
       
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
       
   }
   
   @Test public void testProblemStatementExampleAddSameWordAgain() {
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay", "apes"};
       double[] myWeights = {3, 2, 5, 1, 4, 1};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       ArrayList<Double> actualMaxWeights = new ArrayList<>();
       ArrayList<Double> actualReturnedWeights = new ArrayList<>();
       ArrayList<String> actualMyInfo = new ArrayList<>();
       ArrayList<String> actualMyWords = new ArrayList<>();
       
       ArrayList<Double> expectedMaxWeights = new ArrayList<>();
       ArrayList<Double> expectedReturnedWeights = new ArrayList<>();
       ArrayList<String> expectedMyInfo = new ArrayList<>();
       ArrayList<String> expectedMyWords = new ArrayList<>();
       
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(3.0);
       expectedMaxWeights.add(3.0);
       expectedMaxWeights.add(3.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(3.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("a");
       expectedMyInfo.add("l");
       expectedMyInfo.add("l");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("all");
       
       String wordToTest = "all";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
       
       actualMaxWeights.clear();
       actualReturnedWeights.clear();
       actualMyInfo.clear();
       actualMyWords.clear();
      
       expectedMaxWeights.clear();
       expectedReturnedWeights.clear();
       expectedMyInfo.clear();
       expectedMyWords.clear();
      
       wordToTest = "ape";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(3.0);
       expectedMaxWeights.add(1.0);
       expectedMaxWeights.add(1.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(1.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("a");
       expectedMyInfo.add("p");
       expectedMyInfo.add("e");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("ape");
      
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
     
       actualMaxWeights.clear();
       actualReturnedWeights.clear();
       actualMyInfo.clear();
       actualMyWords.clear();
      
       expectedMaxWeights.clear();
       expectedReturnedWeights.clear();
       expectedMyInfo.clear();
       expectedMyWords.clear();
       
       wordToTest = "apes";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(3.0);
       expectedMaxWeights.add(1.0);
       expectedMaxWeights.add(1.0);
       expectedMaxWeights.add(1.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(1.0);
       expectedReturnedWeights.add(1.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("a");
       expectedMyInfo.add("p");
       expectedMyInfo.add("e");
       expectedMyInfo.add("s");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("ape");
       expectedMyWords.add("apes");
       
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
       
       
       actualMaxWeights.clear();
       actualReturnedWeights.clear();
       actualMyInfo.clear();
       actualMyWords.clear();
      
       expectedMaxWeights.clear();
       expectedReturnedWeights.clear();
       expectedMyInfo.clear();
       expectedMyWords.clear();
       
       wordToTest = "bat";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(2.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(2.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("b");
       expectedMyInfo.add("a");
       expectedMyInfo.add("t");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("bat");
       
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
       
       actualMaxWeights.clear();
       actualReturnedWeights.clear();
       actualMyInfo.clear();
       actualMyWords.clear();
      
       expectedMaxWeights.clear();
       expectedReturnedWeights.clear();
       expectedMyInfo.clear();
       expectedMyWords.clear();
       
       wordToTest = "bay";
       actualMaxWeights = myTrie.returnMaxWeights(wordToTest);
       actualReturnedWeights = myTrie.returnWeights(wordToTest);
       actualMyInfo = myTrie.returnMyInfo(wordToTest);
       actualMyWords = myTrie.returnMyWord(wordToTest);
       
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(4.0);
       expectedMaxWeights.add(4.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(4.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("b");
       expectedMyInfo.add("a");
       expectedMyInfo.add("y");
       
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("");
       expectedMyWords.add("bay");
       
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
              
   }

    @Test
    public void testAddAllSame() {
       String[] myStrings = {"a", "a", "a" , "a", "a", "a"};
       double[] myWeights = {3, 2, 5, 1, 4, 1};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       
       ArrayList<Double> actualMaxWeights = new ArrayList<>();
       ArrayList<Double> actualReturnedWeights = new ArrayList<>();
       ArrayList<String> actualMyInfo = new ArrayList<>();
       ArrayList<String> actualMyWords = new ArrayList<>();
       
       ArrayList<Double> expectedMaxWeights = new ArrayList<>();
       ArrayList<Double> expectedReturnedWeights = new ArrayList<>();
       ArrayList<String> expectedMyInfo = new ArrayList<>();
       ArrayList<String> expectedMyWords = new ArrayList<>();
       
       expectedMaxWeights.add(1.0);
       expectedMaxWeights.add(1.0);
       
       expectedReturnedWeights.add(-1.0);
       expectedReturnedWeights.add(1.0);
       
       expectedMyInfo.add("-");
       expectedMyInfo.add("a");
       
       expectedMyWords.add("");
       expectedMyWords.add("a");
       
       for (int i = 0; i < actualMaxWeights.size(); i++) {
           Assert.assertEquals(expectedMaxWeights.get(i), actualMaxWeights.get(i));
           Assert.assertEquals(expectedReturnedWeights.get(i), actualReturnedWeights.get(i));
           Assert.assertEquals(expectedMyInfo.get(i), actualMyInfo.get(i));
           Assert.assertEquals(expectedMyWords.get(i), actualMyWords.get(i));
       }
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testNegativeWeight() {
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay", "apes"};
       double[] myWeights = {3, 2, 5, 1, 4, -1};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullString() {
       String[] myStrings = {"all", "bat", "apes" , "ape", null, "apes"};
       double[] myWeights = {3, 2, 5, 1, 4, 1};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
    }
    
    
    @Test
    public void testTopMatchProblemStatementExample() {
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay"};
       double[] myWeights = {3, 2, 5, 1, 4};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       
       String topMatch = myTrie.topMatch("b");
       //System.out.println("Top Match: " + topMatch);
    }
    
    @Test
    public void testTraverseDownToNode() {
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay"};
       double[] myWeights = {3, 2, 5, 1, 4};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       
       Node currentNode = myTrie.traverseDownToWord("ape");
       //System.out.println(currentNode.toString());
    }
    
    @Test
    public void testTopMatchesTypical() {
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay"};
       double[] myWeights = {3, 2, 5, 1, 4};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       
       Iterable<String> topMatches = myTrie.topMatches("a", 1);
       for (String element : topMatches) {
           //System.out.println(element);
       }
    }
    
    @Test
    public void testHaveKWords() {
        ArrayList<Node> myNodes = new ArrayList<>();
        
        Node node1 = new Node('a', null, 0);
             node1.setWeight(1);
        
        Node node2 = new Node('a', null, 0);
             node2.setWeight(50);
        
        Node node3 = new Node('a', null, 0);
             node3.setWeight(20);
             
        Node node4 = new Node('a', null, 0);
             node4.setWeight(30);
             
       String[] myStrings = {"all", "bat", "apes" , "ape", "bay"};
       double[] myWeights = {3, 2, 5, 1, 4};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
             
        myNodes.add(node2);
        myNodes.add(node3);
        myNodes.add(node4);
        myNodes.add(node1);
        
        myNodes.sort(null);
    
        Node testNode = new Node('a', null, 20);
    
        int actual = myTrie.numWordsGreater(myNodes, testNode, 1);
        //System.out.println(actual);
    }
    
    @Test
    public void testWord333() throws Exception {
        File file = new File("../dataForTest/words-333333.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] strings = new String [333333];
        double[] weights = new double [333333];
        double weight;
        String string = null;
        String[] st = new String[2];
        
        for (int i = 0; i < 333333; i++) {
                st = br.readLine().trim().split("\t");
                weight = Double.parseDouble(st[0]);
                string = st[1];
                strings[i] = string;
                weights[i] = weight;
                //System.out.println(weight);
        }
        
        for (int i = 0; i < 5; i++) {
            //System.out.println(weights[i]);
        }
        
        
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(strings, weights);
       boolean cont = myTrie.contains("automobile");
       Assert.assertTrue(cont);
       Node automobile = myTrie.traverseDownToWord("auto");
       //System.out.println(automobile.myWeight);
       //System.out.println(automobile.mySubtreeMaxWeight);
       Iterable<String> topMatches = myTrie.topMatches("auto", 10);
       for (String element : topMatches) {
           //System.out.println(element);
       }
       
       String topMatch = myTrie.topMatch("automob");
       //System.out.println("Top match: " + topMatch);
    }


    @Test
    public void testFourLetterWords() throws Exception {
        File file = new File("../dataForTest/fourletterwords.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] strings = new String [456976];
        double[] weights = new double [456976];
        double weight;
        String string = null;
        String[] st = new String[2];
        
        for (int i = 0; i < 456976; i++) {
                st = br.readLine().trim().split("\t");
                weight = Double.parseDouble(st[0]);
                string = st[1];
                strings[i] = string;
                weights[i] = weight;
                //System.out.println(weight);
        }
        
        for (int i = 0; i < 5; i++) {
            //System.out.println(weights[i]);
        }
        
        
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(strings, weights);
       boolean cont = myTrie.contains("zzzz");
       Assert.assertTrue(cont);
       Node automobile = myTrie.traverseDownToWord("zzzz");
       //System.out.println(automobile.myWeight);
       //System.out.println(automobile.mySubtreeMaxWeight);
       Iterable<String> topMatches = myTrie.topMatches("z", 10);
       for (String element : topMatches) {
           //System.out.println(element);
       }
       
       String topMatch = myTrie.topMatch("ne");
       //System.out.println("Top match: " + topMatch);
    }
    
   @Test public void testProblemDeliverableExample() {
       String[] myStrings = {"ape", "app", "ban", "bat", "bee", "car", "cat"};
       double[] myWeights = {6, 4, 2, 3, 5, 7, 1};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       
       Iterable<String> expected = myTrie.topMatches("d", 100);
       
       String expectedTopMatch = myTrie.topMatch("z");
       System.out.println("top match: " + expectedTopMatch);
       
       for (String result : expected) {
           System.out.println(result);
       }
   }
}
