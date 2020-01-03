import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Parser {
    
    private List<String> valueList;
    private Parser obj;

    public Parser (List valueList) {
       this.valueList = valueList;
       this.obj = null;
    }

    public Parser(Parser obj) {
        this.valueList = obj.valueList;
        this.obj = obj;    
    }
    
    public static Parser parse(List stringList) {
        return new Parser(stringList);
    }

    public Parser grab(String str) {
        List<String> resultList = new ArrayList<>();
        for(int i = 0; i < this.valueList.size(); i++) {
            if(this.valueList.get(i).contains(str)) {
                resultList.add(this.valueList.get(i));
            } else {
                continue;
            }
        }
        return new Parser(resultList);
    }

    public Parser echo() {
        List<String> resultList = new ArrayList<>();
        String s1 = new String();
        for(int i = 0; i < this.valueList.size(); i++) {
            if(this.valueList.get(i).contains(" ")) {
                s1 = s1 + this.valueList.get(i).replaceAll("\\s+", " "); //Replace spaces with 1 space
                s1 = s1.strip(); //Strip trial and ending
            }  
            else {
                s1 = s1 + this.valueList.get(i);
            }
            if (i < this.valueList.size() && this.valueList.get(i).trim().length() > 0) {
                s1 = s1 + " ";
            }
        }
        s1 = s1.strip();
        resultList.add(s1);
        return new Parser(resultList);
    }

    public Parser chop(int start, int end) {
        int dataStart = start - 1;
        int dataEnd = end;
        List<String> resultList = new ArrayList<>();
        if (dataStart < 0) {
            dataStart = 0;
        }
        for(int i = 0; i < this.valueList.size(); i++) {
            int dataCopy = dataEnd;
            String s1 = new String();
            if(dataCopy > this.valueList.get(i).length()) {
                dataCopy = this.valueList.get(i).length();
            }
            for(int j = dataStart; j < dataCopy; j++) {
                s1 = s1 + Character.toString(this.valueList.get(i).charAt(j));
            }
            resultList.add(s1);
        }
        return new Parser(resultList);
    }

    public Parser shuffle() {
        String s1 = new String();
        int dataSwapEnd = 0;
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < this.valueList.size(); i++){
            s1 = this.valueList.get(i); //Get current array data and put to string
            StringTokenizer tokenizer = new StringTokenizer(s1); //read for any small segment
            String s2 = new String();
            int secondInd;
            int secondLastInd;
            boolean containsSpecial = false;
            int noOfTokens = tokenizer.countTokens();
            for(int p = 0; p <= noOfTokens; p++) {
                if(!tokenizer.hasMoreElements()) {
                    break;
                }
                String tempString = tokenizer.nextToken();
                List<Integer> letterIndexes = new ArrayList<Integer>();
                for (int l = 0; l < tempString.length(); l++) {
                    char c = tempString.charAt(l);
                    if (Character.isLetter(c)) {
                        letterIndexes.add(l);
                    } else {
                        containsSpecial = true;
                    }
                }
                if (p!=0) {
                    s2 = s2 + " ";
                }
                if (containsSpecial == false) {
                    char[] newtemp = new char[tempString.length()]; //mini segment
                    char[] temp = tempString.toCharArray(); //Initial string char
                    for (int z = 0; z < tempString.length(); z++) {
                        if(z==0 || z == tempString.length() -1) {
                            newtemp[z] = temp[z];
                        } else if(z == tempString.length()-2) {
                            newtemp[z] = temp[1];
                        } else {
                            newtemp[z] = temp[z+1];
                        }
                        if(z==tempString.length() - 1) {
                            s2 = s2 + s2.copyValueOf(newtemp);
                        }
                    }
                } else {
                    if(tempString.length() < 4) {
                        s2 += tempString;
                    } else {
                    secondInd = letterIndexes.get(1);
                    secondLastInd = letterIndexes.get(letterIndexes.size() - 2);
                    s2 += tempString.substring(0, secondInd) + tempString.substring(secondInd + 1, secondLastInd + 1) + tempString.charAt(secondInd) + tempString.substring(secondLastInd + 1, tempString.length());
                    }    
                }
            }
            resultList.add(s2);
            //}
         }
        return new Parser(resultList);
    }

    public Parser linecount() {
    	String test = this.toString();
        StringTokenizer tokenizer = new StringTokenizer(test, "\n");
        List<String> lineCount = new ArrayList<>();
        int counter = 0;
        for(int i = 0; i < this.valueList.size(); i++){
            if(this.valueList.get(i).trim().length() == 0) { //check for empty String inc spaces tab etc.
                counter = counter + 1;
            } else {
                continue;
            }
        }
        lineCount.add(Integer.toString(tokenizer.countTokens() + counter));
        return new Parser(lineCount);
    }
  
    public Parser wordcount() {
        String test = this.toString();
        StringTokenizer tokenizer = new StringTokenizer(test);
        List<String> wordCount = new ArrayList<>();
        wordCount.add(Integer.toString(tokenizer.countTokens())); 
        return new Parser(wordCount);   
    }

    public String toString() {
        String s1 = new String();
        for (int i = 0; i < this.valueList.size(); i++) {
            s1 = s1 + this.valueList.get(i);
            if (i != this.valueList.size() - 1) {
                s1 = s1 + "\n";
            }
        }
        return s1;
    }
}
