package br.com.justoeu.commons.utils.compilerJS.enums;

public enum OutPutResultFormat {

	PRETTY_PRINT ("pretty_print"),
	PRINT_INPUT_DELIMITER ("print_input_delimiter");
	
	private String outPutResult;
	private OutPutResultFormat(String outPutResultFormat){
		this.outPutResult = outPutResultFormat;
	}
	
	public String getValue(){
		return this.outPutResult;
	}
	
	public static OutPutResultFormat getOutPutResultFormat(String outPutResultFormat) {

		OutPutResultFormat outPutResult = null;

		for (OutPutResultFormat out : OutPutResultFormat.values()) {
			if (out.getValue().equals(outPutResultFormat)) {
				outPutResult = out;
				continue;
			}
		}

		return outPutResult;
	}
	
}