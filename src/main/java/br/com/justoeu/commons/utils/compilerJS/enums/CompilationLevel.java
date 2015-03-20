package br.com.justoeu.commons.utils.compilerJS.enums;

public enum CompilationLevel {

	WHITESPACE_ONLY("WHITESPACE_ONLY"), 
	SIMPLE_OPTIMIZATIONS("SIMPLE_OPTIMIZATIONS"), 
	ADVANCED_OPTIMIZATIONS ("ADVANCED_OPTIMIZATIONS");
	
	private String compilation;
	
	private CompilationLevel(String comLevel){
		this.compilation = comLevel;
	}
	
	public String getValue(){
		return this.compilation;
	}

	public static CompilationLevel getCompilationLevel(String typeSource){
		
		CompilationLevel cLCurrent = null;

		for (CompilationLevel clevel : CompilationLevel.values()){
			if (clevel.getValue().equals(typeSource)){
				cLCurrent = clevel;
				continue;
			}
		}
		
		return cLCurrent;
	}
	
}