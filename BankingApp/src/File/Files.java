package File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
public class Files {
		public static void main(String[] args) {
			try(FileWriter fw=new FileWriter ("transaction.txt")){
				BufferedWriter bf=new BufferedWriter(fw);
				Scanner sc=new Scanner(System.in);
				String test =sc.next();
				bf.write(test+" ");
				bf.append(test);
				bf.close();
			}catch(Exception e) {
				System.out.println("not created a file"+e);
			}
		}
}
