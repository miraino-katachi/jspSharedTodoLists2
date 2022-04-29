package com.katachi.miraino.util.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import settings.SecuritySettings;

/**
 * セキュリティ対策クラス
 */
public class SecurityUtil {

	/**
	 * ワンタイムトークン用の文字列を生成します。
	 * @return ワンタイムトークン用の文字列
	 */
	public static String generateToken() {
		// 指定の要素数を持つbyte型の配列を生成する。
		byte token[] = new byte[SecuritySettings.TOKEN_LENGTH];

		// 文字列を操作するためのStringBufferクラスのインスタンスを生成する。
		StringBuffer buf = new StringBuffer();

		// 乱数を生成するためのSecureRandomクラス。
		SecureRandom random = null;

		try {
			// 指定のアルゴリズムを使って、SecureRandomクラスのインスタンスを取得する、
			random = SecureRandom.getInstance(SecuritySettings.ENCRYPTION_ALGORITHM);

			// 乱数を生成する。
			random.nextBytes(token);

			for (int i = 0; i < token.length; i++) {
				// formatメソッドを使って16進数に変換する。（"%02x"は16進数の意味）
				// 16進数に変換した文字列を後ろへ追加していく。
				buf.append(String.format("%02x", token[i]));
			}

			// StringBufferクラスオブジェクトを文字列に変換して返却する。
			return buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

			return null;
		}
	}
}
