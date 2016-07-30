package zx.ffts.entity.chenkai;

import java.io.Serializable;

public class TsMenuMsg implements Serializable{
	private Integer mmid;		//编号
	private Integer mmuserid;	//评论用户
	private Integer mmmuid;		//菜单的id
	private String mmcontent;	//内容
	private Integer mmscore;   //用户评分（1-5分）
	private String mmdate;		//评论时间
	public Integer getMmid() {
		return mmid;
	}
	public void setMmid(Integer mmid) {
		this.mmid = mmid;
	}
	public Integer getMmuserid() {
		return mmuserid;
	}
	public void setMmuserid(Integer mmuserid) {
		this.mmuserid = mmuserid;
	}
	public Integer getMmmuid() {
		return mmmuid;
	}
	public void setMmmuid(Integer mmmuid) {
		this.mmmuid = mmmuid;
	}
	public String getMmcontent() {
		return mmcontent;
	}
	public void setMmcontent(String mmcontent) {
		this.mmcontent = mmcontent;
	}
	public Integer getMmscore() {
		return mmscore;
	}
	public void setMmscore(Integer mmscore) {
		this.mmscore = mmscore;
	}
	public String getMmdate() {
		return mmdate;
	}
	public void setMmdate(String mmdate) {
		this.mmdate = mmdate;
	}
	public TsMenuMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsMenuMsg(Integer mmid, Integer mmuserid, Integer mmmuid,
			String mmcontent, Integer mmscore, String mmdate) {
		super();
		this.mmid = mmid;
		this.mmuserid = mmuserid;
		this.mmmuid = mmmuid;
		this.mmcontent = mmcontent;
		this.mmscore = mmscore;
		this.mmdate = mmdate;
	}
	@Override
	public String toString() {
		return "TsMenuMsg [mmcontent=" + mmcontent + ", mmdate=" + mmdate
				+ ", mmid=" + mmid + ", mmmuid=" + mmmuid + ", mmscore="
				+ mmscore + ", mmuserid=" + mmuserid + "]";
	}
	
	
}
