package rcpl.com.calculatorbeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button one,two,three,four,five,six,seven,eight,nine,zero,clear,divide,multi,add,minus,equal;
    EditText txt;
    int fg=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt= (EditText) findViewById(R.id.textView);
        txt.setInputType((InputType.TYPE_NULL));

        one= (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        add = (Button) findViewById(R.id.add);
        minus = (Button) findViewById(R.id.minus);
        multi = (Button) findViewById(R.id.multi);
        divide = (Button) findViewById(R.id.divide);
        clear= (Button) findViewById(R.id.clear);
        equal = (Button) findViewById(R.id.equal);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        add.setOnClickListener(this);
        minus.setOnClickListener(this);
        multi.setOnClickListener(this);
        divide.setOnClickListener(this);
        clear.setOnClickListener(this);
        equal.setOnClickListener(this);

        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txt.setText("");
                return true;
            }
        });

    }


    @Override
    public void onClick(View v) {

        if(fg==1)
        {
            txt.setText(null);
            fg=0;
        }

        switch (v.getId()) {
            case R.id.one:
                txt.setText(txt.getText() + "1");
                break;
            case R.id.two:
                txt.setText(txt.getText() + "2");
                break;
            case R.id.three:
                txt.setText(txt.getText() + "3");
                break;
            case R.id.four:
                txt.setText(txt.getText() + "4");
                break;
            case R.id.five:
                txt.setText(txt.getText() + "5");
                break;
            case R.id.six:
                txt.setText(txt.getText() + "6");
                break;
            case R.id.seven:
                txt.setText(txt.getText() + "7");
                break;
            case R.id.eight:
                txt.setText(txt.getText() + "8");
                break;
            case R.id.nine:
                txt.setText(txt.getText() + "9");
                break;
            case R.id.zero:
                txt.setText(txt.getText() + "0");
                break;


            case R.id.add:
                String strad = txt.getText().toString();
                if(!strad.isEmpty()) {
                    if (!strad.equals(null)) {
                        int a = addcheck(strad);
                        switch (a) {
                            case 1:
                                txt.setText(strad);
                                break;
                            case 2:
                                strad = strad.substring(0, strad.length() - 3);
                                txt.setText(strad + " + ");
                                break;
                            default:
                                txt.setText(txt.getText() + " + ");
                        }
                    }
                }
                else
                txt.setText("");
                break;


            case R.id.minus:
                String strad1 = txt.getText().toString();
                if(!strad1.isEmpty()) {
                    int b = minuscheck(strad1);
                    switch (b) {
                        case 1:
                            txt.setText(strad1);
                            break;
                        case 2:
                            strad1 = strad1.substring(0, strad1.length() - 3);
                            txt.setText(strad1 + " - ");
                            break;
                        default:
                            txt.setText(txt.getText() + " - ");
                    }
                }
                break;


            case R.id.multi:
                String strad2 = txt.getText().toString();
                if(!strad2.isEmpty()) {
                    int c = multicheck(strad2);
                    switch (c) {
                        case 1:
                            txt.setText(strad2);
                            break;
                        case 2:
                            strad2 = strad2.substring(0, strad2.length() - 3);
                            txt.setText(strad2 + " * ");
                            break;
                        default:
                            txt.setText(txt.getText() + " * ");
                    }
                }
                break;


            case R.id.divide:
                String strad3 = txt.getText().toString();
                if(!strad3.isEmpty()) {
                    int d = dividecheck(strad3);
                    switch (d) {
                        case 1:
                            txt.setText(strad3);
                            break;
                        case 2:
                            strad3 = strad3.substring(0, strad3.length() - 3);
                            txt.setText(strad3 + " / ");
                            break;
                        default:
                            txt.setText(txt.getText() + " / ");
                    }
                }
                break;


            case R.id.clear:
                String str = txt.getText().toString();
                if (str.length() > 0) {
                    if(str.charAt(str.length()-1)==' ')
                        str = str.substring(0, str.length()-3);
                    else
                        str = str.substring(0, str.length()-1);
                }
                txt.setText(str);
                break;


            case R.id.equal:
                try {
                    String str1 = txt.getText().toString();
                    if (str1.length() > 0) {
                        if (str1.charAt(str1.length() - 1) == ' ')
                            str1 = str1.substring(0, str1.length() - 3);
                    }

                    if(str1.isEmpty())
                        txt.setText("");
                    else {
                        String temp =String.valueOf(evaluate(str1));
                        if (temp.charAt(0) == '-')
                        {
                            Toast.makeText(this,temp, Toast.LENGTH_LONG).show();
                            temp="";
                        }
                        txt.setText(temp);
                    }

                }
                catch (Exception e)
                {
                    txt.setText("INVALID EXPRESSION");
                    fg=1;
                }
                break;

        }

    }

    public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();
        Stack<Integer> values = new Stack<Integer>();
        Stack<Character> ops = new Stack<Character>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i] == ' ')
                continue;
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
            }
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/')
            {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        return values.pop();
    }
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    public static int applyOp(char op, int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    public int addcheck(String strad)
    {
        if(strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='+'&& strad.charAt(strad.length()-3)==' ')
            return 1;
        else if(strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='*'&& strad.charAt(strad.length()-3)==' '||
                strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='-'&& strad.charAt(strad.length()-3)==' '||
                strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='/'&& strad.charAt(strad.length()-3)==' ')
            return 2;
        else
            return 3;
    }

    public int minuscheck(String strad)
    {
        if(strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='-'&& strad.charAt(strad.length()-3)==' ')
            return 1;
        else if(strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='*'&& strad.charAt(strad.length()-3)==' '||
                strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='+'&& strad.charAt(strad.length()-3)==' '||
                strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='/'&& strad.charAt(strad.length()-3)==' ')
            return 2;
        else
            return 3;
    }
    public int dividecheck(String strad)
    {
        if(strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='/'&& strad.charAt(strad.length()-3)==' ')
            return 1;
        else if(strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='*'&& strad.charAt(strad.length()-3)==' '||
                strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='-'&& strad.charAt(strad.length()-3)==' '||
                strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='+'&& strad.charAt(strad.length()-3)==' ')
            return 2;
        else
            return 3;
    }
    public int multicheck(String strad)
    {
        if(strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='*'&& strad.charAt(strad.length()-3)==' ')
            return 1;
        else if(strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='+'&& strad.charAt(strad.length()-3)==' '||
                strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='-'&& strad.charAt(strad.length()-3)==' '||
                strad.charAt(strad.length()-1)==' '&& strad.charAt(strad.length()-2)=='/'&& strad.charAt(strad.length()-3)==' ')
            return 2;
        else
            return 3;
    }
}
