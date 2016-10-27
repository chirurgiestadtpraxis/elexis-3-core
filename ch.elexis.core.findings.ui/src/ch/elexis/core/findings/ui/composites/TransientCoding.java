package ch.elexis.core.findings.ui.composites;

import ch.elexis.core.findings.ICoding;

public class TransientCoding implements ICoding {
	
	private String system;
	private String code;
	private String display;
	
	public TransientCoding(String system, String code, String display){
		this.system = system;
		this.code = code;
		this.display = display;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public void setDisplay(String display){
		this.display = display;
	}
	
	@Override
	public String getSystem(){
		return system;
	}
	
	@Override
	public String getCode(){
		return code;
	}
	
	@Override
	public String getDisplay(){
		return display;
	}
	
}
