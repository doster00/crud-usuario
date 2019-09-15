package br.com.teste.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.teste.entidades.Usuario;

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/frm_login.xhtml" })
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = (HttpSession) request.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			response.sendRedirect(request.getContextPath() + "/pages/frm_index.xhtml");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
