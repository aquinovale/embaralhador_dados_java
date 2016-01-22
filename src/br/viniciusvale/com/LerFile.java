package br.viniciusvale.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LerFile {
	
	private ArrayList<String> ofuscadores = new ArrayList<String>();
	private EmbaralhaDUMP embaralhaDUMP = new EmbaralhaDUMP();
	
	public LerFile(boolean isDump, String path) {
		lerFile(isDump, path);
	}
	
	public LerFile(boolean isDump, String path, ArrayList<String> ofuscadores) {
		this.ofuscadores = ofuscadores;
		lerFile(isDump, path);
	}
	
	private void lerFile(boolean isDump, String path) {
		FileReader reader;
		BufferedReader leitor;
		try {
			FileWriter writer = new FileWriter(new File("dump_shuffle"));
			BufferedWriter bwriter = new BufferedWriter(writer);
			
			reader = new FileReader(new File(path));
			leitor = new BufferedReader(reader);
			String linha = null;
			
			while ((linha = leitor.readLine()) != null) {
				if(!isDump){
					tokenOfuscadores(linha);
				}else{
					bwriter.write(this.embaralhaDUMP.interpretaLinha(linha, this.ofuscadores));
					bwriter.newLine();
				}
			}
			leitor.close();
			reader.close();
			
			bwriter.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void tokenOfuscadores(String linha){
		ofuscadores.add(linha);
	}

	public ArrayList<String> getOfuscadores(){
		return ofuscadores;
	}
	

}
