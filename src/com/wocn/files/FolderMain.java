package com.wocn.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wocn.files.funct.FolderFunct;
import com.wocn.files.funct.Functionality;
import com.wocn.files.tree.Node;

public class FolderMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		getStructure();
	}

	public static void getStructure() {

//		String fileName = "W1-Front-end Web UI Frameworks Overview Bootstrap.txt";
		String path = "C://borrar";

		String structs[] = {
				"W1-Front-end Web UI Frameworks Overview Bootstrap.txt",
				"W2 - Bootstrap CSS Components.txt",
				"W3 - Bootstrap Javascript Components.txt",
				"W4 - Web Tools.txt" };

		for(String file: structs){
			buildFolderTreeTMP(file, path);	
		}
		
		
	}

	/**
	 * Esto hay que hacerlo más versatil!, por eso es temporal.
	 * 
	 * Entre otras, se debería diferenciar la construcción de la estructura de arbol
	 * a partir del archivo de la construcción de la estructura de directorios. 
	 * 
	 * Tal vez exista una mejor formar de hacerlo que usando un mapa.
	 * 
	 * @param fileName
	 * @param path
	 */
	public static void buildFolderTreeTMP(String fileName, String path) {

		String completePath = path + "/" + fileName;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(completePath));

			String line;
			String levelInd = "";
			
			/* Se crea un mapa donde la clave es el nivel de cada nodo */
			Map<String, Node> map = new HashMap<String, Node>();
			while ((line = br.readLine()) != null) {

				int endIndex = line.indexOf("-");

				if (endIndex != -1) {
					levelInd = line.substring(0, endIndex);
					// System.out.println("levelInd = " + levelInd);

					map.put(levelInd, new Node(line));
				}
			}

			/* se construye el arbol usando el mapa anterior*/
			String defaultKey = null;
			Node root = null;
			for (Map.Entry<String, Node> entry : map.entrySet()) {

				/* Identificación del nodo padre, cuyo nivel 
				 * es una cadena vacia */
				if (!"".equals(entry.getKey())) {
					defaultKey = "";
				}

				String parentKey = getParentKey(entry.getKey(), defaultKey);

				/* Con la clave del padre, se identifica y se le agrega el hijo */
				Node parent = map.get(parentKey);
				if (parent != null) {
					parent.addChild(entry.getValue());
				} else {
					/* el nodo sin padre, es el root.*/ 
					root = entry.getValue();
				}

				System.out.println("child = " + entry.getKey() + " , parent = "
						+ parentKey);
			}

			System.out.println("map" + map);

			/* Se recorre el arbol a partir del root y construye la estructura */
			FolderFunct folderFunct = new FolderFunct();
			goOverTree(root, folderFunct, "C://borrar");

		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				//
			}
		}
	}

	/**
	 * Esto se puede mejorar, debería ser configurable el patrón con el que
	 * se identifica al nivel del padre o el hijo
	 * 
	 * @param key
	 * @param defaultKey
	 * @return
	 */
	public static String getParentKey(String key, String defaultKey) {

		String parentKey = defaultKey;

		if (key != null) {
			int lastIndex = key.lastIndexOf(".");

			if (lastIndex != -1) {
				parentKey = key.substring(0, lastIndex);
			}
		}

		return parentKey;
	}

	/**
	 * Crea un arbol y una estructura de archivos a partir de ahí.
	 */
	public static void testStructure() {

		Node nodo0 = new Node("nodo0");
		Node nodo1 = new Node("nodo1");
		Node nodo2 = new Node("nodo2");
		Node nodo3 = new Node("nodo3");
		Node nodo11 = new Node("nodo11");
		Node nodo12 = new Node("nodo12");
		Node nodo21 = new Node("nodo21");
		Node nodo31 = new Node("nodo31");
		Node nodo121 = new Node("nodo121");

		nodo0.addChild(nodo1);
		nodo0.addChild(nodo2);
		nodo0.addChild(nodo3);

		nodo1.addChild(nodo11);
		nodo1.addChild(nodo12);
		nodo2.addChild(nodo21);
		nodo3.addChild(nodo31);

		nodo12.addChild(nodo121);

		FolderFunct folderFunct = new FolderFunct();
		String location = "C://borrar";
		goOverTree(nodo0, folderFunct, location);

	}

	/**
	 * Recorre el arbol y aplica la funcionalidad de objFunct
	 * 
	 * @param node
	 * @param objFunct
	 * @param paramsFunct
	 */
	public static void goOverTree(Node node, Functionality objFunct,
			Object paramsFunct) {

		Object obj = objFunct.function(node, paramsFunct);

		for (Node child : node.getChildren()) {
			goOverTree(child, objFunct, obj);
		}

	}

}
