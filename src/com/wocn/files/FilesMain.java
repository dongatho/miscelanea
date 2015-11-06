package com.wocn.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilesMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		fixFile();		
		
	}		
	

	public static void fixFile() {
		
		String fileName = "1.1-Overview of HDFS Architecture_c.txt";
		String path = "C://borrar";
		
		// Step1 : Reemplaza -- por [Slide ]  
		String oldText = "--";
		String newText = "[Slide ]";		
		replaceInFile(fileName, path, oldText, newText, true);
		
		// Step2 : Reemplaza ]1 por 1], ]2 por 2], etc. 
		for(int i=1;i<12; i++){
			oldText = "]" + i;
			newText = i+"]";
			replaceInFile(fileName, path, oldText, newText, true);
		}
		
		// Step3: cambia los saltos de línea por espacios en blanco
		oldText = "\n";
		newText = " ";		
		replaceInFile(fileName, path, oldText, newText, true);
		
		// Step4 : inserta un salto de línea antes de cada [
		oldText = "\\[";
		newText = " \n\\[";			
		replaceInFile(fileName, path, oldText, newText, true);
	}

	
	
	/**
	 * Replace a string by other one. 
	 * 
	 * @param fileName
	 * @param path
	 * @param oldText
	 * @param newText
	 * @param overWriteFile
	 */
	public static void replaceInFile(String fileName, String path, String oldText,
			String newText, boolean overWriteFile) {

		String file = path + "/" + fileName;

		StringBuilder sB = new StringBuilder();
		
		FileReader fr = null;
		BufferedReader buffer = null;
		BufferedWriter bw = null;
		String tmpFileName = path + "/" + "tmp_" + fileName;
		try {
			fr = new FileReader(file);
			buffer = new BufferedReader(fr);
			int c = 0;
			while ((c = buffer.read()) != -1) {
				char character = (char) c;
				
				sB.append(character);
				
				//System.out.println(character);
			}
			
			String newStr = sB.toString().replaceAll(oldText, newText);
						
			bw = new BufferedWriter(new FileWriter(tmpFileName));
			bw.write(newStr);
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if (buffer != null){
					buffer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (bw != null){
					bw.close();
				}					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if( overWriteFile ){
			// Once everything is complete, delete old file..
			File oldFile = new File(file);
			oldFile.delete();
			
			// And rename tmp file's name to old file name
			File newFile = new File(tmpFileName);
			boolean renameTo = newFile.renameTo(oldFile);
			
			System.out.println( "renameTo = " + renameTo );
		}
	}

	/**
	 * @param fileName
	 * @param path
	 * @param oldText
	 * @param newText
	 */
	public static void replace(String fileName, String path, String oldText,
			String newText) {

		String oldFileName = path + "/" + fileName;
		String tmpFileName = path + "/" + "tmp_" + fileName;

		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(oldFileName));
			bw = new BufferedWriter(new FileWriter(tmpFileName));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(oldText)) {
					line = line.replace(oldText, newText);
				}

				// bw.write(line + "\n");
			}
		} catch (Exception e) {
			return;
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				//
			}
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				//
			}
		}
		// Once everything is complete, delete old file..
		File oldFile = new File(oldFileName);
		oldFile.delete();

		// And rename tmp file's name to old file name
		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);

	}

	/**
	 * @param fileName
	 * @param path
	 * @param oldText
	 * @param newText
	 */
	public static void replaceLineFile(String fileName, String path,
			String oldText, String newText) {

		String oldFileName = path + "/" + fileName;
		String tmpFileName = path + "/" + "tmp_" + fileName;

		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(oldFileName));
			bw = new BufferedWriter(new FileWriter(tmpFileName));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(oldText)) {
					line = line.replace(oldText, newText);
				}

				// bw.write(line + "\n");
			}
		} catch (Exception e) {
			return;
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				//
			}
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				//
			}
		}
		// Once everything is complete, delete old file..
		File oldFile = new File(oldFileName);
		oldFile.delete();

		// And rename tmp file's name to old file name
		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);

	}

	/**
	 * @param pathFile
	 */
	public static void readFile(String pathFile) {
		FileReader fr = null;

		try {

			fr = new FileReader(pathFile);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fr)
					fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * @param pathFile
	 */
	public static void escribirArchivo(String pathFile) {
		FileWriter fichero = null;
		PrintWriter pw = null;

		try {

			fichero = new FileWriter(pathFile);
			pw = new PrintWriter(fichero);

			pw.println("asdfasdf");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
