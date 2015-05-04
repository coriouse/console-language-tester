package org.app.console.params;

public class ParamsFactory {

	private final static ParamsFactory PARAMS_FABRIC = new ParamsFactory();
	
	public static ParamsFactory getInstance() {
		return PARAMS_FABRIC;
	}
	
	public  Parameter getValue(String param) {		
		switch(ParamType.getValues(param)) {
			case COUNTWORDS:
				return new CwParam<Integer>();
			case COUNTANSWERS:
				return new CaParam<Integer>();
			case REVERSE:
				return new ReverseParam<String>();
			case PATH:
				return new PathParam<String>();
		}
		return null;
	}
	
	
	public class CwParam<T> implements Parameter<T> {
		
		@Override
		public T getValue(String value) {
			Integer v= Integer.parseInt(value);
			return (T) v;
		}
		
	} 
	
	
	public class CaParam<T> implements Parameter<T> {
		
		@Override
		public T getValue(String value) {
			Integer v= Integer.parseInt(value);
			return (T) v;
		}
	}
	
	
	public class ReverseParam<T> implements Parameter<T> {
		
		@Override
		public T getValue(String value) {
			return (T)value;
		}
	}
	
public class PathParam<T> implements Parameter<T> {
		
		@Override
		public T getValue(String value) {
			return (T)value;
		}
	}
	
}
