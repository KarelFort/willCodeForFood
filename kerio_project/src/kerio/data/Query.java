package kerio.data;

public class Query {

	private int id;
	private String name;
	private String statement;
	private String info;
	private String minDate;
	private String maxDate;
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatement() {
		return this.statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}	

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getMinDate(){
		return this.minDate;
	}
	
	public void setMinDate(String minDate){
		this.minDate = minDate;
	}
	
	public String getMaxDate(){
		return this.maxDate;
	}
	
	public void setMaxDate(String maxDate){
		this.maxDate = maxDate;
	}
	
}
