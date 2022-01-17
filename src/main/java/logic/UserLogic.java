package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.katachi.miraino.database.DBConnection;

import dao.UserDAO;
import model.UserModel;

/**
 * ユーザーロジッククラス
 */
public class UserLogic {

	/** データベースコネクション */
	private Connection connection;

	public UserLogic() throws ClassNotFoundException, SQLException {
		this.connection = DBConnection.getInstance();
	}
	
	/**
	 * ユーザーを1件追加します。
	 * @param model ユーザーモデルクラス
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード(SQLSTATE)
	 */
	public int create(UserModel model) {
		UserDAO dao = new UserDAO();
		return dao.create(this.connection, model);
	}

	/**
	 * ユーザーを全件取得します。
	 * @return 検索結果（ユーザーモデルのリスト）
	 */
	public List<UserModel> find() {
		UserDAO dao = new UserDAO();
		List<UserModel> list = dao.findAll(this.connection);
		return list;
	}

	/**
	 * 指定ユーザーIDのユーザーを取得します。
	 * @param userId ユーザーID
	 * @return 検索結果（ユーザーモデル）
	 */
	public UserModel find(int userId) {
		UserDAO dao = new UserDAO();
		return dao.findOne(this.connection, userId);
	}

	/**
	 * 指定E-mailアドレスとパスワードのユーザーを取得します。
	 * @param email E-mailアドレス
	 * @param password パスワード
	 * @return 検索結果（ユーザーモデル）
	 */
	public UserModel find(String email, String password) {
		UserDAO dao = new UserDAO();
		return dao.findOne(this.connection, email, password);
	}

	/**
	 * 指定ユーザーIDのユーザーを1件更新します。
	 * @param model ユーザーモデル
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 */
	public int update(UserModel model) {
		UserDAO dao = new UserDAO();
		return dao.update(this.connection, model);
	}
}
