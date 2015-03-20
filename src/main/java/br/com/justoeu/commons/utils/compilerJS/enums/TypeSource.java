package br.com.justoeu.commons.utils.compilerJS.enums;


public enum TypeSource {
	
	JS_CODE("js_code"),	CODE_URL("code_url");
	
	private final String typeSource;

	private TypeSource(String typeSource) {
		this.typeSource = typeSource;
	}

	public String getValue(){
		return typeSource;
	}
	
	public static TypeSource getTypeSource(String typeSource){
		
		TypeSource tpCurrent = null;

		for (TypeSource tp : TypeSource.values()){
			if (tp.getValue().equals(typeSource)){
				tpCurrent = tp;
				continue;
			}
		}
		
		return tpCurrent;
	}
	
}
