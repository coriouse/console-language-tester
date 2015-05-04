package org.app.console.params;

import java.util.HashMap;
import java.util.Map;

import org.app.console.params.ParamsFactory;
import org.app.console.params.Parameter;

public class ParamBuilder {
	
	private Map<String, String> params = new HashMap<String, String>();
	
	public ParamBuilder(String[] args) {
		for(String p : args) {
			String[] map = p.split("[=]");
			params.put(map[0], map[1]);
		}
	}
	@SuppressWarnings("unchecked")
	public <T> T getValue(String key) {		
		Parameter<T> paramValue = ParamsFactory.getInstance().getValue(key);
		return paramValue == null ? null : (T) paramValue.getValue(params.get(key));
	}
}
