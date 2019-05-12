
public enum CodeType {
	/**
	 * The different types of code that can be generated
	 */
	PROMOTIONAL_CODE("promotionalcode"),
	TOPUP_CODE("topupvoucher");
	
	/** Stores the table name of each type of code **/
	private String tablename = "";
	/**
	 * 
	 * @param tablename This allows us to store the table name inside the ENUMs
	 */
	private CodeType(String tablename) {
		this.tablename = tablename;
	}
	/**
	 * 
	 * @return the table name of the ENUM.
	 */
	public String getTableName() {
		return this.tablename;
	}
}
