package br.viniciusvale.com;

public class Start {

	public static void main(String[] args) {
		System.out.println("Iniciando Embaralhamento");
		if(args.length == 0){
			System.out.println("Arquivo ofuscador ou DUMP não encontrado, defina o parametro corretamente.");
		}else{
			if(args[0] != null || args[1] != null){
				LerFile lerOfuscadores = new LerFile(false, args[1]);
				LerFile lerDump = new LerFile(true, args[0], lerOfuscadores.getOfuscadores());
				System.out.println("Processo FINALIZADO.");
			}
		}	
	}

}
