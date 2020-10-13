package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplate {
	public JDBCTemplate() {

	}

	public static Connection getConnection() {
		Connection conn = null; // 습관적으로 나와야한다.

		try {
			Class.forName("oracle.jdbc.OracleDriver"); // 위치 다시 한 번 확인
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("연결 실패!");
			e.printStackTrace();
		}
		return conn;

	}

	public List<EmpVO> getEmps() {
		List<EmpVO> list = new ArrayList<EmpVO>();

		Connection conn = getConnection();

		String sql = "select * from emp";
		String sql1 = "";
		String sql2 = "";

		Statement stmt = null;
		PreparedStatement pstmt = null;
		int rsInt = 0;
		ResultSet rs = null;
		String min = "2000";
		try {
			// sql 문을 실행하기 위한 2가지 statement
			// 1. statement
			sql1 = sql + " where sal > " + min;
			stmt = conn.createStatement();
			// 2. prestatement
			sql2 = sql + " where sal > ?";
			pstmt = conn.prepareStatement(sql2);

			// sql 문이 실행된 결과 2가지
			// 1. int 형 - insert / update / delete
			// 2. ResultSet 형
//		sql = sql + " where sal > "; //띄어쓰기 필수,,,!
			rs = stmt.executeQuery(sql1); // where구문 추가
			if (rs.next()) {
				do {
					EmpVO vo = new EmpVO();
					vo.setEmpno(rs.getInt(1));
					vo.setEname(rs.getString(2));
					vo.setJob(rs.getString(3));
					System.out.println("empno: " + rs.getInt(1) // 1번째 column의 데이터를 int형으로 꺼내라.
							+ ", " + "eName: " + rs.getString(2) + ", " + "eJob: " + rs.getString("job"));
					list.add(vo);

				} while (rs.next());

			} else {
				System.out.println("찾는 데이터가 존재하지 않습니다.");
			}
			// PreparedStatement
			System.out.println("------------");
			sql = sql + " where sal > ?"; // ?를 사용
			pstmt.setString(1, min); // set String또는 set Int (?의 순서대로 1~n, 값)
			rs = pstmt.executeQuery(); // sql 굳이 명시하지 않는다.

			if (rs.next()) {
				do {
					System.out.println("empno: " + rs.getInt(1) // 1번째 column의 데이터를 int형으로 꺼내라.
							+ ", " + "eName: " + rs.getString(2) + ", " + "eJob: " + rs.getString("job"));

				} while (rs.next());

			} else {
				System.out.println("찾는 데이터가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			System.out.println("hey, it's not working");
		}
		
		return list;
	}
//	public static void main(String[] args) {
//		Connection conn = getConnection();
//		if(conn!=null) {
//			System.out.println("연결 성공!");
//		} else {
//			System.out.println("연결 실패!");
//		}
//	}

}
