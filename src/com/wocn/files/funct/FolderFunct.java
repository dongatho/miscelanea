package com.wocn.files.funct;

import java.io.File;
import java.util.List;

import com.wocn.files.tree.Node;

public class FolderFunct implements Functionality{

	/**
	 * Se puede mejorar la forma el reemplazo de caracteres invalidos 
	 * a la hora de crear un archivo. 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object function(Object obj, Object parameters) {
		
		Node node = (Node)obj;		
		
		String path = "";
		if( parameters instanceof String ){
			path = (String)parameters;
		}else if( parameters instanceof List ){
			path = ((List<String>)parameters).get(0);
		}
		
		String file = path + "/" + node.getValue().replaceAll("[?|:]", "-");
				
		File dir = new File(file);
		boolean created = dir.mkdir();
		
		System.out.println( "Archivo en la ruta " + file + " : "+ created );
		
		return file;
	}

}
