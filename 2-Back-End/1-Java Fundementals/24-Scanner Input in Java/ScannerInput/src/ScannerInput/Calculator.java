package ScannerInput;
import java.util.Scanner;
public class Calculator {
	
		private static Scanner in;
		private static Scanner in2;

		public static void main(String[] args) {
			
			int num = operatorSelect();
			operators(num);
			
			
			
		}

		private static int operatorSelect() {
			in2 = new Scanner(System.in);
			System.out.print("1:Add ,2:sub ,3:Mult ,4:Div :");
			int num = in2.nextInt();
			return num;
		}

		private static void operators(int num) {
			switch (num) {
			case 1:
				addValue();
				break;
			case 2:
				addSub();
				break;
			case 3:
				addMult();
				break;
			case 4:
				addDiv();
				break;
			default:
				break;
			}
		}

		private static void addDiv() {
			in = new Scanner(System.in);
			System.out.print("Enter the First No :");
			double d1 = in.nextDouble();
			System.out.print("Enter the Second No :");
			double d2 = in.nextDouble();
			double result = d1 / d2 ;
			System.out.println(result);
			
		}

		private static void addMult() {
			in = new Scanner(System.in);
			System.out.print("Enter the First No :");
			double d1 = in.nextDouble();
			System.out.print("Enter the Second No :");
			double d2 = in.nextDouble();
			double result = d1 * d2 ;
			System.out.println(result);
			
		}

		private static void addSub() {
			in = new Scanner(System.in);
			System.out.print("Enter the First No :");
			double d1 = in.nextDouble();
			System.out.print("Enter the Second No :");
			double d2 = in.nextDouble();
			double result = d1 - d2 ;
			System.out.println(result);
			
		}

		private static void addValue() {
			in = new Scanner(System.in);
			System.out.print("Enter the First No :");
			double d1 = in.nextDouble();
			System.out.print("Enter the Second No :");
			double d2 = in.nextDouble();
			double result = d1 + d2 ;
			System.out.println(result); 
		}
}

