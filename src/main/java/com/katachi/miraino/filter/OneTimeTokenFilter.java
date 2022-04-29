package com.katachi.miraino.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.katachi.miraino.util.security.SecurityUtil;

/**
 * ワンタイムトークンを生成して、セッションに保存するフィルタ
 */
@WebFilter(filterName = "OneTimeTokenFilter") // フィルタを実行するURLは/WEB-INF/web.xmlで指定する。
public class OneTimeTokenFilter implements Filter {

	/**
	 * ワンタイムトークンを生成してセッションに保存するフィルタ。
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// getMethod()メソッド、getSession()メソッドが使えるように、
		// ServletResponseクラスオブジェクトをHttpServletRequestクラスオブジェクトにキャストする。
		HttpServletRequest req = (HttpServletRequest) request;

		// ワンタイムトークンを生成してセッションに保存する。
		// メソッドがGETのときのみ処理を行う。
		if (req.getMethod().equals("GET")) {
			// トークンを生成する。
			String token = SecurityUtil.generateToken();

			// HttpSessionインスタンスを取得する。
			// セッションが開始されていない場合は、新しいインスタンスを取得する。
			HttpSession session = req.getSession(true);

			// トークンをセッションスコープに保存する。
			session.setAttribute("token", token);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
