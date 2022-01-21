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
@WebFilter(filterName = "OneTimeTokenFilter") // フィルタを実行するURLは/WEB-INF/web.xmlで指定する
public class OneTimeTokenFilter implements Filter {

	/**
	 * ワンタイムトークンを生成してセッションに保存するフィルタ。
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ワンタイムトークンを生成してセッションに保存する。
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getMethod().equals("GET")) {
			String token = SecurityUtil.generateToken();
			HttpSession session = req.getSession(true);
			// JSPでも${token}で取得できる
			session.setAttribute("token", token);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
