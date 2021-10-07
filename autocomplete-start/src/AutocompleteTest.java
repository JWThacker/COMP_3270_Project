import org.junit.Assert;
import org.junit.Test;


public class AutocompleteTest {

   /** A test that always fails. **/
   @Test public void defaultTest() {
       String[] myStrings = {"word"};
       double[] myWeights = {100.0};
       Autocomplete.TrieAutocomplete myTrie = new Autocomplete.TrieAutocomplete(myStrings, myWeights);
       System.out.println(myTrie.contains("apple"));
   }
}
