package br.viniciusvale.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmbaralhaDUMP {
	
	private boolean entrou = false;
	private ArrayList<Integer> position;
	
	public String interpretaLinha(String linha, ArrayList<String> ofuscadores){
		StringTokenizer token = new StringTokenizer(linha);
		while (token.hasMoreTokens()) {
			String word = token.nextToken();
			if(word.equals("COPY")){
				String tabela = token.nextToken();
				if(verifyOfuscador(tabela, ofuscadores)){
					entrou = true;
					getPositionFields(ofuscadores, tabela, prepareFields(linha));
				}
				break;
			}else{
				if(entrou){
					if(word.equals("\\.")){
						entrou = false;
					}else{
						return embaralhaFields(linha);
					}
				}else{
					break;
				}
			}
		}	
		return linha;
	}
	
	private String embaralhaFields(String linha) {
		StringTokenizer token = new StringTokenizer(linha, "\t");
		int pos = 0;
		String campo;
		ArrayList<String> newLine = new ArrayList<String>();
		while(token.hasMoreTokens()){
			campo = token.nextToken();
			for(int ordem : position){
				if(pos == ordem){
					campo = embaralhar(campo);
				}
			}
			newLine.add(campo);
			pos++;
		}
		return returnNewLine(newLine);
	}

	private String returnNewLine(ArrayList<String> campos) {
		StringBuilder newLine = new StringBuilder();
		for(int i = 0; i < campos.size(); i++){
			if(campos.size() - 1 == i){
				newLine.append(campos.get(i));
			}else{
				newLine.append(campos.get(i)+"\t");
			}
		}
		return newLine.toString();
	}

	private String embaralhar(String campo) {
		if(campo.equals("\\N")){
			return campo;
		}else{
			char[] caracteres = campo.toCharArray();
			List<Character> ocharaters  = new ArrayList<Character>(caracteres.length);
			for (char c:  caracteres ){
				ocharaters.add(c); // autoboxing
			}
			Collections.shuffle(ocharaters);
			for ( int i =0; i < caracteres.length ; i++ ){
				caracteres[i] = ocharaters.get(i); // unboxing
			}
			return new String(caracteres);
		}
	}

	private void getPositionFields(ArrayList<String> ofuscadores, String tabela, StringTokenizer fields) {
		position = new ArrayList<Integer>();
		if(fields != null){
			int pos = 0;
			while(fields.hasMoreTokens()){
				String field = tabela + "." + fields.nextToken().trim();
				for(String ofuscador : ofuscadores){
					if(field.equals(ofuscador.trim())){
						System.out.println(field +" será embaralhada.");
						position.add(pos);
					}
				}
				pos++;
			}
		}
	}

	private StringTokenizer prepareFields(String linha) {
		Pattern pattern = Pattern.compile("\\(.*\\)");
		Matcher m = pattern.matcher(linha);
		if(m.find()){
			return new StringTokenizer(m.group().substring(1, m.group().length()-1), ",");
		}
		return null;
	}

	private boolean verifyOfuscador(String word, ArrayList<String> ofuscadores){
		for(String ofuscador : ofuscadores){
			StringTokenizer token = new StringTokenizer(ofuscador, ".");
			if(token.nextToken().equals(word)){
				return true;
			}
		}
		return false;
	}
}
