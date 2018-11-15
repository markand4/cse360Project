
import java.util.ArrayList;
import java.util.Scanner;

public class NodeBuilder {
        ArrayList<Node> nodes = new ArrayList<Node>();
        
	ArrayList<String> parents = new ArrayList<String>();
        
	public void add(String input) {
		
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
				activityName = "Activity " + "not entered";
			
			if(s.hasNext()) {
				duration = s.nextInt();
				//System.out.println(duration);
			} else
				duration = 0;
			
			
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
                                    System.out.print("ERROR: One or more nodes are not connected to the other nodes. \nPlease reset and enter a new sequence of nodes that are connected.");
                                    errors = 1;
                                    break;
                            }
                    }

                    if(errors == 0) {
                            pathsString = NetworkBuilder.networkBuilder(nodes, onlyCrits);
                    }
                    return(pathsString);
                }
		public void changeDuration(boolean onlyCrits){
                        Scanner kb = new Scanner(System.in);
			System.out.println("Would you like to change the duration of an activity? (y/n)");
			char response = kb.next().charAt(0);
		
			while(response != ('n')) {
				System.out.println("Enter the name of the activity to be changed: ");
				String activity = kb.next();
			
				System.out.println("Enter the new duration: ");
				int newDuration = kb.nextInt();
			
				int changed = 0;
				for(int j=0;j<nodes.size();j++) {
					if(nodes.get(j).getActivityName().equals(activity) && changed == 0) {
						nodes.get(j).setDuration(newDuration);
						changed = 1;
					}
				}
				NetworkBuilder.networkBuilder(nodes, onlyCrits);
			
				System.out.println("Would you like to change the duration of an activity? (y/n)");
				response = kb.next().charAt(0);
			}
                        kb.close();
		}
	
	
	static boolean hasKids(Node node, ArrayList<Node> nodes) {
		for(int i=0;i<nodes.size();i++) {
			if(nodes.get(i).getParents().contains(node.getActivityName())) return true;
		}
		return false;
	}
}
