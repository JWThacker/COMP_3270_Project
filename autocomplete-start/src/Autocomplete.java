import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Collection;
//import java.util.TreeSet;
//import java.util.NavigableSet;

public class Autocomplete {
        /**
         * Uses binary search to find the index of the first Term in the passed in
         * array which is considered equivalent by a comparator to the given key.
         * This method should not call comparator.compare() more than 1+log n times,
         * where n is the size of a.
         * 
         * @param a
         *            - The array of Terms being searched
         * @param key
         *            - The key being searched for.
         * @param comparator
         *            - A comparator, used to determine equivalency between the
         *            values in a and the key.
         * @return The first index i for which comparator considers a[i] and key as
         *         being equal. If no such index exists, return -1 instead.
         */
        public static int firstIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
            // TODO: Implement firstIndexOf
            int beg = 0, end = a.length-1;
            int index = -1;
            while (beg <= end) {
                int mid = (beg + end)/2;
                Term cur = a[mid];
                int comparisonResult = comparator.compare(key, cur); 
                if (comparisonResult == 0) index = mid;
                if (comparisonResult <= 0) end = mid-1;
                else beg = mid+1;
            } 
            return index;
        }

        /**
         * The same as firstIndexOf, but instead finding the index of the last Term.
         * 
         * @param a
         *            - The array of Terms being searched
         * @param key
         *            - The key being searched for.
         * @param comparator
         *            - A comparator, used to determine equivalency between the
         *            values in a and the key.
         * @return The last index i for which comparator considers a[i] and key as
         *         being equal. If no such index exists, return -1 instead.
         */
        public static int lastIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
            // TODO: Implement lastIndexOf
            int beg = 0, end = a.length-1;
            int index = -1;
            while (beg <= end) {
                int mid = (beg + end)/2;
                Term cur = a[mid];
                int comparisonResult = comparator.compare(key, cur); 
                if (comparisonResult == 0) index = mid;
                if (comparisonResult < 0) end = mid-1;
                else beg = mid+1;
            }  
            return index;
        }

    /**
     * An Autocompletor supports returning either the top k best matches, or the
     * single top match, given a String prefix.
     * 
     * @author Austin Lu
     *
     */
    public interface Autocompletor {

        /**
         * Returns the top k matching terms in descending order of weight. If there
         * are fewer than k matches, return all matching terms in descending order
         * of weight. If there are no matches, return an empty iterable.
         */
        public Iterable<String> topMatches(String prefix, int k);

        /**
         * Returns the single top matching term, or an empty String if there are no
         * matches.
         */
        public String topMatch(String prefix);

        /**
         * Return the weight of a given term. If term is not in the dictionary,
         * return 0.0
         */
        public double weightOf(String term);
    }
    /**
     * Implements Autocompletor by scanning through the entire array of terms for
     * every topKMatches or topMatch query.
     */
    public static class BruteAutocomplete implements Autocompletor {

        Term[] myTerms;

        public BruteAutocomplete(String[] terms, double[] weights) {
            if (terms == null || weights == null)
                throw new NullPointerException("One or more arguments null");
            if (terms.length != weights.length)
                throw new IllegalArgumentException("terms and weights are not the same length");
            myTerms = new Term[terms.length];
            HashSet<String> words = new HashSet<String>();
            for (int i = 0; i < terms.length; i++) {
                words.add(terms[i]);
                myTerms[i] = new Term(terms[i], weights[i]);
                if (weights[i] < 0)
                    throw new IllegalArgumentException("Negative weight "+ weights[i]);
            }
            if (words.size() != terms.length)
                throw new IllegalArgumentException("Duplicate input terms");
        }

        public Iterable<String> topMatches(String prefix, int k) {
            if (k < 0)
                throw new IllegalArgumentException("Illegal value of k:"+k);
            // maintain pq of size k
            PriorityQueue<Term> pq = new PriorityQueue<Term>(k, new Term.WeightOrder());
            for (Term t : myTerms) {
                if (!t.getWord().startsWith(prefix))
                    continue;
                if (pq.size() < k) {
                    pq.add(t);
                } else if (pq.peek().getWeight() < t.getWeight()) {
                    pq.remove();
                    pq.add(t);
                }
            }
            int numResults = Math.min(k, pq.size());
            LinkedList<String> ret = new LinkedList<String>();
            for (int i = 0; i < numResults; i++) {
                ret.addFirst(pq.remove().getWord());
            }
            return ret;
        }

        public String topMatch(String prefix) {
            String maxTerm = "";
            double maxWeight = -1;
            for (Term t : myTerms) {
                if (t.getWeight() > maxWeight && t.getWord().startsWith(prefix)) {
                    maxWeight = t.getWeight();
                    maxTerm = t.getWord();
                }
            }
            return maxTerm;
        }

        public double weightOf(String term) {
            for (Term t : myTerms) {
                if (t.getWord().equalsIgnoreCase(term))
                    return t.getWeight();
            }
            // term is not in dictionary return 0
            return 0;
        }
    }
   /**
     * 
     * Using a sorted array of Term objects, this implementation uses binary search
     * to find the top term(s).
     * 
     * @author Austin Lu, adapted from Kevin Wayne
     * @author Jeff Forbes
     */
    public static class BinarySearchAutocomplete implements Autocompletor {

        Term[] myTerms;

        /**
         * Given arrays of words and weights, initialize myTerms to a corresponding
         * array of Terms sorted lexicographically.
         * 
         * This constructor is written for you, but you may make modifications to
         * it.
         * 
         * @param terms
         *            - A list of words to form terms from
         * @param weights
         *            - A corresponding list of weights, such that terms[i] has
         *            weight[i].
         * @return a BinarySearchAutocomplete whose myTerms object has myTerms[i] =
         *         a Term with word terms[i] and weight weights[i].
         * @throws a
         *             NullPointerException if either argument passed in is null
         */
        public BinarySearchAutocomplete(String[] terms, double[] weights) {
            if (terms == null || weights == null)
                throw new NullPointerException("One or more arguments null");
            myTerms = new Term[terms.length];
            for (int i = 0; i < terms.length; i++) {
                myTerms[i] = new Term(terms[i], weights[i]);
            }
            Arrays.sort(myTerms);
        }

        /**
         * Required by the Autocompletor interface. Returns an array containing the
         * k words in myTerms with the largest weight which match the given prefix,
         * in descending weight order. If less than k words exist matching the given
         * prefix (including if no words exist), then the array instead contains all
         * those words. e.g. If terms is {air:3, bat:2, bell:4, boy:1}, then
         * topKMatches("b", 2) should return {"bell", "bat"}, but topKMatches("a",
         * 2) should return {"air"}
         * 
         * @param prefix
         *            - A prefix which all returned words must start with
         * @param k
         *            - The (maximum) number of words to be returned
         * @return An array of the k words with the largest weights among all words
         *         starting with prefix, in descending weight order. If less than k
         *         such words exist, return an array containing all those words If
         *         no such words exist, reutrn an empty array
         * @throws a
         *             NullPointerException if prefix is null
         */
        public Iterable<String> topMatches(String prefix, int k) {
            if (prefix == null) throw new NullPointerException();
            int f = firstIndexOf(myTerms, new Term(prefix, 0) , new Term.PrefixOrder(prefix.length()));
            int l = lastIndexOf(myTerms, new Term(prefix, 0) , new Term.PrefixOrder(prefix.length()));
            if (l < 0) return new ArrayList<String>();
            PriorityQueue<Term> pq = new PriorityQueue<Term>(k, new Term.WeightOrder());
            for (int i = f; i <= l; i++) {
                Term t = myTerms[i];
                if (pq.size() < k) {
                    pq.add(t);
                } else if (pq.peek().getWeight() < t.getWeight()) {
                    pq.remove();
                    pq.add(t);
                }
            }
            int numResults = Math.min(k, pq.size());
            LinkedList<String> ret = new LinkedList<String>();
            for (int i = 0; i < numResults; i++) {
                ret.addFirst(pq.remove().getWord());
            }
            return ret;
        }

        /**
         * Given a prefix, returns the largest-weight word in myTerms starting with
         * that prefix. e.g. for {air:3, bat:2, bell:4, boy:1}, topMatch("b") would
         * return "bell". If no such word exists, return an empty String.
         * 
         * @param prefix
         *            - the prefix the returned word should start with
         * @return The word from myTerms with the largest weight starting with
         *         prefix, or an empty string if none exists
         * @throws a
         *             NullPointerException if the prefix is null
         * 
         */
        public String topMatch(String prefix) {
            if (prefix == null) throw new NullPointerException();
            int f = firstIndexOf(myTerms, new Term(prefix, 0) , new Term.PrefixOrder(prefix.length()));
            int l = lastIndexOf(myTerms, new Term(prefix, 0) , new Term.PrefixOrder(prefix.length()));
            ArrayList<Term> found = new ArrayList<Term>();
            if (l < 0) return "";
            double maxWeight = myTerms[f].getWeight();
            int maxWeightIndex = f;
            for (int i = f+1; i <= l; i++) {
                if (myTerms[i].getWeight() > maxWeight) {
                    maxWeight = myTerms[i].getWeight();
                    maxWeightIndex = i;
                }
            }
            return myTerms[maxWeightIndex].getWord();
        }

        /**
         * Return the weight of a given term. If term is not in the dictionary,
         * return 0.0
         */
        public double weightOf(String term) {
            // TODO complete weightOf
            return 0.0;
        }
    }
    /**
     * General trie/priority queue algorithm for implementing Autocompletor
     * 
     * @author Austin Lu
     * @author Jeff Forbes
     */
    public static class TrieAutocomplete implements Autocompletor {

        /**
         * Root of entire trie
         */
        protected Node myRoot;

        /**
         * Constructor method for TrieAutocomplete. Should initialize the trie
         * rooted at myRoot, as well as add all nodes necessary to represent the
         * words in terms.
         * 
         * @param terms
         *            - The words we will autocomplete from
         * @param weights
         *            - Their weights, such that terms[i] has weight weights[i].
         * @throws NullPointerException
         *             if either argument is null
         * @throws IllegalArgumentException
         *             if terms and weights are different weight
         */
        public TrieAutocomplete(String[] terms, double[] weights) {
            if (terms == null || weights == null)
                throw new NullPointerException("One or more arguments null");
            // Represent the root as a dummy/placeholder node
            myRoot = new Node('-', null, 0);

            for (int i = 0; i < terms.length; i++) {
                add(terms[i], weights[i]);
            }
        }

        /**
         * Add the word with given weight to the trie. If word already exists in the
         * trie, no new nodes should be created, but the weight of word should be
         * updated.
         * 
         * In adding a word, this method should do the following: Create any
         * necessary intermediate nodes if they do not exist. Update the
         * subtreeMaxWeight of all nodes in the path from root to the node
         * representing word. Set the value of myWord, myWeight, isWord, and
         * mySubtreeMaxWeight of the node corresponding to the added word to the
         * correct values
         * 
         * @throws a
         *             NullPointerException if word is null
         * @throws an
         *             IllegalArgumentException if weight is negative.
         */
        private void add(String word, double weight) {
            // TODO: Implement add
            
            if (word == null)
                throw new NullPointerException("Word cannot be null");
            
            if (weight < 0)
                throw new IllegalArgumentException("Negative weight "+ weight);
                
            Node curr = this.myRoot;
            if (!this.contains(word)) {
                for (int i = 0; i < word.length(); i++) {
                    Character currentCharacter = Character.toLowerCase(word.charAt(i));
                    if (!curr.children.containsKey(currentCharacter)) {
                        curr.children.put(currentCharacter,
                                          new Node(Character.toLowerCase(currentCharacter),
                                          curr,
                                          weight));
                        curr.mySubtreeMaxWeight = this.maxWeight(weight, curr.mySubtreeMaxWeight);
                    }
                    else {
                        curr.mySubtreeMaxWeight = this.maxWeight(weight, curr.mySubtreeMaxWeight);
                    }
                    curr = curr.children.get(Character.toLowerCase(currentCharacter));
                }
                    curr.isWord = true;
                    curr.myWord = word;
                    curr.myWeight = weight;
            }
            else {
                curr = this.traverseDownToWord(word);
                //for (int i = 0; i < word.length(); i++) {
                //    Character currentCharacter = word.charAt(i);
                //    curr = curr.children.get(Character.toLowerCase(currentCharacter));
                //}
                
                curr.myWeight = weight;
                Collection<Node> nodes = curr.children.values();
                Node max = null;
                int nodeSize = nodes.size();
                String myWord = word;
                if (nodes.size() > 0) {
                   max = maxNode(nodes);
                   curr.mySubtreeMaxWeight = maxWeight(curr.myWeight, max.mySubtreeMaxWeight);
                }
                else
                    curr.mySubtreeMaxWeight = curr.myWeight;
                
                Node parentNode = curr.parent;    
                do {
                    nodes = parentNode.children.values();
                    max = maxNode(nodes);
                    parentNode.mySubtreeMaxWeight = maxWeight(max.mySubtreeMaxWeight, parentNode.myWeight);
                    parentNode = parentNode.parent;
                } while (parentNode != null);
            }
        }

        /**
         * Required by the Autocompletor interface. Returns an array containing the
         * k words in the trie with the largest weight which match the given prefix,
         * in descending weight order. If less than k words exist matching the given
         * prefix (including if no words exist), then the array instead contains all
         * those words. e.g. If terms is {air:3, bat:2, bell:4, boy:1}, then
         * topKMatches("b", 2) should return {"bell", "bat"}, but topKMatches("a",
         * 2) should return {"air"}
         * 
         * @param prefix
         *            - A prefix which all returned words must start with
         * @param k
         *            - The (maximum) number of words to be returned
         * @return An Iterable of the k words with the largest weights among all
         *         words starting with prefix, in descending weight order. If less
         *         than k such words exist, return all those words. If no such words
         *         exist, return an empty Iterable
         * @throws a
         *             NullPointerException if prefix is null
         */
        public Iterable<String> topMatches(String prefix, int k) {
            // TODO: Implement topKMatches
            if (prefix == null)
                throw new NullPointerException();
                
            if (k < 0)
                throw new IllegalArgumentException();
                
            // Traverse down to the prefix node
            Node curr = this.traverseDownToWord(prefix);
            Node nodeToAdd = null;
            
            // Declaration of all collections to be used
            PriorityQueue<Node> wordQueue = new PriorityQueue<>(k, new Node.ReverseSubtreeMaxWeightComparator());
            ArrayList<Node> bagOfWords = new ArrayList<>(); // Holds words in each highly weighted branch
            ArrayList<String> results = new ArrayList<>(); // Holds final list of strings
            
            if (curr == null)
                return results;
                        
            // Initialize collections/reference variables/Counting variables
            wordQueue.add(curr);
            Node poppedNode = null;
            Node maxInWordQueue = null;
            boolean hasKWordsGreaterThan = false;
            int numGreaterThan = 0;
            
            /* While the popped node is not null and the number of words with weigthts
            *      greater than the max subtree weight of the largest node in the queue,
            *      add words to the bag
            */
            do {
                poppedNode = wordQueue.poll();
                if (poppedNode.isWord) {
                    bagOfWords.add(poppedNode);
                    bagOfWords.sort(null);
                }
                wordQueue.addAll(poppedNode.children.values());
                maxInWordQueue = wordQueue.peek();
                if (maxInWordQueue == null) {
                    numGreaterThan = numWordsGreater(bagOfWords, maxInWordQueue, k);
                    break;
                }
                numGreaterThan = numWordsGreater(bagOfWords, maxInWordQueue, k);
            //} while((!wordQueue.isEmpty()) && (numGreaterThan < k));
            } while(numGreaterThan < k);
            
            int n = bagOfWords.size();
            
            if (numGreaterThan < k) {
                for (int i = n - 1; i >= 0; i--) {
                    results.add(bagOfWords.get(i).myWord);
                }
            }
            else {
                for (int i = n - 1; i >= n-k; i--) {
                    results.add(bagOfWords.get(i).myWord);
                }
            }
            
            return results;
        }

        /**
         * Given a prefix, returns the largest-weight word in the trie starting with
         * that prefix.
         * 
         * @param prefix
         *            - the prefix the returned word should start with
         * @return The word from with the largest weight starting with prefix, or an
         *         empty string if none exists
         * @throws a
         *             NullPointerException if the prefix is null
         */
        public String topMatch(String prefix) {
            // TODO: Implement topMatch
            if (prefix == null) {
                throw new NullPointerException("Prefix cannot be null");
            }
            
            Character currentCharacter = null;
            //Node curr = this.myRoot;
            Node curr = traverseDownToWord(prefix);
            if (curr == null)
                return "";
            //for (int i = 0; i < prefix.length(); i++) {
            //    currentCharacter = Character.toLowerCase(prefix.charAt(i));
            //    curr = curr.children.get(currentCharacter);
            //}
            
            Comparator<Node> reverseWeightComp = new Node.ReverseSubtreeMaxWeightComparator();
            ArrayList<Node> myNodes = new ArrayList<>();
            PriorityQueue<Node> myNOdes = new PriorityQueue<>(reverseWeightComp);
            
            while (curr.mySubtreeMaxWeight != curr.getWeight()) {
                myNOdes.addAll(curr.children.values());
                curr = myNOdes.remove();
            }
            
            /*
            while (curr.mySubtreeMaxWeight != curr.getWeight()) {
                myNodes.addAll(0, curr.children.values());
                myNodes.sort(reverseWeightComp);
                curr = myNodes.get(0);
            }
            */
            
            /*
            while (curr.mySubtreeMaxWeight != curr.getWeight()) {
                curr = maxNode(curr.children.values(), reverseWeightComp);
            }
            */
            
            
            
            return curr.myWord;
        }

        /**
         * Return the weight of a given term. If term is not in the dictionary,
         * return 0.0
         */
        public double weightOf(String term) {
            // TODO complete weightOf
            Node curr = this.traverseDownToWord(term);
            if (curr == null)
                return 0.0;
            if (!curr.isWord)
                return 0.0;
            return curr.myWeight;
        }

        /**
         * Optional: Returns the highest weighted matches within k edit distance of
         * the word. If the word is in the dictionary, then return an empty list.
         * 
         * @param word
         *            The word to spell-check
         * @param dist
         *            Maximum edit distance to search
         * @param k
         *            Number of results to return
         * @return Iterable in descending weight order of the matches
         */
        public Iterable<String> spellCheck(String word, int dist, int k) {
            return null;
        }
        
        public Node traverseDownToWord(String prefix) {
            if (prefix == null)
                throw new NullPointerException();
            Character currentCharacter = null;
            Node curr = this.myRoot;
            for (int i = 0; i < prefix.length(); i++) {
                currentCharacter = prefix.charAt(i);
                currentCharacter = Character.toLowerCase(currentCharacter);
                if (!curr.children.containsKey(currentCharacter)) {
                    return null;
                }
                curr = curr.children.get(currentCharacter);
            }
            return curr;
        }
        
        public int numWordsGreater(ArrayList<Node> array, Node maxInQueue, int k) {
                          
            if (maxInQueue == null)
                return array.size();
                          
            int numElements = 0;
            int n = array.size();
            int i = n - 1;
            Node element = null;
            
            while ((numElements < k) && (i > -1)) {
                element = array.get(i);
                if (element.getWeight() > maxInQueue.mySubtreeMaxWeight) {
                    numElements++;
                }
                i--;
            }
            return numElements;
        }
        
        /**
        * Return the largest from a pair of weights
        *
        * @param d1 - the first weight in the comparison
        * @param d2 - the second weight in the comparison
        * @return the larger of the two weights
        */
        private double maxWeight(double d1, double d2) {
            if (d1 >= d2)
                return d1;
                
            return d2;
        }
        
        public Node maxNode(Collection<Node> nodes) {
            Iterator<Node> itr = nodes.iterator();
            Node max = itr.next();
            Node currentNode = null;
            while (itr.hasNext()) {
                currentNode = itr.next();
                if (currentNode.mySubtreeMaxWeight > max.mySubtreeMaxWeight) {
                    max = currentNode;
                }
            }
            return max;
        }
        
        public Node maxNode(Collection<Node> nodes, Comparator<Node> cmp) {
            Iterator<Node> itr = nodes.iterator();
            Node max = itr.next();
            Node currentNode = null;
            while(itr.hasNext()) {
                currentNode = itr.next();
                if (cmp.compare(currentNode, max) < 0) {
                    max = currentNode;
                }
            }
            return max;
        }
        
        /**
        * Determine if word is in the Trie
        *
        * @param word - a word to be determined its presence in the Trie
        * @return true/false if the word is in the Trie or not
        */
        public boolean contains(String word) {
            Node curr = myRoot;
            for (int i = 0; i < word.length(); i++) {
                Character currentCharacter = word.charAt(i);
                if (!curr.children.containsKey(Character.toLowerCase(currentCharacter))) {
                    return false;
                }
                curr = curr.children.get(Character.toLowerCase(currentCharacter));
            }
            return curr.isWord;
        }
        
	     public class ReverseWeightComparator implements Comparator<Node> {
		      @Override
		      public int compare(Node o1, Node o2) {
			   if (o1.compareTo(o2) < 0) {
				    return 1;
			   } else if (o1.compareTo(o2) < 0) {
				   return -1;
			   }
			    return 0;
		      }
	     }
        
        /**
        * Return mySubtreeMaxWeight at each node along a branch corresponding
        *     to a particular node
        *
        * @param word - the word whose branch we will traverse
        * @return result (ArrayList<Double>) - a list of mySubtreeMaxWeights
        */
        public ArrayList<Double> returnMaxWeights(String word) {
            ArrayList<Double> result = new ArrayList<>();
            
            // If Trie doesn't contain word, return empty ArrayList
            if (!this.contains(word))
                return result;
            Node curr = this.myRoot;
            
            // Add contents from the root
            result.add(curr.mySubtreeMaxWeight); 
            
            // For each character in word, add the contents of myWeights
            for (int i = 0; i < word.length(); i++) {
                Character currentCharacter = word.charAt(i);
                curr = curr.children.get(Character.toLowerCase(currentCharacter));
                result.add(curr.mySubtreeMaxWeight);
            }
            return result;
        }
        
        /**
        * Return myWeight at each node along a branch corresponding
        *     to a particular node
        *
        * @param word - the word whose branch we will traverse
        * @return result (ArrayList<Double>) - a list of the myWeights
        */
        public ArrayList<Double> returnWeights(String word) {
            ArrayList<Double> result = new ArrayList<>();
            
            // if Trie doesn't contain word, return empty ArrayList
            if (!this.contains(word))
                return result;
            Node curr = this.myRoot;
            
            // Add contents from the root
            result.add(curr.myWeight); 
            
            // For each character in word, add the contents of myWeights
            for (int i = 0; i < word.length(); i++) {
                Character currentCharacter = word.charAt(i);
                curr = curr.children.get(Character.toLowerCase(currentCharacter));
                result.add(curr.myWeight);
            }
            return result;
        }
        
        /**
        * Return myInfo at each node along a branch corresponding
        *     to a particular node
        *
        * @param word - the word whose branch we will traverse
        * @return result (ArrayList<String>) - a list of myInfos
        */
        public ArrayList<String> returnMyInfo(String word) {
            ArrayList<String> result = new ArrayList<>();
            
            // If Trie doesn't contain word, return empty ArrayList
            if (!this.contains(word))
                return result;
            Node curr = this.myRoot;
            
            // Add contents from the root
            result.add(curr.myInfo); 
            
            // For each character in word, add the contents of myInfo
            for (int i = 0; i < word.length(); i++) {
                Character currentCharacter = word.charAt(i);
                curr = curr.children.get(Character.toLowerCase(currentCharacter));
                result.add(curr.myInfo);
            }
            return result;
        }
        
        /**
        * Return myWords at each node alonga branch corresponding
        *     to a particular node
        *
        * @param word - the word whose branch we will traverse
        * @return result (ArrayList<String>) - a list of myWords
        */
        public ArrayList<String> returnMyWord(String word) {
            ArrayList<String> result = new ArrayList<>();
            
            // if Trie doesn't contain word, return empty ArrayList
            if (!this.contains(word))
                return result;
                
            Node curr = this.myRoot;
            
            // the root is not a word, add empty string
            result.add("");
            for (int i = 0; i < word.length(); i++) {
                Character currentCharacter = word.charAt(i);
                curr = curr.children.get(Character.toLowerCase(currentCharacter));
                
                // if the current node is a word, add myWord to result
                if (curr.isWord)
                    result.add(curr.myWord);
                else
                    result.add(""); // otherwise add the empty string
            }
            return result;
        }
        
        public void printParentNode(String word) {
                //if (children.parent != null) {
                //    System.out.println("Parent Node: " + child.parent.toString());
                //}
        }
    }
}
