
package SNLPAS1;

//import java.awt.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 class Automata{
	 //String[] tokens=text.split("\\W");

	// YOUR CODE HERE 
    int ss=0;
    ArrayList<String> allowedCharacters = new ArrayList<>(); 
	HashMap<Integer, Integer[]> tt = new HashMap<>();
	ArrayList<Integer> endStates = new ArrayList<>();
    
    /**
     * Constructor taking the grammar String which defines the behavior of this
     * automata.
     */
    public Automata(String grammarDef) {
       
        parseGrammar(grammarDef);
        
    }

    /**
     * An internal method that parses the given grammar String.
     */
    protected void parseGrammar(String grammarDef) {
        // YOUR CODE HERE
    	
    	String[] lines = grammarDef.split("\n");
    	String headline = lines[0];
    	String[] headlineSplitted = headline.split("\t");
    	for(int i=1;i<headlineSplitted.length;i++) {
    		allowedCharacters.add(headlineSplitted[i]);
    	}
    	for(int i=1;i<lines.length;i++) {
    		String[] parts = lines[i].split("\t");
    		Integer[] states = new Integer[parts.length - 1];

    		for(int j=1;j<parts.length;j++) {
    			states[j-1] = Integer.valueOf(parts[j]);
    		}
    		if(parts[0].contains(":")) {
    			
    			parts[0] = parts[0].replaceAll(":", "");
    			endStates.add(Integer.valueOf(parts[0]));
    		}
    		tt.put(Integer.valueOf(parts[0]), states);
    	}
    	System.out.println(Arrays.asList(tt)); // method 1

    	
}

    /**
     * This method should return true if the complete given text is accepted by the
     * FSA. If this is not the case, false should be returned.
     */
    public boolean acceptsString(String text) {
       // boolean accepted = false;
        // YOUR CODE HERE
        for(int i=0;i<=text.length()+1;i++)
        {
             if(i==text.length())
             {
                 for(int j=0;j<endStates.size();j++)
                 {
                     if (ss==endStates.get(j))
                     {
                         return true;
                     }  
                 }       
                 return false;   	 
             } 
            else if(tt.isEmpty())
            {
                return false;
            }
            else
            {
            	for(int k=0;k<allowedCharacters.size();k++)
                {
            		if(text.charAt(i)==allowedCharacters.get(k).charAt(0) )
            		{
            			Integer[] myList=(tt.get(ss));
                        ss=myList[k];
            		}
                }	
            }   	
        }
        return false;
    }
    public String[] findMatches(String text) {
        String[] matches = null;
        // YOUR CODE HERE
        String[] partOf = text.split(" ");
        int k=0;
        for(int i=0;i<partOf.length;i++)
        {
        if(acceptsString(partOf[i]))
        {
            matches[k]=partOf[i];
            k++;
        }  
        }
        return matches;
    }

 public static void checkAutomata(String grammar, String input, boolean expectedResult,Automata automata) {

             automata = new Automata(grammar);
            if (automata.acceptsString(input)) {
  
                       System.out.println( "Your automata "+input+" accepted ");
            } else {
                //Assertions.assertFalse(automata.acceptsString(input),
            	 System.out.println("Your automata "+input+" rejected");
            }
         /*System.out.println("Test successfully completed.");
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        } catch (Throwable e) {
            System.err.println("Your solution caused an unexpected error:");
            throw e;
        }*/
 }

	
    public static void main(String[] args)
    {
    	
    	String grammar1 = "State\ta\tb\t!\n0\t5\t1\t5\n1\t2\t5\t5\n2\t3\t5\t5\n3\t3\t5\t4\n4:\t5\t5\t5\n5\t5\t5\t5";
    	Automata a = new Automata(grammar1);
    	a.checkAutomata(grammar1, "baa!", true,a);
    	a.checkAutomata(grammar1, "baaaaa!", true,a);
    	checkAutomata(grammar1, "baaa!!!", false,a);
    	checkAutomata(grammar1, "!aab", false,a);
    	checkAutomata(grammar1, "xyz", false,a);
    	
    	
    }
}
