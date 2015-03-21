package com.finals.link;

import java.util.Scanner;
import java.util.Stack;
/**
 * Evaulate.java is a stack client that evaluates fully parenthesized 
 * arithmetic expressions . it uses Dikstra's 2-stack algortithm
 * 
 * 1: push oparands onto the operand stack
 * 2: push operators onto the operator stack
 * 3: ignore left parentheses
 * 4: on encountering a right parenthesis, pop an operator, pop the requisite numbers of operands,
 *    and push onto the operand stack the result of applying that operator to those operands.
 *
 */
public class Evaulate {
	
	private static Scanner scanner;
	private static final String CHARSET_NAME = "UTF-8";
	
	static{
		scanner = new Scanner(new java.io.BufferedInputStream(System.in), CHARSET_NAME);
	}
	
	public static void main(String[] args) {
       Stack<String> ops = new Stack<String>();
       Stack<Double> vals = new Stack<Double>();
       
       while(!isEmpty())
       {
    	   String s = scanner.next();
    	   if(s.equals("("))                  ;
    	   else if(s.equals("+"))  ops.push(s);
    	   else if(s.equals("-"))  ops.push(s);
    	   else if(s.equals("*"))  ops.push(s);
    	   else if(s.equals("/"))  ops.push(s);
    	   else if(s.endsWith(")")){
    		    String op = ops.pop();
    		    Double val = vals.pop();
    		    if(op.equals("+")) val = vals.pop() + val;
    		    if(op.equals("-")) val = vals.pop() + val;
    		    if(op.equals("*")) val = vals.pop() + val;
    		    if(op.equals("/")) val = vals.pop() + val;
    		    vals.push(val);
    	   }
    	   else vals.push(Double.parseDouble(s));
       }
	}
	
    public static boolean isEmpty() {
        return !scanner.hasNext();
    }
}
