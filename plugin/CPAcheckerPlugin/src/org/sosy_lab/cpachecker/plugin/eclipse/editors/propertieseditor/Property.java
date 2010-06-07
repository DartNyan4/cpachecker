package org.sosy_lab.cpachecker.plugin.eclipse.editors.propertieseditor;

public class Property {
	private String key;
	private String value;
	public Property(String pKey, String pValue) {
		super();
		this.key = pKey;
		this.value = pValue;
	}
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String pValue) {
		this.value = pValue;
	}
	public void setKey(String pKey) {
		this.key = pKey;
	}
}
