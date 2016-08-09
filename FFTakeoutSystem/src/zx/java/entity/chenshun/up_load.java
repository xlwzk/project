package zx.java.entity.chenshun;

import java.io.File;
import java.io.Serializable;

/**
 * 上传类
 * @author Administrator
 *
 */
public class up_load implements Serializable  { //上传必须实现serializable
        protected File name;
        protected String nameFileName;
		public File getName() {
			return name;
		}
		public void setName(File name) {
			this.name = name;
		}
		public String getNameFileName() {
			return nameFileName;
		}
		public void setNameFileName(String nameFileName) {
			this.nameFileName = nameFileName;
		}
		public up_load(File name, String nameFileName) {
			super();
			this.name = name;
			this.nameFileName = nameFileName;
		}
		public up_load() {
			super();
			// TODO Auto-generated constructor stub
		}
        
}
