package br.com.justoeu.commons.utils.compilerJS.enums;

public enum OutPutInfo {

	COMPILED_CODE ("COMPILED_CODE"), WARNINGS ("WARNINGS"), ERRORS ("ERRORS"), STATISTICS ("STATISTICS");
	
	private String outPutInfo;
	private OutPutInfo(String outPutInfo){
		this.outPutInfo = outPutInfo;
	}
	
	public String getValue(){
		return this.outPutInfo;
	}
	
	public static OutPutInfo getOutPutInfo(String outPutInfo){
		
		OutPutInfo outCurrent = null;

		for (OutPutInfo out : OutPutInfo.values()){
			if (out.getValue().equals(outPutInfo)){
				outCurrent = out;
				continue;
			}
		}
		
		return outCurrent;
	}
}