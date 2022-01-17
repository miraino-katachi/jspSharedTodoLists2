package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.katachi.miraino.database.DBConnection;

import dao.TodoItemDAO;
import model.TodoItemModel;

public class TodoItemInsert {

	/**
	 * トランザクションのテスト
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TodoItemDAO dao = new TodoItemDAO();
			TodoItemModel item = new TodoItemModel();
			Connection conn = DBConnection.getInstance();

			// 自動的にコミットしないようにする。
			conn.setAutoCommit(false);
			Date date = new Date(0);
			
			// 1回目
			item.setUserId(1);
			item.setTodoItem("テストのアイテム1");
			item.setExpirationDate(date);
			item.setRegistrationDate(date);
			System.out.println(dao.create(conn, item));

			// 1回目
			item.setUserId(1);
			item.setTodoItem("テストのアイテム2");
			item.setExpirationDate(date);
			item.setRegistrationDate(date);
			System.out.println(dao.create(conn, item));

			// ロールバックする
			 conn.rollback();

			// コミットする
//			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
