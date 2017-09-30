/* The back-end code for the implementation of python lexer */

import java.util.*;
import java.io.*;
import java.util.regex.*;  

class a
{
	public static void main(String arg[]) throws Exception
	{
	Scanner sc = new Scanner(new File("file.txt"));
	Map<String,Integer> kwList = new HashMap<String, Integer>();
	Map<String,Integer> opList = new HashMap<String, Integer>();
	Map<String,String> idList = new HashMap<String, String>();

	String[] keywords = { "and", "as", "assert", "break",
"class", "continue", "def", "del" , "elif",
"else", "exec", "except", "false" , "finally", "for", "from" , "global" , "if",
"import", "in", "is" , "lambda" , "not", "or", "pass",
"print", "raise", "return", "true" , "try", "while", "with", "yield", };

	String[] operators={ "+","-","*","/","%","//","**","<","==",">","!=","<=",">=","&","<<",">>","|","~","^","=","+=","-=","*=","/=",
	"%=","//=","**=","&=","|=",">>=","<<=","^=" };

    String[] comments=new String[20];
    int com=0;
   
   String pl=null;
   
    
    Set<String> constants = new HashSet<String>();
    Set<String> identifiers = new HashSet<String>();
    

	while(sc.hasNext())
    {
        	String s = sc.next();
        	//System.out.println(s);
        	
        	for( int i = 0; i <= keywords.length - 1; i++)
			{
    			if (s.equals(keywords[i]))
    			{
    				//System.out.println(s+"-"+i);
    				
    				if (kwList.containsKey(keywords[i]))
    				{
    					kwList.put(keywords[i], kwList.get(keywords[i])+1);
					} 
					else 
					{ 
  						kwList.put(keywords[i],1);
					}
					if(s.equals("print"))
    				{
    					s=sc.nextLine();   
    					 String pattern = "[\"].+[\"]";
						Pattern r = Pattern.compile(pattern);
			 			Matcher m = r.matcher(s);
           				 if(m.find())  
           				 {
            				//String temp = m.group(0).substring(1, m.group(0).length() - 1); 
                			//System.out.println(m.group(0));
                			
           				 }				 				 
    				}
    				break;
				}
				
        	}  
        	
        	for( int i = 0; i <= operators.length - 1; i++)
			{
    			if (s.equals(operators[i]))
    			{
    				//System.out.println(s+"-"+i);
    				
    				if (opList.containsKey(operators[i]))
    				{
    					opList.put(operators[i], opList.get(operators[i])+1);
					} 
					else 
					{ 
  						opList.put(operators[i],1);
					}
					
    				break;
				}
				
        	} 	
        	
        	int f=0;
        	if(s.equals("=") || s.equals("+=") || s.equals("-=") || s.equals("*=") || s.equals("/=") || s.equals("def") )
        	{
        		identifiers.add(pl);
        		if(s.equals("def"))
        			identifiers.add(sc.next());
        			
        	}
        		
			
			String pattern = "#[ ]*[a-zA-Z ]*";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(s);
            if(m.find())  
            {
                comments[com]=sc.nextLine();
                com++;
            }  
            
             pattern = "[0-9]+";
			 r = Pattern.compile(pattern);
			 m = r.matcher(s);
            if(m.find())  
            {
            	//System.out.println(m.group(0));
               constants.add(m.group(0));
            }
            
            pattern = "=[\"][a-zA-Z]+[\"]";
			 r = Pattern.compile(pattern);
			 m = r.matcher(s);
            if(m.find())  
            {
               String temp = m.group(0).substring(1, m.group(0).length() - 1);
            	temp = m.group(0).substring(2, m.group(0).length() -1); 
            	//System.out.println(temp);
                constants.add(temp);
            }
            
           pl=s;
            
              	
    }
    		String pattern = "^[a-zA-Z_][a-zA-Z0-9_]*$";
		Pattern	 r = Pattern.compile(pattern);
    		Iterator it= identifiers.iterator();
    		while(it.hasNext())
    		{
		String str=it.next().toString();
    		Matcher	m = r.matcher(str);
            if(m.find())  
            {
               //System.out.println(m.group(0));
		idList.put(m.group(0), "Valid");
            }
	    else
		{
			idList.put(str, "Invalid");
		}
    	}

	System.out.println("--------------------");
		System.out.println("The Identifiers are:");
    	for (Map.Entry<String, String> entry : idList.entrySet()) 
    		System.out.println(entry.getKey()+" : "+entry.getValue());

	


		System.out.println("--------------------");
		System.out.println("The keywords are:");
    	for (Map.Entry<String, Integer> entry : kwList.entrySet()) 
    		System.out.println(entry.getKey()+" : "+entry.getValue());
       
       System.out.println("--------------------");
       System.out.println("The comments are:");
       for(int i=0;comments[i]!=null;i++)
            System.out.println(comments[i]); 
       
       System.out.println("--------------------");
       System.out.println("The operators are:");     
       for (Map.Entry<String, Integer> entry : opList.entrySet()) 
    	System.out.println(entry.getKey()+" : "+entry.getValue());
      
       System.out.println("--------------------");
        System.out.println("The constants are:");    
        Iterator it = constants.iterator();
		while(it.hasNext()) 
 			System.out.println(it.next());
 		 	
 		
 		
 		
		
       System.out.println("--------------------");
        System.out.println("The identifiers are:");    
         it = identifiers.iterator();
		while(it.hasNext()) 
 			System.out.println(it.next());
 		 System.out.println("--------------------");	
            
       
        
   	}
}
