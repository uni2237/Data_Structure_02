package fileHandling;

public enum IOType {
	Input,
	Output;
	
	public String ioTypeName(){
		if(this.equals(IOType.Input)){
			return "�Է�����";
		}
		else if (this.equals(IOType.Output)){
			return "�������";
		}
		else{
			return "���� Ÿ�� ����";
		}
	}

}
