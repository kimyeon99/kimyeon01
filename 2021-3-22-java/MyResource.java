package scpark;

public class MyResource implements AutoCloseable {
	public int getValue() throws Exception{
		int val = (int) Math.random()*2;
		if(val == 1) {
			return val;
		} else throw new Exception("오류가 발생함");
	}
	@Override
	public void close() throws Exception{
		System.out.println("closed..");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
// 이렇게도 가능 close 자동으로 해준다고
//	public static void write() {
//		try(MyResource rc = new MyResource()){
//			System.out.println(rc.getValue());
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
}
