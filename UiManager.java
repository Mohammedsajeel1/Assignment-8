import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class UiManager implements Serializable{

	public static final Scanner scanner = new Scanner(System.in);

	public static String readString(String msg) {
		print(msg, null);
		return scanner.next();
	}

	public static double readDouble(String msg) {

		while (true) {
			try {
				return Double.parseDouble(readString(msg));
			} catch (Exception e) {
				System.out.println("\nInvalid input, please type a decimal number\n");
			}
		}

	}

	public static int readInt(String msg) {
		while (true) {
			try {
				return Integer.parseInt(readString(msg));
			} catch (Exception e) {
				System.out.println("\nInvalid input, please type an integer\n");
			}
		}

	}

	public static void print(String s, Object[] variables) {
		System.out.print(String.format(s, variables));
	}

}