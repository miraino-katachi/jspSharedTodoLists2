package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
import validation.TodoValidation;

/**
 * Servlet implementation class TodoCreate
 */
@WebServlet("/TodoRegister")
public class TodoRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodoRegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォワードする。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoRegister.jsp");
		dispatcher.forward(request, response);

		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータ
		String item = request.getParameter("todoItem");
		String registrationDate = request.getParameter("registrationDate");
		String expirationDate = request.getParameter("expirationDate");
		String finishedDate = null;

		try {
			if (request.getParameter("finishedDate") != null) {
				// 完了日がnullでなかったら、「今日」を完了日にする。
				// チェックボックスにチェックを入れた場合は「On」という文字列がPOSTされてきます。
				java.util.Date date = new java.util.Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				finishedDate = format.format(date);
			}

			// バリデーションチェックを行う。
			TodoValidation validate = new TodoValidation(request);
			Map<String, String> errors = validate.validate();

			// バリデーションエラーがあった時。
			if (validate.hasErrors()) {
				request.setAttribute("errors", errors);

				// JSPのinputタグのvalue値の表示に使うためにリクエストパラメータをMapに保存する。
				Map<String, String> todoItem = new HashMap<String, String>();
				todoItem.put("todoItem", item);
				todoItem.put("registrationDate", registrationDate);
				todoItem.put("expirationDate", expirationDate);
				todoItem.put("finishedDate", finishedDate);
				request.setAttribute("todoItem", todoItem);

				// 登録ページへフォワードして終了する。
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoRegister.jsp");
				dispatcher.forward(request, response);

				return;
			}

			// セッションに保存したユーザーモデルを取得する。
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			// リクエストパラメータをTODOモデルに設定する。
			TodoItemModel todoItem = new TodoItemModel();
			todoItem.setUserId(user.getId());
			todoItem.setRegistrationDate(Date.valueOf(registrationDate));
			todoItem.setExpirationDate(Date.valueOf(expirationDate));

			if (finishedDate == null) {
				todoItem.setFinishedDate(null);
			} else {
				todoItem.setFinishedDate(Date.valueOf(finishedDate));
			}

			todoItem.setTodoItem(item);
			todoItem.setIsDeleted(0);

			// TODOを登録する。
			TodoItemLogic logic;
			logic = new TodoItemLogic();

			if (!logic.create(todoItem)) {
				// エラーがあったときは、todoRegister.jspへフォワードする。
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoRegister.jsp");
				request.setAttribute("todoItem", todoItem);
				dispatcher.forward(request, response);

				return;
			}

			// Mainへリダイレクトする。
			response.sendRedirect(request.getContextPath() + "/Main");

			return;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);

			return;
		}
	}
}
