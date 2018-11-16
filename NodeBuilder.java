
import java.util.ArrayList;
import java.util.Scanner;

public class NodeBuilder {
        ArrayList<Node> nodes = new ArrayList<Node>();
        
	ArrayList<String> parents = new ArrayList<String>();
        
	public int add(String input) {
		
			//Receive user node input
			String activityName;
                        int duration;
			
			//Setting up new Scanner and delimiter
			Scanner s = new Scanner(input);
			s.useDelimiter(",");
			
			//Set node variables with user input
			if(s.hasNext()) {
				activityName = s.next();
				//System.out.println(activityName);
			} else
				return(-1);
			
			if(s.hasNextInt()) {
				duration = s.nextInt();
				//System.out.println(duration);
                                
			} else
				return(-1);
			
			
			while (s.hasNext()) {
				parents.add(s.next());
			}
			
			Node myNode = new Node(activityName, duration, parents);
			nodes.add(myNode);
			parents.clear();
			
			//System.out.println(parents.size());
			//System.out.println(nodes.size());
			
			//reset parents ArrayList
			
			
			//Closing Scanner
			s.close();
			return(0);
		
        }
                
                
                public String computeNetwork(boolean onlyCrits) {
                    String pathsString = "";
                    
                    for(Node node : nodes) {
                            node.setEnd( nodes );
                    }

                    //Error checking
                    int errors = 0;
                    for(int x=0;x<nodes.size();x++) {
                            boolean haskids = hasKids(nodes.get(x),nodes);
                            if(haskids==false && nodes.get(x).getParents().isEmpty()) {
                                    errors = 1;
                                    return("ERROR: One or more nodes are not connected to the other nodes. \nPlease reset and enter a new sequence of nodes that are connected.");
                                                                        
                            }
                    }

                    if(errors == 0) {
                            pathsString = NetworkBuilder.networkBuilder(nodes, onlyCrits);
                    }
                    return(pathsString);
                }
		public String changeDuration(boolean onlyCrits, String activity, int newDuration){
                        
				
			
				int changed = 0;
				for(int j=0;j<nodes.size();j++) {
					if(nodes.get(j).getActivityName().equals(activity) && changed == 0) {
						nodes.get(j).setDuration(newDuration);
						changed = 1;
					}
				}
				return(NetworkBuilder.networkBuilder(nodes, onlyCrits));
			
			
		}
	
	
	static boolean hasKids(Node node, ArrayList<Node> nodes) {
		for(int i=0;i<nodes.size();i++) {
			if(nodes.get(i).getParents().contains(node.getActivityName())) return true;
		}
		return false;
	}
}
