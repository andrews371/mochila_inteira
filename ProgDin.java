import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProgDin {
	
	public static void inic(){
        int i = 0, j = 0;
      
        try{
        	Path arquivo = Paths.get("mochila01.txt");
        	byte[] texto = Files.readAllBytes(arquivo);	        	
        	String leitura = new String(texto);	        	
        	String[] vetor_inicial = leitura.split("[ \r\n]"); 
       
        	
        	for (i = 0; i < vetor_inicial.length; i++){
        		if (!(vetor_inicial[i].trim().isEmpty()))
        		{	
        			vetor_inicial[j] = vetor_inicial[i];
        			j++;
        		}        
        	
        	}
        	ArrayList<Integer> vetor_int = new ArrayList<Integer>();
        	for (i = 0; i < j; i++){
        		vetor_int.add(Integer.parseInt(vetor_inicial[i])); 
        	}
            int cap_mochila = vetor_int.get(1);
            
            int num_elementos = ((vetor_int.size())-2)/2;
        	prog_din(num_elementos, cap_mochila, peso(vetor_int), valor(vetor_int));
        	
        }catch(Exception e){
       
    		System.out.println("Erro ao ler o arquivo");
    	
	    }
    
    }
    
    public static int cap(int[] vetor_int){
    	return vetor_int[1];
    }
    
    public static int[] peso(ArrayList<Integer> vetor_int){
    	int i, k = 0;
    	int[] peso = new int[((vetor_int.size())-2)/2];
    	for (i = 2; i < vetor_int.size(); i+=2){
    		peso[k] = vetor_int.get(i);
    		k++;
    	}
    	return peso;
    }
    
    public static int[] valor(ArrayList<Integer> vetor_int){
    	int i, k = 0;
    	int[] valor = new int[((vetor_int.size())-2)/2];
    	for (i = 3; i < vetor_int.size(); i+=2){
    		valor[k] = vetor_int.get(i);
    		k++;
    	}
    	return valor;
    }

	public static void prog_din(int num_itens, int cap_mochila, int[] peso, int[] valor){
		int[][] tabela = new int[num_itens+1][cap_mochila+1];
		
		for (int i = 0; i <= num_itens; i++){
			tabela[i][0] = 0; 
		}
		for (int j = 0; j <= cap_mochila; j++){
			tabela[0][j] = 0;
		}
		
		for (int i = 1; i <= num_itens; i++){
			for (int j = 1; j <= cap_mochila; j++){
				if ((peso[i-1] <= j) && ((valor[i-1] + tabela[i-1][(j - peso[i-1])]) >= tabela[i-1][j])){
					tabela[i][j] = valor[i-1] + tabela[i-1][(j - peso[i-1])];
				}
				else {
					tabela[i][j] = tabela[i-1][j];
				}
				
			}
		}
		
		System.out.println("\nSolucao\n\nValor: "+tabela[num_itens][cap_mochila]);
		
		ArrayList<Integer> prod = new ArrayList<Integer>();
		
		while(num_itens != 0){
			if (tabela[num_itens][cap_mochila] != tabela[num_itens-1][cap_mochila]){
				prod.add(num_itens);
				cap_mochila -= (peso[num_itens-1]);
				num_itens -= 1;
			}
			else{
				num_itens -= 1;
			}
		}
		
		System.out.print("Produtos escolhidos: ");
		for (int i = prod.size() - 1; i > 0; i--){
			System.out.print(prod.get(i)+", ");
		}
		
		System.out.println(prod.get(0));
		
	}
	
	public static void main(String[] args) {
		inic();		
	}
}
