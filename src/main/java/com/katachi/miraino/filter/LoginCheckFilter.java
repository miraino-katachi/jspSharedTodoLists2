package com.katachi.miraino.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import settings.PageSettings;

/**
 * ログインチェックを行うフィルタ。
 */
@WebFilter(filterName = "LoginCheckFilter") // フィルタを実行するURLは/WEB-INF/web.xmlで指定する
public class LoginCheckFilter implements Filter {

	/**
	 * ログインチェックを行う。
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// セッションにユーザー情報が登録されているかを確認して、登録されていなければログイン画面にリダイレクトする。
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		if (session.getAttribute("user") == null) {
			res.sendRedirect(req.getContextPath() + PageSettings.LOGIN_SERVLET);
			// ここでreturnしないと「レスポンスをコミットした後でフォワードできません」と例外が発生する。
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
