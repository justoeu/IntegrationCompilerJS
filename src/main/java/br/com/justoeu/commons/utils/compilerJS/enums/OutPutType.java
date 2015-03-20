package br.com.justoeu.commons.utils.compilerJS.enums;

public enum OutPutType {

	TEXT("TEXT"), JSON("JSON"), XML("XML");

	private String outPutFormat;

	private OutPutType(String outPut) {
		this.outPutFormat = outPut;
	}

	public String getValue(){
		return this.outPutFormat;
	}
	
	public static OutPutType getOutPutFormat(String outPutFormat) {

		OutPutType outFormatCurrent = null;

		for (OutPutType out : OutPutType.values()) {
			if (out.getValue().equals(outPutFormat)) {
				outFormatCurrent = out;
				continue;
			}
		}

		return outFormatCurrent;
	}
}