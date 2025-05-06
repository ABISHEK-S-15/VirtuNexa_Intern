import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.util.Random;


public class mathquiz extends JFrame implements ActionListener{

    private JLabel questionlabel,toplabel;
    private JTextField answerfield;
    private JButton submitbtn;

    private int currentquestion = 0;
    private int totalquestion = 10;
    private int score = 0;

    private int num1,num2;
    private char operator;
    private double correctanswer;


    public mathquiz(){
        setTitle("MATH QUIZ");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JPanel bottomPanel = new JPanel(new FlowLayout());
        
        Font questionFont = new Font("Arial", Font.BOLD, 20);
        Font inputFont = new Font("SansSerif", Font.BOLD, 20);

        toplabel = new JLabel("Question : 1/"+(totalquestion),SwingConstants.CENTER);
        toplabel.setFont(inputFont);
        
        questionlabel = new JLabel("", SwingConstants.CENTER);
        questionlabel.setFont(questionFont);
        
        answerfield = new JTextField();
        answerfield.setFont(inputFont);
        
        submitbtn = new JButton("SUBMIT");
        submitbtn.setFont(new Font("Verdana", Font.PLAIN, 18));
        submitbtn.setPreferredSize(new Dimension(120, 40));
        
        centerPanel.add(toplabel);
        centerPanel.add(questionlabel);
        centerPanel.add(answerfield);
        bottomPanel.add(submitbtn);
        
        
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        submitbtn.addActionListener(this);

        generatequestion();

        setVisible(true);

    }


    public void generatequestion(){
        Random random = new Random();
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        operator = generateoperator();

        if(operator == '/' && num2 == 0)num2 = 1;

        correctanswer = calculateanswer(num1,num2,operator);

        questionlabel.setText("What is "+num1+" "+operator+" "+num2+" ?");
        answerfield.setText("");

    }

    public char generateoperator(){
        char[] ope = {'+','-','*','/'};
        return ope[new Random().nextInt(ope.length)];

    }

    public double calculateanswer(int num1, int num2, char operator){
        switch(operator){
            case '+':
                return num1+num2;
            case '-':
                return num1-num2;
            case '*':
                return num1*num2;
            case '/':
                return (double)num1/num2;
            default:
                return  0;
            
        }

    }

    @Override
    public void actionPerformed(ActionEvent e){
        try{
            double useranswer = Double.parseDouble(answerfield.getText());
            if(Math.abs(useranswer - correctanswer)<0.01){
                score++;
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Please enter a valid number !!");
            return;
        } 

        currentquestion++;

        if(currentquestion < totalquestion){
            toplabel.setText("Question : "+(currentquestion+1)+"/"+totalquestion);
            generatequestion();

        }else{
            JOptionPane.showMessageDialog(this,"Quiz Over! \nYour Score is : "+score+"/"+totalquestion);
            submitbtn.setEnabled(false);
            answerfield.setEnabled(false);
            questionlabel.setText("Thanks for Playing ! ");
            toplabel.setText("");
        }

    }



    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> new mathquiz());
    }



}