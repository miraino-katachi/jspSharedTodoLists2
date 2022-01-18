package com.katachi.miraino.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import settings.DatabaseSettings;

/**
 * データベース接続クラス
 * AutoCloseableについては、APIのドキュメントの「インタフェースAutoCloseable」の項を参照。
 * @see https://docs.oracle.com/javase/jp/8/docs/api/java/lang/AutoCloseable.html
 */
public class DBConnection implements AutoCloseable {

	/** データベースコネクション **/
	private Connection connection;

	/**
	 * コンストラクタ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DBConnection() throws ClassNotFoundException, SQLException {
		// JDBCドライバを読み込み
		Class.forName(DatabaseSettings.DRIVER_NAME);
		// データベースコネクションを保存
		this.connection = DriverManager.getConnection(DatabaseSettings.JDBC_URL, DatabaseSettings.DB_USER,
				DatabaseSettings.DB_PASS);
	}

	/**
	 * データベースコネクションのインタスンスを返却します。
	 * 
	 * @return データベースコネクションのインスタンス
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getInstance() throws SQLException, ClassNotFoundException {
		// データベースコネクションを返却
		return this.connection;
	}

	/**
	 * データベースコネクションを閉じます。
	 * @throws Exception
	 */
	@Override
	public void close() {
		try {
			this.connection.close();
		} catch (Exception e) {
			// 何もしない
		}
	}
}
