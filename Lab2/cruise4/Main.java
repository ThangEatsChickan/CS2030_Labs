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
	     int LoadersRequired = 0;
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
	         }
	}
}
