package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add")
public class CartAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// cart.jsp에서 넘긴 값 가져오기
		String product = request.getParameter("product");

		// 세션에 담긴 장바구니 가져오기
		HttpSession session = request.getSession();
		ArrayList<String> cartList = (ArrayList<String>) session.getAttribute("cartList");

		if (cartList != null) { // 기존에 담아놓은 장바구니가 존재할 때
			cartList.add(product);
		} else { // 새롭게 장바구니 생성
			cartList = new ArrayList<String>();
			cartList.add(product);
			session.setAttribute("cartList", cartList);
		}

		// session에 값을 담기
		// 장바구니 계속 담아도 덮어쓰는 방식으로 되기 때문에 이 구조에서는 하나만 담을 수 있음
		//	HttpSession session = request.getSession();
		//	session.setAttribute("product", product);

		// 장바구니 보기 페이지 이동
		//	response.sendRedirect("/cart/basket.jsp");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html><head><meta charset=\"UTF-8\">");
		out.print("<title>사용자정보</title></head>");
		out.print("<body>");
		out.print("<p><a href='/cart/cart.jsp'>상품 선택</a></p>");
		out.print("<p><a href='/cart/basket.jsp'>장바구니 보기</a></p>");
		out.print("</body>");
		out.print("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
