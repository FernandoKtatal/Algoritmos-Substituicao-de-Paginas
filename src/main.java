import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(System.in);
			ArrayList<Integer> accessSeq = new ArrayList<Integer>();

			int Nframes = in.nextInt();

			while(in.hasNextLine()) {
				accessSeq.add(in.nextInt());
			}

			in.close();

			Algoritmos.FIFO(accessSeq, Nframes);
			Algoritmos.OTM(accessSeq, Nframes);
			Algoritmos.LRUwStack(accessSeq, Nframes);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
