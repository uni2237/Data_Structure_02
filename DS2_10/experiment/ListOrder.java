package experiment;

public enum ListOrder {

	Random, Ascending, Descending;

	public String orderName() {
		if (this.equals(ListOrder.Random)) {
			return "������";
		} else if (this.equals(ListOrder.Ascending)) {
			return "��������";
		} else {
			return "��������";
		}
	}
}
