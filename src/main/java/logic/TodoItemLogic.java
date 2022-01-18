package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.katachi.miraino.database.DBConnection;

import dao.TodoItemDAO;
import model.TodoItemModel;

/**
 * TODOアイテムロジッククラス
 */
public class TodoItemLogic {

	/**
	 * TODOアイテムを1件追加します。
	 * 
	 * @param model TODOアイテムモデル
	 * @return 結果（true:成功、false:失敗）
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean crate(TodoItemModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			TodoItemDAO dao = new TodoItemDAO();
			return dao.create(conn, model);
		}
	}

	/**
	 * TODOアイテムを全件取得します。
	 * 
	 * @return TODOアイテムモデルのArrayList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<TodoItemModel> find() throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			TodoItemDAO dao = new TodoItemDAO();
			return dao.findAll(conn);
		}
	}

	/**
	 * 指定ユーザーIDのTODOアイテムを全件取得します。
	 * 
	 * @param userId ユーザーID
	 * @return TODOアイテムモデルのArrayList
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TodoItemModel> find(int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			TodoItemDAO dao = new TodoItemDAO();
			return dao.findByUserId(conn, userId);
		}
	}

	/**
	 * 指定ユーザーIDのTODOアイテムを取得します。
	 * 
	 * @param userId ユーザーID
	 * @param limit  取得するレコード数（リミット値）
	 * @param offset 取得開始する行数（オフセット値）
	 * @return TodoItemModelのArrayList
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TodoItemModel> find(int userId, int limit, int offset) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			TodoItemDAO dao = new TodoItemDAO();
			return dao.findByUserId(conn, userId, limit, offset);
		}
	}

	/**
	 * 指定ID、指定ユーザーのTODOアイテムを1件取得します
	 * 
	 * @param id     TODOアイテムID
	 * @param userId ユーザーID
	 * @return TODOアイテムモデル
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TodoItemModel find(int id, int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			TodoItemDAO dao = new TodoItemDAO();
			return dao.findOne(conn, id, userId);
		}
	}

	/**
	 * 指定ユーザーIDのTODOアイテムをキーワードで検索します。
	 * 
	 * @param userId  ユーザーID
	 * @param keyWord 検索キーワード
	 * @return TODOアイテムモデルのArrayList
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TodoItemModel> find(int userId, String keyWord) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			TodoItemDAO dao = new TodoItemDAO();
			return dao.findByKeyWord(conn, userId, keyWord);
		}
	}

	/**
	 * TODOアイテムを1件更新します。
	 * 
	 * @param model TODOアイテムモデル
	 * @return 結果（true:成功、false:失敗）
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean update(TodoItemModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			TodoItemDAO dao = new TodoItemDAO();
			return dao.update(conn, model);
		}
	}
}
