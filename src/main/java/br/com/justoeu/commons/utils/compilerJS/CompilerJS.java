package br.com.justoeu.commons.utils.compilerJS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import br.com.justoeu.commons.utils.compilerJS.enums.CompilationLevel;
import br.com.justoeu.commons.utils.compilerJS.enums.OutPutType;
import br.com.justoeu.commons.utils.compilerJS.enums.OutPutInfo;
import br.com.justoeu.commons.utils.compilerJS.enums.OutPutResultFormat;
import br.com.justoeu.commons.utils.compilerJS.enums.TypeSource;
import br.com.justoeu.commons.utils.compilerJS.enums.WarningLevel;

public class CompilerJS {

	private static final String UTF_8 				= "UTF-8";
	private static final int HTTP_OK 				= 200;
	private static final String OUTPUT_INFO 		= "output_info";
	private static final String OUTPUT_FORMAT 		= "output_format";
	private static final String COMPILATION_LEVEL 	= "compilation_level";
	private static final String FORMATTING_OUTPUT 	= "formatting";
	private static final String WARNING_LEVEL 		= "warning_level";
	
	private String urlCompiler = "http://closure-compiler.appspot.com/compile";
//	private String contentType = "application/x-www-form-urlencoded";

	private TypeSource typeSource;
	private CompilationLevel compLevel;
	private List<OutPutInfo> outInfo;
	private OutPutType outFormat;
	private OutPutResultFormat outResultFormat;
	private WarningLevel warningLevel;

	public CompilerJS(){
		this.outInfo = new ArrayList<OutPutInfo>();
	}
	
	public String compile(String value) throws Exception {

		if (value.equals("")) throw new Exception("The Code to Compile is not Null");
		if (this.typeSource == null) throw new Exception("TypeSoyrce is not Null");
		
		String contentResult = null;

		try {

			CloseableHttpClient httpclient = HttpClients.createDefault();
			
			HttpPost httpPost = new HttpPost(urlCompiler);
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			nvps.add(new BasicNameValuePair(this.typeSource.getValue(), value));
			nvps.add(new BasicNameValuePair(COMPILATION_LEVEL, this.compLevel == null ? CompilationLevel.WHITESPACE_ONLY.getValue() : this.compLevel.getValue()));
			
			if (this.outInfo.size() > 0){
				for (int i=0; i < this.outInfo.size(); i++){
					nvps.add(new BasicNameValuePair(OUTPUT_INFO, this.outInfo.get(i).getValue()));
				}
			} else{
				nvps.add(new BasicNameValuePair(OUTPUT_INFO, OutPutInfo.COMPILED_CODE.getValue()));
			}
			
			nvps.add(new BasicNameValuePair(OUTPUT_FORMAT, this.outFormat == null ? OutPutType.TEXT.getValue() : this.outFormat.getValue()));
			nvps.add(new BasicNameValuePair(FORMATTING_OUTPUT, this.outResultFormat == null ? OutPutResultFormat.PRETTY_PRINT.getValue() : this.outResultFormat.getValue() ));
			
			nvps.add(new BasicNameValuePair(WARNING_LEVEL, this.warningLevel == null ? WarningLevel.DEFAULT.getValue() : this.warningLevel.getValue()));
			
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, UTF_8));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				
				if (response2.getStatusLine().getStatusCode() == HTTP_OK){
					HttpEntity entity2 = response2.getEntity();
					
					contentResult = EntityUtils.toString(entity2); 
				}
				
				else {
					throw new Exception("Http Erro: " + response2.getStatusLine());
				}
			    
			} finally {
			    response2.close();
			}
			
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}

		return contentResult;
	}

	
//	public String compile(String value) throws Exception {
//
//		Content contentResult = null;
//
//		try {
//
//			contentResult = Request
//					.Post(urlCompiler)
//					.bodyForm(
//							Form.form()
//									.add(this.typeSource.getValue(), value)
//									.add(COMPILATION_LEVEL, this.compLevel.getValue())
//									.add(OUTPUT_INFO, this.outInfo.getValue())
//									.add(OUTPUT_FORMAT, this.outFormat.getValue()).build())
//					.setHeader(CONTENT_TYPE, this.contentType)
//					.execute()
//					.returnContent();
//
//		} catch (IOException e) {
//			throw new Exception(e.getMessage(), e);
//		}
//
//		return contentResult != null ? contentResult.asString() : null;
//	}
	
	/**
	 * Method used to set the Path of AppCompile
	 * @param path
	 * @return
	 */
	public CompilerJS urlCompiler(String path) {
		this.urlCompiler = path;
		return this;
	}

	/**
	 * Method used to set What the Type which will considered to get the source  
	 * @param typeSource
	 * @return
	 */
	public CompilerJS typeSource(TypeSource typeSource) {
		this.typeSource = typeSource;
		return this;
	}

	/**
	 * Method used to set the Format's type of response
	 * @param outFormat
	 * @return
	 */
	public CompilerJS outType(OutPutType outFormat) {
		this.outFormat = outFormat;
		return this;
	}

	/**
	 * Method used to set severals infos for response
	 * @param outInfo
	 * @return
	 */
	public CompilerJS outInfo(OutPutInfo outInfo) {
		this.outInfo.add(outInfo);
		return this;
	}

	/**
	 * Method used to set o Type of Level
	 * @param compLevel
	 * @return
	 */
	public CompilerJS levelCompile(CompilationLevel compLevel) {
		this.compLevel = compLevel;
		return this;
	}

	/**
	 * Method used to set the Output Format.
	 * @param outResultFormat
	 * @return
	 */
	public CompilerJS outFormat(OutPutResultFormat outResultFormat) {
		this.outResultFormat = outResultFormat;
		return this;
	}

	/**
	 * Method used set the Warning's Level
	 * @param warningLevel
	 */
	public CompilerJS warningLevel(WarningLevel warningLevel) {
		this.warningLevel = warningLevel;
		return this;
	}

}