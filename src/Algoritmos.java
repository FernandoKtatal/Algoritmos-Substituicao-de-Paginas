import java.util.ArrayList;
import java.util.Collections;

public class Algoritmos {

	public static void FIFO(ArrayList<Integer> accessSeq, int Nframes) {
		ArrayList<Integer> frames = new ArrayList<>(Collections.nCopies(Nframes, -1)); // pra nao precisar percorrer o array
		int missingPages = 0;
		int lastIndex = 0;
/*		
 *		para cada pagina a ser acessada ele verifica se ela ja esta carregada,
 *		caso nao esteja ele seta a pagina no local do ultimo elemento que entrou
 *		e atualiza o ultimo elemento de maneira circular
 */
		for (Integer i : accessSeq) {
//			Verifica se ja possui a pagina			
			if (frames.indexOf(i) == -1) {  
				frames.set(lastIndex, i);
				lastIndex = (lastIndex + 1) % Nframes;
				missingPages++;
			}
		}
		System.out.println("FIFO: " + missingPages);
	}	

	public static void OTM(ArrayList<Integer> accessSeq, int Nframes) {
		ArrayList<Integer> frames = new ArrayList<>(Collections.nCopies(Nframes, -1)); // pra nao precisar percorrer o array
		int missingPages = 0;
		int index_access = 0;
		int index = 0;
/*		
 *		para cada pagina a ser acessada ele verifica se ela ja esta carregada,
 *		caso nao esteja ele seta a pagina no local da pagina que vai demorar mais
 *		a ser referenciada
 */
		for(Integer i : accessSeq) {
//			Verifica se ja possui a pagina
			if (frames.indexOf(i) == -1) {
/*
 *				se nao estiver preenchido, terminar de preencher
 */
				if(index < Nframes) {
					frames.set(index, i);
					index++;
					missingPages++;

				} else {

					int tmp = 0; //pagina que mais demora a ser referenciada
					int gap = 0;
					int largeGap = 0;

					for(int f : frames) {
						for (int aux = index_access; aux < accessSeq.size(); aux++) {
							if(accessSeq.get(aux) == f) {
								break;
							}
							gap++;
						}

						if (gap > largeGap) {
							largeGap = gap;
							tmp = f;
						}

						gap = 0;
					}
					
/*					aloca a nova pagina no lugar da 
 * 					pagina encontrada com maior gap		
 */
					frames.set(frames.indexOf(tmp), i);
					missingPages++;
				}
			}
			index_access++; //olha do atual ate o resto da sequencia (nao olhar a lista toda...)
		}

		System.out.println("OTM: " + missingPages);

	}



	public static void LRUwStack(ArrayList<Integer> accessSeq, int Nframes) {
		ArrayList<Integer> frames = new ArrayList<>(Collections.nCopies(Nframes, -1)); // pra nao precisar percorrer o array
		Stack4LRU stack = new Stack4LRU();
		int index = 0;
		int missingPages = 0;
		int count = 0;
/*		
 *		para cada pagina a ser acessada ele verifica se ela 
 *		ja esta carregada, caso nao esteja ele seta a pagina 
 *		no local da pagina carregada a mais tempo
 */
		for (Integer i : accessSeq) {
// 			move para o topo sempre que a pagina for referenciada
			stack.att(i);
//			Verifica se ja possui a pagina
			if (frames.indexOf(i) == -1) {
/*
 *			se nao estiver preenchido, terminar de preencher
 */			
				 if(count < Nframes) {
					 frames.set(index, i);
					 missingPages++;
					 index++;
					 count++;
				 } else {
					 int lru = stack.getBase();
					 while(frames.indexOf(lru) == -1) {
						 stack.remove(lru);
						 lru = stack.getBase();
					 }
					 
					 index = frames.indexOf(lru);
					 frames.set(index, i);
					 missingPages++;
				 }
			 }
		}
		System.out.println("LRU: "+ missingPages);
	}

}
