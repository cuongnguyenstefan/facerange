package edu.mum.facerange.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.facerange.repo.ImageStoreDao;

@WebServlet(name = "image", value = "/image")
public class ImageServlet extends HttpServlet {

	@Inject
	private ImageStoreDao imageStoreDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			byte[] imageBytes = imageStoreDao.getImage(Integer.parseInt(id));
			resp.getOutputStream().write(imageBytes);
			resp.getOutputStream().close();

		} catch (Exception e) {
			resp.getWriter().write(e.getMessage());
			resp.getWriter().close();
		}
	}
}
