package com.wocn.files.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private Node parent;
	private List<Node> children;
	private String value;
	
	public Node(String value){
		this.value = value;
		children = new ArrayList<Node>();
	}
	
	public Node(String value, Node parent){
		
		this(value);
		this.parent = parent;
	}	
	
	public boolean isLeaf(){
		return children.isEmpty();
	}
	
	public void addChild(Node node){
		node.parent = this;
		children.add(node);
	}

	public Node getParent() {
		return parent;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return value;
	}
	
}