//importing required classes
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.print.attribute.standard.DateTimeAtCreation;
import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.util.ArrayList;


//defining class, implementing listener to specify action for button
public class DisplayGUI  extends JFrame implements ActionListener{
    //Declaring required component
    JTextArea ta2;
    JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,btPath,btReport;
    JTextArea ta1;
    JPanel pl1,pl2,pl3,pl4,pl5;
    JLabel lb1;
    JRadioButton rdBtn1, rdBtn2;
    NodeBuilder nodes;
    boolean onlyCrit = true;
    //defining the constructor
    public DisplayGUI(){
        
        //initializing the component
    	
        ta2=new JTextArea(4,20);
        ta1=new JTextArea(25,20);
        bt1=new JButton("About");
        bt2=new JButton("Help");
        lb1 = new JLabel("Network Path Analyzer");
        bt4=new JButton("X");
        bt5=new JButton("Calculate");
        bt6=new JButton("Reset");
        bt7=new JButton("Change Durations");
        btReport = new JButton("Generate Report");
        rdBtn1 = new JRadioButton("Show only critical paths");
        rdBtn2 = new JRadioButton("Show all Paths");
        bt4.setBackground(Color.red);
        pl1=new JPanel();
        pl4 = new JPanel();
        pl5 = new JPanel();
        pl1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pl5.setLayout(new FlowLayout(FlowLayout.LEFT, 110, 10));
        pl2=new JPanel();
        pl3=new JPanel();
      
        //adding the component to panel
        pl4.add(bt1);
        pl4.add(bt2);
        pl1.add(pl4);
        pl5.add(lb1);
        pl5.add(bt4);
        pl1.add(pl5);
        pl2.setLayout(new GridLayout(2,1));
        pl2.add(rdBtn1);
        pl2.add(rdBtn2);
        pl2.add(bt5);
        pl2.add(bt6);
        pl2.add(ta2);
        pl3.add(pl2);
        pl3.add(ta2);
        pl2.add(btReport);
        pl2.add(bt7);
        //setting the GUI layout and adding the component
        setLayout(new BorderLayout());
        add(pl1,BorderLayout.NORTH);
        add(pl3,BorderLayout.SOUTH);
        add(ta1,BorderLayout.CENTER);
   
      
        //adding listeners and properties to radio buttons
        rdBtn1.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(rdBtn1);
        group.add(rdBtn2);
        rdBtn1.addActionListener(this);
        rdBtn2.addActionListener(this);
        
        //adding the listener to the button
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt4.addActionListener(this);
        bt5.addActionListener(this);
        bt6.addActionListener(this);
        btReport.addActionListener(this);
        bt7.addActionListener(this);
        bt7.setVisible(false);
    }
    //defining the main function
    public static void main(String[] args) {
        //Creating an object of the class
    	DisplayGUI  ob=new DisplayGUI();
        //setting the size of the GUI
        ob.setTitle("Network Path Analyzer");
        ob.setSize(800,500);
        //making it visible
        ob.setVisible(true);
        //adding the exit function for closing the GUI
        ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        
    }
    //function to perform action according to the buttons
    @Override
    public void actionPerformed(ActionEvent ae) {
        //getting the component who created the actionR
        String str=ae.getActionCommand();
        //if about button is clicked
        if(str.equals("About")){
            //showing the message to the user
            JOptionPane.showMessageDialog(bt1, "This program allows the user to create nodes of a desired network path by inputting the activity name, duration and a list of predecessors");
        }
        //if the help button is clicked
        else if(str.equals("Help")){
            //showing message to the user
            JOptionPane.showMessageDialog(bt1, "To compute the Network, type Activity name(multiple characters),Duration(integer),Dependencies(predecessors). Then press Calculate. If an error is displayed, click on OK and enter again. To close the screen, click on X on top right corner.");
        }
       
        //if calculate button is clicked add the text to the text area
        else if(str.equals("Calculate")){
        	this.nodes = new NodeBuilder();
                String input = ta2.getText();
                Scanner s = new Scanner(input);
                s.useDelimiter("\\+");
                while(s.hasNext()){
                    String temp = s.next();
                    if(nodes.add(temp)== -1){
                        ta1.setText("User input invalid reset and do it again");
                        break;
                    }
                }
                s.close();
                ta1.setText(nodes.computeNetwork(onlyCrit));
                bt7.setVisible(true);
                
        }
        else if(str.equals("Generate Report"))
        {
                try {
                	FileWriter fw = new FileWriter("D:\\Report.txt");
                	BufferedWriter buffer = new BufferedWriter(fw);
                	String title="Title: "+lb1.getText();
                	DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
                    Date date = new Date();
                    buffer.write(title);
                    buffer.newLine();
                    buffer.newLine();
                    buffer.write("\nDate and Time of Creation: "+dateFormat.format(date));
                   
                    buffer.newLine();
                    buffer.newLine();
                    buffer.write("\nList of activities & their duration:"); //STILL NEED TO WRITE CODE FOR THIS
                    buffer.newLine();
                    buffer.newLine();
                    buffer.write("List of all paths with the activity names and total duration:");
                    buffer.newLine();
                    
                    String txtArea=ta1.getText();
                    String[] txtArray= txtArea.split("\n");
                    for(int i=0;i<txtArray.length;i++)
                    {
                     buffer.write(txtArray[i]);
                     buffer.newLine();
                    }
                   buffer.close();
                   ta1.setText("Report Generated");
                  
         
               
                    buffer.close();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
        }
        //if user want to exit
        else if(str.equals("X")){
            System.exit(1);
        }
        //user selects critical only
        else if(str.equals("Show only critical paths")){
            onlyCrit = true;
        }
        //user selected all paths
        else if(str.equals("Show all Paths")){
            onlyCrit = false;
        }
        //changing durations only appears after path created goes away from reset 
        else if(str.equals("Change Durations")){
            String activityName = JOptionPane.showInputDialog("Enter Activity Name");
            String duration = JOptionPane.showInputDialog("Enter New Duration");
            ta1.setText(nodes.changeDuration(onlyCrit, activityName, Integer.parseInt(duration)));
        }
        //resetting the text area
        else{
            ta1.setText("");
            ta2.setText("");
            bt7.setVisible(false);
        }
          
    }
  
}
