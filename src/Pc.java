public class Pc {
	
	private int id;
	private boolean estReserve;
	
	public Pc(int _id) {
		this.estReserve = false;
		this.id = _id;
	}

	public void setEstReserve(boolean estReserve) {
		this.estReserve = estReserve;
	}
}
