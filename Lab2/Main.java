import java.util.Scanner;
import java.util.ArrayList;

class Main{
	public static void main(String[] args){
	    ArrayList<Cruise> cruises = new ArrayList<Cruise>();
            Scanner scanner = new Scanner(System.in);
	    int numofInputs = scanner.nextInt();
	        for (int i = 0 ; i < numofInputs; i++)
	        {
                    String cruiseID = scanner.next();
		    int cruiseTime = scanner.nextInt();
		    int cIDCheck = cruiseID.indexOf('B');
		        if(cIDCheck != -1)
			{
			    int noLoader = scanner.nextInt();
			    int completeMins = scanner.nextInt();
		            Cruise c = new BigCruise(cruiseID, cruiseTime, noLoader, completeMins);
			    cruises.add(c);
			}
			else
			{
			    Cruise c = new Cruise(cruiseID, cruiseTime);
			    cruises.add(c);
			}		
	        }
	    computeLoader(cruises);	
	}
        
	public static void computeLoader(ArrayList<Cruise> cruises){
	     int newLoaderID = 1;
	     ArrayList<Loader> loaders = new ArrayList<Loader>();
	     Loader L = new Loader(newLoaderID);
	     loaders.add(L);
	     int loaderIdentifier = 0;
	     int LoadersRequired = 0;
	     int dispLoaderID = 0;
	     for(int i = 0; i < cruises.size(); i++)
	     {
	         LoadersRequired = cruises.get(i).getNumLoadersRequired(); //Based on no. of loaders needed
		 for(int l = 0 ; l < LoadersRequired; l++) //loaders needed
		 {            
		     for(int j = 0; j < loaders.size(); j++)
		     {
                         L = loaders.get(j).serve(cruises.get(i));
			 if(L == null) // Current loader cannot serve cruise
			 {
				 if(j == loaders.size()-1) // Create new loader when not enough
				 {
					 L = new Loader(loaders.size()+1).serve(cruises.get(i));
					 System.out.println(L);
					 loaders.add(L);
					 break;
				 }
				 else //Check with other loaders if current one doesn't have
				 {
					 continue;
				 }
			 }
			 else
			 {
				L = new Loader(j+1).serve(cruises.get(i));
				loaders.set(j, new Loader(j+1).serve(cruises.get(i)));
				System.out.println(L);
				break;
			 }
		     }

		     }
                    /* if(i==0)
		     {  
			 if(LoadersRequired == 1)
			 { 
			     loaders.add(L.serve(cruises.get(i))); //Create ana place new loader in 0
			     dispLoaderID = 0;
			     System.out.println(loaders.get(dispLoaderID));
			 }
			else
			 {
 			     loaders.add(new Loader(newLoaderID).serve(cruises.get(i))); //create more if there is a need.
			     dispLoaderID = newLoaderID;
			     System.out.println(loaders.get(dispLoaderID));
			 }
		     } */ 
	         }
     	     }
	}
	//Use ArrayList to read data for cruise 
	/*public static <ArrayList> Cruise getUserInput(){
	    ArrayList<Cruise> cruises = new ArrayList<Cruise>();
	    Scanner scanner = new Scanner(System.in);
            int counter = 0;
	    String [] cruiseIDString;
	    int [] cruiseTime;
	    int [][] cruiseBDetails;
	    Cruise [] cruiseObj;
	    BigCruise [] bigCruiseObj;
	    Loader [] loaderObj;
	    Loader l;
            numofInputs = scanner.nextInt();
            cruiseIDString = new String[numofInputs];
            cruiseTime = new int[numofInputs];
	    cruiseBDetails = new int[numofInputs][2]; //To take in Loader and time data
	    cruiseObj = new Cruise[counter];
	    bigCruiseObj = new BigCruise[counterBig];
	    loaderObj = new Loader[counter];
            for(int i = 0; i < numofInputs; i++)
	    {
	        cruiseIDString[i] = scanner.next();
		cruiseTime[i] = scanner.nextInt();
		if(cruiseIDString[i].contains("B"))
		{
		    cruiseBDetails[i][0] = scanner.nextInt();
		    cruiseBDetails[i][1] = scanner.nextInt();
		}
	    }
	    for(int j = 0; j < cruiseIDString.length ; j++)
	    {    
		int loaderID = 0;
		Cruise c;
		if(cruiseIDString[j].contains("B"))
		{
		    c = new BigCruise(cruiseIDString[j], cruiseTime[j], cruiseBDetails[j][0], cruiseBDetails[j][1]);
		    l = new Loader(loaderID).serve(c);
		}
                else
	        {
		    c = new Cruise(cruiseIDString[j],cruiseTime[j]);
		    l = new Loader(loaderID).serve(c);
		}
		return l;
                /*if(loaderObj.length == 0)
		{
		    Loader l = new Loader(loaderID).serve(c);
		    loaderObj[counter] = l;
		    counter++;
		    return l;
		}
		else
		{
		   for(int i = 0; i< loaderObj.length; i++)
		   {
			   if(l.serve(cruise) == null)
			   {
				   l = new Loader(loaderID).serve(cruise);
				   return l;
			   }
			   else
			   {
				   return l.serve(cruise);
			   }
		   }
		}*/

                /*while(l==null)
	        {
		    loaderID++;
		    Loader l = new Loader(loaderID).serve(cruise);
	        }
	    }		
	}

	/*public static Loader computeInput(String[] cruiseID, int[] cruiseDetails, int[][][] cruiseBDetails){
             int counter = 0;
	     int Bcounter = 0;
	     int loaderCount = 1;
	     String[] output;
	     for(String [] uniqueID: cruiseID)
	     {
		     
		     if (uniqueID[counter].contains("B"))
	             {       
	                 Cruise cruise = new BigCruise(uniqueID[counter], cruiseBDetails[Bcounter], cruiseBDetails[Bcounter][Bcounter], cruiseBDetails[Bcounter][Bcounter][Bcounter]);  
		     }
		     else
		     {
			 Cruise cruise = new Cruise(uniqueID[counter], cruiseDetails[counter]);
		     }
		     Loader loader = new Loader(loaderCount, cruise).serve(cruise);
		     if (loader == null)
		     {
			 loaderCount++;
			 loader = new Loader(loaderCount, cruise).serve(cruise);
		     }
		     return loader;
	     }
	}*/
