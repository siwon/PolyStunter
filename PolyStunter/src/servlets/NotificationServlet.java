package servlets;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NotificationDAO;
import dao.UserDAO;

import beans.Notification;
import beans.User;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle rb = ResourceBundle.getBundle("properties.text");
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<script type='text/javascript'>");
		for (Notification n: NotificationDAO.getInstance().getNotifications()) {
			buffer.append("var marker" + n.getId() + " = new Mappy.api.map.Marker(new Mappy.api.geo.Coordinates(" + n.getLongitude() +
					"," + n.getLatitude() + "), " + n.getType() + ");");
			buffer.append("marker" + n.getId() + ".addToolTip('" + rb.getString(n.getType().toLowerCase()) + 
					"'); marker" + n.getId() + ".addListener('click', function () { marker" + n.getId() + 
					".openPopUp(\"<div class='custom-popup'><a href='javascript:closeCustom();' class='close-custom-popup'>x</a><div class='custom-popup-content'><p>Envoyé par <b>"+
					UserDAO.getLoginFromId(n.getIdSender())+ "</b> à " + n.getDate().toString() + "<br/><i>" + n.getInformation() + "</i></p></div></div>\")});");
			buffer.append("markerLayer.addMarker(marker" + n.getId() +");");
		}
		buffer.append("</script>");
		request.setAttribute("markers", buffer.toString());
		getServletContext().getRequestDispatcher("/WEB-INF/notification.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String information = request.getParameter("information");
		double latitude = Double.valueOf(request.getParameter("latitude"));
		double longitude = Double.valueOf(request.getParameter("longitude"));
		User user = (User) request.getSession().getAttribute("user");
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		NotificationDAO.getInstance().add(user.getId(), type, information, latitude, longitude, time);
		getServletContext().getRequestDispatcher("/WEB-INF/notification.jsp").forward(request, response);
	}

}
