package zx.ffts.domain.chenshun;

import java.io.File;
import java.io.Serializable;

/**
 * 上传类
 * @author Administrator
 *
 */
public class up_load implements Serializable  { //上传必须实现serializable
	protected  File abc;
    protected String abcContentType;
    protected String abcFileName;
	public File getAbc() {
		return abc;
	}
	public void setAbc(File abc) {
		this.abc = abc;
	}
	public String getAbcContentType() {
		return abcContentType;
	}
	public void setAbcContentType(String abcContentType) {
		this.abcContentType = abcContentType;
	}
	public String getAbcFileName() {
		return abcFileName;
	}
	public void setAbcFileName(String abcFileName) {
		this.abcFileName = abcFileName;
	}
	public up_load(File abc, String abcContentType, String abcFileName) {
		super();
		this.abc = abc;
		this.abcContentType = abcContentType;
		this.abcFileName = abcFileName;
	}
	public up_load() {
		super();
		// TODO Auto-generated constructor stub
	}



    
}
