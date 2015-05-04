package org.app.console.params;


public enum ParamType {
	COUNTWORDS("cw"),
	COUNTANSWERS("ca"),
	REVERSE("r"),	
	PATH("p"); 	
	
	String type;
	
	ParamType(String type) {
		this.type = type;
	}
	
	public static ParamType getValues(String value) {
		
		for(ParamType e: ParamType.values()) {
			if(e.type.equalsIgnoreCase(value)) {
				return e;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		/*  switch(Types.getValues()) {
		  	case JAR:
		  		System.out.println("jar type");
		  		break;
		  	case JPEG:
		  		System.out.println("jpeg type");
		  		break;		  
		  }*/
	}
}
