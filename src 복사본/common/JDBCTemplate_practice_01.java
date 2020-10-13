package common;
import java.sql.*;
import java.util.*;

public class JDBCTemplate_practice_01 {
	public JDBCTemplate_practice_01() {
		
	}
	//db랑 연결하는 method설
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//Class.forName()을 호출하면 Driver 가 자기자신을 초기화하여 DriverManager에 등록을 한다.
			//즉 개발자가 따로 관리하지 않는 static 객체들이 알아서 DriverManager에 등록이 되는 것이다.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			
		} catch (Exception e) {
			System.out.println("오류 발생!");
			e.printStackTrace();
		}
		return conn;
	}
	
	//불러온 db넣을 리스트 생성
	public List<EmpVO> getEmps(){
		List<EmpVO> list1 = new ArrayList<EmpVO>(); //ArrayList 개념 다시 한 번 잡아야한다.-집가서이것만 보자
		List<EmpVO> list2 = new ArrayList<EmpVO>(); //ArrayList 개념 다시 한 번 잡아야한다.-집가서이것만 보자
		
		
		Connection conn = getConnection(); //연결된 정보를 conn에 넣는다.
		
		//기본 설정 선언.
		String sql = "select * from emp";
		String sql1 = "";
		String sql2 = "";
		
		Statement stmt = null; //이거 뭔지 이해해야해
		PreparedStatement pstmt = null; //same
		
		ResultSet rs = null; //뭐야 이건 또
		
		String minValue = "2000";
		try {
		sql1 = sql + " where sal > " + minValue;
		stmt = conn.createStatement();
		//둘 차이 확실히 확인하자.
		sql2 = sql + " where sal > ?";
		pstmt = conn.prepareStatement(sql2);
		
		rs = stmt.executeQuery(sql1);
		if(rs.next()) { //next() method가 무슨 의미인지 모른다..............
			do {
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1)); //parameter들어가는 애 잘 확인해
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString("JOB"));
				
				list1.add(vo);
				
				
				
			} while(rs.next());
		if(rs.next()) {
			do {
		
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getNString("JOB"));
				
				list2.add(vo);
		
		} while(rs.next());
		
		}
		}}
		catch (Exception e) {
			System.out.println("alert! error");
		}
		
		
		
		
		return list1;
		
		}
	}

