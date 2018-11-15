import java.util.ArrayList;

public class NetworkBuilder {
	// goal: 
	static public void networkBuilder(ArrayList<Node> nodes) {
		ArrayList<Node> myStack = new ArrayList<Node>();
		ArrayList<Node> starters = new ArrayList<Node>();
		ArrayList<Node> enders = new ArrayList<Node>();
		ArrayList<Path> paths = new ArrayList<Path>();
		Node lastPopped = null;
		StringBuffer tempNetwork = new StringBuffer();
                int longestPath = 0;
                String criticalPathStr = "";
		
		//Finding starting nodes
		for(Node node : nodes) {
			if(node.getParents().isEmpty()) {
				starters.add(node);
			} else if(node.getEnd()) {
				enders.add(node);
			} 		
		}
		
		//Building paths
		for(Node node : enders) {
			
			myStack.add(node);
			
			while(!myStack.isEmpty()) {
				
				//Getting the top myStack
				Node top = myStack.get( myStack.size()-1 );
				//System.out.println("top:" + top.getActivityName());
				
				//If the current top of myStack is a starter
				if( starters.contains( top ) ) {
				
					//Print stack
					int totalDuration = 0;
					tempNetwork.delete(0, tempNetwork.length());
					for(int i = myStack.size()-1;i>=0;i--) {
						tempNetwork.append(myStack.get(i).getActivityName() + ":" + myStack.get(i).getDuration() + " ");
						totalDuration += myStack.get(i).getDuration();
					}
					tempNetwork.append("Total Duration: " + totalDuration);
					String temp = tempNetwork.toString();
					
					Path myPath = new Path(temp,totalDuration);
					paths.add(myPath);
					
					//Pop because we are the start
					lastPopped = top;
					myStack.remove(top);
				} else {
					String nextName = grabNext(top,lastPopped);
//					for(int i=0;i<myStack.size();i++) {
//						if(myStack.get(i).getActivityName().equals(nextName)) {
//							System.out.println("ERROR: CYCLE PRESENT");
//						}
//					}
					
					// Because at the end of the children
					if(nextName == null) {
						
						// Pop ourselves because no children
						lastPopped = top;
						myStack.remove( top );					
						
					} else {
						
						Node nextNode = getNodeFromName(nextName,nodes);
						myStack.add(nextNode);
						
					}
				}
			}
		}
		int flag = 0;
		while(!paths.isEmpty()) {
			int largestDuration = 0;
			int largestIndex = -1;
			String largestContent = "";
			//choose largest path then next largest etc...
			for(int i=0;i<paths.size();i++) {
				if(paths.get(i).getTotalDuration()>largestDuration) {
					largestDuration = paths.get(i).getTotalDuration();
					largestContent = paths.get(i).getPathContent();
					largestIndex = i;
				}	
			}
                        if(longestPath < largestDuration){
                            longestPath = largestDuration;
                            criticalPathStr = "Critical path is: "+ largestContent;
                        }
			flag = 1;
			System.out.print(largestContent);
			System.out.println();
			paths.remove(largestIndex);
		}
                System.out.print(criticalPathStr);
		System.out.println();
		if(flag==0) {
			System.out.println("ERROR: One or more cycles are present. \nPlease reset and enter a new sequence of nodes that does not contain cycles.");
			System.exit(0);
		}
		
	}
	
	static String grabNext(Node top, Node lastPopped) {
		if(lastPopped == null) {
			return top.getParents().get(0);	
		}
		int index = top.getParents().indexOf(lastPopped.getActivityName());
		if(index == top.getParents().size()-1) {
			return null;
		}
		return top.getParents().get(index+1);
	}
	
	static Node getNodeFromName( String name, ArrayList<Node> nodes ) {
		for( Node node : nodes ) {
			if( node.getActivityName().equals( name ) ) {
				return node;
			}
		}
		return null;
	}
}

