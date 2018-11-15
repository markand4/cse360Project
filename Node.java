import java.util.ArrayList;

public class Node {
	
	private String activityName;
	private int duration;
	private ArrayList<String> parents = new ArrayList<String>();
	private boolean end;
	
	public Node() {
		this.activityName = "ExampleActivity";
		this.duration = 1;
		this.parents.add("null");
		this.end = false;
	}
	public Node(String activityName, int duration, ArrayList<String> parents) {
		this.activityName = activityName;
		this.duration = duration;
		this.parents = (ArrayList<String>) parents.clone();
		this.end = false;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public ArrayList<String> getParents() {
		return parents;
	}
	public void setParents(ArrayList<String> parents) {
		for(int i=0;i<parents.size();i++) {
			this.parents.add(parents.get(i));
		}
	}
	public boolean getEnd() {
		return end;
	}
	public void setEnd( ArrayList<Node> nodes  ){
        for( Node node : nodes ) {
            if( node.parents.contains( this.activityName ) ) return;
        }
        this.end = true;
    }
	@Override
	public String toString() {
		return "Node [activityName=" + activityName + ", duration=" + duration
				+ ", parents=" + parents + ", end=" + end + "]";
	}
}
