package scpark;

public class MyResource implements AutoCloseable {
	public int getValue() throws Exception{
		int val = (int) Math.random()*2;
		if(val == 1) {
			return val;
		} else throw new Exception("������ �߻���");
	}
	@Override
	public void close() throws Exception{
		System.out.println("closed..");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
// �̷��Ե� ���� close �ڵ����� ���شٰ�
//	public static void write() {
//		try(MyResource rc = new MyResource()){
//			System.out.println(rc.getValue());
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
}
