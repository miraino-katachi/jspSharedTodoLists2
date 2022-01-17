package test;

import java.sql.SQLException;
import java.util.List;

import com.katachi.miraino.database.DBConnection;

import dao.TodoItemDAO;
import model.TodoItemModel;

public class TodoItem {

	public static void main(String[] args) {
		TodoItemDAO dao = new TodoItemDAO();
		List<TodoItemModel> model;
		try {
			model = dao.findByUserId(DBConnection.getInstance(), 1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		}
		for (TodoItemModel value : model) {
			System.out.println(value.getId());
		}
	}

}
