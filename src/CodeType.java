
public enum CodeType {
	PROMOTIONAL_CODE("promotionalcode"),
	TOPUP_CODE("topupvoucher");
	
	private String tablename = "";
	private CodeType(String tablename) {
		this.tablename = tablename;
	}
	public String getTableName() {
		return this.tablename;
	}
}
