package com.katachi.miraino.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import settings.DatabaseSettings;

/**
 * データベース接続クラス
 */
public class DBConnection {

//	private Connection connection;

	/**
	 * データベースコネクションのインタスンスを返却します。
	 * @return データベースコネクションのインスタンス
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getInstance() throws SQLException, ClassNotFoundException {
		// JDBCドライバを読み込み
		Class.forName(DatabaseSettings.DRIVER_NAME);
		// データベースコネクションを返却
		return DriverManager.getConnection(DatabaseSettings.JDBC_URL, DatabaseSettings.DB_USER, DatabaseSettings.DB_PASS);
	}
	
	/**
	 * データベース接続クラス
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
//	public  DBConnection() throws ClassNotFoundException, SQLException {
//		// JDBCドライバを読み込み
//		Class.forName(DatabaseSettings.DRIVER_NAME);
//		// データベースへ接続
//		this.connection= DriverManager.getConnection(DatabaseSettings.JDBC_URL, DatabaseSettings.DB_USER, DatabaseSettings.DB_PASS);
//	}

	/**
	 * データベース接続（セッション）を取得します。
	 * @return データベース接続（セッション）
	 * @throws SQLException
	 */
//	public Connection getConnection() throws SQLException {
//		// データベースへ接続
//		return this.connection;
//	}
}
