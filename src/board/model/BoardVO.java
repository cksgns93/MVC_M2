package board.model;

import java.sql.Timestamp;

public class BoardVO {
	private int idx;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Timestamp wdate;
	private int readnum;
	
	private String filename;
	private String originFilename;
	private long filesize;
	
	private int refer;//답변형 게시판에서 필요한 property
	private int lev;
	private int sunbun;
	
	public BoardVO() {
		
	}

	public BoardVO(int idx, String name, String subject, String content, String pwd, Timestamp wdate, int readnum,
			String filename, String originFilename, long filesize) {
		super();
		this.idx = idx;
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.pwd = pwd;
		this.wdate = wdate;
		this.readnum = readnum;
		this.filename = filename;
		this.originFilename = originFilename;
		this.filesize = filesize;
	}

	public BoardVO(int idx, String name, String subject, String content, String pwd, Timestamp wdate, int readnum,
			String filename, String originFilename, long filesize, int refer, int lev, int sunbun) {
		super();
		this.idx = idx;
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.pwd = pwd;
		this.wdate = wdate;
		this.readnum = readnum;
		this.filename = filename;
		this.originFilename = originFilename;
		this.filesize = filesize;
		this.refer = refer;
		this.lev = lev;
		this.sunbun = sunbun;
	}

	//setter, getter ----
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getWdate() {
		return wdate;
	}

	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}

	public int getReadnum() {
		return readnum;
	}

	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getOriginFilename() {
		return originFilename;
	}

	public void setOriginFilename(String originFilename) {
		this.originFilename = originFilename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public int getRefer() {
		return refer;
	}

	public void setRefer(int refer) {
		this.refer = refer;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getSunbun() {
		return sunbun;
	}

	public void setSunbun(int sunbun) {
		this.sunbun = sunbun;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
