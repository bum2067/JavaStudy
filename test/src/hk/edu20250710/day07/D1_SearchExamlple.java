package hk.edu20250710.day07;

	//예제:
	//문자열에서 해당 검색어가 존재하는지 판단하여 존재한다면 해당 검색어를 추출하여
	//출력하고, "###"으로 변경하여 처리하고, 계속 검색어가 존재하는지 확인하여
	//앞에 작업을 진행한다.
			// 
	//1.해당 검색어가 존재하는 여부 판단해보기,해당검색어가 없으면 "검색어가 존재하지 않습니다."출력
	//2.해당 검색어의 인덱스를 구해보기: 검색어 인덱스 출력하기
	//3.해당 검색어를 추출해서 출력해보기: substring()을 사용해서 추출한뒤  출력하기
	//4.해당 검색어를 문자열에서 ###으로 바꿔주기
	//5.해당 검색어의 검색된 개수 출력하기[indexOf("검색어",검색시작인덱스)]

public class D1_SearchExamlple {

	public void search(String str) {//str = "카카오"
		String s="카카오페이가 소상공인 상생 캠페인 '오래오래 함께가게'의 올해 두 번째 팝업스토어를 여의도 IFC몰에 오픈했다고 10일 밝혔다.\r\n"
				+ "\r\n"
				+ "오래오래 함께가게는 카카오페이와 함께일하는재단이 소상공인의 지속가능한 사업 성장을 위해 지원하는 상생 캠페인이다.\r\n"
				+ "\r\n"
				+ "2023년부터 시작한 오래오래 함께가게는 지난 5월 올해 첫 팝업스토어를 현대백화점에서 2주간 운영했다.\r\n"
				+ "리빙, 패션잡화, 식품, 친환경제품 등 254개 소상공인 브랜드가 23만여명의 방문객을 만날 수 있도록 지원해 왔다.";
		
		int count = 0;
		int startIndex = 0;
		
		while (true) {
			int index = s.indexOf(str, startIndex);
			
			// 검색어 존재 여부 확인
			if(index == -1) {
				if(count == 0) {
					System.out.println("검색어가 존재하지 않습니다.");
				}
				else {
					System.out.println("총 검색된 개수 : " + count);
					System.out.println("최종 변경된 문자열 : \n" + s);
				}
				break;
			}
			
			// 검색어 인덱스 출력
			System.out.println("검색어 인덱스 : " + index);
			
			// substring으로 검색어 추출
			String found = s.substring(index, index + str.length());
			System.out.println("추출된 검색어 : " + found);
			
			// 검색어를 ###으로 변경
			s = s.substring(0, index) + "###" + s.substring(index + str.length());
			System.out.println("변경된 문자열 : \n" + s + "\n");
			
			// 검색된 갯수 증가
			count ++;
			
			// 다음 검색을 위해 startIndex를 index로 변경 (3은 ### 길이)
			startIndex = index + 3;
		}
	}

}
