package hk.edu20250725.day17;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class D3_IOTest {
	
	public static void main(String[] args) {
		test01();
	}
	
	// 파일을 읽고 출력하기
	public static void test01() {
		InputStream in = null;	// 입력파이프 선언
		OutputStream out = null;	// 출력파이프 선언
		
		try {
			in = new FileInputStream("C:\\Users\\kt747\\Hello.java");
			out = new FileOutputStream("C:\\Users\\kt747\\output_Hello.java");
			
			int i =0;	//byte 단뒤로 읽어서 데이터를 저장할 변수
			while ((i = in.read()) !=-1) {	// 읽어들일 데이터가 없으면 -1 리턴
				System.out.print((char)i);
				out.write(i);	// 파일 출력하는 코드
			}
				
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			try {
				// 마지막에 실행했던 스트림부터 닫는다.
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
	}

}
