import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fibo_series extends JFrame{
    private JTextField inputfield;
    private JTextArea resultarea;
    private JRadioButton bynumbtn,bytermbtn;
    private JButton generatebtn;

    public Fibo_series(){

        setTitle("Fibonacci Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //setVisible(true);

        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(3,1));

        JLabel instructionlabel = new JLabel("Enter a Number or Number of Terms: ");
        inputfield = new JTextField();
        

        bynumbtn = new JRadioButton("Up to Number",true);
        bytermbtn = new JRadioButton("By Number of Terms");

        ButtonGroup group = new ButtonGroup();
        group.add(bynumbtn);
        group.add(bytermbtn);

        JPanel optionpanel = new JPanel();
        optionpanel.add(bynumbtn);
        optionpanel.add(bytermbtn);

        inputpanel.add(instructionlabel);
        inputpanel.add(inputfield);
        inputpanel.add(optionpanel);

        add(inputpanel, BorderLayout.NORTH);

        resultarea = new JTextArea();
        resultarea.setEditable(false);
    

        
        add(new JScrollPane(resultarea),BorderLayout.CENTER);

        generatebtn = new JButton("Generate");
        add(generatebtn, BorderLayout.SOUTH);

        generatebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                generatefibo();
            }
            
        });

        inputfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                generatefibo();
            }
        });

        setVisible(true);
    }

    private void generatefibo(){
        resultarea.setText("");

        try{
            int input = Integer.parseInt(inputfield.getText());
            int a=0, b=1;

            if(bynumbtn.isSelected()){
                resultarea.append("Fibonacci up to "+input+": \n");
                while(a<=input){
                    resultarea.append(a+" ");
                    int next = a+b;
                    a = b;
                    b = next;
                }
            }else{
                resultarea.append("Fibonacci "+input+" terms: \n");
                for(int i=0;i<input;i++){
                    resultarea.append(a+" ");
                    int next = a+b;
                    a=b;
                    b=next;
                }
            }

        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Please enter a valid Number...");
        }

    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new Fibo_series());
    
    }
}