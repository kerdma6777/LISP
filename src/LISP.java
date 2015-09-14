import java.util.*;
// example lisp expression (ADD (EXP -3 2) (SQR 5) (SUB 6 2) (MULT 6 7 -2 3) (DIV 15 5))
public class LISP 
{
    public static void main (String[] args)
    {
        Scanner input=new Scanner (System.in);
        String exp=input.nextLine(); //the expression is read-in as one line
        exp=exp.substring(1);//removes the first (
        String firstList="("+exp.substring (0,exp.indexOf("(")-1); //reads in the first "list" from the beginning to right before the next (
        exp=exp.substring (exp.indexOf("("),exp.length()-1);//removes the firstList from the exp string
        ArrayList<String> SubLists=findSubLists (exp);
        for (int i=0; i<5; i++)
        {
            String command=input.nextLine(); //reads in the line that contains the command
            if (command.contains("COUNT"))
            {
                System.out.println (count (SubLists));
            }
            else if (command.contains("REMOVE"))
            {
                System.out.println (output ((remove (SubLists, command)),firstList));
            }
            else if (command.contains("SORT"))
            {
                System.out.println (output ((sort (SubLists, command)),firstList));
            }
            else if (command.contains("REVERSE"))
            {
                System.out.println (output ((reverse (SubLists, command)),firstList));
            }
            else if (command.contains("MAXIMUM"))
            {
                System.out.println (SubLists.get(maximum (SubLists)));
            }
        }
    }
    
    /*
     * Determines the sublists with a string that represents the lisp expression
     * @param exp is a string representing the expression
     * @return an array of strings representing the sublists within the lisp expression string
     */
    public static ArrayList<String> findSubLists (String exp)
    {
        ArrayList<String> SubLists=new ArrayList<String> ();
        while (exp.indexOf("(")>-1)
        {
            String sub = exp.substring(0,exp.indexOf(")")+1); //determine the sublist
            SubLists.add(sub); //add the sublist to the ArrayList
            exp=exp.replace(sub, "");//delete the sublist
        }
        SubLists.set (0, " "+SubLists.get(0)); //all other sublists have " " as the first character; add " " to the first sublist to maintain consistency
        return SubLists;
    }
   
    /*
     * Count the number of subLists
     * @param subLists is an ArrayList of strings that each represent a sublist
     * @return the length of the ArrayList, representing the number of sublists
     */
    public static int count (ArrayList<String> subLists)
    {
        return subLists.size();
    }
    
    /*
     * Removes specific sublists
     * @param subLists is an ArrayList of strings that each represent a sublist
     * @param command is a string that represents the user's command i.e. "REMOVE 1 3"
     */
    public static ArrayList<String> remove (ArrayList<String> subLists, String command)
    {
        int lowerBound=Integer.parseInt(command.substring (7, 8));//takes the first number in the command and stores it as an integer
        int upperBound=Integer.parseInt(command.substring (9));//takes the second number in the command and stores it as an integer
        //remove the sublists within the given bounds [lowerBound, upperBound] 
        for (int i=lowerBound-1; i<upperBound; i++)
        {
            subLists.remove (lowerBound-1);
        }
        return subLists;
    }
    
    /*
     * Formats the output so that it resembles a lisp expression
     * @param subLists is an ArrayList of strings that each represent a sublist
     * @param opening is a string representing the first sublist
     * @return a string representing the lisp expression
     */
    public static String output (ArrayList<String> subLists, String opening)
    {
        for (int i=0; i<subLists.size(); i++)
        {
            opening=opening+subLists.get (i);
        }
        opening+=")";
        return opening;
    }
    
    /*
     * Sorts sublists within specific bounds
     * @param subLists is an ArrayList of strings that each represent a sublist
     * @param command is a string that represents the user's command i.e. "SORT 1 3"
     * @return ArrayList of strings that each represent a sublist
     */
    public static ArrayList<String> sort (ArrayList<String> subLists, String command)
    {
    	String change;
        int lowerBound=Integer.parseInt(command.substring (5, 6)); //determine lower bound of sorting interval
        int upperBound=Integer.parseInt(command.substring (7)); //determine upper bound of sorting interval
        ArrayList<String> toSort=new ArrayList<String> (); 
        for (int i=lowerBound-1; i<upperBound; i++)
        {
            toSort.add(subLists.get(i)); //adds each sublist within the sorting interval
        }
        HashMap <String, String> needToSort = new HashMap <String, String> ();
        ArrayList<String> keys=new ArrayList<String> ();
        for (int i=0; i<toSort.size(); i++)
        {
        	change=toSort.get(i).substring(toSort.get(i).indexOf("(")+1);//delete spaces in front and first (
        	keys.add(change.substring(0, change.indexOf(" "))); //add the function name to the keys arrayList i.e. DIV
            needToSort.put (change.substring(0, change.indexOf(" ")),toSort.get(i)); //add the key (the function name) and the sublist it's associated with
        }
        Collections.sort(keys);
        int index=0;
        for (int i=lowerBound-1; i<upperBound; i++)
        {
            subLists.set(i,needToSort.get(keys.get(index))); //add the sublists in their sorted order
            index++;
        }
        return subLists;
    }
    
    /*
     * Reverses the order of sublists within a specific bounds
     * @param subLists is an ArrayList of strings that each represent a sublist
     * @param command is a string that represents the user's command i.e. "REVERSE 1 3"
     */
    public static ArrayList<String> reverse (ArrayList<String> subLists, String command)
    {
        int lowerBound=Integer.parseInt(command.substring (8, 9)); //determine lower bound 
        int upperBound=Integer.parseInt(command.substring (10)); //determine upper bound
        ArrayList<String> toReverse=new ArrayList<String> ();
        for (int i=lowerBound-1; i<upperBound; i++)
        {
            toReverse.add(subLists.get(i)); //add sublists within the interval
        }
        int index=toReverse.size()-1; //index starts at the last element of toReverse
        for (int i=lowerBound-1; i<upperBound; i++)
        {
            subLists.set(i,toReverse.get(index)); 
            index--;
        }
        return subLists;
    }
    
    /*
     * Determine the longest sublist
     * @param subLists is an ArrayList of strings that each represent a sublist
     * @return the index of the longest sublist
     */
    public static int maximum (ArrayList<String> subLists)
    {
        int max=0;
        String option;
        int index=0;
        for (int i=0; i<subLists.size(); i++)
        {
            option=subLists.get(i);
            if (numSpaces (option)>max)
            {
                max=numSpaces (option);
                index=i;
            }
        }
        return index;
    }
    
    /*
     * Count the number of spaces
     * @param input is the string representing the sublist
     * @return the number of spaces
     */
    public static int numSpaces (String input)
    {
        int size1=input.length();
        input=input.replaceAll(" ", "");
        return size1-input.length(); //calculates difference between the original sublist and the sublist without spaces
    }
}