package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.katachi.miraino.database.DBConnection;

import dao.TodoItemDAO;
import dao.UserDAO;
import model.TodoItemModel;
import model.UserModel;

public class TodoItemInsert {

	/**
	 * トランザクションのテスト
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TodoItemDAO itemDAO = new TodoItemDAO();
			TodoItemModel item = new TodoItemModel();
			UserDAO userDAO = new UserDAO();
			UserModel user = new UserModel();
			try (DBConnection db = new DBConnection()) {
				Connection conn = db.getInstance();

				// 自動的にコミットしないようにする。
				conn.setAutoCommit(false);

				// 1回目
				item.setUserId(1);
				item.setTodoItem("テストのアイテム1");
				Date date = new Date(0);
				item.setExpirationDate(date);
				item.setRegistrationDate(date);
				System.out.println(itemDAO.create(conn, item));

				// 2回目
				user.setEmail("test@example.com");
				user.setPassword("abcdefgh");
				user.setName("test user");
				System.out.println(userDAO.create(conn, user));

				// ロールバックして、2回ともインサートされないことを確認する。
//				conn.rollback();

				// コミットして、2回ともコミットされていることを確認する。
				conn.commit();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
