package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.TodoItemLogic;
import model.TodoItemModel;
import model.UserModel;
import settings.PageSettings;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// TODOリストを取得する。
			TodoItemLogic logic;
			logic = new TodoItemLogic();

			// セッションスコープに保存されたユーザー情報を取得する。
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			List<TodoItemModel> items = null;

			if (request.getParameter("key") != null) {
				// 検索キーワードがある場合。
				// GETパラメータで日本語を受け取ると文字化けするので、server.xmlに下記を追記する。
				// useBodyEncodingForURI="true"
				items = logic.find(user.getId(), request.getParameter("key"));

				// 検索テキストボックス表示用
				request.setAttribute("key", request.getParameter("key"));
			} else {
				// 検索キーワードがない場合。
				items = logic.find(user.getId());
			}

			request.setAttribute("items", items);

			// 今日の日付を取得する（期限日が今日を過ぎているかどうかの判断に使う）。
			Date today = new Date();
			request.setAttribute("today", today);

			// メインページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);

			return;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);

			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
