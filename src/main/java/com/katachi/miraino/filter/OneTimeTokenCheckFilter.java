package com.katachi.miraino.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import settings.MessageSettings;
import settings.PageSettings;

/**
 * ワンタイムトークンのチェックを行うフィルタ
 */
@WebFilter(filterName = "OneTimeTokenCheckFilter") // フィルタを実行するURLは/WEB-INF/web.xmlで指定する
public class OneTimeTokenCheckFilter implements Filter {

	/**
	 * セッションに保存されているトークンとPOSTされてきたトークンを比較する（ワンタイムトークンのチェックを行う）。
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// メソッドがPOSTのときのみ処理を行う
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getMethod().equals("POST")) {
			HttpSession session = req.getSession(true);
			// POSTされてきた値とセッションの値を比較する
			// Objects.equals(a, b)は、abそれぞれがnullであってもNullPointerExceptionが発生しない
			if (!Objects.equals(request.getParameter("token"), session.getAttribute("token"))) {
				// ログインしている場合はログアウトさせる
				session.removeAttribute("user");
				// エラーメッセージを設定し、ログインページにフォワードする
				req.setAttribute("error", MessageSettings.MSG_INVALID_PROCESS);
				RequestDispatcher dispatcher = req.getRequestDispatcher(PageSettings.LOGIN_JSP);
				dispatcher.forward(request, response);
				return;
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
