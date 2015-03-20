package br.com.justoeu.icjs;


import org.junit.Assert;
import org.junit.Test;

import br.com.justoeu.commons.utils.compilerJS.CompilerJS;
import br.com.justoeu.commons.utils.compilerJS.enums.CompilationLevel;
import br.com.justoeu.commons.utils.compilerJS.enums.OutPutInfo;
import br.com.justoeu.commons.utils.compilerJS.enums.OutPutResultFormat;
import br.com.justoeu.commons.utils.compilerJS.enums.OutPutType;
import br.com.justoeu.commons.utils.compilerJS.enums.TypeSource;
import br.com.justoeu.commons.utils.compilerJS.enums.WarningLevel;

public class JCompilerJS {

	private static final String URL_PATH_COMPILER = "http://websvn.kinghost.net/templates/calm/collapse.js";

	@Test
	public void executeCompilerJSUsingJSCode(){
		
		StringBuilder codeJS = new StringBuilder();
		codeJS.append("alert('hello'); <!-- teste-->");
		
		
		String jsCompile;
		try {
			jsCompile = new CompilerJS().typeSource(TypeSource.JS_CODE)
										.levelCompile(CompilationLevel.WHITESPACE_ONLY)
										.outInfo(OutPutInfo.COMPILED_CODE)
										.outInfo(OutPutInfo.STATISTICS)
										.outType(OutPutType.TEXT)
										.outFormat(OutPutResultFormat.PRETTY_PRINT)
										.compile(codeJS.toString());
			
			//System.out.println("executeCompilerJSUsingJSCode" + jsCompile);
			
			Assert.assertTrue(!jsCompile.trim().equals(""));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	@Test
	public void executeCompilerJSUsingJSCodeError(){
		
		StringBuilder codeJS = new StringBuilder();
		codeJS.append("function getElementbyClass(rootobj, classname){");
		codeJS.append("var temparray=new Array()");
		codeJS.append("var inc=0");
		codeJS.append("for (i=0; i<rootobj.length; i++){");
		codeJS.append(" if (rootobj[i].className==classname) {");
		codeJS.append("temparray[inc++]=rootobj[i]");
		codeJS.append("}");
		codeJS.append("}");
		codeJS.append("return temparray;");
		codeJS.append("}");
		
		String jsCompile;
		try {
			jsCompile = new CompilerJS().typeSource(TypeSource.JS_CODE)
										.levelCompile(CompilationLevel.WHITESPACE_ONLY)
										.outInfo(OutPutInfo.COMPILED_CODE)
										.outInfo(OutPutInfo.STATISTICS)
										.outInfo(OutPutInfo.ERRORS)
										.outType(OutPutType.TEXT)
										.outFormat(OutPutResultFormat.PRETTY_PRINT)
										.compile(codeJS.toString());
			
			System.out.println("executeCompilerJSUsingJSCodeError" + jsCompile);
			
			Assert.assertTrue(!jsCompile.trim().equals(""));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	
	@Test
	public void executeCompilerJSUsingURL(){
		
		String jsCompile;
		try {
			jsCompile = new CompilerJS().typeSource(TypeSource.CODE_URL)
									   	.levelCompile(CompilationLevel.WHITESPACE_ONLY)
									   	.outInfo(OutPutInfo.COMPILED_CODE)
									   	.outInfo(OutPutInfo.STATISTICS)
									   	.outType(OutPutType.TEXT)
									   	.outFormat(OutPutResultFormat.PRETTY_PRINT)
									   	.warningLevel(WarningLevel.QUIET)
									   	.compile(URL_PATH_COMPILER);
			
			//System.out.println("executeCompilerJSUsingURL" + jsCompile);
			
			Assert.assertTrue(!jsCompile.trim().equals(""));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
}