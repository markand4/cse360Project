import java.util.ArrayList;

public class Path {
	
		
		private String pathContent;
		private int totalDuration;
		
		public Path() {
			this.pathContent = "ExamplePath";
			this.totalDuration = 0;
		}
		public Path(String pathContents, int totalDuration) {
			this.pathContent = pathContents;
			this.totalDuration = totalDuration;
		}
		public String getPathContent() {
			return pathContent;
		}
		public void setPathContent(String pathContent) {
			this.pathContent = pathContent;
		}
		public int getTotalDuration() {
			return totalDuration;
		}
		public void setTotalDuration(int duration) {
			this.totalDuration = duration;
		}
		@Override
		public String toString() {
			return "Path [pathContent=" + pathContent + ", totalDuration=" + totalDuration + "]";
		}
	}
