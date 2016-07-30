package zx.ffts.entity.chenkai;

import java.io.Serializable;

public class TsGiftRecord implements Serializable{
	private Integer grid;		//编号
	private Integer grgid;		//礼物编号
	private Integer gruserid;	//兑换人
	private Integer grnum;		//兑换数量
	private String grdate;		//兑换日期
	private Integer grstatus;	//兑换状态，0
	public Integer getGrid() {
		return grid;
	}
	public void setGrid(Integer grid) {
		this.grid = grid;
	}
	public Integer getGrgid() {
		return grgid;
	}
	public void setGrgid(Integer grgid) {
		this.grgid = grgid;
	}
	public Integer getGruserid() {
		return gruserid;
	}
	public void setGruserid(Integer gruserid) {
		this.gruserid = gruserid;
	}
	public Integer getGrnum() {
		return grnum;
	}
	public void setGrnum(Integer grnum) {
		this.grnum = grnum;
	}
	public String getGrdate() {
		return grdate;
	}
	public void setGrdate(String grdate) {
		this.grdate = grdate;
	}
	public Integer getGrstatus() {
		return grstatus;
	}
	public void setGrstatus(Integer grstatus) {
		this.grstatus = grstatus;
	}
	public TsGiftRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsGiftRecord(Integer grid, Integer grgid, Integer gruserid,
			Integer grnum, String grdate, Integer grstatus) {
		super();
		this.grid = grid;
		this.grgid = grgid;
		this.gruserid = gruserid;
		this.grnum = grnum;
		this.grdate = grdate;
		this.grstatus = grstatus;
	}
	@Override
	public String toString() {
		return "TsGiftRecord [grdate=" + grdate + ", grgid=" + grgid
				+ ", grid=" + grid + ", grnum=" + grnum + ", grstatus="
				+ grstatus + ", gruserid=" + gruserid + "]";
	}

}
