package br.com.justoeu.commons.utils.compilerJS.enums;

public enum WarningLevel {

	QUIET("quiet"), DEFAULT("default"), VERBOSE ("verbose");
	
	private String warningLevel;
	
	private WarningLevel(String warningLevel){
		this.warningLevel = warningLevel;
	}
	
	public String getValue(){
		return this.warningLevel;
	}
	
	public static WarningLevel getWarningLevel(String warningLevel){
		
		WarningLevel wlResult = null;

		for (WarningLevel wl : WarningLevel.values()){
			if (wl.getValue().equals(warningLevel)){
				wlResult = wl;
				continue;
			}
		}
		
		return wlResult;
	}
}