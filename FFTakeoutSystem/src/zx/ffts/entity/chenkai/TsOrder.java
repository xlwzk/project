package zx.ffts.entity.chenkai;

import java.io.Serializable;

public class TsOrder implements Serializable{
	private Integer oid;			//订单编号
	private Integer ouserid;		//用户
	private Integer omuid;			//某个菜
	private Integer ortid;			//某个点
	private Integer ocount;			//数量
	private Integer osender;		//配送员
	private String odate;			//配送时间
	private String ouuid;			
	private Integer ostatus;		//订单状态
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getOuserid() {
		return ouserid;
	}
	public void setOuserid(Integer ouserid) {
		this.ouserid = ouserid;
	}
	public Integer getOmuid() {
		return omuid;
	}
	public void setOmuid(Integer omuid) {
		this.omuid = omuid;
	}
	public Integer getOrtid() {
		return ortid;
	}
	public void setOrtid(Integer ortid) {
		this.ortid = ortid;
	}
	public Integer getOcount() {
		return ocount;
	}
	public void setOcount(Integer ocount) {
		this.ocount = ocount;
	}
	public Integer getOsender() {
		return osender;
	}
	public void setOsender(Integer osender) {
		this.osender = osender;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public String getOuuid() {
		return ouuid;
	}
	public void setOuuid(String ouuid) {
		this.ouuid = ouuid;
	}
	public Integer getOstatus() {
		return ostatus;
	}
	public void setOstatus(Integer ostatus) {
		this.ostatus = ostatus;
	}
	public TsOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsOrder(Integer oid, Integer ouserid, Integer omuid, Integer ortid,
			Integer ocount, Integer osender, String odate, String ouuid,
			Integer ostatus) {
		super();
		this.oid = oid;
		this.ouserid = ouserid;
		this.omuid = omuid;
		this.ortid = ortid;
		this.ocount = ocount;
		this.osender = osender;
		this.odate = odate;
		this.ouuid = ouuid;
		this.ostatus = ostatus;
	}
	@Override
	public String toString() {
		return "TsOrder [ocount=" + ocount + ", odate=" + odate + ", oid="
				+ oid + ", omuid=" + omuid + ", ortid=" + ortid + ", osender="
				+ osender + ", ostatus=" + ostatus + ", ouserid=" + ouserid
				+ ", ouuid=" + ouuid + "]";
	}
	
	
}
