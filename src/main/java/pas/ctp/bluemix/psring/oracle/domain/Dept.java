package pas.ctp.bluemix.psring.oracle.domain;


public class Dept
{
	
	private double lng;
    private double lat;
	private String category;
    private int count;
	private long rowid_num;

	public Dept()
    {
    }
    
    
	public double getLng() {
		return lng;
	}



	public void setLng(double lng) {
		this.lng = lng;
	}



	public double getLat() {
		return lat;
	}



	public void setLat(double lat) {
		this.lat = lat;
	}
	
    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getRowid_num() {
		return rowid_num;
	}

	public void setRowid_num(long rowid_num) {
		this.rowid_num = rowid_num;
	}

	@Override
	public String toString() {
		return "Dept{" +
				"lng=" + lng +
				", lat=" + lat +
				", category='" + category + '\'' +
				", count=" + count +
				", rowid_num=" + rowid_num +
				'}';
	}
}
