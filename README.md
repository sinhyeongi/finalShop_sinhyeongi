# 메인 메뉴 - 실행시 초기값 : MallMain
<pre>
  <code>
    1000/admin/admin/관리자 시작시 자동 생성
    프로그램 종료 -> controller next = null
  </code>
</pre>
>1. 회원가입 : MallJoin

	id 중복검사 
	

>2. 로그인 : MallLogin

	관리자 로그인, 맴버 로그인메뉴 따로 있음, id값 controller가 저장

# 관리자 메뉴 :  AdminMain

>1. 회원관리 : AdminMember
	  
	[1]회원 목록	memberDAO.PrintInfo
	[2]회원 삭제 	controller.DeleterUser(삭제 아이디 )
	[3]뒤로가기	AdminMain
	[0] 종료	 Controller.next->null

	전체 회원 목록 num, id, pw, name
	회원 삭제 시 구매목록 삭제 시 재 확인 구매 아이템 삭제 (게시글 삭제 X)
	admin 관리자는 삭제 불가능

>2. 상품관리 : AdminItem
   <pre>
     <code>
	[1] 아이템 추가	ItemDAO.NewItem(아이템이름,카테고리,가격)
       아이템 이름 중복 확인
       [2] 아이템 삭제 : Controller.DeleteItem(아이템넘버)
        현재 아이템 이 없다면 "아이템이 없습니다! " 출력
       아이템 삭제 후 세이브
       [3] 총 매출 아이템 갯수 출력 ItemDAO.PrintAdminCartData(CartDAO.PrintCart() )
       PrintCart() -> 아이템 구매가 가장 많은것이 가장 앞으로 정렬되며 (아이템 넘버 / 아이템 갯수 \n ..... 아이템 넘버 / 아이템 갯수) String값으로 리턴
       PrintAdminCartData() -> 받아온 String 값에 해당 하는 아이템 [넘버] [카테고리] [아이템 이름] [가격] 갯수 를 출력
       [4] 뒤로가기 Controller.next -> AdminMain
     </code>
   </pre>
   >3. 게시판 관리 : AdminBoard
  <pre>
    <code>
      [1] 게시글 목록 
        BoardDAO.PrintBoard -> 전체 게시글 중 페이지 에 해당하는 개시글 출력 
        [1]이전 beforePage
        [2]이후 NextPage
        [3]게시글 보기
          getBoardendNum -> 마지막 게시글 번호
          게시글번호를 입력 받아 PrintBoard(게시글번호)로 게시글 넘버 / 타이틀 / 작성자 / 날짜 / 조회수 / 본문 내용 출력 후 조회수 1증가
        [0] AdminBoard로 이동
      [2]게시글 삭제 BoardDAO.DeleteUserBoard(현재 로그인한 회원 아이디 값, 입력 받은 게시글 번호)
        해당 게시글의 작성자가 로그인 한 회원이라면 삭제 가능
      [3] 뒤로가기 Controller.next -> AdminMain
    </code>
  </pre>
  >4.로그아웃
  <pre>
    <code>
      컨트롤러에 로그인한 아이디값을 가진 LoginId값을 변경
      Controller.next -> MallMain으로 변경
    </code>
  </pre>
  >5. 세이브  Save
<pre>
  <code>
    FileDAO에 SaveAllFiles에  멤버,아이템,카트,게시글 에 대한 스트링 데이터 값을 넘겨준다
  </code>
</pre>

#유저 메뉴 : MemberMain
>1.상품 구매 MemverShop
<pre>
  <code>
    아이템이 없을시 아이템이 없습니다 출력 후 MemberMain으로 이동
    ItemDAO.PrintCategory를 사용하여 카테고리를 출력
    카테고리 번호를 입력 받고
    카테고리에 해당하는 아이템 출력 PrintCateGoryItem(입력받은 카테고리번호)
    아이템 이름을 입력 받고 아이템 이름이 없다면 아이템 이름 오류 다시 입력 해주세요 출력 후 재 입력
    아이템 갯수를 입력 받는다 1~100개 까지 
    아이템 구매를 카트에 전달 CartDAO.BuyItem(로그인 유저 아이디 , 아이템 넘버 , 갯수)
    아이템 넘버 -> ItemDAO.getItemNo(아이템이름)을 통하여 전달
  </code>
</pre>
>2. 구매내역 MemberCart
<pre>
  <code>
    [1]쇼핑하기 controller.next -> MemberShopping
    [2]뒤로가기 controller.next -> MemberMain
    [0]종료 controller.next -> null
  </code>
</pre>
>3. 게시판 MemberBoard
<pre>
  <code>
    [1]게시글 보기
       BoardDAO에 페이지 값에 해당 하는 게시글 5개를 출력 한다
      [1]이전 beforePage 이전 페이지 변경 현재 페이지가 1이라면 이동 불가
      [2] 이후 NextPage 다음 페이지로 변경 현재 페이지가 마지막 페이지라면 이동 불가
      [3]게시글 보기 PrintBoard(게시글번호)
        게시글의 번호/타이틀/작성자/날짜/조회수 / 본문 내용 을 출력 후 조회수 1증가
    [2]게시글 추가
      게시글의 제목, 내용을 입력 받아 BoardDAO.NewBoard에 ( 제목 , 본문내용, 현재 회원 아이디)값을 전달
    [3]내 개시글(삭제)
      자신의 개시글 갯수를 확인하여 0 개라면 게시글이 없습니다 출력
      게시글이 있다면 자신의 개시글을 출력 한다
      [1] 삭제
          삭제할 게시글 번호를 입력 받고 해당 게시글 번호가 없다면 해당 게시글이 없습니다 출력
          해당 게시글의 작성자가 본인이라면 삭제 가능
      [0] 돌아가기 MemberBoard로 전환
    [4]뒤로가기 next -> MemberMain
  </code>
</pre> 
>4. 나의정보 MemberInfo
<pre>
  <code>
    [1]비밀번호 변경
      현재 비밀번호를 입력 받고 입력 받은 비밀 번호가 맞으면 새로운 비밀번호를 입력 받는다 현재 비밀번호와 새로운 비밀번호가 같다면 변경을 취소한다
      MemberDAO.UpdatePw(사용자의 아이디,바꿀 비밀번호)
    
    [2]뒤로가기 next -> MemberMain
  </code>
</pre>
>5. 회원 탈퇴 
<pre>
  <code>
    회원 탈퇴를 한번더 확인 하며 회원 탈퇴시 구매내역과 해당 유저를 삭제 (게시글 삭제 X)
    Controller.DeleteUser -> 현재 로그인한 회원 삭제
  </code>
</pre>
>6. 로그아웃
<pre>
  <code>
    컨트롤러가 가지고 있는 로그인 아이디값을 변경 후 next -> MallMain    
  </code>
</pre>
